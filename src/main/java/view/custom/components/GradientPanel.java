package view.custom.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class GradientPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Color colorOne, colorTwo;

	public GradientPanel(Color colorOne, Color colorTwo) {
		setLayout(new BorderLayout());
		this.colorOne = colorOne;
		this.colorTwo = colorTwo;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		int w = getWidth();
		int h = getHeight();
		GradientPaint gp = new GradientPaint(0, 0, colorOne, 0, h, colorTwo);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, w, h);
	}

}
