package view.library;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.AbstractTableModel;

import controller.AuthorController;
import controller.BookController;
import controller.PublisherController;
import controller.exceptions.UserException;
import dao.factory.DAOFactory;
import model.Author;
import model.Book;
import model.Publisher;
import program.PropertiesConfig;
import view.custom.components.FlatButton;
import view.custom.components.GradientPanel;
import view.custom.components.PlaceholderTextField;
import view.library.table.AuthorTableModel;
import view.library.table.BookTableModel;
import view.library.table.CustomTable;
import view.library.table.PublisherTableModel;

public class MainView extends JFrame implements AuthorDataListener, PublisherDataListener {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private Point mouseDownCompCoords = null;

	PropertiesConfig propConfig;
	protected Color primaryBackgroundColor;
	protected Color secondaryBackgroundColor;
	protected Color tertiaryBackgroundColor;
	protected Color primaryForegroundColor;
	protected Color quaternaryBackgroundColor;
	GradientPanel mainPanel;

	AbstractTableModel abstractTableModel;
	JComboBox<String> viewTableOptions;
	JTable viewTable;
	JScrollPane viewTableJScrollPane;
	private JTextField searchTextField;
	private JPanel editPanel;
	private JLabel lblIsbn;
	private JTextField txtISBN;
	private JLabel lblBookPrice;
	private JTextField txtBookTitle;
	private JLabel lblBookTitle;
	private JTextField txtPrice;

	private AuthorController authorController;
	private BookController bookController;
	private PublisherController publisherController;
	private Author savedAuthor;
	private Publisher savedPublisher;

