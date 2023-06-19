package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hrms_induction")
public class Induction {

	@EmbeddedId
	private InductionId id;

	@Column(name = "indc_date")
	private Date inductionDate;

	@Column(name = "indc_processes_ausr_id")
	private Integer indc_processes_ausr_id;

	@Column(name = "indc_status")
	private String status;

	// Constructors, getters, and setters

	public InductionId getId() {
		return id;
	}

	public void setId(InductionId id) {
		this.id = id;
	}

	public Date getInductionDate() {
		return inductionDate;
	}

	public void setInductionDate(Date inductionDate) {
		this.inductionDate = inductionDate;
	}

	public void setIndcProcessesAusrId(Integer processerid) {

		indc_processes_ausr_id = processerid;
	}

	public Integer getIndcProcessesAusrId() {
		return indc_processes_ausr_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
