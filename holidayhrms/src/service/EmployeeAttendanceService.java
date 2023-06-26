package service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DAO_Interfaces.EmployeeAttendanceDAO;
import models.AttendanceEvent;
import models.EmployeeAttendance;
import models.EmployeeRequestResult;
import service_interfaces.EmployeeAttendanceServiceInterface;

@Service
public class EmployeeAttendanceService implements EmployeeAttendanceServiceInterface {
	@Autowired
	private EmployeeAttendanceDAO employeeAttendanceDAO;

	@Autowired
	private ApplicationContext context;

	private EmployeeRequestResult response;

	@Autowired
	public EmployeeAttendanceService(EmployeeRequestResult response) {
		this.response = response;
	}

	@Transactional
	@Override
	public void insertEmployeeAttendance(EmployeeAttendance attendance) {
		employeeAttendanceDAO.save(attendance);

	}

	@Override
	public List<AttendanceEvent> getYesterdayPunchData(int employeeId) {
	    // Retrieve punch-in and punch-out data from the DAO
		List<Object[]> results = employeeAttendanceDAO.getYesterdayPunchInAndPunchOut(employeeId);

		// Formatting the data required for the graphs
		List<AttendanceEvent> formattedEvents = new ArrayList<>();
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a");
		for (Object[] row : results) {
			LocalDateTime punchIn = (LocalDateTime) row[0];
			LocalDateTime punchOut = (LocalDateTime) row[1];

	        // Format punch-in time
			String formattedPunchIn = punchIn.format(outputFormatter);
	        // Format punch-out time
			String formattedPunchOut = punchOut.format(outputFormatter);
	        // Create AttendanceEvent object for punch-in event
			AttendanceEvent attendanceEvent = context.getBean(AttendanceEvent.class);
			attendanceEvent.setTime(formattedPunchIn);
			attendanceEvent.setEvent("Punch In");
			formattedEvents.add(attendanceEvent);
	        // Create AttendanceEvent object for punch-out event
			attendanceEvent = context.getBean(AttendanceEvent.class);
			attendanceEvent.setTime(formattedPunchOut);
			attendanceEvent.setEvent("Punch Out");
			formattedEvents.add(attendanceEvent);
		}
	    // Return the list of formatted events
		return formattedEvents;
	}

	@Override
	public EmployeeRequestResult calculateAttendance(List<Object[]> punchData) {
		int daysWithMinimumHours = 0;

		Map<LocalDateTime, Duration> workingHoursPerDay = new HashMap<>();

	    // Iterate over the punch-in and punch-out data
		for (Object[] punches : punchData) {
			LocalDateTime punchIn = (LocalDateTime) punches[0];
			LocalDateTime punchOut = (LocalDateTime) punches[1];

	        // Skip data with missing punch-in or punch-out
			if (punchIn == null || punchOut == null) {
				continue; 
			}
	        // Calculate the duration between punch-in and punch-out
			Duration duration = Duration.between(punchIn, punchOut);
			LocalDateTime dateOnly = punchIn.toLocalDate().atStartOfDay();
	        // Update the working hours for the specific day
			if (workingHoursPerDay.containsKey(dateOnly)) {
				Duration totalDuration = workingHoursPerDay.get(dateOnly).plus(duration);
				workingHoursPerDay.put(dateOnly, totalDuration);
			} else {
				workingHoursPerDay.put(dateOnly, duration);
			}
		}
	    // Count the number of days with minimum working hours (8 hours)
		for (Duration duration : workingHoursPerDay.values()) {
			long hours = duration.toHours();

			if (hours >= 8) {
				daysWithMinimumHours++;
			}
		}
	    // Calculate the attendance percentage
		int totalDays = workingHoursPerDay.size();
		double attendancePercentage = (double) daysWithMinimumHours / totalDays * 100;

		if (Double.isNaN(attendancePercentage)) {
			attendancePercentage = 0.0;
		}


		response.setDayswithminhrs(daysWithMinimumHours);
		response.setPercentage(attendancePercentage);
		response.setTotaldays(totalDays);

		return response;

	}

	@Override
	public LocalDateTime convertToDateTime(Cell cell) {
		if (cell.getCellType() == CellType.NUMERIC) {
			// Assuming the cell contains a date/time value
			return cell.getLocalDateTimeCellValue();
		} else {
			// Handle other cell types or formats as needed
			return null;
		}
	}

	@Override
	public List<Integer> getYears(Date joinDate) {
	    // Convert the joinDate to a LocalDate object
		LocalDate join = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(joinDate));
	    // Get the current date
		LocalDate currentDate = LocalDate.now();
	    // Create a list to store the years
		List<Integer> yearList = new ArrayList<>();
	    // Get the year of the join date
		int joinYear = join.getYear();
	    // Get the year of the current date
		int currentYear = currentDate.getYear();
	    // Iterate from the join year to the current year (inclusive)
		while (joinYear <= currentYear) {
	        // Add the current year to the yearList
			yearList.add(joinYear);
	        // Move to the next year
			joinYear++;
		}
	    // Return the list of years
		return yearList;

	}

	@Override
	public List<Long> getAvgPunchInAndOut(int id) {

		int i;
		long punchin = 0, punchout = 0;
	    // Retrieve the punch-in and punch-out data for the current year and month
		List<Object[]> punchData = employeeAttendanceDAO.getPunchInAndPunchOutDataForYearAndMonthAndEmployee(id,
				LocalDate.now().getYear(), LocalDate.now().getMonthValue());
		List<Long> result = new ArrayList<>();

		if (punchData.size() > 0) {

			List<LocalDateTime> noofDays = new ArrayList<>();
			LocalDateTime dateOnly = null;

	        // Iterate over the punch data
			for (i = 0; i < punchData.size() - 1; i++) {
	            // Calculate the total punch-in time in minutes
				punchin += Duration.between((LocalDateTime) punchData.get(i)[0], (LocalDateTime) punchData.get(i)[1])
						.toMinutes();

				LocalDateTime pOutOfCurrent = (LocalDateTime) punchData.get(i)[1];
				LocalDateTime pInOfNext = (LocalDateTime) punchData.get(i + 1)[0];
	            // Check if the punch-out and punch-in are on the same day
				if (pOutOfCurrent.toLocalDate().getDayOfMonth() == pInOfNext.toLocalDate().getDayOfMonth()) {
	                // Calculate the punch-out to punch-in time difference in minutes
					punchout += Duration.between(pOutOfCurrent, pInOfNext).toMinutes();
				}
				dateOnly = (LocalDateTime) punchData.get(i)[0];
	            // Add unique dates to the noofDays list
				if (!noofDays.contains(dateOnly.toLocalDate().atStartOfDay()))
					noofDays.add(dateOnly.toLocalDate().atStartOfDay());

			}
	        // Calculate the punch-in time and date for the last entry
			punchin += Duration.between((LocalDateTime) punchData.get(i)[0], (LocalDateTime) punchData.get(i)[1])
					.toMinutes();
			dateOnly = (LocalDateTime) punchData.get(i)[0];
			if (!noofDays.contains(dateOnly.toLocalDate().atStartOfDay()))
				noofDays.add(dateOnly.toLocalDate().atStartOfDay());

	        // Calculate the average punch-in and punch-out times
			result.add(punchin / noofDays.size());
			result.add(punchout / noofDays.size());


		}

		return result;
	}

}