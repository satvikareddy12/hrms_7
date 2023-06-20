package controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
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

import DAO.EmployeeDAO;
import DAO.EmployeeLeaveRequestDAO;
import models.ApprovedLeaveModel;
import models.Employee;
import models.EmployeeLeaveInputModel;
import models.EmployeeLeaveModel;
import models.EmployeeLeaveRequest;
import models.EmployeeLeaveRequestId;
import models.JobGradeWiseLeaves;
import models.LeaveValidationModel;
import service.EmployeeLeaveService;

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
	private EmployeeLeaveService employeeService;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private EmployeeLeaveRequestDAO leaveRequestDAO;

	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	private ApplicationContext context;

	@RequestMapping(value = "/leaveform", method = RequestMethod.GET)
	public String leaverequest(HttpServletRequest request, Model model) {
		// should have the employee id in session ( pending )

		Employee employee = employeeDAO.getEmployee(1);

		System.out.println(employee.getEmplId());
		System.out.println(employee.getEmplJbgrId());

		JobGradeWiseLeaves leavesProvidedStatistics = leaveRequestDAO
				.getJobGradeWiseLeaves(employee.getEmplJbgrId().trim());
		System.out.println(leavesProvidedStatistics);
		List<EmployeeLeaveRequest> leaves = leaveRequestDAO
				.getApprovedAndPendingEmployeeAndLeaveRequestData(employee.getEmplId());
		LeaveValidationModel validation = employeeService.calculateLeavesTaken(leaves, leavesProvidedStatistics);
		model.addAttribute("validationData", validation);
		return "leaveform";
	}

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

	@RequestMapping(value = "/leaveRequests", method = RequestMethod.GET)
	public String leaveRequests(HttpSession session, Model model) {

		// need to get the Admin Id
		List<Employee> employees = employeeDAO.getEmployeesByHRAndManager(123);
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

			// should take care of it
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

	@RequestMapping(value = "/AdminapprovedLeaves", method = RequestMethod.GET)
	public String adminApprovedLeaves(Model model) {
		// should get the admin id (pending)
		List<ApprovedLeaveModel> approvedOutModel = new ArrayList();
		List<EmployeeLeaveRequest> approvedLeaves = leaveRequestDAO.getApprovedEmployeeAndLeaveRequestData(123);
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

}
