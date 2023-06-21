package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.EmployeeDAO;
import models.Employee;
import models.input.output.EmployeePayRollInputModel;
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

	@RequestMapping(value = "/getpayslip")
	public String getPayroll(@ModelAttribute("employee") EmployeePayRollInputModel employee, Model model) {
		// Retrieve employee data from the input model
		int id = employee.getId();
		System.out.println(id);
		String name = employee.getName();
		String dest = employee.getDesignation();
		double basicPay = employee.getBasicPay();
		double fixedPay = employee.getFixedPay();
		double variablePay = employee.getVariablePay();
		double gratuity = employee.getGratuity();
		double healthInsurance = employee.getHealthInsurance();
		double pf = employee.getPf();
		int earnedLeave = employee.getEarnedLeave();
		int unpaidLeave = employee.getUnpaidLeave();

		// Calculate payroll details
		double gp = payRollservice.grossPay(basicPay, fixedPay, variablePay, earnedLeave);
		double deduction = payRollservice.deductions(basicPay, healthInsurance, gratuity, pf, unpaidLeave);
		double total = payRollservice.totalsal(basicPay, fixedPay, variablePay, healthInsurance, gratuity, pf,
				earnedLeave, unpaidLeave);
		double hra = payRollservice.forHRA(fixedPay);
		double ta = payRollservice.forspecialAllowance(fixedPay);

		// Set the payroll details in the output model

		payRollOutput.setId(id);
		payRollOutput.setName(name);
		payRollOutput.setDesignation(dest);
		payRollOutput.setBasicPay(basicPay);
		payRollOutput.setFixedPay(fixedPay);
		payRollOutput.setVariablePay(variablePay);
		payRollOutput.setGratuity(gratuity);
		payRollOutput.setHealthInsurance(healthInsurance);
		payRollOutput.setPf(pf);
		payRollOutput.setEarnedLeave(earnedLeave);
		payRollOutput.setUnpaidLeave(unpaidLeave);
		payRollOutput.setGp(gp);
		payRollOutput.setDeduction(deduction);
		payRollOutput.setTotal(total);
		payRollOutput.setHra(hra);
		payRollOutput.setTa(ta);

		// Add the output model to the model attribute
		model.addAttribute("pay", payRollOutput);

		double ctc = payRollservice.calCTC();
		System.out.println(ctc);
		return "payslip";
	}

	@RequestMapping(value = "/getpayroll")
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

	@RequestMapping(value = "/getemppay")
	public String getPayslip2(Model model) {
		return "payrollemp";
	}

}
