package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

	private static final String PERSISTENCE_UNIT_NAME = "library-jpa";

	private static EntityManagerFactory emFactory;

	public static EntityManager getEntityManager() {
		if (emFactory == null) {
			emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return emFactory.createEntityManager();
	}

	public static void closeEntityManager() {
		if (emFactory != null) {
			emFactory.close();
			emFactory = null;
		}
	}
}
