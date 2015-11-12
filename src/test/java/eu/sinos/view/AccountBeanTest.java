package eu.sinos.view;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import eu.sinos.model.Account;
import eu.sinos.model.User;

@RunWith(Arquillian.class)
public class AccountBeanTest {

	@Inject
	private AccountBean accountBean;
	
	Account account;

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class).addClass(UserBean.class)
				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
				.addPackages(true, "javax.faces")
				.addPackages(true, "eu.sinos")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void shouldBeDeployed() {
		Assert.assertNotNull(accountBean);
	}
	
	@Test
	public void shouldPersistNewAccount(){
		account = new Account();
		
		whenCreatingAccount();
		
		assertThat(account.getId(), is(not(nullValue())));
		assertThat(account.getCreateDate(), is(not(nullValue())));
		assertThat(account.getUpdateDate(), is(nullValue()));
	}

	@Test
	public void shouldUpdateAccount(){
		givenSavedAccount();
		
		whenUpdatingAccount();
		
		assertThat(account.getUpdateDate(), is(not(nullValue())));
		assertThat(account.getName(), is(equalTo("Marek")));
	}
	
	@Test
	public void persistShouldCascadeUser(){
		givenSavedAccount();
		
		whenAddingUser();
		
		User user = account.getUsers().iterator().next();
		assertThat(user.getId(), is(not(nullValue())));
	}

	private void whenAddingUser() {
		accountBean.create();
		account.addUser(new User());
		accountBean.update();
	}
	
	private void whenCreatingAccount() {
		account = new Account();
		accountBean.setAccount(account);
		accountBean.create();
		accountBean.update();
	}
	
	private void givenSavedAccount(){
		account = new Account();
		accountBean.setAccount(account);
		accountBean.create();
		accountBean.update();
	}
	
	private void whenUpdatingAccount() {
		accountBean.create();
		account.setName("Marek");
		accountBean.update();
	}
	
}
