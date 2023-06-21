package models.input.output;

public class EmploymentInductionDocumentViewModel {
	private int emplid;
	private int emid_idty_id;
	private String documentData;
	private String verified;

	public EmploymentInductionDocumentViewModel(int emplid, int emid_idty_id, String documentData, String verified) {
		this.emplid = emplid;
		this.emid_idty_id = emid_idty_id;
		this.documentData = documentData;
		this.verified = verified;
	}

	public int getEmplid() {
		return emplid;
	}

	public void setEmplid(int emplid) {
		this.emplid = emplid;
	}

	public int getEmid_idty_id() {
		return emid_idty_id;
	}

	public void setEmid_idty_id(int emid_idty_id) {
		this.emid_idty_id = emid_idty_id;
	}

	public String getDocumentData() {
		return documentData;
	}

	public void setDocumentData(String documentData) {
		this.documentData = documentData;
	}

	public String getVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}
}