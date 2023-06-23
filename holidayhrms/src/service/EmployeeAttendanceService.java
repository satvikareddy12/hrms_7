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

import DAO.EmployeeAttendanceDAO;
import models.AttendanceEvent;
import models.EmployeeAttendance;
import models.EmployeeRequestResult;

@Service
public class EmployeeAttendanceService {
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
	public void insertEmployeeAttendance(EmployeeAttendance attendance) {
		employeeAttendanceDAO.save(attendance);

	}

	public List<AttendanceEvent> getYesterdayPunchData(int employeeId) {
		List<Object[]> results = employeeAttendanceDAO.getYesterdayPunchInAndPunchOut(employeeId);

		// Formatting the data required for the graphs
		List<AttendanceEvent> formattedEvents = new ArrayList<>();
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a");
		for (Object[] row : results) {
			LocalDateTime punchIn = (LocalDateTime) row[0];
			LocalDateTime punchOut = (LocalDateTime) row[1];

			String formattedPunchIn = punchIn.format(outputFormatter);
			String formattedPunchOut = punchOut.format(outputFormatter);

			AttendanceEvent attendanceEvent = context.getBean(AttendanceEvent.class);
			attendanceEvent.setTime(formattedPunchIn);
			attendanceEvent.setEvent("Punch In");
			formattedEvents.add(attendanceEvent);

			attendanceEvent = context.getBean(AttendanceEvent.class);
			attendanceEvent.setTime(formattedPunchOut);
			attendanceEvent.setEvent("Punch Out");
			formattedEvents.add(attendanceEvent);
		}

		for (AttendanceEvent ae : formattedEvents) {
			System.out.println(ae.getEvent() + " " + ae.getTime());
		}
		return formattedEvents;
	}

	public EmployeeRequestResult calculateAttendance(List<Object[]> punchData) {
		int daysWithMinimumHours = 0;

		Map<LocalDateTime, Duration> workingHoursPerDay = new HashMap<>();

		for (Object[] punches : punchData) {
			LocalDateTime punchIn = (LocalDateTime) punches[0];
			LocalDateTime punchOut = (LocalDateTime) punches[1];

			if (punchIn == null || punchOut == null) {
				continue; // Skip data with missing punch-in or punch-out
			}

			Duration duration = Duration.between(punchIn, punchOut);
			LocalDateTime dateOnly = punchIn.toLocalDate().atStartOfDay();

			System.out.println("date key :" + dateOnly);

			if (workingHoursPerDay.containsKey(dateOnly)) {
				Duration totalDuration = workingHoursPerDay.get(dateOnly).plus(duration);
				workingHoursPerDay.put(dateOnly, totalDuration);
			} else {
				workingHoursPerDay.put(dateOnly, duration);
			}
		}

		for (Duration duration : workingHoursPerDay.values()) {
			long hours = duration.toHours();

			if (hours >= 8) {
				daysWithMinimumHours++;
			}
		}

		int totalDays = workingHoursPerDay.size();
		double attendancePercentage = (double) daysWithMinimumHours / totalDays * 100;

		if (Double.isNaN(attendancePercentage)) {
			attendancePercentage = 0.0;
		}

		System.out.println("no of days : " + totalDays);
		System.out.println("days with minimum hours" + daysWithMinimumHours);
		System.out.println("attendance percentage" + attendancePercentage);

		response.setDayswithminhrs(daysWithMinimumHours);
		response.setPercentage(attendancePercentage);
		response.setTotaldays(totalDays);

		return response;

	}

	public LocalDateTime convertToDateTime(Cell cell) {
		if (cell.getCellType() == CellType.NUMERIC) {
			// Assuming the cell contains a date/time value
			return cell.getLocalDateTimeCellValue();
		} else {
			// Handle other cell types or formats as needed
			return null;
		}
	}

	public List<Integer> getYears(Date joinDate) {

		LocalDate join = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(joinDate));
		LocalDate currentDate = LocalDate.now();

		List<Integer> yearList = new ArrayList<>();

		int joinYear = join.getYear();
		int currentYear = currentDate.getYear();

		while (joinYear <= currentYear) {
			yearList.add(joinYear);
			joinYear++;
		}

		return yearList;

	}

	public List<Long> getAvgPunchInAndOut(int id) {

		int i;
		long punchin = 0, punchout = 0;
		List<Object[]> punchData = employeeAttendanceDAO.getPunchInAndPunchOutDataForYearAndMonthAndEmployee(id,
				LocalDate.now().getYear(), LocalDate.now().getMonthValue());
		List<LocalDateTime> noofDays = new ArrayList<>();
		LocalDateTime dateOnly = null;

		for (i = 0; i < punchData.size() - 1; i++) {
			punchin += Duration.between((LocalDateTime) punchData.get(i)[0], (LocalDateTime) punchData.get(i)[1])
					.toMinutes();

			LocalDateTime pOutOfCurrent = (LocalDateTime) punchData.get(i)[1];
			LocalDateTime pInOfNext = (LocalDateTime) punchData.get(i + 1)[0];

			if (pOutOfCurrent.toLocalDate().getDayOfMonth() == pInOfNext.toLocalDate().getDayOfMonth())
				punchout += Duration.between(pOutOfCurrent, pInOfNext).toMinutes();
			dateOnly = (LocalDateTime) punchData.get(i)[0];
			System.out.println(dateOnly);
			if (!noofDays.contains(dateOnly.toLocalDate().atStartOfDay()))
				noofDays.add(dateOnly.toLocalDate().atStartOfDay());

		}

		punchin += Duration.between((LocalDateTime) punchData.get(i)[0], (LocalDateTime) punchData.get(i)[1])
				.toMinutes();
		dateOnly = (LocalDateTime) punchData.get(i)[0];
		if (!noofDays.contains(dateOnly.toLocalDate().atStartOfDay()))
			noofDays.add(dateOnly.toLocalDate().atStartOfDay());

		List<Long> result = new ArrayList<>();
		result.add(punchin / noofDays.size());
		result.add(punchout / noofDays.size());

		for (LocalDateTime date : noofDays) {
			System.out.println("array" + date);
		}

		System.out.println(noofDays.size());

		return result;
	}

}