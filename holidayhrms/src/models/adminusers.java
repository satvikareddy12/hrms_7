package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hrms_adminusers")
public class adminusers {

	@Id
	@Column(name = "ausr_id")
	private int userId;

	@Column(name = "ausr_name")
	private String username;

	@Column(name = "ausr_empl_id")
	private int employeeId;

	@Column(name = "ausr_empl_jbgr")
	private String employeeJobGroup;

	@Column(name = "ausr_email")
	private String email;

	@Column(name = "ausr_password")
	private String password;

	// Constructors, getters, and setters

	public adminusers() {
	}

	public adminusers(int userId, String username, int employeeId, String employeeJobGroup, String email,
			String password) {
		this.userId = userId;
		this.username = username;
		this.employeeId = employeeId;
		this.employeeJobGroup = employeeJobGroup;
		this.email = email;
		this.password = password;
	}

	// Getters and setters

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeJobGroup() {
		return employeeJobGroup;
	}

	public void setEmployeeJobGroup(String employeeJobGroup) {
		this.employeeJobGroup = employeeJobGroup;
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
}