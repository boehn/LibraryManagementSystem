package view.library.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Book;

public class BookTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private final List<Book> books;
	private final String[] columnNames = { "ISBN", "Título", "Editora", "Preço", "Autor(es)" };

	public BookTableModel(List<Book> books) {
		this.books = books;
	}

	@Override
	public int getRowCount() {
		return books.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Book book = books.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return book.getIsbn();
		case 1:
			return book.getTitle();
		case 2:
			return book.getPublisher().getName();
		case 3:
			return "R$ " + book.getPrice();
		case 4:
			return book.bookAuthorString();
		default:
			throw new IndexOutOfBoundsException("Invalid column index");
		}
	}
}