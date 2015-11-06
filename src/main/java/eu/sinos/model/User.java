package eu.sinos.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import eu.sinos.model.Account;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.OneToMany;
@Entity
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column
	@NotNull
	private String name;

	@Column
	private String surname;

	@Column
	private String accountID;

	@Column
	private String email;

	@Column
	private String address;

	@Column
	private boolean isValid;

	@OneToMany
	private Set<Account> account = new HashSet<Account>();

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

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

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@NotNull
	public boolean isIsValid() {
		return isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (name != null && !name.trim().isEmpty())
			result += "name: " + name;
		if (surname != null && !surname.trim().isEmpty())
			result += ", surname: " + surname;
		if (accountID != null && !accountID.trim().isEmpty())
			result += ", accountID: " + accountID;
		if (email != null && !email.trim().isEmpty())
			result += ", email: " + email;
		if (address != null && !address.trim().isEmpty())
			result += ", address: " + address;
		result += ", isValid: " + isValid;
		return result;
	}

	public Set<Account> getAccount() {
		return this.account;
	}

	public void setAccount(final Set<Account> account) {
		this.account = account;
	}
}