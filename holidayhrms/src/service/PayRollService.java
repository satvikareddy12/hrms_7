package service;

public interface PayRollService {

	// to calculate total

	double deductions(double basicPay, double esi, double gratuity, double pf, int unpaidLeave, double tsd);

	double totalsal(double basicPay, double fixedpay, double variablePay, double healthInsurance, double gratuity,
			double pf, int earnedLeave, int unpaidLeave, double tds);

	double grossPay(double basicPay, double fixedpay, double variablePay);

	double forHRA(double fixedpay);

	double forDA(double fixedpay);

	double forTA(double fixedpay);

	double additions(double additionalPay);

	double calTax(double ctc);

}
