package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.PayRollDao;

@Controller
public class PayRoll {

	PayRollDao pd;

	@Autowired
	PayRoll(PayRollDao prd) {
		pd = prd;
	}

	// hello jii
	@RequestMapping(value = "/getpayslip")
	public String getPayroll(@RequestParam("employee-id") int id, @RequestParam("employee-name") String name,
			@RequestParam("designation") String dest, @RequestParam("basic-pay") double basicPay,
			@RequestParam("hra") double hra, @RequestParam("ta") double ta,
			@RequestParam("variable-pay") double variablePay, @RequestParam("gratuity") double gratuity,
			@RequestParam("health-insurance") double healthInsurance, @RequestParam("pf") double pf,
			@RequestParam("earned-leave") int earnedLeave, @RequestParam("unpaid-leaves") int unpaidLeave,
			Model model) {
		System.out.println(id);
		System.out.println(name);
		System.out.println(dest);
		System.out.println(basicPay);
		System.out.println(hra);
		System.out.println(ta);
		System.out.println(variablePay);
		System.out.println(pf);
		System.out.println(healthInsurance);
		System.out.println(gratuity);

		double gp = pd.grossPay(basicPay, hra, ta, variablePay, earnedLeave);
		double deduction = pd.deductions(basicPay, healthInsurance, gratuity, pf, unpaidLeave);
		double total = pd.totalsal(basicPay, hra, ta, variablePay, healthInsurance, gratuity, pf, earnedLeave,
				unpaidLeave);

		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("dest", dest);
		model.addAttribute("basicPay", basicPay);
		model.addAttribute("hra", hra);
		model.addAttribute("ta", ta);
		model.addAttribute("variablePay", variablePay);
		model.addAttribute("pf", pf);
		model.addAttribute("healthInsurance", healthInsurance);
		model.addAttribute("gratuity", gratuity);
		model.addAttribute("gp", gp);
		model.addAttribute("deduction", deduction);
		model.addAttribute("total", total);
		model.addAttribute("earnedLeave", earnedLeave);
		model.addAttribute("unpaidLeave", unpaidLeave);

		return "payslip";
	}

	@RequestMapping(value = "/getpayroll")
	public String getPayslip(Model model) {
		return "payroll";
	}

}
