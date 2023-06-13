package controller;

import java.sql.SQLException;
import java.util.List;

import controller.exceptions.UserException;
import dao.UserDAO;
import model.User;

public class UserController {

	private UserDAO userDAO;

	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void saveUser(String username, String password, String passwordConfirmed) throws UserException {
		userDataValidation(username, password, passwordConfirmed);
		User user = new User(username, password);
		userDAO.saveUser(user);
	}

	public User getUserById(Long id) {
		return userDAO.getUserById(id);
	}

	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}

	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	public void deleteUser(User user) {
		userDAO.deleteUser(user);
	}

	public boolean userExists(String user) {
		return userDAO.userExists(user);
	}

	public boolean authenticate(String username, String password) throws SQLException, UserException {
		User user = new User(username, password);

		if (userExists(user.getUsername())) {
			if (userDAO.authenticate(user)) {
				return true;
			} else {
				throw new UserException("Senha incorreta!");
			}
		}
		throw new UserException("Usuário não existe!");
	}

	private void userDataValidation(String username, String password, String passwordConfirmed) throws UserException {
		if (userExists(username)) {
			throw new UserException("Usuário já existe!");
		}

		if (!validUsername(username)) {
			throw new UserException("Usuário inválido!");
		}

		if (!validPassword(password)) {
			throw new UserException("Senha inválida");
		}

		if (!passwordConfirm(password, passwordConfirmed)) {
			throw new UserException("As senhas devem ser iguais");
		}
	}

	private boolean validPassword(String password) {
		return !(password.length() < 6 || password.length() > 9 || password == null || password.trim().isEmpty());
	}

	private boolean passwordConfirm(String password, String confirmPassword) {
		if (!validPassword(password) || !password.equals(confirmPassword)) {
			return false;
		}

		return true;
	}

	private boolean validUsername(String username) {
		return !(username.equalsIgnoreCase("usuário") || username.isBlank() || username == null
				|| username.trim().isEmpty() || username.length() > 15 || username.length() < 3);
	}

}
