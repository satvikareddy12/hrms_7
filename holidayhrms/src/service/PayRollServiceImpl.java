package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service_interfaces.PayRollService;

public class PayRollServiceImpl implements PayRollService {

	private double total, basicpay, netpay, hra, ta, da, additions;
	private double deductions_sal, pf, esi, gratuity;
	private double ptax = 200;
	private double tax = 0;
	private final Logger logger = LoggerFactory.getLogger(PayRollServiceImpl.class);

	@Override
	public double calculateBasicPay(double basicpay) {
		// TODO Auto-generated method stub
		logger.info("calculating basic pay");

		this.basicpay = basicpay;
		return basicpay;
	}

	@Override
	public double calculateHRA(double fixedpay) {
		logger.info("calculating fixed pay");

		this.hra = fixedpay * 0.4;
		return hra;
	}

	@Override
	public double calculateDA(double fixedpay) {
		logger.info("calculating da");
		this.da = fixedpay * 0.3;
		return da;
	}

	@Override
	public double calculateTA(double fixedpay) {
		logger.info("calculating Ta");
		this.ta = fixedpay * 0.3;
		return ta;
	}

	@Override
	public double calculateAdditions(double additionalPay) {
		logger.info("calculating Additons");
		this.additions = additionalPay;
		return additions;
	}

	@Override
	public double calculatePF(double ctc) {
		logger.info("calculating pf");
		this.pf = (0.6 * ctc) / 12;

		return pf;
	}

	@Override
	public double calculateESI(double ctc) {
		logger.info("calculating ESI");
		this.esi = (0.15 * ctc) / 12;

		return esi;
	}

	@Override
	public double calculateGratuity(double ctc) {
		logger.info("calculating gratuity");
		this.gratuity = (0.25 * ctc) / 12;

		return gratuity;
	}

	@Override
	public double calculateDeductions() {
		logger.info("calculating deduction");
		deductions_sal = gratuity + esi + pf + ptax + tax;
		return deductions_sal;
	}

	@Override
	public double calculateTax(double ctc) {
		logger.info("calculating tax");
		if (ctc <= 500000) {
			this.tax = 0;
		} else if (ctc > 500000 && ctc <= 800000) {
			this.tax = ((0.05 * (ctc - 500000)) / 12);
		} else if (ctc > 800000 && ctc <= 1100000) {
			this.tax = ((0.1 * (ctc - 800000)) / 12);
		} else {
			this.tax = ((0.2 * (ctc - 1100000)) / 12);
		}

		return this.tax;
	}

	@Override
	public double calculateNetPay() {
		logger.info("calculating net pay");
		this.netpay = (total + additions) - deductions_sal;

		return netpay;
	}

	@Override
	public double calculateTotalPay() {
		logger.info("calculating total pay");
		this.total = basicpay + hra + ta + da;
		return total;
	}

}
