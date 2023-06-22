package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.EmployeeDAO;
import models.Employee;
import models.input.output.EmployeePayRollOutputModel;
import service.PayRollService;

@Controller
public class PayRoll {

	private PayRollService payRollservice;
	private EmployeeDAO ed;
	private EmployeePayRollOutputModel payRollOutput;

	@Autowired
	PayRoll(PayRollService payRollservice, EmployeeDAO ed, EmployeePayRollOutputModel payRollOutput) {
		this.payRollservice = payRollservice;
		this.ed = ed;
		this.payRollOutput = payRollOutput;
	}

	@RequestMapping(value = "/getpayslip", method = RequestMethod.POST)
	public String getPayroll(@RequestParam("empl_id") int id, Model model) {

		Employee employee = ed.getEmployeeById(id);
		double ctc = employee.getEmpl_ctc();
		double basicsal = employee.getEmpl_basicsal();
		double fixedsal = employee.getEmpl_fixedsal();
		double variablesal = employee.getEmpl_variablesal();

		payRollservice.forDA(fixedsal / 12);
		payRollservice.forTA(fixedsal / 12);
		payRollservice.forHRA(fixedsal / 12);
		payRollservice.additions(variablesal / 12);
		payRollservice.grossPay(basicsal / 12, fixedsal / 12, variablesal / 12);

		return "payslip";
	}

	@RequestMapping(value = "/getpayroll", method = RequestMethod.POST)
	public String getPayslip(@RequestParam("empl_id") int id, Model model) {

		// for fetching details of an employee based on id
		Employee employee = ed.getEmployeeById(id);

		if (employee != null) {
			model.addAttribute("employee", employee);
		} else {
			model.addAttribute("error", "No employee found with the provided ID.");
		}
		return "payroll";
	}

	@RequestMapping(value = "/getemppay", method = RequestMethod.GET)
	public String getPayslip2(Model model) {
		return "payrollemp";
	}

	@RequestMapping(value = "/EmployeePaySlip", method = RequestMethod.GET)
	public String getPayslip3(Model model) {
		return "EmployeePaySlip";
	}

}
