package controllers;

import java.sql.Timestamp;
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
import models.EmployeeParameter;
import models.input.output.EmployeeOutput;
import models.input.output.EmployeeParameterIO;

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
		EmployeeOutput eout = modelMapper.map(employee, new TypeToken<EmployeeOutput>() {
		}.getType());
		model.addAttribute("employee", eout);
		return "get-employee-details";
	}

	@RequestMapping(value = "/addempl", method = RequestMethod.GET)
	public String addEmpl() {

		return "addempl";
	}

	@RequestMapping(value = "/employeeList", method = RequestMethod.POST)
	public String insertEmployee(@ModelAttribute Employee emps, Model model) {

		emps.setEmplLuuser(301);
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		emps.setEmplLuudate(currentTimestamp);
		emp.insertEmployee(emps);

		List<Employee> employees = emp.getAllEmployees();
		List<EmployeeOutput> eout = modelMapper.map(employees, new TypeToken<List<EmployeeOutput>>() {
		}.getType());
		model.addAttribute("employees", eout);
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
		List<EmployeeOutput> employeeOutputs = modelMapper.map(employees, new TypeToken<List<EmployeeOutput>>() {
		}.getType());
		model.addAttribute("employees", employeeOutputs);
		return "employees";
	}

	@RequestMapping(value = "/updempl", method = RequestMethod.GET)
	public String updEmpl(@RequestParam("id") int emplId, Model model) {
		Employee existingEmployee = emp.getEmployeeById(emplId);
		EmployeeOutput eout = modelMapper.map(existingEmployee, new TypeToken<EmployeeOutput>() {
		}.getType());
		model.addAttribute("updating", eout);
		System.out.println("route");
		return "updempl";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateEmployeeing(@ModelAttribute EmployeeOutput empst, Model model) {
		Employee erm = modelMapper.map(empst, new TypeToken<Employee>() {
		}.getType());
		emp.updateEmployee(erm);
		model.addAttribute("message", "Employee details updated successfully!");
		return "update";
	}

	@RequestMapping("/emplparam")
	public String getEmployeeParameters(@RequestParam("id") Integer employeeId, Model model) {
		List<EmployeeParameter> employeeParameters = emp.getEmployeeParametersById(employeeId);
		List<EmployeeParameterIO> ep = modelMapper.map(employeeParameters, new TypeToken<List<EmployeeParameterIO>>() {
		}.getType());
		model.addAttribute("employeeParameters", ep);
		return "employeeparamaters";
	}

}