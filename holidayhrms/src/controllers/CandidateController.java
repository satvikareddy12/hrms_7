package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import DAO.CandidateDAO;
import models.Candidate;

@Controller
public class CandidateController {

	private final CandidateDAO cd;

	@Autowired
	public CandidateController(CandidateDAO candidateDAO) {
		this.cd = candidateDAO;

	}



	@RequestMapping("/candidates")

	public String getissuingCandidates(Model model) {
		List<Candidate> candidates = cd.findAllIssuedCandidates();

		model.addAttribute("candidates", candidates);

		return "candidates";
	}
}
