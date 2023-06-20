package DAO;

import java.util.ArrayList;


import models.EmploymentInductionDocument;

public interface EmploymentInductionDocumentDAO {

	void addEmploymentInductionDocument(EmploymentInductionDocument document);

	EmploymentInductionDocument getEmploymentInductionDocument(int documentIndex);

	ArrayList<EmploymentInductionDocument> getAllDocuments();

	// Other methods for updating and deleting EmploymentInductionDocuments can be added here
}
