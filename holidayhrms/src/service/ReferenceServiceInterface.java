package service;

import java.util.List;

import models.EmployeeRefDocuments;

public interface ReferenceServiceInterface {

	public EmployeeRefDocuments getReferenceDocumentById(String id);

	public void addReferenceDocument(EmployeeRefDocuments document);

	public void deleteReferenceDocument(String docname);

	public List<EmployeeRefDocuments> getAllDocuments();
}
