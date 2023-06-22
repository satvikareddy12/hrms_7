
package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.InductionDAO;
import models.EmploymentInductionDocument;
import models.Induction;
import models.input.output.EmploymentInductionDocumentViewModel;
import models.input.output.addinductionDOC;
import service.EmploymentInductionDocumentService;

@Controller
public class InductionController {

	@Autowired
	private EmploymentInductionDocumentService docServ;

	@Autowired
	private EmploymentInductionDocument document;

	@Autowired
	private InductionDAO idao;

	@Autowired
	private Induction j;

	@RequestMapping("/inductionlist")
	public String showEmployees(Model model) {
		List<Integer> inductions = idao.getAllInductions();
		model.addAttribute("inductions", inductions);
		return "inductions";
	}

	@RequestMapping("/get-induction-details")
	public String getEmployeeDetails(@RequestParam("id") int indid, Model model) {
		List<Induction> i = idao.getInductionById(indid);
		model.addAttribute("indid", i);
		model.addAttribute("ID", indid);
		return "inductiondetails";
	}

	@RequestMapping(value = "/inductioninsert", method = RequestMethod.GET)
	public String createInduction(Model model) {
		List<Integer> hd = idao.getAllEmploymentOffers();
		model.addAttribute("employmentOffers", hd);
		return "createInduction";
	}

	@RequestMapping(value = "/inductionsave", method = RequestMethod.POST)
	public String saveInduction(@RequestParam("inductionId") int inductionId,
			@RequestParam("employeeOffers") List<Integer> employeeOffers,
			@RequestParam("inductionDate") String inductionDate, @RequestParam("authorizedId") int authorizedId,
			@RequestParam("status") String status, Model model) {
		// Perform any necessary data processing or validations

		// Create the Induction objects
		List<Induction> inductions = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (Integer indcEmofId : employeeOffers) {
			Induction induction = new Induction();
			induction.setIndcId(inductionId);
			induction.setIndcEmofId(indcEmofId);

			try {
				Date date = (Date) dateFormat.parse(inductionDate);
				induction.setIndcDate(new java.sql.Date(date.getTime()));
			} catch (ParseException e) {
				// Handle the parse exception appropriately
			}

			induction.setIndcProcessedAusrId(authorizedId);
			induction.setIndcStatus(status);

			inductions.add(induction);
			idao.insertEmployee(induction);
			idao.updateEmploymentOfferStatus(indcEmofId, "INDC");

		}

		return "inductions";
	}

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
		String path = input.getDocumentData().getAbsolutePath();
		System.out.println("-----------------------" + path);
		document.setDocumentData(path);
		docServ.addEmploymentInductionDocument(document);
		return "success";
	}
}
