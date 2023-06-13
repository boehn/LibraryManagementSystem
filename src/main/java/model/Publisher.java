package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Publishers")
public class Publisher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String url;

	@OneToMany(mappedBy = "publisher")
	private Set<Book> books = new HashSet<>();

	public Publisher() {
	}

	public Publisher(Long id, String name, String url) {
		this.id = id;
		this.name = name;
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public String publisherBooksString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Livros:\n");
		for (Book book : books) {
			stringBuilder.append("- ").append(book.getTitle()).append(" (ISBN: ").append(book.getIsbn()).append(")\n");
		}
		return stringBuilder.toString();
	}

	@Override
	public String toString() {
		return "Editora: " + this.name;
	}
}
