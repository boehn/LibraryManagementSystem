package controller;

import java.util.List;

import dao.AuthorDAO;
import model.Author;

public class AuthorController {

	private AuthorDAO authorDAO;

	public AuthorController(AuthorDAO authorDAO) {
		this.authorDAO = authorDAO;
	}

	public void saveAuthor(Author author) {
		authorDAO.saveAuthor(author);
	}

	public void updateAuthor(Author author) {
		authorDAO.updateAuthor(author);
	}

	public void deleteAuthor(Author author) {
		authorDAO.deleteAuthor(author);
	}

	public Author getAuthorById(Long id) {
		return authorDAO.getAuthorById(id);
	}

	public List<Author> getAllAuthors() {
		return authorDAO.getAllAuthors();
	}

	public List<Author> searchAuthor(String name) {
		return authorDAO.searchAuthor(name);
	}

}