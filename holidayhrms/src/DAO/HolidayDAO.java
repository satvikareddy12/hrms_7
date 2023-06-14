package DAO;

import java.util.List;

import models.Holiday;

public interface HolidayDAO {
	List<Holiday> findAllHolidays();

	Holiday findHolidayById(int id);

	void saveHoliday(Holiday holiday);

	void updateHoliday(Holiday holiday);

	void deleteHoliday(Holiday holiday);
}
