package service_interfaces;

import java.util.List;

import models.EmploymentInductionDocument;
import models.input.output.EmploymentInductionDocumentViewModel;

public interface EmploymentInductionDocumentServiceInterface {
	public void addEmploymentInductionDocument(EmploymentInductionDocument document);

	public String getEmploymentInductionDocumentFile(int documentIndex);

	public List<EmploymentInductionDocumentViewModel> getAllDocuments();
}
