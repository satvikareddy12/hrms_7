package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DAO.EmploymentInductionDocumentDAO;
import models.EmploymentInductionDocument;
import models.input.output.EmploymentInductionDocumentViewModel;

@Service
public class EmploymentInductionDocumentService {

	private final EmploymentInductionDocumentDAO docDAO;

	// Use constructor injection to inject the DAO bean
	@Autowired
	public EmploymentInductionDocumentService(EmploymentInductionDocumentDAO docDAO) {
		this.docDAO = docDAO;
	}

	public void addEmploymentInductionDocument(EmploymentInductionDocument document) {
		docDAO.addEmploymentInductionDocument(document);
	}

	public String getEmploymentInductionDocumentFile(int documentIndex) {
		EmploymentInductionDocument document = docDAO.getEmploymentInductionDocument(documentIndex);

		String fileData = document.getDocumentData();

		return fileData;
	}

	public List<EmploymentInductionDocumentViewModel> getAllDocuments() {
		return docDAO.getAllDocuments();
	}

}
