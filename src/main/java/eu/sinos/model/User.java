package eu.sinos.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class User extends Account {

	private static final long serialVersionUID = -919840836445832509L;

	@Column
	private String accountID;
	
	@Column
	private String address;

	@Embedded
	private Status status;
	
	@NotNull
	@ManyToOne(optional = false)
	private Account account;
	
	public User() {
	}
	
	public User(Account account){
		this.account = account;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}