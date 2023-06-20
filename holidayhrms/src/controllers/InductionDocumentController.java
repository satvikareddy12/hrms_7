package controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import models.EmploymentInductionDocument;
import models.addinductionDOC;
import service.EmploymentInductionDocumentService;

@Controller
public class InductionDocumentController {

	@Autowired
	private EmploymentInductionDocumentService docServ;

	@Autowired
	private EmploymentInductionDocument document;

	@GetMapping("/getform")
	public String getform(Model model) {
		model.addAttribute("documents", docServ.getAllDocuments());
		return "InductionDocument";
	}

	@PostMapping("/add")
	public String addDocument(@ModelAttribute("input") addinductionDOC input) {
		try {

			document.setEmplid(input.getEmploymentOfferId());
			document.setEmplidty(input.getDocumentTypeId());
			byte[] fileData = input.getDocumentData().getBytes();
			document.setDocumentData(fileData);
			document.setIndcProcessedAusrId(input.getProcessedUserId());
			document.setVerified(input.getVerified());

			docServ.addEmploymentInductionDocument(document);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success";
	}

	@GetMapping("/documents/download")
	public ResponseEntity<Resource> downloadDocument(@RequestParam("documentIndex") int documentIndex) {
		// Retrieve the document file using the service
		File documentFile = docServ.getEmploymentInductionDocumentFile(documentIndex);
		// Prepare the file as a resource for download
		Resource fileResource = new FileSystemResource(documentFile);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + StringUtils.cleanPath(documentFile.getName()) + "\"")
				.body(fileResource);
	}

}
