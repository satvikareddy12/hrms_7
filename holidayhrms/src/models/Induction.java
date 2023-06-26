package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hrms_induction")
@IdClass(InductionId.class)
public class Induction {
	@Id
	@Column(name = "indc_id")
	private int indcId;

	@Id
	@Column(name = "indc_emof_id")
	private int indcEmofId;

	@Column(name = "indc_date")
	private Date indcDate;

	@Column(name = "indc_processes_ausr_id")
	private Integer indcProcessedAusrId;

	@Column(name = "indc_status")
	private String indcStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "indc_emof_id", referencedColumnName = "eofr_cand_id", insertable = false, updatable = false)
	private HrmsEmploymentOffer employmentOffer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "indc_processes_ausr_id", referencedColumnName = "ausr_empl_id", insertable = false, updatable = false)
	private HrmsAdminUser adminUser;

	public int getIndcId() {
		return indcId;
	}

	public void setIndcId(int indcId) {
		this.indcId = indcId;
	}

	public int getIndcEmofId() {
		return indcEmofId;
	}

	public void setIndcEmofId(int indcEmofId) {
		this.indcEmofId = indcEmofId;
	}

	public Date getIndcDate() {
		return indcDate;
	}

	public void setIndcDate(Date indcDate) {
		this.indcDate = indcDate;
	}

	public Integer getIndcProcessedAusrId() {
		return indcProcessedAusrId;
	}

	public void setIndcProcessedAusrId(Integer indcProcessedAusrId) {
		this.indcProcessedAusrId = indcProcessedAusrId;
	}

	public String getIndcStatus() {
		return indcStatus;
	}

	public void setIndcStatus(String indcStatus) {
		this.indcStatus = indcStatus;
	}

	public HrmsEmploymentOffer getEmploymentOffer() {
		return employmentOffer;
	}

	public void setEmploymentOffer(HrmsEmploymentOffer employmentOffer) {
		this.employmentOffer = employmentOffer;
	}

	public HrmsAdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(HrmsAdminUser adminUser) {
		this.adminUser = adminUser;
	}

	// Constructors, getters, and setters
	// ...

}