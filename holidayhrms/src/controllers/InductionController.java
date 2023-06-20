package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.InductionDAO;
import models.Induction;

@Controller
public class InductionController {

	private final InductionDAO ind;
	private final Induction i;

	@Autowired
	public InductionController(InductionDAO idao, Induction j) {
		ind = idao;
		i = j;
	}

	@RequestMapping("/inductionlist")
	public String showEmployees(Model model) {
		List<Integer> inductions = ind.getAllInductions();
		model.addAttribute("inductions", inductions);
		return "inductions";
	}

	@RequestMapping("/get-induction-details")
	public String getEmployeeDetails(@RequestParam("id") int indid, Model model) {
		List<Induction> i = ind.getInductionById(indid);
		model.addAttribute("indid", i);
		model.addAttribute("ID", indid);
		return "inductiondetails";
	}

	@RequestMapping(value = "/inductioninsert", method = RequestMethod.GET)
	public String createInduction(Model model) {
		List<Integer> hd = ind.getAllEmploymentOffers();
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
			ind.insertEmployee(induction);
			ind.updateEmploymentOfferStatus(indcEmofId, "INDC");

		}

		return "inductions";
	}

}
