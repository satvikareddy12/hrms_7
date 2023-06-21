package service;

public interface PayRollService {

	// to calculate total

	double deductions(double basicPay, double healthInsurance, double gratuity, double pf, int unpaidLeave);

	double totalsal(double basicPay, double fixedpay, double variablePay, double healthInsurance, double gratuity,
			double pf, int earnedLeave, int unpaidLeave);

	double grossPay(double basicPay, double fixedpay, double variablePay, int earnedLeave);

	double calCTC();

	double forHRA(double fixedpay);

	double forspecialAllowance(double fixedpay);

}
