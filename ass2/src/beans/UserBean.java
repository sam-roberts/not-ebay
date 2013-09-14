package beans;

public class UserBean {

	private String username;
	private String email;
	private String nickname;
	private String firstName;
	private String lastName;
	private int yearOfBirth;
	private String postalAddress;
	
	public UserBean(String username, String email, String nickname,
			String firstName, String lastName, int yearOfBirth,
			String postalAddress) {

		this.username = username;
		this.email = email;
		this.nickname = nickname;
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearOfBirth = yearOfBirth;
		this.postalAddress = postalAddress;
	}

	public String getUsername() {
		return username;
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
	
}
