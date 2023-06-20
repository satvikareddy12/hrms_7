package DAO;

public class PayRollDaoImpl implements PayRollDao {
	// private double basicPay;
	// private double hra;
	// private double ta;
	// private double gratuity;
	// private double healthInsurance;
	// private double variablePay;
	// private double pf;
	//
	// private double gp;
	// private double dd;
	// private double ts;
	@Override
	public double grossPay(double basicPay, double hra, double ta, double variablePay, int earnedLeave) {
		// TODO Auto-generated method stub
		return basicPay + hra + ta + variablePay + ((basicPay * 0.008) * earnedLeave);

	}

	@Override
	public double deductions(double basicPay, double healthInsurance, double gratuity, double pf, int unpaidLeave) {
		// TODO Auto-generated method stub
		return gratuity + healthInsurance + pf + ((basicPay * 0.005) * unpaidLeave);
	}

	@Override
	public double totalsal(double basicPay, double hra, double ta, double variablePay, double healthInsurance,
			double gratuity, double pf, int earnedLeave, int unpaidLeave) {
		// TODO Auto-generated method stub
		return grossPay(basicPay, hra, ta, variablePay, earnedLeave)
				- deductions(basicPay, healthInsurance, gratuity, pf, unpaidLeave);

	}

}
