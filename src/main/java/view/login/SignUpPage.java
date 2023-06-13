package view.login;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.UserController;
import controller.exceptions.UserException;
import dao.factory.DAOFactory;
import util.EntityManagerUtil;

public class SignUpPage extends BaseLoginScreen {

	private static final long serialVersionUID = 1L;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtPasswordConfirm;
	JLabel lblPasswordCheck;

	public SignUpPage() throws IOException, FontFormatException {
		super(new JLabel("Cadastro"), new JButton("Cadastrar"));

		txtUsername = insertTextField(67, "/Images/user-icon.png", "Usuário", false);
		txtPassword = insertTextField(127, "/Images/key-icon.png", "Senha", true);
		txtPasswordConfirm = insertTextField(187, "/Images/key-icon.png", "Novasenha", true);

		lblPasswordCheck = new JLabel("Confirme sua senha");
		lblPasswordCheck.setBounds(140, 234, 125, 15);
		lblPasswordCheck.setForeground(primaryForegroundColor);
		roundedPanel.add(lblPasswordCheck);

		mainButton.setLocation(85, 265);
		mainButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					createUser();
				} catch (IOException | FontFormatException e1) {
					e1.printStackTrace();
				}
			}
		});

		JLabel lblSignIn = new JLabel("Fazer login");
		lblSignIn.setBounds(120, 320, 80, 15);
		lblSignIn.setForeground(primaryForegroundColor);
		lblSignIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignIn.setVerticalAlignment(SwingConstants.CENTER);
		lblSignIn.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, primaryForegroundColor));
		Font font = lblSignIn.getFont();
		Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
		lblSignIn.setFont(boldFont);

		lblSignIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BaseLoginScreen signInPage = null;
				try {
					signInPage = new SignInPage();
				} catch (IOException | FontFormatException e1) {
					e1.printStackTrace();
				}
				dispose();
				signInPage.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblSignIn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblSignIn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		roundedPanel.add(lblSignIn);
	}

	private void createUser() throws IOException, FontFormatException {
		
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		String confirmedPassword = txtPasswordConfirm.getText();

		DAOFactory daoFactory = new DAOFactory(new EntityManagerUtil());
		UserController userController = new UserController(daoFactory.createUserDAO());

		try {
			userController.saveUser(username, password, confirmedPassword);
			BaseLoginScreen signInPage = new SignInPage();
			dispose();
			showSuccessMessage("Usuário cadastrado!", "");
			signInPage.setVisible(true);
		} catch (UserException userException) {
			showErrorMessage(userException);
		}
	}

}
