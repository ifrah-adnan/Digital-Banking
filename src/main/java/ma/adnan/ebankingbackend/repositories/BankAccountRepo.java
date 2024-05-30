package ma.adnan.ebankingbackend.repositories;

import ma.adnan.ebankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dell Latitude 5420
 * IFRAH ADNAN
 **/
public interface BankAccountRepo extends JpaRepository<BankAccount,String > {
}
