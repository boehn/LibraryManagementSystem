package view.library.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Author;

public class AuthorTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private final List<Author> authors;
	private final String[] columnNames = { "id", "Nome", "Sobrenome" };

	public AuthorTableModel(List<Author> authors) {
		this.authors = authors;
	}

	@Override
	public int getRowCount() {
		return authors.size();
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
		Author author = authors.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return author.getId();
		case 1:
			return author.getfName();
		case 2:
			return author.getName();
		default:
			throw new IndexOutOfBoundsException("Invalid column index");
		}
	}
}