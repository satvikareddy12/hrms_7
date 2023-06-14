package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import DAO.HolidayDAO;
import models.Holiday;

@Controller
public class HolidayController {

	private final HolidayDAO hd;

	@Autowired
	public HolidayController(HolidayDAO holidayDAO) {
		this.hd = holidayDAO;
	}

	@RequestMapping("/holidays")
	public String showHolidays() {
		return "index2";
	}

	@RequestMapping("/holidaysupd")
	public String showHolidays(Model model) {
		List<Holiday> holidays = hd.findAllHolidays();
		model.addAttribute("holidays", holidays);
		return "holidays";
	}

	@RequestMapping("/yourholidays")
	public String yourHolidays() {
		return "yourholiday";
	}

	@RequestMapping("/admindashboard")
	public String showAdmin() {
		return "Index_admin";
	}

}
