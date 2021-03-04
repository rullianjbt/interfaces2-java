package models.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.entities.Contract;
import models.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService; 
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, Integer months) {
		List<Installment> list = new ArrayList<Installment>();
		double basicQuota = contract.getTotalValue() / months;
		Date startDate = contract.getDate();
		for (int i = 1; i <= months; i++) {
			double updatedQuota = basicQuota + onlinePaymentService.interest(basicQuota, i) + onlinePaymentService.paymentFee(basicQuota);
			Installment installment = new Installment(null, null);
			list.add(installment);
		}
	}

}
