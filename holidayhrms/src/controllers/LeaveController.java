package controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import DAO_Interfaces.EmployeeDAO;
import DAO_Interfaces.EmployeeLeaveRequestDAO;
import models.ApprovedLeaveModel;
import models.Employee;
import models.EmployeeLeaveInputModel;
import models.EmployeeLeaveModel;
import models.EmployeeLeaveRequest;
import models.EmployeeLeaveRequestId;
import models.JobGradeWiseLeaves;
import models.LeaveValidationModel;
import models.input.output.JobGradeLeavesOutModel;
import service.EmployeeLeaveService;
import service_interfaces.EmployeeLeaveServiceInterface;

@Controller
public class LeaveController {

	@Autowired
	public LeaveController(EmployeeLeaveRequest leaveRequest, EmployeeLeaveRequestId leaveRequestId, Gson gson,
			EmployeeLeaveService employeeService) {
		this.leaveRequest = leaveRequest;
		this.leaveRequestId = leaveRequestId;
		this.gson = gson;
		this.employeeService = employeeService;
	}

	private EmployeeLeaveRequest leaveRequest;
	private EmployeeLeaveRequestId leaveRequestId;
	private Gson gson;
	private EmployeeLeaveServiceInterface employeeService;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private EmployeeLeaveRequestDAO leaveRequestDAO;

	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	private ApplicationContext context;

	// to get the leave form
	@RequestMapping(value = "/leaveform", method = RequestMethod.GET)
	public String leaverequest(HttpSession session, Model model) {

		int id = (int) session.getAttribute("employeeId");
		// Retrieves the employee ID from the session

		Employee employee = employeeDAO.getEmployee(id);
		// Retrieves the employee information from the DAO using the employee ID

		JobGradeWiseLeaves leavesProvidedStatistics = leaveRequestDAO
				.getJobGradeWiseLeaves(employee.getEmplJbgrId().trim());
		// Retrieves the leaves provided statistics based on the job grade ID of the employee from the DAO

		List<EmployeeLeaveRequest> leaves = leaveRequestDAO
				.getApprovedAndPendingEmployeeAndLeaveRequestData(employee.getEmplId(), Year.now().getValue());
		// Retrieves the approved and pending leave request data for the employee ID and the current year from the DAO

		LeaveValidationModel validation = employeeService.calculateLeavesTaken(leaves, leavesProvidedStatistics);
		// Calculates the leaves taken based on the retrieved leave request data and leaves provided statistics using
		// the service method

		model.addAttribute("validationData", validation);
		// Adds the validation data as an attribute named "validationData" to the model

		return "leaveform";
	}

