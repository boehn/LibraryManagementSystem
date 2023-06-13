package view.login;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.UserController;
import controller.exceptions.UserException;
import dao.factory.DAOFactory;
import util.EntityManagerUtil;
import view.library.MainView;

public class SignInPage extends BaseLoginScreen {

	private static final long serialVersionUID = 1L;
	JTextField txtUsername;
	JTextField txtPassword;

	public SignInPage() throws IOException, FontFormatException {

		super(new JLabel("Bem-vindo"), new JButton("Login"));
		txtUsername = insertTextField(112, "/Images/user-icon.png", "Usuário", false);
		txtPassword = insertTextField(172, "/Images/key-icon.png", "Senha", true);

		JLabel lblCreateAccount = new JLabel("Criar nova conta");
		lblCreateAccount.setBounds(102, 290, 116, 15);
		lblCreateAccount.setForeground(primaryForegroundColor);
		lblCreateAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateAccount.setVerticalAlignment(SwingConstants.CENTER);
		lblCreateAccount.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, primaryForegroundColor));
		Font font = lblCreateAccount.getFont();
		Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
		lblCreateAccount.setFont(boldFont);

		lblCreateAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BaseLoginScreen signUpPage = null;
				try {
					signUpPage = new SignUpPage();
				} catch (IOException | FontFormatException e1) {
					e1.printStackTrace();
				}
				dispose();
				signUpPage.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblCreateAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblCreateAccount.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		roundedPanel.add(lblCreateAccount);

		mainButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					authenticateUser();
				} catch (UserException | IOException | FontFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private void authenticateUser() throws UserException, IOException, FontFormatException {

		String username = txtUsername.getText();
		String password = txtPassword.getText();

		DAOFactory daoFactory = new DAOFactory(new EntityManagerUtil());
		UserController userController = new UserController(daoFactory.createUserDAO());

		try {
			if (userController.authenticate(username, password)) {
				dispose();
				new MainView(daoFactory);
			}
		} catch (UserException e) {
			showErrorMessage(e);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
