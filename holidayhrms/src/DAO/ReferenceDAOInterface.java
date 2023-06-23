
package DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import models.EmployeeRefDocuments;

@Repository
public interface ReferenceDAOInterface {

	EmployeeRefDocuments findById(String id);

	void save(EmployeeRefDocuments document);

	List<EmployeeRefDocuments> getAllDocs();

	int getIndex();

	void deleteById(int id);

}