package cts.udemy.springboot.dtos;

public class UserMsDTO {

	private int userid;

	private String username;

	private String emailaddress;

	private String rolename;

	public UserMsDTO() {

	}

	public UserMsDTO(int userid, String username, String emailaddress, String rolename) {
		super();
		this.userid = userid;
		this.username = username;
		this.emailaddress = emailaddress;
		this.rolename = rolename;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
