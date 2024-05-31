package ma.adnan.ebankingbackend.mappers;

import ma.adnan.ebankingbackend.dto.CustomersDto;
import ma.adnan.ebankingbackend.entities.Customers;
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
}
