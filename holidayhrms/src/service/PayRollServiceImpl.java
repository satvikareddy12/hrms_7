package service;

public class PayRollServiceImpl implements PayRollService {

	private double gross;
	private double deductions_sal;

	@Override
	public double grossPay(double basicPay, double fixedpay, double variablePay) {

		this.gross = basicPay + fixedpay + variablePay + ((basicPay * 0.008));

		return basicPay + fixedpay + variablePay + ((basicPay * 0.008));

	}

	@Override
	public double deductions(double basicPay, double esi, double gratuity, double pf, int unpaidLeave, double tds) {

		this.deductions_sal = gratuity + esi + pf + ((basicPay * 0.005) * unpaidLeave);

		return gratuity + esi + pf + ((basicPay * 0.005) * unpaidLeave);

	}

	@Override
	public double totalsal(double basicPay, double fixedpay, double variablePay, double esi, double gratuity, double pf,
			int earnedLeave, int unpaidLeave, double tds) {

		return grossPay(basicPay, fixedpay, variablePay) - deductions(basicPay, esi, gratuity, pf, unpaidLeave, tds);

	}

	@Override
	public double forHRA(double fixedpay) {

		return fixedpay * 0.4;
	}

	@Override
	public double forDA(double fixedpay) {

		return fixedpay * 0.3;
	}

	@Override
	public double forTA(double fixedpay) {

		return fixedpay * 0.3;
	}

	@Override
	public double additions(double additionalPay) {

		return 0;
	}

	@Override
	public double calTax(double ctc) {

		return 0;
	}

}
