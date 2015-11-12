package eu.sinos.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.InheritanceType;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public class Account extends BaseEntity {

	private static final long serialVersionUID = 4555126247434114648L;

	@Column
	private String name;

	@Column
	private String surname;

	@Column
	private String email;

	@Column
	private boolean isValid;

	@Column
	private String login;

	@Column
	private String password;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<User> users = new HashSet<User>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isIsValid() {
		return isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<User> getUsers() {
		return Collections.unmodifiableSet(this.users);
	}
	
	/**
	 * Add user and handle bidirectional relationship
	 * @param user
	 */
	public void addUser(User user){
		if (users.contains(user)) return;
        users.add(user);
        user.setAccount(this);
	}
	
	public void removeUser(User user){
		users.remove(user);
	}

	public void setUsers(final Set<User> users) {
		this.users = users;
	}
}