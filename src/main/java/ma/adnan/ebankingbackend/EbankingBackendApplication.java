package ma.adnan.ebankingbackend;

import ma.adnan.ebankingbackend.dto.CustomersDto;
import ma.adnan.ebankingbackend.entities.*;
import ma.adnan.ebankingbackend.enums.AccountStatus;
import ma.adnan.ebankingbackend.enums.OperationType;
import ma.adnan.ebankingbackend.exception.BalanceNotSufficentException;
import ma.adnan.ebankingbackend.exception.NotFoundBankAccountException;
import ma.adnan.ebankingbackend.exception.NotFoundCustomerException;
import ma.adnan.ebankingbackend.repositories.BankAccountRepo;
import ma.adnan.ebankingbackend.repositories.BankOperationRepo;
import ma.adnan.ebankingbackend.repositories.CustomerRepo;
import ma.adnan.ebankingbackend.service.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(EbankingBackendApplication.class, args);
	}
	@Bean
	CommandLineRunner run(BankAccountService bankAccountService) {

		return args -> {
			Stream.of("Hassan","ali","Adnan").forEach(name->{
				CustomersDto customers=new CustomersDto();
				customers.setName(name);
				customers.setEmail(name+"@gmail.com");

				bankAccountService.saveCustomer(customers);

			});
//			bankAccountService.listCustomers().forEach(customers -> {
//				try {
//					bankAccountService.saveSavingBankAccount(Math.random()*3333,customers.getId(),4.33);
//					bankAccountService.saveCurrentBankAccount(Math.random()*3332, customers.getId(),38888);
//					bankAccountService.bankAccountList().forEach(bankAccount -> {
//						for (int i = 0; i < 10; i++) {
//							try {
//								bankAccountService.credit(bankAccount.getId(), 10000+Math.random()*1200,"credit");
//								bankAccountService.debit(bankAccount.getId(), 1000+Math.random()*200,"debit");
//
//							} catch (BalanceNotSufficentException |NotFoundBankAccountException e) {
//								throw new RuntimeException(e);
//							}
//
//						}
//
//					});
//				} catch (NotFoundCustomerException e) {
//					throw new RuntimeException(e);
//				}
//			});


			};
		}


}
