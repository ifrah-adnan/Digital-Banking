package ma.adnan.ebankingbackend.mappers;

import ma.adnan.ebankingbackend.dto.AccountOperationDto;
import ma.adnan.ebankingbackend.dto.BankAccountCurrentDTO;
import ma.adnan.ebankingbackend.dto.BankAccountDTOSAving;
import ma.adnan.ebankingbackend.dto.CustomersDto;
import ma.adnan.ebankingbackend.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author Dell Latitude 5420
 * IFRAH ADNAN
 **/
@Service
public class BankAccountMapperImpl {
public CustomersDto fromCustomer(Customers customers){
    CustomersDto customersDto=new CustomersDto();
    BeanUtils.copyProperties(customers,customersDto);

//    customersDto.setId(customers.getId());
//    customersDto.setName(customers.getName());
//    customersDto.setEmail(customers.getEmail());

    return customersDto;
}
public Customers fromCustomerDto(CustomersDto customersDto){
   Customers customers=new Customers();
   BeanUtils.copyProperties(customersDto,customers);
    return  customers;
}
public BankAccountDTOSAving fromSavingBankAccount(SavingAccount bankAccount){
    BankAccountDTOSAving bankAccountDTOSAving=new BankAccountDTOSAving();
    BeanUtils.copyProperties(bankAccount,bankAccountDTOSAving);
    bankAccountDTOSAving.setCustomer(fromCustomer(bankAccount.getCustomer()));
    bankAccountDTOSAving.setType(bankAccount.getClass().getSimpleName());
    return bankAccountDTOSAving;
}
    public BankAccountCurrentDTO fromCurrentBankAccount(CurrentAccount bankAccount){
        BankAccountCurrentDTO bankAccountCurrentDTO=new BankAccountCurrentDTO();
        BeanUtils.copyProperties(bankAccount,bankAccountCurrentDTO);
        bankAccountCurrentDTO.setCustomer(fromCustomer(bankAccount.getCustomer()));
        bankAccountCurrentDTO.setType(bankAccount.getClass().getSimpleName());
        return bankAccountCurrentDTO;
    }
    public CurrentAccount fromBankAccountDTo(BankAccountCurrentDTO bankAccount){
        CurrentAccount currentAccount=new CurrentAccount();
        BeanUtils.copyProperties(bankAccount,currentAccount);
        currentAccount.setCustomer(fromCustomerDto(bankAccount.getCustomer()));
        return currentAccount;
    }
    public SavingAccount fromBankAccountDTo(BankAccountDTOSAving bankAccount){
        SavingAccount savingAccount=new SavingAccount();
        BeanUtils.copyProperties(bankAccount,savingAccount);
savingAccount.setCustomer(fromCustomerDto(bankAccount.getCustomer()));
        return savingAccount;
    }
    public AccountOperationDto fromAccountoOperation(Operations operations){
    AccountOperationDto accountOperationDto=new AccountOperationDto();
    BeanUtils.copyProperties(operations,accountOperationDto);
    return  accountOperationDto;
    }
    public Operations fromAccountOperationDto(AccountOperationDto accountOperationDto){
    Operations operations=new Operations();
    BeanUtils.copyProperties(accountOperationDto,operations);
    return  operations;
    }

}
