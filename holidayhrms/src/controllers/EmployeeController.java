package controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.EmployeeDAO;
import models.Employee;

@Controller
public class EmployeeController {

	private final EmployeeDAO emp;
	private final Employee empr;

	@Autowired
	public EmployeeController(EmployeeDAO ed, Employee es) {
		emp = ed;
		empr = es;
	}

	@RequestMapping("/employees")
	public String showEmployees(Model model) {
		List<Object[]> employees = emp.getAllEmployees();
		model.addAttribute("employees", employees);
		return "employees";
	}

	@RequestMapping("/get-employee-details")
	public String getEmployeeDetails(@RequestParam("id") int employeeId, Model model) {
		Employee employee = emp.getEmployeeById(employeeId);
		model.addAttribute("employee", employee);
		return "get-employee-details";
	}

	@RequestMapping(value = "/addempl", method = RequestMethod.GET)
	public String addEmpl() {

		return "addempl";
	}

	@RequestMapping(value = "/success3", method = RequestMethod.POST)
	public String insertEmployee(@RequestParam("emplId") Integer emplId,
			@RequestParam("emplFirstname") String emplFirstname, @RequestParam("emplLastname") String emplLastname,
			@RequestParam("emplSurname") String emplSurname,
			@RequestParam("emplRmanagerEmplId") Integer emplRmanagerEmplId,
			@RequestParam("emplHrEmplId") Integer emplHrEmplId, @RequestParam("emplJbgrId") String emplJbgrId,
			@RequestParam("emplPhoto") String emplPhoto, @RequestParam("emplJondate") Date emplJondate,
			@RequestParam("emplDob") Date emplDob, @RequestParam("emplDesignation") String emplDesignation,
			@RequestParam("emplOffemail") String emplOffemail, @RequestParam("emplPemail") String emplPemail,
			@RequestParam("emplMobile") long emplMobile, @RequestParam("emplAlemail") String emplAlemail,
			@RequestParam("emplBloodgroup") String emplBloodgroup, @RequestParam("emplGender") char emplGender,
			@RequestParam("emplAddress") String emplAddress, @RequestParam("emplFname") String emplFname,
			@RequestParam("emplLuudate") Date emplLuudate, @RequestParam("emplLuuser") Integer emplLuuser,
			@RequestParam("empl_ctc") Double empl_ctc, @RequestParam("empl_basicsal") Double empl_basicsal,
			@RequestParam("empl_fixedsal") Double empl_fixedsal,
			@RequestParam("empl_variablesal") Double empl_variablesal, @RequestParam("empl_status") String empl_status,
			Model model) {

		empr.setEmplId(emplId);
		empr.setEmplFirstname(emplFirstname);
		empr.setEmplLastname(emplLastname);
		empr.setEmplSurname(emplSurname);
		empr.setEmplRmanagerEmplId(emplRmanagerEmplId);
		empr.setEmplHrEmplId(emplHrEmplId);
		empr.setEmplJbgrId(emplJbgrId);
		empr.setEmplPhoto(emplPhoto);
		empr.setEmplJondate(emplJondate);
		empr.setEmplDob(emplDob);
		empr.setEmplDesignation(emplDesignation);
		empr.setEmplOffemail(emplOffemail);
		empr.setEmplPemail(emplPemail);
		empr.setEmplMobile(emplMobile);
		empr.setEmplAlemail(emplAlemail);
		empr.setEmplBloodgroup(emplBloodgroup);
		empr.setEmplGender(emplGender);
		empr.setEmplAddress(emplAddress);
		empr.setEmplFname(emplFname);
		empr.setEmplLuudate(emplLuudate);
		empr.setEmplLuuser(emplLuuser);
		empr.setEmpl_ctc(empl_ctc);
		empr.setEmpl_basicsal(empl_basicsal);
		empr.setEmpl_fixedsal(empl_fixedsal);
		empr.setEmpl_variablesal(empl_variablesal);
		empr.setEmpl_status(empl_status);

		emp.insertEmployee(empr);
		model.addAttribute("message", "Employee details saved successfully!");
		return "success3";
	}

