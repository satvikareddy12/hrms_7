package controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.CandidateDAO;
import models.Candidate;
import models.Eofr;
import models.HRDepartment;
import models.Inductiondocuments;
import models.OfferModel;
import models.empoffdocuments;
import service.offerlettermail;

@Controller
public class OfferController {
	private CandidateDAO cd;
	OfferModel of;
	Candidate can;
	Inductiondocuments indoc;

	@Autowired
	public OfferController(CandidateDAO cd) {
		this.cd = cd;

	}

	@RequestMapping("/abs")

	public String getissuingCandidates(Model model) {
		List<Candidate> candidates = cd.findAllIssuedCandidates();
		model.addAttribute("candidates", candidates);
		return "front";
	}

	@RequestMapping("/get-candidate-details")
	public String getEmployeeDetails(@RequestParam("id") int candidateId, Inductiondocuments indocm, Model model) {
		Candidate candidate = cd.getCandidateById(candidateId);
		int HR_id = 123;
		HRDepartment emp = cd.getHrById(HR_id);
		System.out.println("mobileno" + emp.getMobileNumber());
		indoc = indocm;
		can = candidate;
		List<String> listOfDocuments = cd.getAllDocuments();
		model.addAttribute("candidate", candidate);
		model.addAttribute("hr", emp);
		model.addAttribute("listOfDocuments", listOfDocuments);

		return "viewCandidatess";
	}

	@RequestMapping("/email")
	public String sendToMail(@Validated OfferModel offerModel, Model model) {
		of = offerModel;

		System.out.println(offerModel.getDocuments());
		model.addAttribute("offerModel", offerModel);

		// Return the appropriate view
		return "email";
	}

	@RequestMapping("/sendOfferLetter")

	public String redirectedFromOfferLetter(Eofr eofr, empoffdocuments eod, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		eofr.setEofr_id(cd.getLatestEofrIdFromDatabase() + 1);
		eofr.setEofr_ref_id("ref " + eofr.getEofr_id());
		eofr.eofr_cand_id = can.getCandId();
		System.out.println(can.getCandId());
		eofr.setEofr_hremail(of.getAdminEmail());
		eofr.setEofr_hrmobileno(Long.parseLong(of.getAdminMobile()));
		eofr.setEofr_offerdate(Date.valueOf(of.getOfferDate()));
		eofr.setEofr_offerjobe(of.getOfferedJob());
		eofr.setEofr_reportingdate(Date.valueOf(LocalDate.parse(of.getReportingDate())));
		eofr.setEofr_status("INPR");

		try {
			offerlettermail.sendEmail(request, response, of);
		} catch (Exception e) {

			e.printStackTrace();
		}

		cd.insertEofrInto(eofr);

		cd.updateEmploymentOfferDocuments(eofr, of);

		cd.updateCandidateStatus("cand_status", "AC");

		// eod.setEofrId((long) can.getCand_id());
		// eod.setEofdDocIndex();
		// eod.setEofdIdtyId(eofdIdtyId);
		return "front";
	}

	@RequestMapping("/provided")
	public String getprovidedCandidates(Model model) {
		List<Candidate> candidates = cd.findAllProvidedCandidates();
		model.addAttribute("candidates", candidates);
		return "frontprovided";
	}
}
