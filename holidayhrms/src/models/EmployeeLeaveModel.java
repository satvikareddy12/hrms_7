package models;

import java.time.LocalDate;

public class EmployeeLeaveModel {
	
	private int empId;
	private String name;
	private int leaveRequestIndex;
	private String reason;
	private String leaveType;
	private LocalDate leaveStartDate;
	private LocalDate leaveEndDate;
	
	public EmployeeLeaveModel() {
	}
	
	public EmployeeLeaveModel(int empId, String name, int leaveRequestIndex, String reason, String leaveType,
			LocalDate leaveStartDate, LocalDate leaveEndDate ) {
		this.empId = empId;
		this.name = name;
		this.leaveRequestIndex = leaveRequestIndex;
		this.reason = reason;
		this.leaveType = leaveType;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
	}
	

	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLeaveRequestIndex() {
		return leaveRequestIndex;
	}
	public void setLeaveRequestIndex(int leaveRequestIndex) {
		this.leaveRequestIndex = leaveRequestIndex;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public LocalDate getLeaveStartDate() {
		return leaveStartDate;
	}
	public void setLeaveStartDate(LocalDate leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}
	public LocalDate getLeaveEndDate() {
		return leaveEndDate;
	}
	public void setLeaveEndDate(LocalDate leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}

	

}
