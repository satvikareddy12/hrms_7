package controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.CandidateDAO;
import models.Candidate;

@Controller
public class CandidateController {

	private CandidateDAO candidateDAO;
	private Candidate cdd;

	public CandidateController(CandidateDAO candidateDAO, Candidate cds) {
		this.candidateDAO = candidateDAO;
		cdd = cds;
	}

	@RequestMapping("/viewcandidates")
	public String showCandidates(Model model) {
		List<Object[]> candidates = candidateDAO.getAllCandidates();
		model.addAttribute("candidates", candidates);
		return "candidateview";
	}

	@GetMapping("/candid")
	public String showEmployeeDocumentsPage() {
		return "getcandidate";
	}

	@GetMapping("/viewcandidate")
	public String getCandidateDetails(@RequestParam("id") int candidateId, Model model) {
		Candidate candidate = candidateDAO.getCandidateById(candidateId);
		if (candidate != null) {
			model.addAttribute("candidate", candidate);
		} else {
			model.addAttribute("error", "No candidate found with the provided ID.");
		}
		return "viewcandidate";
	}

	@RequestMapping(value = "/candidate", method = RequestMethod.GET)
	public String showCandidateForm() {
		return "candidate";
	}

	@RequestMapping(value = "/success", method = RequestMethod.POST)
	public String saveCandidate(@RequestParam("firstName") String firstName,
			@RequestParam("middleName") String middleName, @RequestParam("lastName") String lastName,
			@RequestParam("rDate") Date rDate, @RequestParam("gender") String gender, @RequestParam("dob") Date dob,
			@RequestParam("email") String email, @RequestParam("mobile") Long mobile,
			@RequestParam("address") String address, @RequestParam("ludate") Date ludate,
			@RequestParam("status") String status, Model model) {

		cdd.setCandFirstName(firstName);
		cdd.setCandMiddleName(middleName);
		cdd.setCandLastName(lastName);
		cdd.setCandRDate(rDate);
		cdd.setCandGender(gender);
		cdd.setCandDOB(dob);
		cdd.setCandEmail(email);
		cdd.setCandMobile(mobile);
		cdd.setCandAddress(address);
		cdd.setCandLUDate(ludate);
		cdd.setCandStatus(status);

		candidateDAO.saveCandidate(cdd);

		model.addAttribute("message", "Candidate details saved successfully!");
		return "success";
	}
}
