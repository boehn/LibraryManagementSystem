package view.library.table;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import program.PropertiesConfig;

public class CustomTable extends JTable {

	private static final long serialVersionUID = 1L;
	PropertiesConfig propConfig;
	protected Color tableBackground;
	protected Color tableForeground;
	protected Color tableSelectionBackground;
	protected Color tableSelectionForeground;
	protected Color tableSecondaryBackground;

	public CustomTable(AbstractTableModel model) {
		super(model);
		// Get the header from the table
		JTableHeader header = this.getTableHeader();
		// Set the background color of the header
		header.setBackground(tableBackground);

		// Set the foreground (text) color of the header
		header.setForeground(tableForeground);

		// Set a custom renderer for the header
		header.setDefaultRenderer(new CustomHeaderRenderer());
	}

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		try {
			propConfig = new PropertiesConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
		tableBackground = propConfig.getColor("color.table.background");
		tableForeground = propConfig.getColor("color.table.foreground");
		tableSelectionBackground = propConfig.getColor("color.table.selection.background");
		tableSecondaryBackground = propConfig.getColor("color.table.background.secondary");

		Component component = super.prepareRenderer(renderer, row, column);

		// Set column header color and font
		if (component instanceof javax.swing.table.DefaultTableCellRenderer) {
			((javax.swing.table.DefaultTableCellRenderer) component)
					.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			component.setBackground(tableBackground);
			component.setForeground(tableForeground);
		}

		if (isRowSelected(row)) {
			component.setBackground(tableSelectionForeground);
		} else if (row % 2 == 0) {
			component.setBackground(tableBackground);
		} else {
			component.setBackground(tableSecondaryBackground);
		}

		return component;
	}
}