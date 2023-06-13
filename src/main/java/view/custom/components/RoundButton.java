package view.custom.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class RoundButton extends JButton {

	private static final long serialVersionUID = 1L;

	public void setColor(Color color) {
		this.color = color;
		setBackground(color);
	}

	public RoundButton(String text, int size) {
		super(text);
		this.size = size;
		setPreferredSize(new Dimension(size, size));
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setForeground(Color.WHITE);

		setColor(new Color(0, 119, 134));
		colorHover = new Color(29, 44, 79);
		colorClick = new Color(0, 119, 134);

		// Add event mouse
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent me) {
				setBackground(colorHover);
				hover = true;
			}

			@Override
			public void mouseExited(MouseEvent me) {
				setBackground(color);
				hover = false;

			}

			@Override
			public void mousePressed(MouseEvent me) {
				setBackground(colorClick);
			}

			@Override
			public void mouseReleased(MouseEvent me) {
				if (hover) {
					setBackground(colorHover);
				} else {
					setBackground(color);
				}
			}
		});
	}

	private boolean hover;
	private Color color;
	private Color colorHover;
	private Color colorClick;
	private int size;

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fillOval(0, 0, size, size);
		g2.dispose();
		super.paintComponent(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(size, size);
	}
}
