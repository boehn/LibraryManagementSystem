package view.library;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import program.PropertiesConfig;
import view.custom.components.FlatButton;
import view.custom.components.GradientPanel;

public class AuthorView extends JFrame {

	private static final long serialVersionUID = 1L;
	private Point mouseDownCompCoords = null;

	PropertiesConfig propConfig;
	protected Color primaryBackgroundColor;
	protected Color secondaryBackgroundColor;
	protected Color tertiaryBackgroundColor;
	protected Color primaryForegroundColor;
	protected Color quaternaryBackgroundColor;
	GradientPanel mainPanel;
	private JTextField txtAuthorName;
	private JTextField txtAuthorLastname;
	private JLabel lblAuthorPanelTitle;
	private AuthorDataListener listener;

	public AuthorView(AuthorDataListener listener) throws IOException {

		this.listener = listener;
		propConfig = new PropertiesConfig();

		primaryBackgroundColor = propConfig.getColor("color.primary.background");
		secondaryBackgroundColor = propConfig.getColor("color.secondary.background");
		tertiaryBackgroundColor = propConfig.getColor("color.tertiary.background");
		primaryForegroundColor = propConfig.getColor("color.primary.foreground");
		quaternaryBackgroundColor = propConfig.getColor("color.quaternary.background");

		mainPanel = new GradientPanel(primaryBackgroundColor, quaternaryBackgroundColor);
		setSize(450, 166);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setUndecorated(true);
		mainPanel.setLocation(0, 0);
		mainPanel.setSize(450, 165);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseDownCompCoords = null;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				mouseDownCompCoords = e.getPoint();
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point currCoords = e.getLocationOnScreen();
				setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
			}
		});

		FlatButton btnExit = new FlatButton("X", primaryBackgroundColor, primaryForegroundColor);
		btnExit.setFont(new Font("Dialog", Font.BOLD, 12));
		btnExit.setBounds(408, 0, 42, 42);
		mainPanel.add(btnExit);

		txtAuthorName = new JTextField();
		txtAuthorName.setColumns(10);
		txtAuthorName.setBounds(22, 77, 169, 25);
		mainPanel.add(txtAuthorName);

		JLabel lblAuthorName = new JLabel("Nome");
		lblAuthorName.setForeground(Color.WHITE);
		lblAuthorName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAuthorName.setBounds(22, 54, 71, 24);
		mainPanel.add(lblAuthorName);

		txtAuthorLastname = new JTextField();
		txtAuthorLastname.setColumns(10);
		txtAuthorLastname.setBounds(220, 77, 197, 25);
		mainPanel.add(txtAuthorLastname);

		JLabel lblAuthorLastname = new JLabel("Sobrenome");
		lblAuthorLastname.setForeground(Color.WHITE);
		lblAuthorLastname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAuthorLastname.setBounds(220, 52, 71, 24);
		mainPanel.add(lblAuthorLastname);

		lblAuthorPanelTitle = new JLabel("Autor");
		lblAuthorPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthorPanelTitle.setForeground(Color.WHITE);
		lblAuthorPanelTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAuthorPanelTitle.setBounds(186, 18, 71, 24);
		mainPanel.add(lblAuthorPanelTitle);

		FlatButton btnCancelSave = new FlatButton("Salvar", secondaryBackgroundColor, primaryForegroundColor);
		btnCancelSave.setText("Cancelar");
		btnCancelSave.setFont(new Font("Dialog", Font.BOLD, 12));
		btnCancelSave.setBounds(221, 120, 90, 23);
		btnCancelSave.setOpaque(true);
		mainPanel.add(btnCancelSave);

		FlatButton btnSaveAuthor = new FlatButton("Salvar", new Color(29, 44, 79), new Color(220, 224, 234));
		btnSaveAuthor.setOpaque(true);
		btnSaveAuthor.setFont(new Font("Dialog", Font.BOLD, 12));
		btnSaveAuthor.setBounds(327, 120, 90, 23);
		mainPanel.add(btnSaveAuthor);

		btnCancelSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnSaveAuthor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveData();
			}
		});

		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void saveData() {
		String name = txtAuthorName.getText();
		String lastName = txtAuthorLastname.getText();

		if (listener != null) {
			listener.onAuthorDataSaved(name, lastName);
		}

		dispose();
	}
}
