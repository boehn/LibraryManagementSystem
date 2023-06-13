package view.login;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import program.PropertiesConfig;
import view.custom.components.GradientPanel;
import view.custom.components.RoundedPanel;

public class BaseLoginScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	PropertiesConfig colorConfig;
	protected Color primaryBackgroundColor;
	protected Color secondaryBackgroundColor;
	protected Color tertiaryBackgroundColor;
	protected Color primaryForegroundColor;
	protected Color quaternaryBackgroundColor;

	GradientPanel mainPanel;
	RoundedPanel roundedPanel;
	JButton mainButton;
	JLabel lblHead;

	public BaseLoginScreen(JLabel lblHead, JButton screenButton) throws IOException, FontFormatException {

		colorConfig = new PropertiesConfig();

		primaryBackgroundColor = colorConfig.getColor("color.primary.background");
		secondaryBackgroundColor = colorConfig.getColor("color.secondary.background");
		tertiaryBackgroundColor = colorConfig.getColor("color.tertiary.background");
		primaryForegroundColor = colorConfig.getColor("color.primary.foreground");
		quaternaryBackgroundColor = colorConfig.getColor("color.quaternary.background");

		setFont(new Font("Noto Sans CJK SC", Font.PLAIN, 13));
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(460, 560);
		mainPanel = new GradientPanel(primaryBackgroundColor, quaternaryBackgroundColor);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(130, 70, 200, 50);
		panel.setBackground(quaternaryBackgroundColor);
		mainPanel.add(panel);
		panel.setLayout(null);

		this.lblHead = lblHead;
		this.lblHead.setFont(new Font("Mallanna", Font.PLAIN, 28));
		this.lblHead.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblHead.setBounds(0, 0, 200, 50);
		this.lblHead.setForeground(secondaryBackgroundColor);
		panel.add(this.lblHead);

		roundedPanel = new RoundedPanel();
		roundedPanel.setBounds(70, 80, 320, 360);
		roundedPanel.setBorder(BorderFactory.createEmptyBorder());
		roundedPanel.setBackground(secondaryBackgroundColor);
		mainPanel.add(roundedPanel);
		roundedPanel.setLayout(null);

		mainButton = screenButton;
		mainButton.setBackground(quaternaryBackgroundColor);
		mainButton.setBounds(85, 235, 150, 40);
		roundedPanel.add(mainButton);

		mainButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mainButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mainButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		setLocationByPlatform(true);
		setVisible(true);
		requestFocus();
	}

	public JTextField insertTextField(int y, String iconPath, String placeholder, boolean isPassword) {
		RoundedPanel textPanel = new RoundedPanel();
		textPanel.setBounds(50, y, 220, 45);
		textPanel.setBorder(BorderFactory.createEmptyBorder());
		textPanel.setBackground(tertiaryBackgroundColor);
		roundedPanel.add(textPanel);
		textPanel.setLayout(null);

		ImageIcon tempIcon = new ImageIcon(getClass().getResource(iconPath));
		Image img = tempIcon.getImage();
		Image imgIcon = img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		ImageIcon panelIcon = new ImageIcon(imgIcon);
		JLabel labelText = new JLabel(panelIcon);
		labelText.setBounds(-65, 3, 175, 35);
		textPanel.add(labelText);

		JTextField textField;

		if (!isPassword) {
			textField = new JTextField(15);
		} else {
			textField = new JPasswordField(9);
		}

		textField.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 5));
		textField.setText(placeholder);
		textField.setBounds(40, 3, 175, 35);
		textField.setBackground(tertiaryBackgroundColor);
		textField.setForeground(primaryForegroundColor);

		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField.getText().equals(placeholder)) {
					textField.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				String text = textField.getText().trim();
				if (text.isEmpty()) {
					textField.setText(placeholder);
				}
			}
		});
		textPanel.add(textField);
		return textField;
	}

	protected void showSuccessMessage(String message, String title) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	protected void showErrorMessage(Exception exception) {
		JOptionPane.showMessageDialog(this, exception.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	}
}
