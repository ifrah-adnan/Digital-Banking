package ma.adnan.ebankingbackend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.adnan.ebankingbackend.dto.*;
import ma.adnan.ebankingbackend.entities.*;
import ma.adnan.ebankingbackend.enums.OperationType;
import ma.adnan.ebankingbackend.exception.BalanceNotSufficentException;
import ma.adnan.ebankingbackend.exception.NotFoundBankAccountException;
import ma.adnan.ebankingbackend.exception.NotFoundCustomerException;
import ma.adnan.ebankingbackend.mappers.BankAccountMapperImpl;
import ma.adnan.ebankingbackend.repositories.BankAccountRepo;
import ma.adnan.ebankingbackend.repositories.BankOperationRepo;
import ma.adnan.ebankingbackend.repositories.CustomerRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dell Latitude 5420
 * IFRAH ADNAN
 **/
@Service
@Transactional
@AllArgsConstructor
@Slf4j

public class BankAccountServiceImpl implements BankAccountService {
    private CustomerRepo customerRepo;
    private BankAccountRepo bankAccountRepo;
    private BankOperationRepo bankOperationRepo;
    private BankAccountMapperImpl bankAccountMapper;

    @Override
    public CustomersDto saveCustomer(CustomersDto customersDto) {
        Customers customers = bankAccountMapper.fromCustomerDto(customersDto);
        log.info("Saving new Customer");
        Customers customers1 = customerRepo.save(customers);
        return bankAccountMapper.fromCustomer(customers1);
    }

    @Override
    public BankAccountCurrentDTO saveCurrentBankAccount(double initialBalance, Long customerId, double overDraft) throws NotFoundCustomerException {
        Customers customers = customerRepo.findById(customerId).orElse(null);
        if (customers == null) {
            throw new NotFoundCustomerException("Customer not found");
        }
        CurrentAccount currentAccount = new CurrentAccount();


        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCustomer(customers);
        currentAccount.setOverDraft(overDraft);
        return bankAccountMapper.fromCurrentBankAccount(bankAccountRepo.save(currentAccount));
    }

    @Override
    public BankAccountDTOSAving saveSavingBankAccount(double initialBalance, Long customerId, double interestRate) throws NotFoundCustomerException {
        Customers customers = customerRepo.findById(customerId).orElse(null);
        if (customers == null) {
            throw new NotFoundCustomerException("Customer not found");
        }
        SavingAccount savingAccount = new SavingAccount();


        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setCustomer(customers);
        savingAccount.setInterestRate(interestRate);
        return bankAccountMapper.fromSavingBankAccount(bankAccountRepo.save(savingAccount));
    }


    @Override
    public List<CustomersDto> listCustomers() {
        List<Customers> c = customerRepo.findAll();
       /* List<CustomersDto> customersDtos=new ArrayList<>();
        for (Customers customers:c){
            CustomersDto customersDto=bankAccountMapper.fromCustomer(customers);
            customersDtos.add(customersDto);

        }*/ //or
        List<CustomersDto> customersDtos = c.stream().map(customer ->
                bankAccountMapper.fromCustomer(customer)).collect(Collectors.toList());


        return customersDtos;
    }

    @Override
    public BankAccountDTo getBankAccount(String accountId) throws NotFoundBankAccountException {
        BankAccount bankAccount = bankAccountRepo.findById(accountId).orElseThrow(() -> new NotFoundBankAccountException("Bank accoun not found "));

        if (bankAccount instanceof SavingAccount) {
            SavingAccount savingAccount = (SavingAccount) bankAccount;
            return bankAccountMapper.fromSavingBankAccount(savingAccount);
        } else {
            CurrentAccount currentAccount = (CurrentAccount) bankAccount;

            return bankAccountMapper.fromCurrentBankAccount(currentAccount);
        }
    }

    @Override
    public void debit(String accountId, double amount, String description) throws NotFoundBankAccountException, BalanceNotSufficentException {
        BankAccount bankAccount = bankAccountRepo.findById(accountId).orElseThrow(() -> new NotFoundBankAccountException("Bank accoun not found "));
        if (bankAccount.getBalance() < amount) {
            throw new BalanceNotSufficentException("Balance not sufficient");
        }
        Operations operations = new Operations();
        operations.setType(OperationType.DEBIT);
        operations.setAmount(amount);
        operations.setDescription(description);
        operations.setOperationDate(new Date());
        operations.setBankAccount(bankAccount);
        bankOperationRepo.save(operations);
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        bankAccountRepo.save(bankAccount);

    }

