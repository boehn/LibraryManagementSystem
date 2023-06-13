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

public class PublisherView extends JFrame {

	private static final long serialVersionUID = 1L;
	private Point mouseDownCompCoords = null;

	PropertiesConfig propConfig;
	protected Color primaryBackgroundColor;
	protected Color secondaryBackgroundColor;
	protected Color tertiaryBackgroundColor;
	protected Color primaryForegroundColor;
	protected Color quaternaryBackgroundColor;
	GradientPanel mainPanel;
	private JTextField txtPublisherName;
	private JTextField txtPublisherUrl;
	private JLabel lblPublisherPanelTitle;
	private PublisherDataListener listener;

	public PublisherView(PublisherDataListener listener) throws IOException {

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

		txtPublisherName = new JTextField();
		txtPublisherName.setColumns(10);
		txtPublisherName.setBounds(22, 77, 169, 25);
		mainPanel.add(txtPublisherName);

		JLabel lblPublisherName = new JLabel("Nome");
		lblPublisherName.setForeground(Color.WHITE);
		lblPublisherName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPublisherName.setBounds(22, 54, 71, 24);
		mainPanel.add(lblPublisherName);

		txtPublisherUrl = new JTextField();
		txtPublisherUrl.setColumns(10);
		txtPublisherUrl.setBounds(220, 77, 197, 25);
		mainPanel.add(txtPublisherUrl);

		JLabel lblPublisherUrl = new JLabel("URL");
		lblPublisherUrl.setForeground(Color.WHITE);
		lblPublisherUrl.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPublisherUrl.setBounds(220, 52, 71, 24);
		mainPanel.add(lblPublisherUrl);

		lblPublisherPanelTitle = new JLabel("Editora");
		lblPublisherPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblPublisherPanelTitle.setForeground(Color.WHITE);
		lblPublisherPanelTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPublisherPanelTitle.setBounds(170, 18, 105, 24);
		mainPanel.add(lblPublisherPanelTitle);

		FlatButton btnCancelSave = new FlatButton("Salvar", secondaryBackgroundColor, primaryForegroundColor);
		btnCancelSave.setText("Cancelar");
		btnCancelSave.setFont(new Font("Dialog", Font.BOLD, 12));
		btnCancelSave.setBounds(221, 120, 90, 23);
		btnCancelSave.setOpaque(true);
		mainPanel.add(btnCancelSave);

		FlatButton btnSavePublisher = new FlatButton("Salvar", new Color(29, 44, 79), new Color(220, 224, 234));
		btnSavePublisher.setOpaque(true);
		btnSavePublisher.setFont(new Font("Dialog", Font.BOLD, 12));
		btnSavePublisher.setBounds(327, 120, 90, 23);
		mainPanel.add(btnSavePublisher);

		btnCancelSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnSavePublisher.addActionListener(new ActionListener() {
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
		String name = txtPublisherName.getText();
		String url = txtPublisherUrl.getText();

		if (listener != null) {
			listener.onPublisherDataSaved(name, url);
		}

		dispose();
	}
}
