package de.ganicoga.client.presenter;

import gwtquery.plugins.droppable.client.DroppableOptions.AcceptFunction;
import gwtquery.plugins.droppable.client.events.DragAndDropContext;
import gwtquery.plugins.droppable.client.events.DropEvent;
import gwtquery.plugins.droppable.client.events.DropEvent.DropEventHandler;

import java.util.ArrayList;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasWidgets;

import de.ganicoga.client.Main;
import de.ganicoga.client.Resources;
import de.ganicoga.client.events.ConfigChangeEvent;
import de.ganicoga.client.events.ConfigLoadEvent;
import de.ganicoga.client.events.ResetCellEvent;
import de.ganicoga.client.model.AbstractBaseModel;
import de.ganicoga.client.model.BaseModel;
import de.ganicoga.client.model.DefenseStructure;
import de.ganicoga.client.model.HasLevel;
import de.ganicoga.client.model.IsObstacle;
import de.ganicoga.client.model.IsResource;
import de.ganicoga.client.model.Structure;
import de.ganicoga.client.model.UniqueStructure;
import de.ganicoga.client.model.defense.OilSlick;
import de.ganicoga.client.model.defense.Wall;
import de.ganicoga.client.model.resource.Crystal;
import de.ganicoga.client.model.resource.Harvester;
import de.ganicoga.client.model.resource.HarvesterCrystal;
import de.ganicoga.client.model.resource.HarvesterTiberium;
import de.ganicoga.client.model.resource.Tiberium;
import de.ganicoga.client.model.unique.AirField;
import de.ganicoga.client.model.unique.Barracks;
import de.ganicoga.client.model.unique.CommandCenter;
import de.ganicoga.client.model.unique.ConstructionYard;
import de.ganicoga.client.model.unique.DefenseFacility;
import de.ganicoga.client.model.unique.DefenseHQ;
import de.ganicoga.client.model.unique.Factory;
import de.ganicoga.client.model.unique.FalconSupport;
import de.ganicoga.client.model.unique.IonSupport;
import de.ganicoga.client.model.unique.SkySupport;
import de.ganicoga.client.view.BaseView;
import de.ganicoga.client.widget.GridCell;
import de.ganicoga.client.widget.Tile;

public class BasePresenter implements BaseView.Presenter {

	private BaseView view;
	private AbstractBaseModel model;
	private FlexTable flex;
	private DropEventHandler dropHandler;

	public BasePresenter(BaseView view) {
		this.view = view;
		view.setPresenter(this);

		initHandlers();

		String token = History.getToken();

		// create base
		if (token.equals("")) {
			model = new BaseModel();
		} else {
			model = new BaseModel(token);
		}

		// get table
		flex = view.getTable();
		flex.setCellSpacing(1);
		flex.setCellPadding(0);
		flex.setBorderWidth(0);

		populateView();

	}

	/**
	 * Mirrors the base model to the view
	 */
	private void populateView() {
		// fill widgets in table

		resetStructureCounts();

		// base
		for (int i = 0; i < BaseModel.gridRows; i++) {
			for (int j = 0; j < BaseModel.gridCols; j++) {

				Tile t = new Tile(model.getStructure(i, j), i, j);

				t.addMouseWheelHandler(onWheelHandler);

				if (t.getStructure() != null) {
					if (t.getStructure() instanceof HarvesterTiberium) {
						Tiberium.INSTANCE.decrementCount();
					} else if (t.getStructure() instanceof HarvesterCrystal) {
						Crystal.INSTANCE.decrementCount();
					} else {
						t.getStructure().decrementCount();
					}
				}

				GridCell cell = new GridCell(i, j);
				cell.setTile(t);
				cell.setAccept(acceptFunction);
				cell.addDropHandler(dropHandler);

				flex.setWidget(i, j, cell);
			}
		}

		Main.getClientFactory().getEventBus()
				.fireEvent(new ConfigChangeEvent(model));

	}

	private void set(Tile tile, GridCell cell) {

		// update view
		tile.setRow(cell.getRow());
		tile.setColumn(cell.getColumn());

		if (tile.getStructure() instanceof DefenseStructure) {
			updateDefenseImage(tile, 0);
		}
		if(!tile.hasWheelHandler()){
			tile.addMouseWheelHandler(onWheelHandler);
		}
		// update model
		model.setStructure(cell.getRow(), cell.getColumn(), tile.getStructure());

		cell.setTile(tile);
	}

