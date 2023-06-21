package models.input.output;

public class EmployeePayRollInputModel {

	private int id;
	private String name;
	private String designation;
	private double basicPay;
	private double fixedPay;
	private double variablePay;
	private double gratuity;
	private double healthInsurance;
	private double pf;
	private int earnedLeave;
	private int unpaidLeave;

	// Default constructor
	public EmployeePayRollInputModel() {
	}

	// Getters and setters for the fields

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getBasicPay() {
		return basicPay;
	}

	public void setBasicPay(double basicPay) {
		this.basicPay = basicPay;
	}

	public double getFixedPay() {
		return fixedPay;
	}

	public void setFixedPay(double fixedPay) {
		this.fixedPay = fixedPay;
	}

	public double getVariablePay() {
		return variablePay;
	}

	public void setVariablePay(double variablePay) {
		this.variablePay = variablePay;
	}

	public double getGratuity() {
		return gratuity;
	}

	public void setGratuity(double gratuity) {
		this.gratuity = gratuity;
	}

	public double getHealthInsurance() {
		return healthInsurance;
	}

	public void setHealthInsurance(double healthInsurance) {
		this.healthInsurance = healthInsurance;
	}

	public double getPf() {
		return pf;
	}

	public void setPf(double pf) {
		this.pf = pf;
	}

	public int getEarnedLeave() {
		return earnedLeave;
	}

	public void setEarnedLeave(int earnedLeave) {
		this.earnedLeave = earnedLeave;
	}

	public int getUnpaidLeave() {
		return unpaidLeave;
	}

	public void setUnpaidLeave(int unpaidLeave) {
		this.unpaidLeave = unpaidLeave;
	}
}
