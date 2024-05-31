package ma.adnan.ebankingbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.adnan.ebankingbackend.enums.AccountStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Dell Latitude 5420
 * IFRAH soumia
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",length = 20)
public class BankAccount {
    @Id

    private String id;
    private double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING)

    private AccountStatus status;
    @ManyToOne
    private Customers customer;
    @OneToMany(mappedBy = "bankAccount" ,fetch = FetchType.EAGER)
    private List<Operations> operationsList;
    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
        if (createdAt == null) {
            createdAt = new Date();
        }
    }
}
