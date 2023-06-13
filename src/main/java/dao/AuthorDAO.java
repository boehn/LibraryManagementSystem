package dao;

import java.util.List;

import model.Author;

public interface AuthorDAO {

	void saveAuthor(Author author);

	void updateAuthor(Author author);

	void deleteAuthor(Author author);

	Author getAuthorById(Long id);

	List<Author> getAllAuthors();

	List<Author> searchAuthor(String name);

}
