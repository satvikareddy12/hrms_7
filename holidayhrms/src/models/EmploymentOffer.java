package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;;

@Entity
@Table(name = "hrms_employmentoffers")
public class EmploymentOffer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eofr_id")
	private int eofrId;

	@Column(name = "eofr_ref_id", unique = true)
	private String eofrRefId;

	@ManyToOne
	@JoinColumn(name = "eofr_cand_id", referencedColumnName = "cand_id")
	private Candidate candidate;

	@Column(name = "eofr_offerdate")
	private Date eofrOfferDate;

	@Column(name = "eofr_offeredjob")
	private String eofrOfferedJob;

	@Column(name = "eofr_reportingdate")
	private Date eofrReportingDate;

	@Column(name = "eofr_hrmobileno")
	private long eofrHrMobileNo;

	@Column(name = "eofr_hremail")
	private String eofrHrEmail;

	@Column(name = "eofr_status")
	private String eofrStatus;

	// Constructors, getters, and setters

	public EmploymentOffer() {
	}

	// Getters and setters

	public int getEofrId() {
		return eofrId;
	}

	public void setEofrId(int eofrId) {
		this.eofrId = eofrId;
	}

	public String getEofrRefId() {
		return eofrRefId;
	}

	public void setEofrRefId(String eofrRefId) {
		this.eofrRefId = eofrRefId;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Date getEofrOfferDate() {
		return eofrOfferDate;
	}

	public void setEofrOfferDate(Date eofrOfferDate) {
		this.eofrOfferDate = eofrOfferDate;
	}

	public String getEofrOfferedJob() {
		return eofrOfferedJob;
	}

	public void setEofrOfferedJob(String eofrOfferedJob) {
		this.eofrOfferedJob = eofrOfferedJob;
	}

	public Date getEofrReportingDate() {
		return eofrReportingDate;
	}

	public void setEofrReportingDate(Date eofrReportingDate) {
		this.eofrReportingDate = eofrReportingDate;
	}

	public long getEofrHrMobileNo() {
		return eofrHrMobileNo;
	}

	public void setEofrHrMobileNo(long eofrHrMobileNo) {
		this.eofrHrMobileNo = eofrHrMobileNo;
	}

	public String getEofrHrEmail() {
		return eofrHrEmail;
	}

	public void setEofrHrEmail(String eofrHrEmail) {
		this.eofrHrEmail = eofrHrEmail;
	}

	public String getEofrStatus() {
		return eofrStatus;
	}

	public void setEofrStatus(String eofrStatus) {
		this.eofrStatus = eofrStatus;
	}
}
