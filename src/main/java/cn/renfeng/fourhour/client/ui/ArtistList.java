package cn.renfeng.fourhour.client.ui;

import java.util.ArrayList;
import java.util.List;

import cn.renfeng.fourhour.shared.ArtistService;
import cn.renfeng.fourhour.shared.ArtistServiceAsync;
import cn.renfeng.fourhour.shared.model.Artist;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;

public class ArtistList extends Composite {

	private static ArtistListUiBinder uiBinder = GWT
			.create(ArtistListUiBinder.class);

	interface ArtistListUiBinder extends UiBinder<Widget, ArtistList> {
	}

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final ArtistServiceAsync artistService = GWT
			.create(ArtistService.class);

	private final ArrayList<Artist> artistList;

	private final DialogBox newArtistDialogBox;

	@UiField
	CellTable<Artist> artistTable;

	@UiHandler("newArtistButton")
	void onNewArtistButtonClick(ClickEvent event) {
		newArtistDialogBox.setWidget(new NewArtist(this));
		newArtistDialogBox.center();
	}

	@UiFactory
	CellTable<Artist> buildTable() {

		CellTable<Artist> table = new CellTable<Artist>();

		Column<Artist, String> firstnameColumn;
		Column<Artist, String> lastnameColumn;
		Column<Artist, String> genderColumn;
		Column<Artist, String> countryColumn;

		firstnameColumn = new TextColumn<Artist>() {

			@Override
			public String getValue(Artist object) {
				return object.getFirstname();
			}
		};
		lastnameColumn = new TextColumn<Artist>() {

			@Override
			public String getValue(Artist object) {
				return object.getLastname();
			}
		};
		genderColumn = new TextColumn<Artist>() {

			@Override
			public String getValue(Artist object) {
				return object.getGender();
			}
		};
		countryColumn = new TextColumn<Artist>() {

			@Override
			public String getValue(Artist object) {
				return object.getCountry();
			}
		};

		table.addColumn(firstnameColumn, "First name");
		table.addColumn(lastnameColumn, "Last name");
		table.addColumn(genderColumn, "Gender");
		table.addColumn(countryColumn, "Country");

		return table;
	}

	public ArtistList() {

		initWidget(uiBinder.createAndBindUi(this));

		artistList = new ArrayList<Artist>();

		newArtistDialogBox = new DialogBox(true, true);
		newArtistDialogBox.setText("New Artist");

		refresh();

		return;
	}

	private void refresh() {

		artistService.list(new AsyncCallback<List<Artist>>() {

			@Override
			public void onSuccess(List<Artist> artists) {
				artistList.clear();
				artistList.addAll(artists);
				artistTable.setRowData(artistList);
			}

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("failed");
			}
		});

		return;
	}

	public void add(String firstname, String lastname, String gender,
			String country) {

		Artist artist = new Artist();
		artist.setFirstname(firstname);
		artist.setLastname(lastname);
		artist.setGender(gender);
		artist.setCountry(country);

		artistList.add(artist);
		artistTable.setRowData(artistList);

		newArtistDialogBox.hide();

		/*
		 * store
		 */
		artistService.save(artist, new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				Window.alert("saved");
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("failed");
			}
		});

		return;
	}

}
