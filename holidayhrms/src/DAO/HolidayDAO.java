package DAO;

import java.util.List;

import models.GradeHoliday;
import models.Holiday;

public interface HolidayDAO {
	List<Holiday> findAllHolidays();

	GradeHoliday findHolidayById(String id);

	List<GradeHoliday> findAllGradeHolidays();
}
