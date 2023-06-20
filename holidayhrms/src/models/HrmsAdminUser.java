package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hrms_adminusers")
public class HrmsAdminUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ausr_id")
	private int userId;

	@Column(name = "ausr_name")
	private String userName;

	@Column(name = "ausr_empl_id")
	private int employeeId;

	@Column(name = "ausr_email")
	private String email;

	@Column(name = "ausr_password")
	private String password;

	@Column(name = "ausr_empl_jbgr")
	private String employeeJobGrade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ausr_empl_id", referencedColumnName = "empl_id", insertable = false, updatable = false)
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ausr_empl_jbgr", referencedColumnName = "jbgr_id", insertable = false, updatable = false)
	private HrmsJobGrade employeeJobGradeEntity;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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

	public String getEmployeeJobGrade() {
		return employeeJobGrade;
	}

	public void setEmployeeJobGrade(String employeeJobGrade) {
		this.employeeJobGrade = employeeJobGrade;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public HrmsJobGrade getEmployeeJobGradeEntity() {
		return employeeJobGradeEntity;
	}

	public void setEmployeeJobGradeEntity(HrmsJobGrade employeeJobGradeEntity) {
		this.employeeJobGradeEntity = employeeJobGradeEntity;
	}

	// Constructors, getters, and setters
	// ...
}
