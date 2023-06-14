package models;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hrms_employeepermissionrequests")
public class ApplyPermissions {

	@EmbeddedId
	private PermissionCompositeKey id;

	private Date eprq_date;
	private Time eprq_sttime;
	private Time eprq_endtime;

	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public PermissionCompositeKey getId() {
		return id;
	}

	public void setId(PermissionCompositeKey id) {
		this.id = id;
	}

	public Date getEprq_date() {
		return eprq_date;
	}

	public void setEprq_date(Date eprq_date) {
		this.eprq_date = eprq_date;
	}

	public Time getEprq_sttime() {
		return eprq_sttime;
	}

	public void setEprq_sttime(Time eprq_sttime) {
		this.eprq_sttime = eprq_sttime;
	}

	public Time getEprq_endtime() {
		return eprq_endtime;
	}

	public void setEprq_endtime(Time eprq_endtime) {
		this.eprq_endtime = eprq_endtime;
	}

}
