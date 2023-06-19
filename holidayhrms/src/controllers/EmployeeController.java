package controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.EmployeeDAO;
import models.Employee;
import models.EmployeeOutput;
import models.EmployeeParameter;

@Controller
public class EmployeeController {

	private final EmployeeDAO emp;
	private final ModelMapper modelMapper;

	@Autowired
	public EmployeeController(EmployeeDAO ed, ModelMapper mp) {
		emp = ed;
		modelMapper = mp;

	}

	@RequestMapping("/employees")
	public String showEmployees(Model model) {
		List<Employee> employees = emp.getAllEmployees();
		List<EmployeeOutput> employeeOutputs = modelMapper.map(employees, new TypeToken<List<EmployeeOutput>>() {
		}.getType());
		model.addAttribute("employees", employeeOutputs);
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

	@RequestMapping(value = "/employeeList", method = RequestMethod.POST)
	public String insertEmployee(@ModelAttribute Employee emps, Model model) {
		emp.insertEmployee(emps);
		List<Employee> employees = emp.getAllEmployees();
		model.addAttribute("employees", employees);
		return "employees";
	}

	@RequestMapping(value = "/delempl")
	public String delEmpl(Model model) {

		return "delempl";
	}

	@PostMapping(value = "/employeeListDelete")
	public String updateEmployeeStatus(@RequestParam("emplId") int emplId, Model model) {
		emp.updateEmployeeStatus(emplId, "deleted");
		List<Employee> employees = emp.getAllEmployees();
		model.addAttribute("employees", employees);
		return "employees";
	}

	@RequestMapping(value = "/updempl", method = RequestMethod.GET)
	public String updEmpl(@RequestParam("id") int emplId, Model model) {
		Employee existingEmployee = emp.getEmployeeById(emplId);
		model.addAttribute("updating", existingEmployee);
		System.out.println("route");
		return "updempl";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateEmployeeing(@ModelAttribute Employee empst, Model model) {
		emp.updateEmployee(empst);
		model.addAttribute("message", "Employee details updated successfully!");
		return "update";
	}

	@RequestMapping("/emplparam")
	public String getEmployeeParameters(@RequestParam("id") Integer employeeId, Model model) {
		List<EmployeeParameter> employeeParameters = emp.getEmployeeParametersById(employeeId);
		model.addAttribute("employeeParameters", employeeParameters);
		return "employeeparamaters";
	}

}