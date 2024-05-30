package ma.adnan.ebankingbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Dell Latitude 5420
 * IFRAH soumia
 **/
@Data @NoArgsConstructor @AllArgsConstructor
public class Customers {
    private Long id;
    private String name;
    private String email;
    private List<BankAccount> bankAccountList;
}