	// to submit leave
	@Transactional
	@RequestMapping(value = "/submitleave", method = RequestMethod.POST)
	public ResponseEntity<String> submitLeaveRequest(@ModelAttribute EmployeeLeaveInputModel employeeLeaveInputModel) {

		try {

			leaveRequest.setLeaveStartDate(
					LocalDate.parse(employeeLeaveInputModel.getLeaveStartDate(), DateTimeFormatter.ISO_DATE));
			leaveRequest.setLeaveEndDate(
					LocalDate.parse(employeeLeaveInputModel.getLeaveEndDate(), DateTimeFormatter.ISO_DATE));
			leaveRequest.setLeaveType(employeeLeaveInputModel.getLeaveType());
			leaveRequest.setReason(employeeLeaveInputModel.getReason());
			leaveRequest.setRequestDateTime(LocalDateTime.now());

			leaveRequestId.setEmployeeId(employeeLeaveInputModel.getEmployeeId());

			int nextLeaveRequestIndex = leaveRequestDAO
					.getNextLeaveRequestIndex(employeeLeaveInputModel.getEmployeeId());
			leaveRequestId.setLeaveRequestIndex(nextLeaveRequestIndex);

			leaveRequest.setLeaveRequestId(leaveRequestId);

			leaveRequestDAO.saveEmployeeLeaveRequest(leaveRequest);

		} catch (Exception e) {
			System.out.println(e);
			String errorMessage = "Internal Server Error";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok("Success");
	}

	// to get leave requests at admin side
	@RequestMapping(value = "/leaveRequests", method = RequestMethod.GET)
	public String leaveRequests(HttpSession session, Model model) {

		int id = (int) session.getAttribute("adminId");
		List<Employee> employees = employeeDAO.getEmployeesByHRAndManager(id);
		List<EmployeeLeaveModel> outputmodel = new ArrayList<>();
		for (Employee employee : employees) {
			List<EmployeeLeaveRequest> leaves = leaveRequestDAO.getEmployeeAndLeaveRequestData(employee.getEmplId());
			for (EmployeeLeaveRequest leave : leaves) {
				EmployeeLeaveModel leavemodel = context.getBean(EmployeeLeaveModel.class);
				leavemodel.setEmpId(employee.getEmplId());
				leavemodel.setName(employee.getEmplFirstname() + employee.getEmplLastname());
				leavemodel.setLeaveRequestIndex(leave.getLeaveRequestId().getLeaveRequestIndex());
				leavemodel.setLeaveType(leave.getLeaveType());
				leavemodel.setLeaveStartDate(leave.getLeaveStartDate());
				leavemodel.setLeaveEndDate(leave.getLeaveEndDate());
				leavemodel.setReason(leave.getReason());

				outputmodel.add(leavemodel);
			}

		}

		model.addAttribute("data", outputmodel);

		return "AdminLeaveRequests";

	}

	// to reject leave
	@RequestMapping(value = "/rejectLeave", method = RequestMethod.POST)
	@Transactional
	public ResponseEntity<String> rejectLreave(@ModelAttribute EmployeeLeaveModel employeeLeaveModel) {
		try {
			System.out.println(employeeLeaveModel.getEmpId());
			System.out.println(employeeLeaveModel.getLeaveRequestIndex());
			System.out.println("rejecting...");

			leaveRequestId.setEmployeeId(employeeLeaveModel.getEmpId());
			leaveRequestId.setLeaveRequestIndex(employeeLeaveModel.getLeaveRequestIndex());

			EmployeeLeaveRequest employeeLeaveRequest = leaveRequestDAO.getEmployeeLeaveRequest(leaveRequestId);

			if (employeeLeaveRequest != null) {
				employeeLeaveRequest.setApprovedBy(-1);
			}

		} catch (Exception e) {
			System.out.println(e);
			String errorMessage = "Internal Server Error";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok("successfully status updated");
	}

	// to accept leave
	@RequestMapping(value = "/acceptLeave", method = RequestMethod.POST)
	@Transactional
	public ResponseEntity<String> acceptLeave(@ModelAttribute EmployeeLeaveInputModel employeeLeaveInputModel,
			HttpSession session) {
		try {
			System.out.println(employeeLeaveInputModel.getEmployeeId());
			System.out.println(employeeLeaveInputModel.getLeaveRequestIndex());
			System.out.println("rejecting...");

			leaveRequestId.setEmployeeId(employeeLeaveInputModel.getEmployeeId());
			leaveRequestId.setLeaveRequestIndex(employeeLeaveInputModel.getLeaveRequestIndex());

			EmployeeLeaveRequest employeeLeaveRequest = leaveRequestDAO.getEmployeeLeaveRequest(leaveRequestId);

			int adminId = (int) session.getAttribute("adminId");

			if (employeeLeaveRequest != null) {
				employeeLeaveRequest.setApprovedBy(adminId);
				employeeLeaveRequest.setApprovedLeaveEndDate(
						LocalDate.parse(employeeLeaveInputModel.getLeaveEndDate(), DateTimeFormatter.ISO_DATE));
				employeeLeaveRequest.setApprovedLeaveStartDate(
						LocalDate.parse(employeeLeaveInputModel.getLeaveStartDate(), DateTimeFormatter.ISO_DATE));
				employeeLeaveRequest.setApprovedRemarks(employeeLeaveInputModel.getRemarks());
			}

		} catch (Exception e) {
			System.out.println(e);
			String errorMessage = "Internal Server Error";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok("successfully status updated");
	}

	// to get admin approved leaves
	@RequestMapping(value = "/AdminapprovedLeaves", method = RequestMethod.GET)
	public String adminApprovedLeaves(Model model, HttpSession session) {

		int id = (int) session.getAttribute("adminId");
		List<ApprovedLeaveModel> approvedOutModel = new ArrayList<>();
		List<EmployeeLeaveRequest> approvedLeaves = leaveRequestDAO.getApprovedEmployeeAndLeaveRequestData(id);
		for (EmployeeLeaveRequest leave : approvedLeaves) {
			Employee employee = employeeDAO.getEmployee(leave.getLeaveRequestId().getEmployeeId());
			ApprovedLeaveModel approvedLeaveModel = context.getBean(ApprovedLeaveModel.class);
			approvedLeaveModel.setEmployeeId(employee.getEmplId());
			approvedLeaveModel.setEmployeeName(employee.getEmplFirstname() + " " + employee.getEmplLastname());
			approvedLeaveModel.setApprovedStartDate(leave.getApprovedLeaveStartDate());
			approvedLeaveModel.setApprovedEndDate(leave.getApprovedLeaveEndDate());

			approvedOutModel.add(approvedLeaveModel);
		}

		model.addAttribute("approvedLeaves", approvedOutModel);

		return "AdminApprovedLeaves";
	}

	// to get employee leave history
	@RequestMapping(value = "/geEmployeeLeaves", method = RequestMethod.GET)
	public String getEmployeeLeavesHistory(Model model, HttpSession session) {

		int id = (int) session.getAttribute("employeeId");
		List<EmployeeLeaveModel> history = new ArrayList<>();
		List<EmployeeLeaveRequest> employeeLeavesData = leaveRequestDAO.getLeaveRequestHistory(id);
		for (EmployeeLeaveRequest leave : employeeLeavesData) {
			EmployeeLeaveModel leavemodel = context.getBean(EmployeeLeaveModel.class);
			leavemodel.setLeaveRequestIndex(leave.getLeaveRequestId().getLeaveRequestIndex());
			leavemodel.setLeaveRequestDate(leave.getRequestDateTime());
			leavemodel.setLeaveStartDate(leave.getApprovedLeaveStartDate());
			leavemodel.setLeaveEndDate(leave.getApprovedLeaveEndDate());
			leavemodel.setLeaveType(leave.getLeaveType());
			leavemodel.setReason(leave.getReason());
			leavemodel.setStatus(leave.getApprovedBy());
			history.add(leavemodel);

		}

		model.addAttribute("leavehistory", history);
		return "employeeLeaveHistory";
	}

	// get jobgradewise leaves

	@RequestMapping(value = "/getJobGradeWiseLeaves", method = RequestMethod.GET)
	public String getJobGradeWiseLeaves(Model model) {
		List<JobGradeWiseLeaves> jobGradeLeaves = leaveRequestDAO.getJobGradeWiseLeaves();
		List<JobGradeLeavesOutModel> result = new ArrayList<>();
		for (JobGradeWiseLeaves leaves : jobGradeLeaves) {
			System.out.println(leaves);
			JobGradeLeavesOutModel leavedata = context.getBean(JobGradeLeavesOutModel.class);
			leavedata.setJobGradeId(leaves.getJbgrId());
			leavedata.setTotalLeaves(leaves.getTotalLeavesPerYear());
			leavedata.setSickLeaves(leaves.getSickLeavesPerYear());
			leavedata.setCasualLeaves(leaves.getCasualLeavesPerYear());
			leavedata.setOtherLeaves(leaves.getOtherLeavesPerYear());

			result.add(leavedata);
		}

		model.addAttribute("jobgradeleaves", result);

		return "jobGradeWiseLeaves";
	}

	// get leave statistics for dashboard
	@RequestMapping(value = "/getLeaveStatistics", method = RequestMethod.GET)
	public ResponseEntity<String> getLeaveStatistics(HttpSession session) {
		int id = (int) session.getAttribute("employeeId");
		Employee employee = employeeDAO.getEmployee(id);

		System.out.println(employee.getEmplId());
		System.out.println(employee.getEmplJbgrId());

		JobGradeWiseLeaves leavesProvidedStatistics = leaveRequestDAO
				.getJobGradeWiseLeaves(employee.getEmplJbgrId().trim());
		System.out.println(leavesProvidedStatistics);
		List<EmployeeLeaveRequest> leaves = leaveRequestDAO.getApprovedLeaveRequests(employee.getEmplId(),
				Year.now().getValue());
		LeaveValidationModel validation = employeeService.calculateLeavesTaken(leaves, leavesProvidedStatistics);

		return ResponseEntity.ok(gson.toJson(validation));
	}

}