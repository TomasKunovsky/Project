package pis.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	private String login;
	private String telephoneNumber;
	private String firstname;
	private String surname;
	private String email;
	private String password;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return firstname + " " + surname;
	}
	
	@Override
	public String toString() {
		if (getName().equals(" ")) {
			return getLogin();
		} else {
			return getLogin() + " : " + getName();
		}
	}
	
	@Override
	public boolean equals(Object other)
    {
		if (other == null) return false;
        if (other instanceof User)
            return ((User) other).getLogin().equals(login);
        else
            return false;
    }
	
	
}