    @Override
    public void credit(String accountId, double amount, String description) throws NotFoundBankAccountException {
        BankAccount bankAccount = bankAccountRepo.findById(accountId).orElseThrow(() -> new NotFoundBankAccountException("Bank accoun not found "));

        Operations operations = new Operations();
        operations.setType(OperationType.CREDIT);
        operations.setAmount(amount);
        operations.setDescription(description);
        operations.setOperationDate(new Date());
        operations.setBankAccount(bankAccount);
        bankOperationRepo.save(operations);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepo.save(bankAccount);

    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BalanceNotSufficentException, NotFoundBankAccountException {
        debit(accountIdSource, amount, "Transfer to" + accountIdDestination);
        credit(accountIdDestination, amount, "Transfer from " + accountIdSource);
    }

    @Override
    public List<BankAccountDTo> bankAccountList() {
        List<BankAccount> bankAccounts = bankAccountRepo.findAll();
        List<BankAccountDTo> collect= bankAccounts.stream().map(bankAccount -> {
            if (bankAccount instanceof SavingAccount) {
                SavingAccount savingAccount = (SavingAccount) bankAccount;
                return bankAccountMapper.fromSavingBankAccount(savingAccount);

            } else {
                CurrentAccount currentAccount = (CurrentAccount) bankAccount;
                return bankAccountMapper.fromCurrentBankAccount(currentAccount);
            }
        }).collect(Collectors.toList());

return collect;
    }


    @Override
    public CustomersDto getCustomerById(Long id) throws NotFoundCustomerException {
Customers customers=customerRepo.findById(id).orElseThrow(()->new NotFoundCustomerException("customer Not found"));
CustomersDto customersDto=bankAccountMapper.fromCustomer(customers);
return   customersDto;
    }
    @Override
    public CustomersDto updateCustomer(CustomersDto customersDto) {
        Customers customers=bankAccountMapper.fromCustomerDto(customersDto);
        log.info("Saving new Customer");
        Customers customers1= customerRepo.save(customers);
        return bankAccountMapper.fromCustomer(customers1);
    }

    @Override
    public void DeleteCustomer(Long id) throws NotFoundCustomerException {
        if (!customerRepo.existsById(id)) {
            throw new NotFoundCustomerException("Customer not found");
        }
        List<BankAccount> accounts = bankAccountRepo.findByCustomerId(id);
        for (BankAccount account : accounts) {
            bankAccountRepo.delete(account);
        }
        customerRepo.deleteById(id);
    }
    @Override
    public List<AccountOperationDto> accountHistory(String accountId){
        List<Operations> operations=bankOperationRepo.findByBankAccount_Id(accountId);
return operations.stream().map(operations1 ->
    bankAccountMapper.fromAccountoOperation(operations1)
).collect(Collectors.toList());
    }

    @Override
    public AccountHistoryDto getAccountHistory(String accountId,int page ,int size) throws NotFoundBankAccountException {
        BankAccount bankAccount=bankAccountRepo.findById(accountId).orElse(null);
        if (bankAccount==null){
            throw new NotFoundBankAccountException("Account not found");
        }
        Page<Operations> operationsPage=bankOperationRepo.findByBankAccount_Id(accountId, PageRequest.of(page,size));
        AccountHistoryDto accountHistoryDto=new AccountHistoryDto();
        accountHistoryDto.setAccountId(accountId);
       List<AccountOperationDto> operationDtos= operationsPage.getContent().stream().map(operations -> bankAccountMapper.fromAccountoOperation(operations)).collect(Collectors.toList());
        accountHistoryDto.setAccountOperationDtos(operationDtos);
        accountHistoryDto.setBalance(bankAccount.getBalance());
        accountHistoryDto.setPageSize(size);
        accountHistoryDto.setCurrentPage(page);
        accountHistoryDto.setTotalPages(operationsPage.getTotalPages());

        return accountHistoryDto;
    }


}
