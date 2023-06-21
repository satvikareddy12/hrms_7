package DAO;

import java.util.List;

import models.EmploymentInductionDocument;
import models.input.output.EmploymentInductionDocumentViewModel;

public interface EmploymentInductionDocumentDAO {

	void addEmploymentInductionDocument(EmploymentInductionDocument document);

	EmploymentInductionDocument getEmploymentInductionDocument(int documentIndex);

	List<EmploymentInductionDocumentViewModel> getAllDocuments();

	// Other methods for updating and deleting EmploymentInductionDocuments can be added here
}
