package dao.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import dao.BookDAO;
import model.Author;
import model.Book;

@Repository
@Transactional
public class BookDAOImpl implements BookDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public BookDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void saveBook(Book book) {
		entityManager.persist(book);
	}

	@Override
	public void updateBook(Book book) {
		entityManager.merge(book);
	}

	@Override
	public void deleteBook(Book book) {
		entityManager.remove(book);
	}

	@Override
	public Book getBookByIsbn(String isbn) {
		return entityManager.find(Book.class, isbn);
	}

	@Override
	public List<Book> getAllBooks() {
		TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
		return query.getResultList();
	}

	@Override
	public List<Book> searchBook(String title) {
		String queryString = "SELECT b FROM Book b WHERE b.title LIKE :title";
		TypedQuery<Book> query = entityManager.createQuery(queryString, Book.class);
		query.setParameter("title", "%" + title + "%");
		return query.getResultList();
	}

	@Override
	public List<Author> searchBookByAuthor(String authorName) {
		String queryString = "SELECT a FROM Author a JOIN a.books b WHERE a.name LIKE :authorName";
		TypedQuery<Author> query = entityManager.createQuery(queryString, Author.class);
		query.setParameter("authorName", "%" + authorName + "%");
		return query.getResultList();
	}

}
