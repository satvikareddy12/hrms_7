package DAO;

public interface PayRollDao {

	// to calculate total

	double deductions(double basicPay, double healthInsurance, double gratuity, double pf, int unpaidLeave);

	double totalsal(double basicPay, double hra, double ta, double variablePay, double healthInsurance, double gratuity,
			double pf, int earnedLeave, int unpaidLeave);

	double grossPay(double basicPay, double hra, double ta, double variablePay, int earnedLeave);

}
