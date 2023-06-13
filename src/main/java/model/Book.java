package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Books")
public class Book {

	@Id
	private String isbn;

	private String title;

	private BigDecimal price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publisher_id")
	private Publisher publisher;

	@ManyToMany
	@JoinTable(name = "BooksAuthors", joinColumns = @JoinColumn(name = "isbn"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	@OrderColumn(name = "sequence_number")
	private List<Author> authors = new ArrayList<>();

	public Book() {
	}

	public Book(String title, String isbn, BigDecimal price, Publisher publisher, List<Author> bookAuthor) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.price = price;
		this.publisher = publisher;
		this.authors = bookAuthor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public String bookAuthorString() {
		StringBuilder authorString = new StringBuilder();
		for (Author author : authors) {
			authorString.append(author.getName()).append(" ").append(author.getfName()).append(", ");
		}
		if (authorString.length() > 0) {
			authorString.delete(authorString.length() - 2, authorString.length());
		}
		return authorString.toString();
	}

	@Override
	public String toString() {
		return "Título: " + this.title + "\n" + "ISBN: " + this.isbn + "\n" + "Editora: " + this.publisher.getName()
				+ "\n" + "Preço: R$ " + this.price;
	}

}
