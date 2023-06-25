package service;

import org.springframework.beans.factory.annotation.Autowired;

import DAO.InductionDAO;
import service.EmploymentInductionServiceInterface;

public class EmploymentInductionService implements EmploymentInductionServiceInterface {

	@Autowired
	private InductionDAO idao;// injecting DAO class object

	public int getid() {
		return idao.getIndex();
	}

	public int getidNext() {

		int i = idao.getIndex();
		return i + 1;
	}

}
