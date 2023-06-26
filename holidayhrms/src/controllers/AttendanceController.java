package controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import DAO_Interfaces.EmployeeAttendanceDAO;
import DAO_Interfaces.EmployeeDAO;
import models.AttendanceEvent;
import models.AttendanceRequest;
import models.Employee;
import models.EmployeeAttendance;
import models.EmployeeAttendanceId;
import models.EmployeeRequestResult;
import service_interfaces.EmployeeAttendanceServiceInterface;

@Controller
public class AttendanceController {

	private EmployeeAttendance attendance;
	private EmployeeAttendanceId attendanceId;
	private Gson gson;
	private EmployeeDAO employeeDAO;

	@Autowired
	public AttendanceController(EmployeeAttendance attendance, EmployeeAttendanceId attendanceId, Gson gson,
			EmployeeDAO employeeDAO) {
		this.attendance = attendance;
		this.attendanceId = attendanceId;
		this.gson = gson;
		this.employeeDAO = employeeDAO;
	}

	@Autowired
	private EmployeeAttendanceServiceInterface employeeAttendanceService;

	@Autowired
	private EmployeeAttendanceDAO employeeAttendanceDAO;

	// gets the attendance form to upload the attendance
	@RequestMapping(value = "/attendanceform", method = RequestMethod.GET)
	public String attendanceform() {
		return "attendanceform";
	}

	// gets the attendance form the employee
	@RequestMapping(value = "/employeeAttendance", method = RequestMethod.GET)
	public String employeeAttendanceForm(HttpSession session, Model model) {

		int id = (int) session.getAttribute("employeeId");
		// Retrieves the employee ID from the session

		Employee employee = employeeDAO.getEmployee(id);
		// Retrieves the employee information from the DAO using the employee ID

		Date joinDate = employee.getEmplJondate();
		// Retrieves the join date of the employee

		List<Integer> yearsList = employeeAttendanceService.getYears(joinDate);
		// Retrieves a list of years based on the employee's join date using the service method

		model.addAttribute("years", yearsList);
		// Adds the yearsList as an attribute named "years" to the model

		return "employeeAttendance";
		// Returns the view name "employeeAttendance" to render the employee attendance form

	}

	// Admin side attendance
	@RequestMapping(value = "/adminAttendance", method = RequestMethod.GET)
	public String adminAttendanceForm() {

		return "adminAttendance";
	}

	// to get attendance data
	@RequestMapping(value = "/attendance", method = RequestMethod.POST)
	public ResponseEntity<String> attendanceData(@ModelAttribute AttendanceRequest attendanceRequest) {

		int year = attendanceRequest.getYear();
		int month = attendanceRequest.getMonth();
		int id = attendanceRequest.getEmployeeid();
		// Retrieves the year, month, and employee ID from the attendance request object

		List<Object[]> results = employeeAttendanceDAO.getPunchInAndPunchOutDataForYearAndMonthAndEmployee(id, year,
				month);
		// Retrieves the punch in and punch out data for the specified year, month, and employee ID from the DAO

		EmployeeRequestResult response = employeeAttendanceService.calculateAttendance(results);
		// Calls the service method to calculate the attendance based on the retrieved results

		return ResponseEntity.ok(gson.toJson(response));
		// Returns a response entity with the calculated attendance result serialized as JSON
	}

	// to get punch data for graphs
	@RequestMapping(value = "/punchData", method = RequestMethod.GET)
	public ResponseEntity<String> getPunchData(HttpSession session) {
		int id = (int) session.getAttribute("employeeId");
		// gets yesterday punchin and punchout data for a particular employee based on id
		List<AttendanceEvent> punchData = employeeAttendanceService.getYesterdayPunchData(id);
		return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(punchData));
	}

	@RequestMapping(value = "/uploadAttendance", method = RequestMethod.POST)
	public ResponseEntity<String> uploadEmployeeAttendance(@RequestParam("file") MultipartFile file) {
		try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {

			// Get the first sheet from the workbook
			Sheet sheet = workbook.getSheetAt(0);

			// Create an iterator to iterate over the rows of the sheet
			Iterator<Row> rowIterator = sheet.iterator();

			rowIterator.next(); // Skip header row

			// iterate over each row
			while (rowIterator.hasNext()) {

				// get next row
				Row row = rowIterator.next();

				// Assuming column order: employeeId, punchIn, punchOut, punchSystem
				// retrieve respective data from the excel
				Integer employeeId = (int) row.getCell(0).getNumericCellValue();
				LocalDateTime punchIn = employeeAttendanceService.convertToDateTime(row.getCell(1));
				LocalDateTime punchOut = employeeAttendanceService.convertToDateTime(row.getCell(2));
				String punchSystem = row.getCell(3).toString();

				// set the data to the model
				attendance.setPunchIn(punchIn);
				attendance.setPunchOut(punchOut);
				attendance.setPunchSystem(punchSystem);
				attendanceId.setEmployeeId(employeeId);

				// generate next index for the record
				int nextIndex = employeeAttendanceDAO.getNextAttendanceRequestIndex(employeeId);
				attendanceId.setEmplPIndex(nextIndex);
				attendance.setAttendanceId(attendanceId);

				// insert the record into database using service
				employeeAttendanceService.insertEmployeeAttendance(attendance);

			}
		} catch (IOException e) {
			// print the stack trace of IO Exception
			e.printStackTrace();
			String errorMessage = "Internal Server Error";
			// return 500 error for any internal errors
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// on successful completion return 200 status with success message
		return ResponseEntity.ok("succesfully updated");
	}

	@RequestMapping(value = "/getYearsList", method = RequestMethod.POST)
	public ResponseEntity<String> getYearsOfEmployee(@ModelAttribute AttendanceRequest attendanceRequest) {

		int id = attendanceRequest.getEmployeeid();
		// Retrieves the employee ID from the attendance request object

		Employee employee = employeeDAO.getEmployee(id);
		// Retrieves the employee information from the DAO using the employee ID

		if (employee == null)
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		// Checks if the employee is null, and if so, returns an error response indicating an internal server error

		Date joinDate = employee.getEmplJondate();
		// Retrieves the join date of the employee

		List<Integer> yearsList = employeeAttendanceService.getYears(joinDate);
		// Retrieves a list of years based on the employee's join date using the service method

		return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(yearsList));
		// Returns a response entity with the yearsList serialized as JSON and an HTTP status code indicating a
		// successful request
	}

	@RequestMapping(value = "/getAvgPunchInAndOut", method = RequestMethod.GET)
	public ResponseEntity<String> getAvgPunchInAndOut(HttpSession session) {

		int id = (int) session.getAttribute("employeeId");
		// Retrieves the employee ID from the session

		List<Long> result = employeeAttendanceService.getAvgPunchInAndOut(id);
		// Retrieves the average punch in and punch out time for the employee ID using the service method

		return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(result));
		// Returns a response entity with the average punch in and punch out time serialized as JSON and an HTTP status
		// code indicating a successful request
	}
}