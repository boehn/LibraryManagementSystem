package dao;

import java.sql.SQLException;
import java.util.List;

import model.User;

public interface UserDAO {

	void saveUser(User user);

	User getUserById(Long id);

	User getUserByUsername(String username);

	List<User> getAllUsers();

	void updateUser(User user);

	void deleteUser(User user);

	boolean userExists(String user);

	boolean authenticate(User user) throws SQLException;

}
