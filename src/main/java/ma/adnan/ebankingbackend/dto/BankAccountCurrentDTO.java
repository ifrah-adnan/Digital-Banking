package ma.adnan.ebankingbackend.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.adnan.ebankingbackend.entities.Customers;
import ma.adnan.ebankingbackend.entities.Operations;
import ma.adnan.ebankingbackend.enums.AccountStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Dell Latitude 5420
 * IFRAH soumia
 **/
@Data

public class BankAccountCurrentDTO extends BankAccountDTo{
    private String id;
    private double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING)

    private AccountStatus status;
    private CustomersDto customer;
    private double overDraft;
}
