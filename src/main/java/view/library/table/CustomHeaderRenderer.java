package view.library.table;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import program.PropertiesConfig;

public class CustomHeaderRenderer extends DefaultTableCellRenderer {

	PropertiesConfig propConfig;
	protected Color primaryForegroundColor;
	protected Color primaryBackgroundColor;
	private static final long serialVersionUID = 1L;

	public CustomHeaderRenderer() {
		try {
			propConfig = new PropertiesConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}

		primaryBackgroundColor = propConfig.getColor("color.primary.background");
		primaryForegroundColor = propConfig.getColor("color.primary.foreground");
		
		setHorizontalAlignment(JLabel.CENTER);
		setOpaque(true);
		setForeground(primaryForegroundColor);
		setBackground(primaryBackgroundColor);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		return this;
	}
}
