package ma.adnan.ebankingbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.adnan.ebankingbackend.dto.CustomersDto;
import ma.adnan.ebankingbackend.entities.Customers;
import ma.adnan.ebankingbackend.exception.NotFoundCustomerException;
import ma.adnan.ebankingbackend.service.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dell Latitude 5420
 * IFRAH ADNAN
 **/
@RestController
@AllArgsConstructor
@Slf4j

public class CustomerRestController {
private BankAccountService bankAccountService;
@GetMapping("/customers")
public List<CustomersDto> customersList(){
    return bankAccountService.listCustomers();
}
@GetMapping("/customer/{id}")
public CustomersDto customerId(@PathVariable(name = "id") Long customerID) throws NotFoundCustomerException {
    return  bankAccountService.getCustomerById(customerID);
}
@PostMapping("/customer")
    public CustomersDto customersDto(@RequestBody CustomersDto customersDto){

return bankAccountService.saveCustomer(customersDto);
}
@PutMapping("/customer/{id}")
public CustomersDto updateCustomer(@RequestBody CustomersDto customersDto,@PathVariable(name = "id") Long id){
customersDto.setId(id);
return bankAccountService.updateCustomer(customersDto);

}
@DeleteMapping("/customer/{id}")
public  void DeleteCustomer(@PathVariable Long id) throws NotFoundCustomerException {
    bankAccountService.DeleteCustomer(id);

}
}
