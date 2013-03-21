package cn.renfeng.fourhour.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public interface PersistenceService {

	PersistenceManagerFactory PMF = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");
}