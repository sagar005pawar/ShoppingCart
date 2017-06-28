package model;

import java.io.Serializable;

public class User implements Serializable, Cloneable {

	private String username;
	private String password;
	private String city;
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public User() {
		super();
		this.username = null;
		this.password = null;
		this.city = null;
	}
	
	public User(String username, String password, String city) {
		super();
		this.username = username;
		this.password = password;
		this.city = city;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", city=" + city + "]";
	}
}