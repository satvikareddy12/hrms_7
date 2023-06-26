package service_interfaces;

import java.util.List;

import models.EmployeeLeaveRequest;
import models.JobGradeWiseLeaves;
import models.LeaveValidationModel;

public interface EmployeeLeaveServiceInterface {

    /**
     * Calculates the leaves taken by an employee based on the leave request data and leave statistics.
     * 
     * @param leaves                   The list of employee leave requests.
     * @param leavesProvidedStatistics The job grade-wise leave statistics.
     * @return The leave validation model containing the calculated leaves taken.
     */
    LeaveValidationModel calculateLeavesTaken(List<EmployeeLeaveRequest> leaves, JobGradeWiseLeaves leavesProvidedStatistics);

}
