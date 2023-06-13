package dao;

import java.util.List;

import model.Author;
import model.Book;

public interface BookDAO {

	void saveBook(Book book);

	void updateBook(Book book);

	void deleteBook(Book book);

	Book getBookByIsbn(String isbn);

	List<Book> searchBook(String title);

	List<Author> searchBookByAuthor(String authorName);

	List<Book> getAllBooks();
}
