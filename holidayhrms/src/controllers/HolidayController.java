package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import DAO_Interfaces.HolidayDAO;
import models.GradeHoliday;
import models.Holiday;

@Controller
public class HolidayController {

	private final HolidayDAO hd;

	@Autowired
	public HolidayController(HolidayDAO holidayDAO) {
		this.hd = holidayDAO;
	}

	// to get list of holidays
	@RequestMapping("/holidaysupd")
	public String showHolidays(Model model) {
		List<Holiday> holidays = hd.findAllHolidays();
		model.addAttribute("holidays", holidays);
		return "holidays";
	}

	// to get list of grade wise holidays
	@RequestMapping("/getgradewiseholidays")
	public String getgradewiseHolidays(Model model) {
		List<GradeHoliday> gradeholidays = hd.findAllGradeHolidays();
		model.addAttribute("gradeholidays", gradeholidays);
		return "gradeholidays";
	}
}
