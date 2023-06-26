package service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import models.EmployeeLeaveRequest;
import models.JobGradeWiseLeaves;
import models.LeaveValidationModel;

public class EmployeeLeaveService {

	public EmployeeLeaveService() {
	}

	@Autowired
	public EmployeeLeaveService(LeaveValidationModel leaveValidation) {
		this.leaveValidation = leaveValidation;
	}

	private LeaveValidationModel leaveValidation;

	public static long calculateLeavesTakenBetwwenDates(LocalDate startDate, LocalDate endDate) {
		System.out.println(startDate + "  / " + endDate);
		return ChronoUnit.DAYS.between(startDate, endDate) + 1;
	}

	public LeaveValidationModel calculateLeavesTaken(List<EmployeeLeaveRequest> leaves,
			JobGradeWiseLeaves leavesProvidedStatistics) {

		System.out.println(leavesProvidedStatistics);

		if (leavesProvidedStatistics != null) {

			long totalNoOfLeaves = 0;
			long sickLeaves = 0;
			long casualLeaves = 0;
			long otherLeaves = 0;

			long pendingTotalNoOfLeaves = 0;
			long pendingSickLeaves = 0;
			long pendingCasualLeaves = 0;
			long pendingOtherLeaves = 0;

			for (EmployeeLeaveRequest leave : leaves) {

				if (leave.getApprovedLeaveStartDate() == null || leave.getApprovedLeaveEndDate() == null) {

					long leavesCount = EmployeeLeaveService.calculateLeavesTakenBetwwenDates(leave.getLeaveStartDate(),
							leave.getLeaveEndDate());
					pendingTotalNoOfLeaves += leavesCount;
					if (leave.getLeaveType().trim().equals("SICK")) {
						pendingSickLeaves += leavesCount;
					} else if (leave.getLeaveType().trim().equals("CASL")) {
						pendingCasualLeaves += leavesCount;
					} else if (leave.getLeaveType().trim().equals("OTHR")) {
						pendingOtherLeaves += leavesCount;
					}

				} else {
					long leavesCount = EmployeeLeaveService.calculateLeavesTakenBetwwenDates(
							leave.getApprovedLeaveStartDate(), leave.getApprovedLeaveEndDate());
					totalNoOfLeaves += leavesCount;
					if (leave.getLeaveType().trim().equals("SICK")) {
						sickLeaves += leavesCount;
					} else if (leave.getLeaveType().trim().equals("CASL")) {
						casualLeaves += leavesCount;
					} else if (leave.getLeaveType().trim().equals("OTHR")) {
						otherLeaves += leavesCount;
					}
				}
			}

			leaveValidation.setTakenCasualLeaves(casualLeaves);
			leaveValidation.setTakenOtherLeaves(otherLeaves);
			leaveValidation.setTakenSickLeaves(sickLeaves);
			leaveValidation.setTakenTotalLeaves(totalNoOfLeaves);

			leaveValidation.setPendingCasualLeaves(pendingCasualLeaves);
			leaveValidation.setPendingOtherLeaves(pendingOtherLeaves);
			leaveValidation.setPendingSickLeaves(pendingSickLeaves);
			leaveValidation.setPendingTotalNoOfLeaves(pendingTotalNoOfLeaves);

			leaveValidation.setAllowedCasualLeaves(leavesProvidedStatistics.getCasualLeavesPerYear());
			leaveValidation.setAllowedOtherLeaves(leavesProvidedStatistics.getOtherLeavesPerYear());
			leaveValidation.setAllowedSickLeaves(leavesProvidedStatistics.getSickLeavesPerYear());
			leaveValidation.setAllowedTotalLeaves(leavesProvidedStatistics.getTotalLeavesPerYear());

		}

		return leaveValidation;
	}

}
