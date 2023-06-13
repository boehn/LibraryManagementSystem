package view.library.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Publisher;

public class PublisherTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private final List<Publisher> publishers;
	private final String[] columnNames = { "id", "Nome", "URL" };

	public PublisherTableModel(List<Publisher> publishers) {
		this.publishers = publishers;
	}

	@Override
	public int getRowCount() {
		return publishers.size();
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
		Publisher publisher = publishers.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return publisher.getId();
		case 1:
			return publisher.getName();
		case 2:
			return publisher.getUrl();
		default:
			throw new IndexOutOfBoundsException("Invalid column index");
		}
	}
}
