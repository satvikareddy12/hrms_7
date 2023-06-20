package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JobGrades")
public class JobGrade {
    
    @Id
    @Column(name = "jbgr_id", length = 2)
    private String jbgrId;
    
    public JobGrade(String jbgrId, String jbgrName, String jbgrDescription) {
		this.jbgrId = jbgrId;
		this.jbgrName = jbgrName;
		this.jbgrDescription = jbgrDescription;
	}

	public JobGrade() {
	}

	@Column(name = "jbgr_name", length = 20)
    private String jbgrName;
    
    @Column(name = "jbgr_desc", length = 100)
    private String jbgrDescription;

	public String getJbgrId() {
		return jbgrId;
	}

	public void setJbgrId(String jbgrId) {
		this.jbgrId = jbgrId;
	}

	public String getJbgrName() {
		return jbgrName;
	}

	public void setJbgrName(String jbgrName) {
		this.jbgrName = jbgrName;
	}

	public String getJbgrDescription() {
		return jbgrDescription;
	}

	public void setJbgrDescription(String jbgrDescription) {
		this.jbgrDescription = jbgrDescription;
	}
    
    
}

