package service;

public class PayRollServiceImpl implements PayRollService {

	private double gross;
	private double deductions_sal;

	@Override
	public double grossPay(double basicPay, double fixedpay, double variablePay, int earnedLeave) {
		// TODO Auto-generated method stub
		this.gross = basicPay + fixedpay + variablePay + ((basicPay * 0.008) * earnedLeave);

		return basicPay + fixedpay + variablePay + ((basicPay * 0.008) * earnedLeave);

	}

	@Override
	public double deductions(double basicPay, double healthInsurance, double gratuity, double pf, int unpaidLeave) {
		// TODO Auto-generated method stub
		this.deductions_sal = gratuity + healthInsurance + pf + ((basicPay * 0.005) * unpaidLeave);

		return gratuity + healthInsurance + pf + ((basicPay * 0.005) * unpaidLeave);

	}

	@Override
	public double totalsal(double basicPay, double fixedpay, double variablePay, double healthInsurance,
			double gratuity, double pf, int earnedLeave, int unpaidLeave) {
		// TODO Auto-generated method stub
		return grossPay(basicPay, fixedpay, variablePay, earnedLeave)
				- deductions(basicPay, healthInsurance, gratuity, pf, unpaidLeave);

	}

	@Override
	public double calCTC() {
		// TODO Auto-generated method stub
		double ctc = (gross + deductions_sal) * 12;
		System.out.println(ctc);

		return ctc;
	}

	@Override
	public double forHRA(double fixedpay) {
		// TODO Auto-generated method stub
		return fixedpay * 0.4;
	}

	@Override
	public double forspecialAllowance(double fixedpay) {
		// TODO Auto-generated method stub
		return fixedpay * 0.6;
	}

}
