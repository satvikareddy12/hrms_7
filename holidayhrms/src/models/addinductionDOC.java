package models;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;

public class addinductionDOC {
	private int employmentOfferId;
	private int documentTypeId;
	private MultipartFile documentData;
	private int processedUserId;
	private String verified;

	// Getters and setters

	@NotNull(message = "employmentOfferId must be provided")
	public int getEmploymentOfferId() {
		return employmentOfferId;
	}

	public void setEmploymentOfferId(int employmentOfferId) {
		this.employmentOfferId = employmentOfferId;
	}

	@NotNull(message = "documentTypeId must be provided")
	public int getDocumentTypeId() {
		return documentTypeId;
	}

	public void setDocumentTypeId(int documentTypeId) {
		this.documentTypeId = documentTypeId;
	}

	@NotNull(message = "documentData must be provided")
	public MultipartFile getDocumentData() {
		return documentData;
	}

	public void setDocumentData(MultipartFile documentData) {
		this.documentData = documentData;
	}

	@NotNull(message = "processedUserId must be provided")
	public int getProcessedUserId() {
		return processedUserId;
	}

	public void setProcessedUserId(int processedUserId) {
		this.processedUserId = processedUserId;
	}

	@NotNull(message = "verified must be provided")
	public String getVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}

}
