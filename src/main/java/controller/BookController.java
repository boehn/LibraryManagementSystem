package controller;

import java.util.List;

import dao.BookDAO;
import model.Author;
import model.Book;

public class BookController {
	
    private BookDAO bookDAO;

    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public void saveBook(Book book) {
        bookDAO.saveBook(book);
    }

    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    public void deleteBook(Book book) {
        bookDAO.deleteBook(book);
    }

    public Book getBookByIsbn(String isbn) {
        return bookDAO.getBookByIsbn(isbn);
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public List<Book> searchBook(String title) {
        return bookDAO.searchBook(title);
    }

    public List<Author> searchBookByAuthor(String authorName) {
        return bookDAO.searchBookByAuthor(authorName);
    }
}
