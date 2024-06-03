package ma.adnan.ebankingbackend.service;

import ma.adnan.ebankingbackend.dto.*;
import ma.adnan.ebankingbackend.entities.BankAccount;
import ma.adnan.ebankingbackend.entities.CurrentAccount;
import ma.adnan.ebankingbackend.entities.Customers;
import ma.adnan.ebankingbackend.entities.SavingAccount;
import ma.adnan.ebankingbackend.exception.BalanceNotSufficentException;
import ma.adnan.ebankingbackend.exception.NotFoundBankAccountException;
import ma.adnan.ebankingbackend.exception.NotFoundCustomerException;

import java.util.List;

/**
 * @author Dell Latitude 5420
 * IFRAH ADNAN
 **/
public interface BankAccountService {
     CustomersDto saveCustomer(CustomersDto customersDto);
     BankAccountCurrentDTO saveCurrentBankAccount(double initialBalance, Long customerId, double overDraft) throws NotFoundCustomerException;
     BankAccountDTOSAving saveSavingBankAccount(double initialBalance, Long customerId, double interestRate) throws NotFoundCustomerException;

     List<CustomersDto> listCustomers();
     BankAccountDTo getBankAccount(String accountId) throws NotFoundBankAccountException;
     void debit(String accountId,double amount,String description) throws NotFoundBankAccountException, BalanceNotSufficentException;
     void credit (String accountId,double amount,String description) throws BalanceNotSufficentException, NotFoundBankAccountException;
     void transfer(String accountIdSource,String accountIdDestination,double amount) throws BalanceNotSufficentException, NotFoundBankAccountException;
     List<BankAccountDTo> bankAccountList();
     CustomersDto getCustomerById(Long id) throws NotFoundCustomerException;


     CustomersDto updateCustomer(CustomersDto customersDto);
     void DeleteCustomer(Long id) throws NotFoundCustomerException;

     List<AccountOperationDto> accountHistory(String accountId);

     AccountHistoryDto getAccountHistory(String accountId,int page,int size) throws NotFoundBankAccountException;
}

