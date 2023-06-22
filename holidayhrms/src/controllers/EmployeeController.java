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
import service.EmpService;

@Controller
public class EmployeeController {

	private final EmployeeDAO emp;
	private final ModelMapper modelMapper;
	private final EmpService empserv;

	@Autowired
	public EmployeeController(EmployeeDAO ed, ModelMapper mp, EmpService empserv) {
		emp = ed;
		modelMapper = mp;
		this.empserv = empserv;

	}

	// To display employees list
	@RequestMapping("/employees")
	public String showEmployeeList(Model model) {
		List<Employee> employees = emp.getAllEmployees();
		List<EmployeeOutput> employeeOutputs = modelMapper.map(employees, new TypeToken<List<EmployeeOutput>>() {
		}.getType());
		model.addAttribute("employees", employeeOutputs);
		return "employees";
	}

	// To display the details of a particular employee
	@RequestMapping("/get-employee-details")
	public String getEmployeeDetails(@RequestParam("id") int employeeId, Model model) {
		Employee employee = emp.getEmployeeById(employeeId);
		EmployeeOutput eout = modelMapper.map(employee, new TypeToken<EmployeeOutput>() {
		}.getType());
		model.addAttribute("employee", eout);
		return "get-employee-details";
	}

	// Form to insert employee details
	@RequestMapping(value = "/addempl", method = RequestMethod.GET)
	public String addEmployee() {

		return "addempl";
	}

	// To display list of employees after insertion of a new employee
	@RequestMapping(value = "/employeeList", method = RequestMethod.POST)
	public String insertedEmployeeList(@ModelAttribute Employee emps, Model model) {

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

	// To delete an employee(Soft delete)
	@RequestMapping(value = "/delempl")
	public String deleteEmployee(Model model) {

		return "delempl";
	}

	// To display list of employees after insertion of an employee
	@PostMapping(value = "/employeeListDelete")
	public String deletedEmployeeList(@RequestParam("emplId") int emplId, Model model) {
		emp.updateEmployeeStatus(emplId, "deleted");
		List<Employee> employees = emp.getAllEmployees();
		List<EmployeeOutput> employeeOutputs = modelMapper.map(employees, new TypeToken<List<EmployeeOutput>>() {
		}.getType());
		model.addAttribute("employees", employeeOutputs);
		return "employees";
	}

	// Form to update the details of an employee
	@RequestMapping(value = "/updempl", method = RequestMethod.GET)
	public String updateEmployeeDetails(@RequestParam("id") int emplId, Model model) {
		Employee existingEmployee = emp.getEmployeeById(emplId);
		EmployeeOutput eout = modelMapper.map(existingEmployee, new TypeToken<EmployeeOutput>() {
		}.getType());
		model.addAttribute("updating", eout);
		System.out.println("route");
		return "updempl";
	}

	// To show the status of the employee update
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateStatus(@ModelAttribute EmployeeOutput empst, Model model) {
		Employee erm = modelMapper.map(empst, new TypeToken<Employee>() {
		}.getType());
		emp.updateEmployee(erm);
		model.addAttribute("message", "Employee details updated successfully!");
		return "update";
	}

	// To view the parameters of an employee
	@RequestMapping("/emplparam")
	public String getEmployeeParameters(@RequestParam("id") Integer employeeId, Model model) {
		List<EmployeeParameter> employeeParameters = emp.getEmployeeParametersById(employeeId);
		List<EmployeeParameterIO> ep = modelMapper.map(employeeParameters, new TypeToken<List<EmployeeParameterIO>>() {
		}.getType());
		model.addAttribute("employeeParameters", ep);
		return "employeeparamaters";
	}

	// To display the employee profile
	@RequestMapping(value = "/toprofile", method = RequestMethod.GET)
	public String showEmployeeProfile(Model model) {

		System.out.println("this is sp_orm controller getting employes method ");
		Employee empdetails = empserv.getByEmail("akshay@pennant.com");
		EmployeeOutput eout = modelMapper.map(empdetails, new TypeToken<EmployeeOutput>() {
		}.getType());
		model.addAttribute("empdet", eout);
		return "profile";
	}

	// To update the address in the employee profile
	@RequestMapping(value = "/update_address", method = RequestMethod.POST)
	public String updateAddress(@ModelAttribute EmployeeOutput emp, Model model) {

		Employee erm = modelMapper.map(emp, new TypeToken<Employee>() {
		}.getType());
		empserv.updateAddress(erm.getEmplId(), erm.getEmplAddress());

		return "index2";

	}

}