	public MainView(DAOFactory daoFactory) throws IOException, FontFormatException {
		authorController = new AuthorController(daoFactory.createAuthorDAO());
		bookController = new BookController(daoFactory.createBookDAO());
		publisherController = new PublisherController(daoFactory.createPublisherDAO());

		propConfig = new PropertiesConfig();

		primaryBackgroundColor = propConfig.getColor("color.primary.background");
		secondaryBackgroundColor = propConfig.getColor("color.secondary.background");
		tertiaryBackgroundColor = propConfig.getColor("color.tertiary.background");
		primaryForegroundColor = propConfig.getColor("color.primary.foreground");
		quaternaryBackgroundColor = propConfig.getColor("color.quaternary.background");

		mainPanel = new GradientPanel(primaryBackgroundColor, quaternaryBackgroundColor);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setUndecorated(true);
		mainPanel.setLocation(0, 0);
		mainPanel.setSize(WIDTH, HEIGHT);
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
		btnExit.setBounds(758, 0, 42, 42);
		mainPanel.add(btnExit);

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		List<Book> books = bookController.getAllBooks();
		abstractTableModel = new BookTableModel(books);

		// Create table
		viewTable = new CustomTable(abstractTableModel);
		viewTable.setBounds(12, 189, 636, 398);
		viewTableJScrollPane = new JScrollPane(viewTable);
		viewTableJScrollPane.getViewport().setBackground(primaryBackgroundColor);
		viewTableJScrollPane.setForeground(primaryBackgroundColor);
		viewTableJScrollPane.setBounds(12, 189, 778, 398);

		mainPanel.add(viewTableJScrollPane);

		JLabel lblTableView = new JLabel("Views: ");
		lblTableView.setForeground(new Color(220, 224, 234));
		lblTableView.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTableView.setBounds(12, 149, 50, 29);
		mainPanel.add(lblTableView);

		viewTableOptions = new JComboBox<String>();
		viewTableOptions.setBounds(58, 154, 76, 25);
		viewTableOptions.addItem("Livros");
		viewTableOptions.addItem("Autores");
		viewTableOptions.addItem("Editoras");
		mainPanel.add(viewTableOptions);

		searchTextField = new PlaceholderTextField("Pesquisar...");
		searchTextField.setHorizontalAlignment(SwingConstants.LEFT);
		searchTextField.setColumns(10);
		searchTextField.setBounds(146, 154, 640, 25);
		searchTextField.setBorder(BorderFactory.createEmptyBorder());
		mainPanel.add(searchTextField);

		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(new Color(255, 255, 255));
		searchPanel.setBounds(142, 154, 648, 25);
		mainPanel.add(searchPanel);

		editPanel = new JPanel(null);
		editPanel.setBounds(12, 8, 780, 139);
		editPanel.setOpaque(false);
		mainPanel.add(editPanel);

		lblIsbn = new JLabel("ISBN");
		lblIsbn.setForeground(new Color(255, 255, 255));
		lblIsbn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIsbn.setBounds(8, 8, 43, 23);
		editPanel.add(lblIsbn);

		txtISBN = new JTextField();
		txtISBN.setBounds(8, 28, 90, 25);
		editPanel.add(txtISBN);
		txtISBN.setColumns(10);

		lblBookPrice = new JLabel("Preço");
		lblBookPrice.setForeground(Color.WHITE);
		lblBookPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBookPrice.setBounds(622, 8, 43, 23);
		editPanel.add(lblBookPrice);

		txtBookTitle = new JTextField();
		txtBookTitle.setColumns(10);
		txtBookTitle.setBounds(110, 28, 500, 25);
		editPanel.add(txtBookTitle);

		lblBookTitle = new JLabel("Título");
		lblBookTitle.setForeground(Color.WHITE);
		lblBookTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBookTitle.setBounds(110, 8, 43, 23);
		editPanel.add(lblBookTitle);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(622, 28, 90, 25);
		editPanel.add(txtPrice);

		FlatButton btnAuthorPanel = new FlatButton("Autor", secondaryBackgroundColor, primaryForegroundColor);
		btnAuthorPanel.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAuthorPanel.setBounds(8, 95, 90, 23);
		btnAuthorPanel.setOpaque(true);
		btnAuthorPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAuthorWindow();
			}
		});
		editPanel.add(btnAuthorPanel);

		FlatButton btnPublisherPanel = new FlatButton("Editora", secondaryBackgroundColor, primaryForegroundColor);
		btnPublisherPanel.setFont(new Font("Dialog", Font.BOLD, 12));
		btnPublisherPanel.setBounds(110, 95, 90, 23);
		btnPublisherPanel.setOpaque(true);
		btnPublisherPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPublisherWindow();
			}
		});
		editPanel.add(btnPublisherPanel);

		FlatButton btnAddBook = new FlatButton("Adicionar", secondaryBackgroundColor, primaryForegroundColor);
		btnAddBook.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAddBook.setBounds(622, 95, 90, 23);
		btnAddBook.setOpaque(true);
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBook();
			}
		});
		editPanel.add(btnAddBook);

		viewTableOptions.addActionListener(e -> {
			updateTableData();
		});
		setLocationByPlatform(true);
		setVisible(true);
	}

	private void updateTableData() {
		String viewOption = viewTableOptions.getEditor().getItem().toString();

		switch (viewOption) {
		case "Livros":
			List<Book> books = bookController.getAllBooks();
			abstractTableModel = new BookTableModel(books);
			break;
		case "Autores":
			List<Author> authors = authorController.getAllAuthors();
			abstractTableModel = new AuthorTableModel(authors);
			break;
		case "Editoras":
			List<Publisher> publishers = publisherController.getAllPublishers();
			abstractTableModel = new PublisherTableModel(publishers);
			break;
		}
		viewTable.setModel(abstractTableModel);
		;
	}

	private void addBook() {
		if (savedAuthor == null) {
			showErrorMessage(new UserException("Preencha os dados relativos ao Autor"));
			return;
		}

		if (savedPublisher == null) {
			showErrorMessage(new UserException("Preencha os dados relativos à Editora"));
			return;
		}

		String isbn = txtISBN.getText();
		String title = txtBookTitle.getText();
		String price = txtPrice.getText() != null && !txtPrice.getText().isEmpty() ? txtPrice.getText() : "0.0";
		BigDecimal decimalValue = new BigDecimal(price, new MathContext(2));

		if (isbn == null || isbn.isEmpty() || title == null || title.isEmpty()) {
			showErrorMessage(new UserException("Os campos ISBN e Título são obrigatórios"));
			return;
		}

		Publisher publisher = new Publisher();
		publisher.setName(savedPublisher.getName());
		publisher.setUrl(publisher.getUrl());
		Set<Book> publisherBooks = new HashSet<>();

		Book book = new Book();
		book.setTitle(title);
		book.setIsbn(isbn);
		book.setPrice(decimalValue);
		book.setPublisher(publisher);

		publisherBooks.add(book);
		publisher.setBooks(publisherBooks);
		publisherController.savePublisher(publisher);
		
		bookController.saveBook(book);

		Author author = new Author();
		author.setfName(savedAuthor.getfName());
		author.setName(savedAuthor.getName());
		List<Book> authorBooks = new ArrayList<>();
		authorBooks.add(book);
		author.setBooks(authorBooks);
		authorController.saveAuthor(author);

		

		System.out.println("Book saved");
	}

	@Override
	public void onAuthorDataSaved(String firstName, String lastName) {

		if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()) {
			savedAuthor = new Author() {
				{
					setName(firstName);
					setfName(lastName);
				}
			};
		}
	}

	@Override
	public void onPublisherDataSaved(String name, String url) {
		if (name != null && !name.isEmpty() && url != null && !url.isEmpty()) {
			savedPublisher = new Publisher() {
				{
					setName(name);
					setUrl(url);
				}
			};
		}

	}

	private void showAuthorWindow() {
		AuthorView authorWindow;
		try {
			authorWindow = new AuthorView(this);
			authorWindow.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showPublisherWindow() {
		PublisherView publisherWindow;
		try {
			publisherWindow = new PublisherView(this);
			publisherWindow.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void showSuccessMessage(String message, String title) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	protected void showErrorMessage(Exception exception) {
		JOptionPane.showMessageDialog(this, exception.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	}
}
