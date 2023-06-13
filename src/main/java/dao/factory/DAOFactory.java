package dao.factory;

import javax.persistence.EntityManager;

import dao.AuthorDAO;
import dao.BookDAO;
import dao.PublisherDAO;
import dao.UserDAO;
import dao.implementation.AuthorDAOImpl;
import dao.implementation.BookDAOImpl;
import dao.implementation.PublisherDAOImpl;
import dao.implementation.UserDAOImpl;
import util.EntityManagerUtil;

public class DAOFactory {

	private EntityManager entityManager;

	public DAOFactory(EntityManagerUtil entityManagerUtil) {
		this.entityManager = EntityManagerUtil.getEntityManager();
	}

	public AuthorDAO createAuthorDAO() {
		return new AuthorDAOImpl(entityManager);
	}

	public BookDAO createBookDAO() {
		return new BookDAOImpl(entityManager);
	}

	public PublisherDAO createPublisherDAO() {
		return new PublisherDAOImpl(entityManager);
	}

	public UserDAO createUserDAO() {
		return new UserDAOImpl(entityManager);
	}
}
