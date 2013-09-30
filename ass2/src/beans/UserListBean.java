package beans;

import java.util.LinkedList;

public class UserListBean {

	private LinkedList<UserBean> users;
	
	public UserListBean() {
		users = new LinkedList<UserBean>();
	}
	
	public void addUser(UserBean a) {
		users.add(a);
	}
	
	public LinkedList<UserBean> getUsers() {
		return users;
	}
	
	public boolean isEmpty() {
		return users.isEmpty();
	}
	
	public int getNumUsers() {
		return users.size();
	}
	
	//TODO stylize
	public String display() {
		String strConcat = "<select name='username'>";
		for (UserBean ub : users)
			strConcat += "<option value='" + ub.getUsername() + "'>" + ub.getUsername() + "</option>";
		strConcat += "</select>";
		return strConcat;
	}
}
