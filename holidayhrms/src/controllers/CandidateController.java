package controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.CandidateDAO;
import models.Candidate;

@Controller
public class CandidateController {

	private CandidateDAO candidateDAO;

	public CandidateController(CandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;

	}

	@RequestMapping("/viewcandidates")
	public String showCandidates(Model model) {
		List<Candidate> candidates = candidateDAO.getAllCandidates();
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
	public String saveCandidate(@ModelAttribute Candidate cand, Model model) {

		candidateDAO.saveCandidate(cand);

		model.addAttribute("message", "Candidate details saved successfully!");
		return "success";
	}
}
