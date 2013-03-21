package cn.renfeng.fourhour.shared;

import java.util.List;

import cn.renfeng.fourhour.shared.model.Artist;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("artist")
public interface ArtistService extends RemoteService {

	String save(Artist artist) throws IllegalArgumentException;

	List<Artist> list() throws IllegalArgumentException;
}
