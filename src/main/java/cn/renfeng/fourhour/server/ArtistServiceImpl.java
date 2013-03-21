package cn.renfeng.fourhour.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Query;
import javax.jdo.Transaction;

import org.datanucleus.api.jdo.JDOPersistenceManager;

import cn.renfeng.fourhour.shared.ArtistService;
import cn.renfeng.fourhour.shared.model.Artist;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ArtistServiceImpl extends RemoteServiceServlet implements
		ArtistService, PersistenceService {

	@Override
	public String save(Artist artist) throws IllegalArgumentException {

		JDOPersistenceManager pm = (JDOPersistenceManager) PMF
				.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(Integer.MAX_VALUE);

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			pm.makePersistent(artist);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return "ok";
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Artist> list() throws IllegalArgumentException {

		List<Artist> list = new ArrayList<Artist>();

		JDOPersistenceManager pm = (JDOPersistenceManager) PMF
				.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(Integer.MAX_VALUE);

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Query q = pm.newQuery(Artist.class);
			// list = (List<Artist>) q.execute();
			// pm.detachCopyAll(list);
			// // pm.makeTransientAll(list);
			List<Artist> artists = (List<Artist>) q.execute();
			for (Artist a : artists) {
				Artist artist = new Artist();
				artist.setFirstname(a.getFirstname());
				artist.setLastname(a.getLastname());
				artist.setGender(a.getGender());
				artist.setCountry(a.getCountry());

				list.add(artist);
			}

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return list;
	}
}
