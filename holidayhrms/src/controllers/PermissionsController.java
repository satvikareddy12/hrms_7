package controllers;

import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.ApplyPermissionDaoImpl;
import models.ApplyPermissions;
import models.PermissionCompositeKey;

@Controller
public class PermissionsController {

	ApplyPermissionDaoImpl apd;
	ApplyPermissions ap;
	PermissionCompositeKey compositeKey;

	@Autowired
	public PermissionsController(ApplyPermissionDaoImpl apdi, ApplyPermissions app, PermissionCompositeKey cKey) {
		apd = apdi;
		ap = app;
		compositeKey = cKey;
	}

	@RequestMapping(value = "/getpermissions")
	public String getEmpPermissions() {
		return "emppermission";
	}

	@RequestMapping(value = "/applyPermission")
	public String applyPermission(@RequestParam("id") int id, @RequestParam("current-date") String currentdate,
			@RequestParam("start-time") String sttime, @RequestParam("end-time") String endtime,
			@RequestParam("reason") String reason) {

		System.out.println(id);
		System.out.println(currentdate);
		System.out.println(sttime);
		System.out.println(endtime);
		System.out.println(reason);

		Date currentDate1 = Date.valueOf(currentdate);

		Time endtime1 = Time.valueOf(endtime + ":00"); // converting string to time for start time
		Time sttime1 = Time.valueOf(sttime + ":00"); // converting string to time for end time
		int i = 1;

		int indexval = apd.getNextPermissionIndex(id); // for getting the next index value

		// PermissionCompositeKey compositeKey = new PermissionCompositeKey();
		compositeKey.setEmpl_id(id);
		compositeKey.setEp_index(indexval);

		// ApplyPermissions applyPermissions = new ApplyPermissions();
		// applyPermissions.setId(compositeKey);
		// applyPermissions.setEprq_date(currentDate1);
		// applyPermissions.setEprq_sttime(sttime1);
		// applyPermissions.setEprq_endtime(endtime1);
		// applyPermissions.setReason(reason);
		// System.out.println(i);
		// apd.persist(applyPermissions);

		ap.setId(compositeKey);
		ap.setEprq_date(currentDate1);
		ap.setEprq_sttime(sttime1);
		ap.setEprq_endtime(endtime1);
		ap.setReason(reason);

		apd.persist(ap);

		return "permissionfinal";
	}

}

// getMonthlyPermissions()

// viewPermission()
// updatePermissionStatus()
// getApprovedPermissions() to be approved
// showApprovals() those which are approved by admin
