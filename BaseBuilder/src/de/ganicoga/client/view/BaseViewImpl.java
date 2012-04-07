package de.ganicoga.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class BaseViewImpl extends Composite implements BaseView {

	private static BaseViewImplUiBinder uiBinder = GWT
			.create(BaseViewImplUiBinder.class);

	interface BaseViewImplUiBinder extends UiBinder<Widget, BaseViewImpl> {
	}

	@UiField
	FlexTable content;
	
	private Presenter presenter;

	public BaseViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		
	}

	@Override
	public FlexTable getTable() {
		return content;
	}
}
