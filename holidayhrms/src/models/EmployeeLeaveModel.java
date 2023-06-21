package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeLeaveModel {

	private int empId;
	private String name;
	private int leaveRequestIndex;
	private String reason;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	private String leaveType;
	private LocalDate leaveStartDate;
	private LocalDate leaveEndDate;
	private LocalDateTime leaveRequestDate;
	private int status;

	public EmployeeLeaveModel() {
	}

	public LocalDateTime getLeaveRequestDate() {
		return leaveRequestDate;
	}

	public void setLeaveRequestDate(LocalDateTime leaveRequestDate) {
		this.leaveRequestDate = leaveRequestDate;
	}

	public EmployeeLeaveModel(int empId, String name, int leaveRequestIndex, String reason, String leaveType,
			LocalDate leaveStartDate, LocalDate leaveEndDate, LocalDateTime leaveRequestDate, int status) {
		this.empId = empId;
		this.name = name;
		this.leaveRequestIndex = leaveRequestIndex;
		this.reason = reason;
		this.leaveType = leaveType;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.leaveRequestDate = leaveRequestDate;
		this.status = status;
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

	@Override
	public String toString() {
		return "EmployeeLeaveModel [empId=" + empId + ", name=" + name + ", leaveRequestIndex=" + leaveRequestIndex
				+ ", reason=" + reason + ", leaveType=" + leaveType + ", leaveStartDate=" + leaveStartDate
				+ ", leaveEndDate=" + leaveEndDate + ", leaveRequestDate=" + leaveRequestDate + ", status=" + status
				+ "]";
	}

	public void setLeaveEndDate(LocalDate leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}

}