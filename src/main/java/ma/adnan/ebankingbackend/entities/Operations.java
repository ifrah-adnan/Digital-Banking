package ma.adnan.ebankingbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.adnan.ebankingbackend.enums.OperationType;

import java.util.Date;

/**
 * @author Dell Latitude 5420
 * IFRAH soumia
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Operations {
    private Long id ;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private BankAccount bankAccount;
}
