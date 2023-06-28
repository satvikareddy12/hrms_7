package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import models.Employee;
import service.EmployeeLoginService;

@Controller
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(LoginController.class);

	EmployeeLoginService empservice;

	@Autowired
	public LoginController(EmployeeLoginService empservice, Employee empauto) {
		this.empservice = empservice;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String employeeLogin(Model model) {

		logger.info("Login Page Requested");
		logger.warn("testing...");
		logger.error("heloo");
		return "login";
	}

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public String enterIntoMenu_employee(@RequestParam("empl_email") String email,
			@RequestParam("empl_password") String password, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		System.out.println("thi9s isemployee side ");

		if (empservice.authenticateUser(email, password)) {

			// Set employee ID in session
			Employee empdetails = empservice.getByEmail(email);
			int employeeId = empdetails.getEmplId();
			session.setAttribute("employeeId", employeeId);
			return "index2"; // Redirect to the dashboard page
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String enterIntoMenu_admin(@RequestParam("admin_email") String email,
			@RequestParam("admin_password") String password, HttpServletRequest request) {
		System.out.println("thi9s isv admin side ");

		HttpSession session = request.getSession(true);
		if (empservice.authenticateUser_admin(email, password)) {

			// Set employee ID in session
			Employee empdetails = empservice.getByEmail(email);
			int adminId = empdetails.getEmplId();
			System.out.println(adminId);

			session.setAttribute("adminId", adminId);

			return "Index_admin"; // Redirect to the dashboard page
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/h", method = RequestMethod.GET)
	public void getAllDetailsEmploye(HttpSession session) {

		if (session.getAttribute("adminId") == null)
			System.out.println("no admin login");
		else {
			System.out.println("admin login");
			int employeeid = (int) session.getAttribute("adminId");
			System.out.println(employeeid);
		}
		if (session.getAttribute("employeeId") == null)
			System.out.println("no employee login");
		else {
			System.out.println("employee login");
			int adminid = (int) session.getAttribute("employeeId");
			System.out.println(adminid);
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<String> logout(HttpSession session) {
		session.invalidate();
		System.out.println("logout");

		return ResponseEntity.ok("success");
	}
}