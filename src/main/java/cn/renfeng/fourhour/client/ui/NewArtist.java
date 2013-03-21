package cn.renfeng.fourhour.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class NewArtist extends Composite {

	private static NewArtistUiBinder uiBinder = GWT
			.create(NewArtistUiBinder.class);

	interface NewArtistUiBinder extends UiBinder<Widget, NewArtist> {
	}

	private final ArtistList artistList;

	@UiField
	TextBox firstnameTextBox;

	@UiField
	TextBox lastnameTextBox;

	@UiField
	RadioButton maleRadioButton;

	@UiField
	RadioButton femaleRadioButton;

	@UiField
	ListBox countryListBox;

	@UiHandler("addButton")
	void onAddButtonClick(ClickEvent event) {

		String firstname = firstnameTextBox.getText();
		String lastname = lastnameTextBox.getText();

		String gender;
		if (maleRadioButton.getValue()) {
			gender = "male";
		} else if (femaleRadioButton.getValue()) {
			gender = "female";
		} else {
			gender = "(disclosed)";
		}

		/*
		 * XXX watch out for index = -1?
		 */
		String country = countryListBox.getValue(countryListBox
				.getSelectedIndex());

		artistList.add(firstname, lastname, gender, country);
		
		return;
	}

	public NewArtist(ArtistList artistList) {
		initWidget(uiBinder.createAndBindUi(this));
		this.artistList = artistList;
	}

}
