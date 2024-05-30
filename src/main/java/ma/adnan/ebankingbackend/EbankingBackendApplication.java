package ma.adnan.ebankingbackend;

import ma.adnan.ebankingbackend.entities.CurrentAccount;
import ma.adnan.ebankingbackend.entities.Customers;
import ma.adnan.ebankingbackend.entities.Operations;
import ma.adnan.ebankingbackend.entities.SavingAccount;
import ma.adnan.ebankingbackend.enums.AccountStatus;
import ma.adnan.ebankingbackend.enums.OperationType;
import ma.adnan.ebankingbackend.repositories.BankAccountRepo;
import ma.adnan.ebankingbackend.repositories.BankOperationRepo;
import ma.adnan.ebankingbackend.repositories.CustomerRepo;
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
	CommandLineRunner run(CustomerRepo customerRepo, BankAccountRepo bankAccountRepo, BankOperationRepo bankOperationRepo){

		return args -> {
			Stream.of("Hassan","ALi","Fatima").forEach(name->{
				Customers c=new Customers();
				c.setName(name);
				c.setEmail(name+"@email.com");
				customerRepo.save(c);
			});
			customerRepo.findAll().forEach(cust->{
				CurrentAccount currentAccount=new CurrentAccount();
				currentAccount.setBalance(Math.random()*9000);
				currentAccount.setCreatedAt(new Date());
				currentAccount.setStatus(AccountStatus.CREATED);
				currentAccount.setId(UUID.randomUUID().toString());
				currentAccount.setCustomer(cust);
				currentAccount.setOverDraft(9000);
				bankAccountRepo.save(currentAccount);
				SavingAccount savingAccount =new SavingAccount();
				savingAccount.setBalance(Math.random()*9000);
				savingAccount.setCreatedAt(new Date());
				savingAccount.setStatus(AccountStatus.CREATED);
				savingAccount.setCustomer(cust);
				savingAccount.setInterestRate(3.9);
				savingAccount.setId(UUID.randomUUID().toString());
				bankAccountRepo.save(savingAccount);
			});
			bankAccountRepo.findAll().forEach(acc->{
				Operations operations=new Operations();
				operations.setOperationDate(new Date());
				operations.setAmount(Math.random()*3394);
				operations.setType(Math.random()>0.5? OperationType.CREDIT:OperationType.DEBIT);
				operations.setBankAccount(acc);
				bankOperationRepo.save(operations);
			});
        };
    }

}
