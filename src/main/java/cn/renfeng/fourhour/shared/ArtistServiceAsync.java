package cn.renfeng.fourhour.shared;

import java.util.List;

import cn.renfeng.fourhour.shared.model.Artist;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>ArtistService</code>.
 */
public interface ArtistServiceAsync {

	void save(Artist artist, AsyncCallback<String> callback);

	void list(AsyncCallback<List<Artist>> callback);
}
