package models.services;

import java.util.Calendar;
import java.util.Date;

import models.entities.Contract;
import models.entities.Installment;

public class ContractService {

	private OnlinePaymentService onlinePaymentService;

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, Integer months) {
		double basicQuota = contract.getTotalValue() / months;

		for (int i = 1; i <= months; i++) {
			// added the interest value to the basic quota
			double updatedQuota = basicQuota + onlinePaymentService.interest(basicQuota, i);
			// added the payment fee value to the basic quota + interest
			double fullQuota = updatedQuota + onlinePaymentService.paymentFee(updatedQuota);
			Date dueDateInstallment = addMonth(contract.getDate(), i);
			Installment installment = new Installment(dueDateInstallment, fullQuota);
			contract.getList().add(installment);
		}
	}

	private Date addMonth(Date date, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, n);
		return calendar.getTime();
	}

}
