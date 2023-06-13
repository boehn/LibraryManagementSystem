package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "salt", nullable = false)
    private String salt;

	public User(String username, String password) {
		setUsername(username);
		setPassword(password);
	}

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {	
		this.password = password;
	}

	public void setPasswordHash(String password) {
		this.salt = BCrypt.gensalt();	
		this.password = BCrypt.hashpw(password, salt);
	}

	public boolean checkPassword(String password) {
		String hashedPassword = BCrypt.hashpw(password, this.salt);
		return BCrypt.checkpw(hashedPassword, this.password);
	}

}
