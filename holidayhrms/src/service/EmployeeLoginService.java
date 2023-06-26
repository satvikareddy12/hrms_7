package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import DAO_Interfaces.EmpDAO;
import models.Admin;
import models.Employee;

@Component
public class EmployeeLoginService {

	@Autowired
	private EmpDAO empdao;

	@Transactional
	public Employee getByEmail(String email) {
		return empdao.getDetailsByEmail(email);
	}

	public Admin getAdmin(String email) {
		Admin user = empdao.getDetailsByEmail_admin(email);
		return user;
	}

	@Transactional
	public boolean authenticateUser(String email, String password) {
		Employee user = empdao.getDetailsByEmail(email);

		if (user != null && user.getPassword().equals(hashPassword(password))) {
			return true;
		}
		return false;
	}

	@Transactional
	public boolean authenticateUser_admin(String email, String password) {
		Admin user = empdao.getDetailsByEmail_admin(email);

		if (user != null && user.getAusr_password().equals(hashPassword(password))) {
			return true;
		}
		return false;
	}

	public Employee getEmployee(String email) {
		Employee user = empdao.getDetailsByEmail(email);
		return user;
	}

	public String hashPassword(String password) {
		try {
			return HashGenerator.hashPassword(password);
		} catch (Exception e) {
			e.printStackTrace();
			// Handle the exception appropriately
		}

		return null;
	}

}