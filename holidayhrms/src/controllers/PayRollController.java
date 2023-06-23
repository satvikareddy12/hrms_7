package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.EmployeeDAO;
import DAO.PayRollDAO;
import models.Employee;
import models.EmployeePayslip;
import models.input.output.EmployeePayRollInputModel;
import models.input.output.EmployeePayRollOutputModel;
import service.PayRollService;
import service.PaySlipMail;

@Controller
public class PayRollController {

	private PayRollService payRollservice;
	private EmployeeDAO ed;
	private EmployeePayRollOutputModel payRollOutput;
	private EmployeePayRollInputModel payRollInput;
	private EmployeePayslip empPaySlip;
	private PayRollDAO payrollDAO;
	private final ModelMapper modelMapper;

	@Autowired
	PayRollController(PayRollService payRollservice, EmployeeDAO ed, EmployeePayRollOutputModel payRollOutput,
			EmployeePayRollInputModel payRollInput, EmployeePayslip empPaySlip, PayRollDAO payrollDAO, ModelMapper mp) {
		this.payRollservice = payRollservice;
		this.ed = ed;
		this.payRollOutput = payRollOutput;
		this.payRollInput = payRollInput;
		this.empPaySlip = empPaySlip;
		this.payrollDAO = payrollDAO;
		modelMapper = mp;
	}

	@RequestMapping(value = "/getpayslip", method = RequestMethod.POST)
	public String getPayroll(@RequestParam("empl_id") int id, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		Employee employee = ed.getEmployeeById(id);
		String firstName = employee.getEmplFirstname();
		String lastName = employee.getEmplLastname();
		String designation = employee.getEmplDesignation();
		double ctc = employee.getEmpl_ctc();
		double basicsal = employee.getEmpl_basicsal();
		double fixedsal = employee.getEmpl_fixedsal();
		double variablesal = employee.getEmpl_variablesal();

		// for setting values into input model
		payRollInput.setId(id);
		payRollInput.setmonthYear("02-2023");
		payRollInput.setBasicPay(payRollservice.basicPay(basicsal / 12));
		payRollInput.setFixedPay(fixedsal / 12);
		payRollInput.setVariablePay(variablesal / 12);
		payRollInput.setGratuity(payRollservice.gratuity((ctc * 0.1) - 200));
		payRollInput.setPf(payRollservice.pf((ctc * 0.1) - 200));
		payRollInput.setHra(payRollservice.forHRA(fixedsal / 12));
		payRollInput.setDa(payRollservice.forDA(fixedsal / 12));
		payRollInput.setTa(payRollservice.forTA(fixedsal / 12));
		payRollInput.setAdditions(payRollservice.additions(variablesal / 12));
		payRollInput.setDeductions(payRollservice.deductions());
		payRollInput.setPtax(200);
		payRollInput.setEsi(payRollservice.esi((ctc * 0.1) - 200));
		payRollInput.setTds(payRollservice.calTax(ctc));

		// for setting into entity model
		empPaySlip.setEmployeeId(payRollInput.getId());
		empPaySlip.setMonthYear(payRollInput.getmonthYear());
		empPaySlip.setBasicSalary(payRollInput.getBasicPay());
		empPaySlip.setDa(payRollInput.getDa());
		empPaySlip.setAdditions(payRollInput.getAdditions());
		empPaySlip.setDeductions(payRollInput.getDeductions());
		empPaySlip.setEsi(payRollInput.getEsi());
		empPaySlip.setGratuity(payRollInput.getGratuity());
		empPaySlip.setHra(payRollInput.getHra());
		empPaySlip.setPf(payRollInput.getPf());
		empPaySlip.setPtax(payRollInput.getPtax());
		empPaySlip.setTa(payRollInput.getTa());
		empPaySlip.setTds(payRollInput.getTds());

		payrollDAO.insertEmployeePayslip(empPaySlip);

		// for setting into output model
		payRollOutput.setId(id);
		payRollOutput.setMonthYear(empPaySlip.getMonthYear());
		payRollOutput.setFirstName(firstName);
		payRollOutput.setLastName(lastName);
		payRollOutput.setDesignation(designation);
		payRollOutput.setBasicPay(empPaySlip.getBasicSalary());
		payRollOutput.setDa(empPaySlip.getDa());
		payRollOutput.setAdditions(empPaySlip.getAdditions());
		payRollOutput.setDeduction(empPaySlip.getDeductions());
		payRollOutput.setEsi(empPaySlip.getEsi());
		payRollOutput.setGratuity(empPaySlip.getGratuity());
		payRollOutput.setHra(empPaySlip.getHra());
		payRollOutput.setPf(empPaySlip.getPf());
		payRollOutput.setPtax(empPaySlip.getPtax());
		payRollOutput.setTa(empPaySlip.getTa());
		payRollOutput.setTds(empPaySlip.getTds());
		payRollOutput.setTotal(payRollservice.total());
		payRollOutput.setNetpay(payRollservice.netPay());

		model.addAttribute("pay", payRollOutput);

		try {
			PaySlipMail.sendEmail(request, response, payRollOutput);
		} catch (Exception e) {

			e.printStackTrace();
		}

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

	@RequestMapping(value = "/EmployeeSidePaySlip", method = RequestMethod.GET)
	public String getEmployeePayslip(Model model) {

		Employee employee = ed.getEmployeeById(102);
		EmployeePayslip eps = payrollDAO.getEmployeePayslipsByEmployeeId(102);
		String firstName = employee.getEmplFirstname();
		String lastName = employee.getEmplLastname();
		String designation = employee.getEmplDesignation();
		payRollOutput.setId(102);
		payRollOutput.setMonthYear(eps.getMonthYear());
		payRollOutput.setFirstName(firstName);
		payRollOutput.setLastName(lastName);
		payRollOutput.setDesignation(designation);
		payRollOutput.setBasicPay(eps.getBasicSalary());
		payRollOutput.setDa(eps.getDa());
		payRollOutput.setAdditions(eps.getAdditions());
		payRollOutput.setDeduction(eps.getDeductions());
		payRollOutput.setEsi(eps.getEsi());
		payRollOutput.setGratuity(eps.getGratuity());
		payRollOutput.setHra(eps.getHra());
		payRollOutput.setPf(eps.getPf());
		payRollOutput.setPtax(eps.getPtax());
		payRollOutput.setTa(eps.getTa());
		payRollOutput.setTds(eps.getTds());

		payRollOutput.setTotal(eps.getBasicSalary() + eps.getHra() + eps.getDa() + eps.getTa() + eps.getAdditions());
		payRollOutput.setNetpay((eps.getBasicSalary() + eps.getHra() + eps.getDa() + eps.getTa() + eps.getAdditions())
				- eps.getDeductions());

		model.addAttribute("output", payRollOutput);

		return "EmployeeSidePaySlip";
	}

}
