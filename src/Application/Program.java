package Application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import models.entities.Contract;
import models.services.ContractService;
import models.services.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Scanner scan = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println("Enter contract data");
		System.out.println("Number:");
		int numberContract = scan.nextInt();
		System.out.println("Date (dd/MM/yyyy):");
		Date dateContract = sdf.parse(scan.next());
		System.out.println("Contract value:");
		double contractValue = scan.nextDouble();

		Contract contract = new Contract(numberContract, dateContract, contractValue);
		System.out.println("Enter number of installments:");
		int n = scan.nextInt();
		ContractService contractService = new ContractService(new PaypalService());
		contractService.processContract(contract, n);

		System.out.println("Installments:");
		for (int i = 0; i < contract.getList().size(); i++) {
			System.out.println(contract.getList().get(i).toString());
		}

		scan.close();

	}

}
