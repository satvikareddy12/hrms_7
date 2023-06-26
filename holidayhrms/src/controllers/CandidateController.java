
package controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO_Interfaces.CandidateDAO;
import models.Candidate;
import models.input.output.CandidateIO;

@Controller
public class CandidateController {

	private CandidateDAO candidateDAO;
	private final ModelMapper modelMapper;

	public CandidateController(CandidateDAO candidateDAO, ModelMapper mp) {
		this.candidateDAO = candidateDAO;
		modelMapper = mp;

	}

	// To view list of candidates
	@RequestMapping("/viewcandidates")
	public String showCandidateList(Model model) {
		List<Candidate> candidates = candidateDAO.getAllCandidates();
		List<CandidateIO> candidateOutputs = modelMapper.map(candidates, new TypeToken<List<CandidateIO>>() {
		}.getType());
		model.addAttribute("candidates", candidateOutputs);
		return "candidateview";
	}

	// To display candidate details by id
	@GetMapping("/viewcandidate")
	public String getCandidateDetails(@RequestParam("id") int candidateId, Model model) {
		Candidate candidate = candidateDAO.getCandidateById(candidateId);
		CandidateIO cout = modelMapper.map(candidate, new TypeToken<CandidateIO>() {
		}.getType());
		if (candidate != null) {
			model.addAttribute("candidate", cout);
		} else {
			model.addAttribute("error", "No candidate found with the provided ID.");
		}
		return "viewcandidate";
	}

	// To insert a new candidate
	@RequestMapping(value = "/candidate", method = RequestMethod.GET)
	public String addCandidates() {
		return "candidate";
	}

	// To display the list of candidates after insertion of new candidate
	@RequestMapping(value = "/candidateadded", method = RequestMethod.POST)
	public String listOfCandidatesAfterInsertion(@ModelAttribute Candidate cand, Model model) {

		candidateDAO.saveCandidate(cand);
		List<Candidate> candidates = candidateDAO.getAllCandidates();
		List<CandidateIO> candidateOutputs = modelMapper.map(candidates, new TypeToken<List<CandidateIO>>() {
		}.getType());

		model.addAttribute("candidates", candidateOutputs);
		return "candidateview";
	}
}
