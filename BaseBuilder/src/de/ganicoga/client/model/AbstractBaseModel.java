package de.ganicoga.client.model;

import java.util.ArrayList;
import java.util.List;

import de.ganicoga.client.Main;
import de.ganicoga.client.Util;

public abstract class AbstractBaseModel {

	public final static int gridRows = 8;
	public final static int gridCols = 9;
	public final static int gridSize = gridRows * gridCols;

	protected Structure[][] grid;

	public AbstractBaseModel() {
		grid = new Structure[gridRows][gridCols];
	}

	public AbstractBaseModel(String token) {
		this.grid = toGrid(token);
		collectAll();
	}

	public AbstractBaseModel(Structure[][] grid) {
		this.grid = grid;
		collectAll();
	}

	protected abstract void collectAll();

	protected abstract void collect(Structure s, List<Structure> neighbors);

	private Structure[][] toGrid(String token) {

		Structure[][] sa = new Structure[gridRows][gridCols];
		
		if(token.length() != 72){
			token = Util.decode(token);
		}

		String[] rows = new String[gridRows];
		int start = 0;
		for (int i = 0; i < gridRows; i++) {

			rows[i] = token.substring(start, start + gridCols);
			start += gridCols;

			for (int j = 0; j < gridCols; j++) {
				sa[i][j] = Main.getClientFactory().getStructure(
						Util.convert(rows[i].charAt(j)));
			}
		}
		return sa;
	}

	private String toString(Structure[][] grid) {
		StringBuilder sb = new StringBuilder(gridSize);
		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridCols; j++) {
				if (grid[i][j] == null) {
					sb.append('0');
				} else {
					sb.append(Util.convert(grid[i][j].getId()));
				}

			}
		}
		return Util.encode(sb.toString());
	}

	public void setStructure(int row, int col, Structure t) {

		grid[row][col] = t;
		collectAll();
	}

	public Structure getStructure(int row, int col) {
		try {
			return grid[row][col];
		} catch (IndexOutOfBoundsException e) {
			return null;
		}

	}

	public void setStructures(Structure[][] grid) {
		this.grid = grid;
		collectAll();
	}

	public Structure[][] getGrid() {
		return grid;
	}

	public String getConfig() {
		return toString(grid);
	}

	protected List<Structure> getNeighbors(int row, int col) {
		ArrayList<Structure> list = new ArrayList<Structure>();

		if (row > 0) {
			if (col > 0) {
				list.add(getStructure(row - 1, col - 1));
			}
			if (col < BaseModel.gridCols - 1) {
				list.add(getStructure(row - 1, col + 1));
			}
			list.add(getStructure(row - 1, col));
		}
		if (row < BaseModel.gridRows - 1) {
			if (col > 0) {
				list.add(getStructure(row + 1, col - 1));
			}
			if (col < BaseModel.gridCols - 1) {
				list.add(getStructure(row + 1, col + 1));
			}
			list.add(getStructure(row + 1, col));
		}
		if (col > 0) {
			list.add(getStructure(row, col - 1));
		}
		if (col < BaseModel.gridCols - 1) {
			list.add(getStructure(row, col + 1));

		}
		return list;
	}

}
