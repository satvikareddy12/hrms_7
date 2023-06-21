
package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import models.EmploymentInductionDocument;
import models.addinductionDOC;
import models.input.output.EmploymentInductionDocumentViewModel;
import service.EmploymentInductionDocumentService;

@Controller
public class InductionDocumentController {

	@Autowired
	private EmploymentInductionDocumentService docServ;

	@Autowired
	private EmploymentInductionDocument document;

	@GetMapping("/getform")
	public String getform(Model model) {
		List<EmploymentInductionDocumentViewModel> doc = docServ.getAllDocuments();
		System.out.println(doc);
		model.addAttribute("doc", doc);
		return "InductionDocument";
	}

	@GetMapping("/add")
	public String addDocument(@ModelAttribute addinductionDOC input) {

		document.setEmplid(input.getEmploymentOfferId());// employee offer id
		System.out.println(input.getEmploymentOfferId());
		document.setEmplidty(input.getDocumentTypeId());// employee offer document type setting
		document.setIndcProcessedAusrId(input.getProcessedUserId());
		document.setVerified(input.getVerified());
		// String path = input.getDocumentData().getAbsolutePath();
		// System.out.println("-----------------------" + path);
		// document.setDocumentData(path);
		docServ.addEmploymentInductionDocument(document);
		return "success";
	}
}
