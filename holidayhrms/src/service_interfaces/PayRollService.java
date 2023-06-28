package service_interfaces;

public interface PayRollService {

	// to calculate total
	double basicPay(double basicpay);

	double forHRA(double fixedpay);

	double forDA(double fixedpay);

	double forTA(double fixedpay);

	double additions(double additionalPay);

	double calTax(double ctc);

	double pf(double ctc);

	double esi(double ctc);

	double gratuity(double ctc);

	double deductions();

	double netPay();

	double total();

}
