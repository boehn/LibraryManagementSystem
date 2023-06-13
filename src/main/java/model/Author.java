package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Authors")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany
	@JoinTable(name = "BooksAuthors", joinColumns = @JoinColumn(name = "author_id"), inverseJoinColumns = @JoinColumn(name = "isbn"))
	@OrderColumn(name = "sequence_number")
	private List<Book> books;

	private String fName;

	public Author() {
	}

	public Author(Long id, String name, String fName, List<Book> books) {
		this.id = id;
		this.name = name;
		this.fName = fName;
		this.books = books;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
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

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String authorBooksString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.fName).append(" ").append(this.name).append("\n");
		stringBuilder.append("Livros:\n");
		for (Book book : books) {
			stringBuilder.append("- ").append(book.getTitle()).append(" (ISBN: ").append(book.getIsbn()).append(")\n");
		}
		return stringBuilder.toString();
	}

	@Override
	public String toString() {
		return this.fName + " " + this.name;
	}

}