	private void set(int row, int col, Tile tile) {
		set(tile, (GridCell) flex.getWidget(row, col));
	}

	private Tile get(int row, int col) {
		return ((GridCell) flex.getWidget(row, col)).getTile();
	}

	private void swap(Tile tile1, Tile tile2) {

		int row = tile2.getRow();
		int col = tile2.getColumn();

		Tile tempTile = tile1;

		set(tile1.getRow(), tile1.getColumn(), tile2);

		set(row, col, tempTile);
	}

	/**
	 * Set empty Tile at position. A Harvester is being replaced by the
	 * corresponding resource field.
	 * 
	 * @param row
	 * @param col
	 */
	private void reset(int row, int col) {
		// valid cell
		if (row >= 0 && col >= 0) {

			Structure structure = null;

			if (model.getStructure(row, col) instanceof HarvesterCrystal) {
				structure = new Crystal();
			} else if (model.getStructure(row, col) instanceof HarvesterTiberium) {
				structure = new Tiberium();
			}

			else if (model.getStructure(row, col) instanceof DefenseStructure) {
				resetDefenseImage(get(row, col));
			}

			set(row, col, new Tile(structure));

		}
	}

	private void initHandlers() {
		dropHandler = new DropEventHandler() {
			public void onDrop(DropEvent event) {

				// From here on only valid placements are considered, invalid
				// ones must be filtered out by the accept function of the
				// GridCell

				GridCell dropTarget = (GridCell) event.getDroppableWidget();
				Tile dragSource = (Tile) event.getDraggableWidget();
				Structure structure = dragSource.getStructure();
				Structure dropTargetStructure = dropTarget.getTile()
						.getStructure();

				// new instance if first time placement
				if (dragSource.getRow() < 0 || dragSource.getColumn() < 0) {
					structure.decrementCount();
					dragSource = new Tile(structure, dragSource.getRow(),
							dragSource.getColumn());
				}

				// Dropping a Harvester
				if (structure instanceof Harvester) {
					if (dropTargetStructure instanceof Tiberium) {
						dragSource.setStructure(new HarvesterTiberium());
					} else {
						dragSource.setStructure(new HarvesterCrystal());
					}

					reset(dragSource.getRow(), dragSource.getColumn());
					set(dragSource, dropTarget);
				}

				// Swapping 2 Tiles
				else if (dropTargetStructure != null) {
					swap(dropTarget.getTile(), dragSource);
				}
				// Dropping any other Structure
				else {
					reset(dragSource.getRow(), dragSource.getColumn());
					set(dragSource, dropTarget);
				}

				// fire config change after the drop is done
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {

					@Override
					public void execute() {
						Main.getClientFactory().getEventBus()
								.fireEvent(new ConfigChangeEvent(model));
					}
				});

			}
		};

