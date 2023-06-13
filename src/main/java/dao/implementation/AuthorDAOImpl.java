package dao.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import dao.AuthorDAO;
import model.Author;

@Repository
@Transactional
public class AuthorDAOImpl implements AuthorDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public AuthorDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void saveAuthor(Author author) {
		entityManager.getTransaction().begin();
		entityManager.persist(author);
		entityManager.getTransaction().commit();
	}

	@Override
	public void updateAuthor(Author author) {
		entityManager.getTransaction().begin();
		entityManager.merge(author);
		entityManager.getTransaction().commit();
	}

	@Override
	public void deleteAuthor(Author author) {
		entityManager.getTransaction().begin();
		entityManager.remove(author);
		entityManager.getTransaction().commit();
	}

	@Override
	public Author getAuthorById(Long id) {
		return entityManager.find(Author.class, id);
	}

	@Override
	public List<Author> getAllAuthors() {
		TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a", Author.class);
		return query.getResultList();
	}

	@Override
	public List<Author> searchAuthor(String name) {
		TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a WHERE a.name LIKE :name",
				Author.class);
		query.setParameter("name", "%" + name + "%");
		return query.getResultList();
	}

}
