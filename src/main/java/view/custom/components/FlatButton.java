package view.custom.components;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FlatButton extends JButton {

	private static final long serialVersionUID = 1L;

	public FlatButton(String text, Color background, Color foreground) {
		super(text);
		setBackground(background);
		setForeground(foreground);
		setFont(new Font("Mallanna", Font.BOLD, 22));
		setBorder(new EmptyBorder(5, 15, 5, 15));
		setContentAreaFilled(false);
		setFocusPainted(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBorder(BorderFactory.createLineBorder(new Color(0, 119, 134).darker(), 2));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(new EmptyBorder(5, 15, 5, 15));
			}
		});
	}

	public void addPanel(CardLayout cardLayout, JPanel cardPanel, String panelName) {
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, panelName);
			}
		});
	}
}
