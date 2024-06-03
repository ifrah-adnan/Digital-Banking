package ma.adnan.ebankingbackend.web;

import lombok.AllArgsConstructor;
import ma.adnan.ebankingbackend.dto.AccountHistoryDto;
import ma.adnan.ebankingbackend.dto.AccountOperationDto;
import ma.adnan.ebankingbackend.dto.BankAccountDTo;
import ma.adnan.ebankingbackend.entities.BankAccount;
import ma.adnan.ebankingbackend.exception.NotFoundBankAccountException;
import ma.adnan.ebankingbackend.service.BankAccountService;
import ma.adnan.ebankingbackend.service.BankAccountServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Dell Latitude 5420
 * IFRAH ADNAN
 **/@RestController
@AllArgsConstructor
public class BankAccountRestAPI {
     private BankAccountService bankAccountService;
     @GetMapping("/accounts/{accountId}")

     public BankAccountDTo getBankAccount(@PathVariable  String accountId) throws NotFoundBankAccountException {
          return bankAccountService.getBankAccount(accountId);
     }
     @GetMapping("/accounts")
     public List<BankAccountDTo> bankAccountDToList(){
          return  bankAccountService.bankAccountList();
     }
     @GetMapping("/accounts/{accountId}/operations")
     public List<AccountOperationDto>  getHistory(@PathVariable String accountId){
          return bankAccountService.accountHistory(accountId);
     }
     @GetMapping("/accounts/{accountId}/pageOperations")
     public AccountHistoryDto AccountHistroy(@PathVariable String accountId, @RequestParam(name = "page",defaultValue = "0") int page, @RequestParam(name = "size",defaultValue = "5") int size) throws NotFoundBankAccountException {
          return bankAccountService.getAccountHistory(accountId,page,size);
     }


}
