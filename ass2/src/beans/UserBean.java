package beans;

public class UserBean {
	
	private String username;
	private String password;
	private String email;
	private String nickname;
	private String firstName;
	private String lastName;
	private int yearOfBirth;
	private String postalAddress;
	private int ccNumber;
	private boolean isBanned;
	private boolean isAdmin;
	
	public UserBean() {
		username = null;
		password = null;
		email = null;
		nickname = null;
		firstName = null;
		lastName = null;
		yearOfBirth = 0;
		postalAddress = null;
		isAdmin = false;
		ccNumber = 0;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	public void setCcNumber(int ccNumber) {
		this.ccNumber = ccNumber;
	}
	public void setIsAdmin(boolean admin) {
		isAdmin = admin;
	}
	public void setIsBanned(boolean banned) {
		isBanned = banned;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public String getNickname() {
		return nickname;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getYearOfBirth() {
		return yearOfBirth;
	}
	public String getPostalAddress() {
		return postalAddress;
	}
	public int getCcNumber() {
		return ccNumber;
	}
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public boolean getIsBanned() {
		return isBanned;
	}
	
}
