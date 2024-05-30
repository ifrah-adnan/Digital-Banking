package ma.adnan.ebankingbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.adnan.ebankingbackend.enums.AccountStatus;

import java.util.Date;

/**
 * @author Dell Latitude 5420
 * IFRAH soumia
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CurrentAccount extends BankAccount{
    private  double overDraft;


}
