package dao.implementation;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import dao.UserDAO;
import model.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public UserDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void saveUser(User user) {
		entityManager.getTransaction().begin();
		user.setPasswordHash(user.getPassword());
		entityManager.persist(user);
		entityManager.getTransaction().commit();
	}

	@Override
	public User getUserById(Long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public User getUserByUsername(String username) {
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username",
				User.class);
		query.setParameter("username", username);
		return query.getSingleResult();
	}

	@Override
	public List<User> getAllUsers() {
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
	}

	@Override
	public void updateUser(User user) {
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
	}

	@Override
	public void deleteUser(User user) {
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
	}

	@Override
	public boolean userExists(String user) {
		TypedQuery<Long> query = entityManager
				.createQuery("SELECT COUNT(username) FROM User u WHERE u.username = :user", Long.class);
		query.setParameter("user", user);
		return query.getSingleResult() > 0;
	}

	@Override
	public boolean authenticate(User user) throws SQLException {
		try {
			TypedQuery<String> query = entityManager
					.createQuery("SELECT u.password FROM User u WHERE u.username = :user", String.class);
			query.setParameter("user", user.getUsername());
			String passwordHash = query.getSingleResult();
			return BCrypt.checkpw(user.getPassword(), passwordHash);
		} catch (NoResultException e) {
			return false;
		}
	}

}
