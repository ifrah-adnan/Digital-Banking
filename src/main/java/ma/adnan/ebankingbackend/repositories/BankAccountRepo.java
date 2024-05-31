package ma.adnan.ebankingbackend.repositories;

import ma.adnan.ebankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Dell Latitude 5420
 * IFRAH ADNAN
 **/
public interface BankAccountRepo extends JpaRepository<BankAccount,String > {
    List<BankAccount> findByCustomerId(Long customerId);

}
