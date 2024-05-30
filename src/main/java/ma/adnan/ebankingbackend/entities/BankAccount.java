package ma.adnan.ebankingbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Entity
public class BankAccount {
    @Id

    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    @ManyToOne
    private Customers customer;
    @OneToMany(mappedBy = "bankAccount")
    private List<Operations> operationsList;
}