	@RequestMapping(value = "/delempl")
	public String delEmpl(Model model) {

		return "delempl";
	}

	@PostMapping(value = "/delete")
	public String updateEmployeeStatus(@RequestParam("emplId") int emplId, Model model) {
		emp.updateEmployeeStatus(emplId, "deleted");
		model.addAttribute("message", "Employee deleted!");
		return "delete";
	}

	@RequestMapping(value = "/updempl", method = RequestMethod.GET)
	public String updEmpl(@RequestParam("id") int emplId, Model model) {
		Employee existingEmployee = emp.getEmployeeById(emplId);
		model.addAttribute("updating", existingEmployee);
		System.out.println("route");
		return "updempl";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateEmployeeing(@RequestParam("emplId") Integer emplId,
			@RequestParam("emplFirstname") String emplFirstname, @RequestParam("emplLastname") String emplLastname,
			@RequestParam("emplSurname") String emplSurname,
			@RequestParam("emplRmanagerEmplId") Integer emplRmanagerEmplId,
			@RequestParam("emplHrEmplId") Integer emplHrEmplId, @RequestParam("emplJbgrId") String emplJbgrId,
			@RequestParam("emplPhoto") String emplPhoto, @RequestParam("emplJondate") Date emplJondate,
			@RequestParam("emplDob") Date emplDob, @RequestParam("emplDesignation") String emplDesignation,
			@RequestParam("emplOffemail") String emplOffemail, @RequestParam("emplPemail") String emplPemail,
			@RequestParam("emplMobile") long emplMobile, @RequestParam("emplAlemail") String emplAlemail,
			@RequestParam("emplBloodgroup") String emplBloodgroup, @RequestParam("emplGender") char emplGender,
			@RequestParam("emplAddress") String emplAddress, @RequestParam("emplFname") String emplFname,
			@RequestParam("emplLuudate") Date emplLuudate, @RequestParam("emplLuuser") Integer emplLuuser,
			@RequestParam("empl_ctc") Double empl_ctc, @RequestParam("empl_basicsal") Double empl_basicsal,
			@RequestParam("empl_fixedsal") Double empl_fixedsal,
			@RequestParam("empl_variablesal") Double empl_variablesal, @RequestParam("empl_status") String empl_status,
			Model model) {

		empr.setEmplId(emplId);
		empr.setEmplFirstname(emplFirstname);
		empr.setEmplLastname(emplLastname);
		empr.setEmplSurname(emplSurname);
		empr.setEmplRmanagerEmplId(emplRmanagerEmplId);
		empr.setEmplHrEmplId(emplHrEmplId);
		empr.setEmplJbgrId(emplJbgrId);
		empr.setEmplPhoto(emplPhoto);
		empr.setEmplJondate(emplJondate);
		empr.setEmplDob(emplDob);
		empr.setEmplDesignation(emplDesignation);
		empr.setEmplOffemail(emplOffemail);
		empr.setEmplPemail(emplPemail);
		empr.setEmplMobile(emplMobile);
		empr.setEmplAlemail(emplAlemail);
		empr.setEmplBloodgroup(emplBloodgroup);
		empr.setEmplGender(emplGender);
		empr.setEmplAddress(emplAddress);
		empr.setEmplFname(emplFname);
		empr.setEmplLuudate(emplLuudate);
		empr.setEmplLuuser(emplLuuser);
		empr.setEmpl_ctc(empl_ctc);
		empr.setEmpl_basicsal(empl_basicsal);
		empr.setEmpl_fixedsal(empl_fixedsal);
		empr.setEmpl_variablesal(empl_variablesal);
		empr.setEmpl_status(empl_status);

		emp.updateEmployee(empr);
		model.addAttribute("message", "Employee details updated successfully!");
		return "update";
	}

}