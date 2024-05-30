package ma.adnan.ebankingbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.adnan.ebankingbackend.enums.AccountStatus;

import java.util.Date;
import java.util.List;

/**
 * @author Dell Latitude 5420
 * IFRAH soumia
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor

public class BankAccount {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private Customers customer;
    private List<Operations> operationsList;
}
