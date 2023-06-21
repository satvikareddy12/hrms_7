package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import models.Employee;
import service.EmpService;

@Controller
public class EmpProfileController {

	EmpService empserv;
	String global;
	Employee empauto;

	@Autowired
	public EmpProfileController(EmpService empserv, Employee empauto) {
		this.empserv = empserv;
		this.empauto = empauto;
	}

	@RequestMapping(value = "/toprofile", method = RequestMethod.GET)
	public String getAllDetailsEmploye(Model model) {

		System.out.println("this is sp_orm controller getting employes method ");
		Employee empdetails = empserv.getByEmail("akshay@pennant.com");

		// set it to the model
		model.addAttribute("empdet", empdetails);

		// call the view
		return "profile";
	}

	@Transactional
	@RequestMapping(value = "/update_address", method = RequestMethod.POST)
	public String SaveAdressIntoDataBase(@RequestParam("emplId") String empl_id,
			@RequestParam("emplAddress") String empl_address) {

		System.out.println(empl_id);
		empauto = empserv.findEmpByid(Integer.parseInt(empl_id));
		empauto.setEmplAddress(empl_address);
		return "front";

	}
}