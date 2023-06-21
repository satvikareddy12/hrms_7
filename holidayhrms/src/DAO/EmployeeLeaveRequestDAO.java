package DAO;

import java.util.List;

import models.EmployeeLeaveRequest;
import models.EmployeeLeaveRequestId;
import models.JobGradeWiseLeaves;

public interface EmployeeLeaveRequestDAO {
	void saveEmployeeLeaveRequest(EmployeeLeaveRequest leaveRequest);

	int getNextLeaveRequestIndex(int employeeId);

	List<EmployeeLeaveRequest> getEmployeeAndLeaveRequestData(int id);

	EmployeeLeaveRequest getEmployeeLeaveRequest(EmployeeLeaveRequestId id);

	JobGradeWiseLeaves getJobGradeWiseLeaves(String jobGradeId);

	List<EmployeeLeaveRequest> getApprovedAndPendingEmployeeAndLeaveRequestData(int id, int year);

	List<EmployeeLeaveRequest> getApprovedEmployeeAndLeaveRequestData(int id);

	List<EmployeeLeaveRequest> getLeaveRequestHistory(int id);

	List<JobGradeWiseLeaves> getJobGradeWiseLeaves();
}
