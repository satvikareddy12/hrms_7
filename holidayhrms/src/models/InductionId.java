package models;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class InductionId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer indc_id;
	private Integer indc_emof_id;

	// Default constructor
	public InductionId() {
	}

	// Constructor with both fields
	public InductionId(Integer indc_id, Integer indc_emof_id) {
		this.indc_id = indc_id;
		this.indc_emof_id = indc_emof_id;
	}

	// Getters and Setters
	public Integer getindc_id() {
		return indc_id;
	}

	public void setindc_id(Integer indc_id) {
		this.indc_id = indc_id;
	}

	public Integer getindc_emof_id() {
		return indc_emof_id;
	}

	public void setindc_emof_id(Integer indc_emof_id) {
		this.indc_emof_id = indc_emof_id;
	}

	// Override equals() and hashCode() methods
	// ...

}