		// events on load button clicked
		Main.getClientFactory()
				.getEventBus()
				.addHandler(ConfigLoadEvent.TYPE,
						new ConfigLoadEvent.Handler() {
							public void onConfigLoad(ConfigLoadEvent event) {
								if (event.getConfig().length() < 1) {
									model = new BaseModel();
								} else {
									model = new BaseModel(event.getConfig());
								}

								populateView();
							}
						});
		// events on reset cell (SelectionPresenter)
		Main.getClientFactory().getEventBus()
				.addHandler(ResetCellEvent.TYPE, new ResetCellEvent.Handler() {

					@Override
					public void onResetCell(ResetCellEvent event) {
						reset(event.getRow(), event.getColumn());

						Scheduler.get().scheduleDeferred(
								new ScheduledCommand() {

									@Override
									public void execute() {
										Main.getClientFactory()
												.getEventBus()
												.fireEvent(
														new ConfigChangeEvent(
																model));
									}
								});
					}
				});
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
	}

	private MouseWheelHandler onWheelHandler = new MouseWheelHandler() {
		@Override
		public void onMouseWheel(MouseWheelEvent event) {
			Tile tile = (Tile) event.getSource();

			if (tile.getStructure() != null
					&& tile.getStructure() instanceof HasLevel) {
				HasLevel structure = (HasLevel) tile.getStructure();
				if (event.getDeltaY() < 0) {
					structure.setLevel(structure.getLevel() + 1);
				} else if (event.getDeltaY() > 0) {
					structure.setLevel(structure.getLevel() - 1);
				}

				tile.setLevel(structure.getLevel());
				model.update();
				Main.getClientFactory().getEventBus()
						.fireEvent(new ConfigChangeEvent(model));
			}
		}
	};

	private AcceptFunction acceptFunction = new AcceptFunction() {
		public boolean acceptDrop(DragAndDropContext context) {

			Tile dragTile = (Tile) context.getDraggableWidget();
			Structure structure = dragTile.getStructure();

			Tile dropTile = ((GridCell) context.getDroppableWidget()).getTile();
			Structure dropStructure = dropTile.getStructure();

			// If unique to be placed check each cell if it has uniques around
			// if it does, it is invalid
			// TODO fix for swap of structure and unique
			if (structure instanceof UniqueStructure) {
				if (isInvalidPlace(dropTile.getRow(), dropTile.getColumn(),
						(UniqueStructure) structure)) {
					return false;
				}
			}

			// only harvesters cannot be placed on empty ground
			if (dropStructure == null) {
				if (structure instanceof Harvester) {
					return false;
				}
				return true;
			}

			// Structures from here on are placed on other structures

			// Only Harvesters can be placed on resources
			if (dropStructure instanceof IsResource) {
				if (structure instanceof Harvester) {
					return true;
				}
				return false;
			}

			// no other structures can be placed on other structures in insert
			// mode
			if (Main.getClientFactory().isInsertMode()) {
				return false;
			}

			//
			// from here on we consider only swapping
			//

			// Nothing can be swapped with Harvesters
			if (dropStructure instanceof Harvester) {
				return false;
			}

			// only Obstacles can be swapped with obstacles
			if (dropStructure instanceof IsObstacle) {
				if (!(structure instanceof IsObstacle)) {
					return false;
				}
				return true;
			}

			// Harvester, Resource and Obstacle cannot swap with any other
			// structures
			if (structure instanceof Harvester
					|| structure instanceof IsResource
					|| structure instanceof IsObstacle) {
				return false;
			}
			return true;

		}
	};

	private boolean isInvalidPlace(int row, int col, UniqueStructure placeTile) {
		Structure target = model.getStructure(row, col);

		// in move mode unique strucutres are allowed to be exchanged
		if (!Main.getClientFactory().isInsertMode() && target != null
				&& target instanceof UniqueStructure) {
			return false;
		}

		ArrayList<Structure> list = new ArrayList<Structure>();

		if (row > 0) {
			if (col > 0) {
				list.add(model.getStructure(row - 1, col - 1));
			}
			if (col < BaseModel.gridCols - 1) {
				list.add(model.getStructure(row - 1, col + 1));
			}
			list.add(model.getStructure(row - 1, col));
		}
		if (row < BaseModel.gridRows - 1) {
			if (col > 0) {
				list.add(model.getStructure(row + 1, col - 1));
			}
			if (col < BaseModel.gridCols - 1) {
				list.add(model.getStructure(row + 1, col + 1));
			}
			list.add(model.getStructure(row + 1, col));
		}
		if (col > 0) {
			list.add(model.getStructure(row, col - 1));
		}
		if (col < BaseModel.gridCols - 1) {
			list.add(model.getStructure(row, col + 1));
		}

		for (Structure structure : list) {
			// if there is a unique structure around and the unique is not the
			// tile itself, set position invalid
			if (structure != null && (structure instanceof UniqueStructure)
					&& !placeTile.equals((UniqueStructure) structure)) {
				return true;
			}
		}

		return false;

	}

	private void resetStructureCounts() {
		Tiberium.INSTANCE.reset();
		Crystal.INSTANCE.reset();
		AirField.INSTANCE.reset();
		Barracks.INSTANCE.reset();
		CommandCenter.INSTANCE.reset();
		ConstructionYard.INSTANCE.reset();
		DefenseFacility.INSTANCE.reset();
		DefenseHQ.INSTANCE.reset();
		Factory.INSTANCE.reset();
		FalconSupport.INSTANCE.reset();
		IonSupport.INSTANCE.reset();
		SkySupport.INSTANCE.reset();
	}

	private void updateDefenseImage(Tile tile, int direction) {

		boolean left = leftNeighborIsSame(tile);
		boolean right = rightNeighborIsSame(tile);

		if (left && direction <= 0) {
			updateDefenseImage(get(tile.getRow(), tile.getColumn() - 1), -1);
		}
		if (right && direction >= 0) {
			updateDefenseImage(get(tile.getRow(), tile.getColumn() - 1), 1);
		}

		// current is wall
		if (tile.getStructure() instanceof Wall) {

			if (direction == 0) {
				if (left && right) {
					tile.setImage(Resources.INSTANCE.wallInner());
				} else if (left) {
					tile.setImage(Resources.INSTANCE.wallRight());
				} else if (right) {
					tile.setImage(Resources.INSTANCE.wallLeft());
				}
			} else if (direction == -1) {
				if (left) {
					tile.setImage(Resources.INSTANCE.wallInner());
				} else {
					tile.setImage(Resources.INSTANCE.wallLeft());
				}
			} else {
				if (right) {
					tile.setImage(Resources.INSTANCE.wallInner());
				} else {
					tile.setImage(Resources.INSTANCE.wallRight());
				}
			}
		} else if (tile.getStructure() instanceof OilSlick) {
			if (direction == 0) {
				if (left && right) {
					tile.setImage(Resources.INSTANCE.oilInner());
				} else if (left) {
					tile.setImage(Resources.INSTANCE.oilRight());
				} else if (right) {
					tile.setImage(Resources.INSTANCE.oilLeft());
				}
			} else if (direction == -1) {
				if (left) {
					tile.setImage(Resources.INSTANCE.oilInner());
				} else {
					tile.setImage(Resources.INSTANCE.oilLeft());
				}
			} else {
				if (right) {
					tile.setImage(Resources.INSTANCE.oilInner());
				} else {
					tile.setImage(Resources.INSTANCE.oilRight());
				}
			}
		}

	}

	private void resetDefenseImage(Tile tile) {

		boolean left = leftNeighborIsSame(tile);
		boolean right = rightNeighborIsSame(tile);
		boolean leftleft = false;
		boolean rightright = false;

		if (left) {
			leftleft = leftNeighborIsSame(get(tile.getRow(),
					tile.getColumn() - 1));
		}
		if (right) {
			rightright = rightNeighborIsSame(get(tile.getRow(),
					tile.getColumn() + 1));
		}

		// current is wall
		if (tile.getStructure() instanceof Wall) {

			tile.setImage(Resources.INSTANCE.wall());

			if (left) {
				if (leftleft) {
					get(tile.getRow(), tile.getColumn() - 1).setImage(
							Resources.INSTANCE.wallRight());
				} else {
					get(tile.getRow(), tile.getColumn() - 1).setImage(
							Resources.INSTANCE.wall());
				}
			}
			if (right) {
				if (rightright) {
					get(tile.getRow(), tile.getColumn() + 1).setImage(
							Resources.INSTANCE.wallLeft());
				} else {
					get(tile.getRow(), tile.getColumn() + 1).setImage(
							Resources.INSTANCE.wall());
				}
			}
		}

		// current is wall
		if (tile.getStructure() instanceof OilSlick) {

			tile.setImage(Resources.INSTANCE.oil());

			if (left) {
				if (leftleft) {
					get(tile.getRow(), tile.getColumn() - 1).setImage(
							Resources.INSTANCE.oilRight());
				} else {
					get(tile.getRow(), tile.getColumn() - 1).setImage(
							Resources.INSTANCE.oil());
				}
			}
			if (right) {
				if (rightright) {
					get(tile.getRow(), tile.getColumn() + 1).setImage(
							Resources.INSTANCE.oilLeft());
				} else {
					get(tile.getRow(), tile.getColumn() + 1).setImage(
							Resources.INSTANCE.oil());
				}
			}
		}
	}

	private boolean rightNeighborIsSame(Tile tile) {
		Structure structure = model.getStructure(tile.getRow(),
				tile.getColumn() + 1);

		if (structure != null
				&& structure.getId() == tile.getStructure().getId()) {
			return true;
		}
		return false;
	}

	private boolean leftNeighborIsSame(Tile tile) {
		Structure structure = model.getStructure(tile.getRow(),
				tile.getColumn() - 1);

		if (structure != null
				&& structure.getId() == tile.getStructure().getId()) {
			return true;
		}
		return false;
	}
}
