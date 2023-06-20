package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name = "hrms_employmentofferdocuments")
public class EmploymentOfferDocument implements Serializable {

	@Id
	@Column(name = "eofd_id", insertable = false, updatable = false)
	private int offerid;

	@Column(name = "eofd_docindex")
	private int documentIndex;

	@Column(name = "eofd_idty_id", insertable = false, updatable = false)
	private int offeridentity;

	@ManyToOne
	@JoinColumn(name = "eofd_id", referencedColumnName = "eofr_cand_id", insertable = false, updatable = false)
	private HrmsEmploymentOffer employmentOffer;

	@ManyToOne
	@JoinColumn(name = "eofd_idty_id", referencedColumnName = "idty_id", insertable = false, updatable = false)
	private InductionDocumentTypes documentType;

	public int getOfferid() {
		return offerid;
	}

	public void setOfferid(int offerid) {
		this.offerid = offerid;
	}

	public int getDocumentIndex() {
		return documentIndex;
	}

	public void setDocumentIndex(int documentIndex) {
		this.documentIndex = documentIndex;
	}

	public int getOfferidentity() {
		return offeridentity;
	}

	public void setOfferidentity(int offeridentity) {
		this.offeridentity = offeridentity;
	}

	public HrmsEmploymentOffer getEmploymentOffer() {
		return employmentOffer;
	}

	public void setEmploymentOffer(HrmsEmploymentOffer employmentOffer) {
		this.employmentOffer = employmentOffer;
	}

	public InductionDocumentTypes getDocumentType() {
		return documentType;
	}

	public void setDocumentType(InductionDocumentTypes documentType) {
		this.documentType = documentType;
	}

	// Constructors, getters, and setters
	/**
	 * // Override equals() and hashCode() methods
	 * 
	 * @Override public boolean equals(Object o) { if (this == o) { return true; } if (o == null || getClass() !=
	 *           o.getClass()) { return false; } EmploymentOfferDocument that = (EmploymentOfferDocument) o; return
	 *           Objects.equals(id, that.id); }
	 * 
	 * @Override public int hashCode() { return Objects.hash(id); }
	 **/
}
