package ma.adnan.ebankingbackend.dto;

import jakarta.persistence.*;
import lombok.Data;

import ma.adnan.ebankingbackend.enums.AccountStatus;

import java.util.Date;


/**
 * @author Dell Latitude 5420
 * IFRAH soumia
 **/
@Data
public class BankAccountDTOSAving extends BankAccountDTo{


    private String id;
    private double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING)

    private AccountStatus status;
    private CustomersDto customer;
    private double interestRate;

}
