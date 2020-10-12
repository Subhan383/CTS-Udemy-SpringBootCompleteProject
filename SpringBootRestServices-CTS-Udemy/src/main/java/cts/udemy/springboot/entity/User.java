package cts.udemy.springboot.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonView;

@Entity(name = "user")
@Table(name = "user")
//@JsonFilter(value = "userFilter") -- Used for MappingJacksonValue filtering section (JacksonMappingFiltering GIT Branch)
public class User extends RepresentationModel {

	@Id
	@GeneratedValue
	@JsonView(JsonViews.External.class)
	private int userid;

	@NotBlank(message = "Username should not be empty")
	@Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
	@JsonView(JsonViews.External.class)
	private String username;

	@Size(min = 3, message = "First name should be atleast 3 characters")
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	@JsonView(JsonViews.External.class)
	private String firstname;

	@Column(name = "LAST_NAME", length = 50, nullable = false)
	@JsonView(JsonViews.External.class)
	private String lastname;

	@Column(name = "EMAIL_ADDRESS", length = 50, nullable = false)
	@JsonView(JsonViews.External.class)
	private String email;

	@Column(name = "ROLE", length = 50, nullable = false)
	@JsonView(JsonViews.Internal.class)
	private String role;

	@Column(name = "SSN", length = 50, nullable = false, unique = true)
	@JsonView(JsonViews.Internal.class)
	private String ssn;

	@OneToMany(mappedBy = "user")
	@JsonView(JsonViews.Internal.class)
	private List<Order> order;

	@Column(name = "address")
	private String address;

	public User() {

	}

	public User(int userid, @NotBlank(message = "Username should not be empty") String username,
			@Size(min = 3, message = "First name should be atleast 3 characters") String firstname, String lastname,
			String email, String role, String ssn, List<Order> order, String address) {
		super();
		this.userid = userid;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.order = order;
		this.address = address;
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", order=" + order + ", address="
				+ address + "]";
	}

}
