package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DAO.EmploymentInductionDocumentDAO;
import models.EmploymentInductionDocument;

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

	public File getEmploymentInductionDocumentFile(int documentIndex) {
		EmploymentInductionDocument document = docDAO.getEmploymentInductionDocument(documentIndex);
		if (document != null) {
			try {
				byte[] fileData = document.getDocumentData();
				File tempFile = File.createTempFile("document", ".dat");
				writeBytesToFile(fileData, tempFile);
				return tempFile;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private void writeBytesToFile(byte[] data, File file) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(data);
		}
	}

	public List<EmploymentInductionDocument> getAllDocuments() {
		return docDAO.getAllDocuments();
	}

}
