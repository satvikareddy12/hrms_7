
package controllers;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DAO_Interfaces.ApplyPermissionDao;
import models.ApplyPermissions;
import models.Employee;
import models.PermissionAdminModel;
import models.PermissionCompositeKey;
import models.PermissionInputModel;

@Controller
public class PermissionsController {

	private ApplyPermissionDao apd;
	private ApplyPermissions ap;
	private PermissionCompositeKey pcompositeKey;

	@Autowired
	public PermissionsController(ApplyPermissionDao apdi, ApplyPermissions app, PermissionCompositeKey cKey) {
		apd = apdi;
		ap = app;
		pcompositeKey = cKey;
	}

	// To apply a permission
	@RequestMapping(value = "/getpermissions")
	public String applyPermission(Model model) {
		// set employee id from session
		Long daycount = apd.getEmployeeAndPermissionRequestDataCountPerDay(102, Date.valueOf(LocalDate.now()));
		Long monthcount = apd.getEmployeeAndPermissionRequestDataCountPerMonth(102, LocalDate.now().getMonthValue(),
				LocalDate.now().getYear());
		System.out.println(daycount);
		System.out.println(monthcount);
		model.addAttribute("PermissionDayCount", daycount);
		model.addAttribute("PermissionMonthCount", monthcount);
		return "emppermission";
	}

	// To check the status of the the applied permission
	@RequestMapping(value = "/applyPermission", method = RequestMethod.POST)
	public ResponseEntity<String> applyPermissionStatus(@ModelAttribute PermissionInputModel permissionInput) {
		try {

			ap.setCurrent_date(Date.valueOf(permissionInput.getCurrent_date()));
			// Convert the start and end time strings to Time objects
			// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
			String startTime = permissionInput.getStart_time();
			String endTime = permissionInput.getEnd_time();

			ap.setStart_time(Time.valueOf(startTime + ":00"));
			ap.setEnd_time(Time.valueOf(endTime));
			ap.setReason(permissionInput.getReason());

			pcompositeKey.setId(permissionInput.getId());
			int index = apd.getNextPermissionIndex(permissionInput.getId());
			pcompositeKey.setEp_index(index);

			ap.setId(pcompositeKey);

			apd.persist(ap);

			return ResponseEntity.ok("success");
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = "Internal Server Error";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}
	}

	// To view permission requests by the admin
	@RequestMapping(value = "/adminviewpermissions")
	public String adminViewPermissionRequests(Model model) {

		List<Employee> employees = apd.getEmployeesByHRAndManager(301);
		List<ApplyPermissions> outputmodel = new ArrayList<>();
		for (Employee employee : employees) {
			System.out.println(employee.getEmplId());
			ApplyPermissions permission = apd.getEmployeeAndPermissionRequestData(employee.getEmplId(),
					Date.valueOf(LocalDate.now()));

			System.out.println(permission);
			if (permission != null)
				outputmodel.add(permission);
		}
		model.addAttribute("permissions", outputmodel);
		return "adminviewpermission";
	}

	// If admin accept the permission request
	@RequestMapping(value = "/acceptpermissions")
	@Transactional
	public ResponseEntity<String> acceptPermission(@ModelAttribute PermissionAdminModel pm) {
		try {
			System.out.println(pm.getId());
			ApplyPermissions permission = apd.getPermissionByIdAndIndex(pm.getId(), pm.getIndex());

			System.out.println(permission);
			if (permission != null) {
				permission.setEprq_status("accept");
				// Set other properties if needed
				permission.setEprq_approvedby(123 + "");
				apd.persist(permission);
				return ResponseEntity.ok("success");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Permission not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = "Internal Server Error";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}
	}

	// If admin reject the permission request
	@RequestMapping(value = "/rejectpermissions")
	@Transactional
	public ResponseEntity<String> rejectPermission(@ModelAttribute PermissionAdminModel pm) {
		try {

			ApplyPermissions permission = apd.getPermissionByIdAndIndex(pm.getId(), pm.getIndex());
			if (permission != null) {
				permission.setEprq_status("reject");
				// Set other properties if needed
				apd.persist(permission);
				return ResponseEntity.ok("success");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Permission not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = "Internal Server Error";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}
	}

}
