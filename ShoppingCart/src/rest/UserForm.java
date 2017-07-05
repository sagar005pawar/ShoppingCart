package rest;

import java.util.List;

import model.User;

public class UserForm {
	List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public UserForm(List<User> users) {
		super();
		this.users = users;
	}

	public UserForm() {
		super();
		this.users = null;
	}

	@Override
	public String toString() {
		return "UserForm [users=" + users + "]";
	}
}
