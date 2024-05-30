package ma.adnan.ebankingbackend.repositories;

import ma.adnan.ebankingbackend.entities.Operations;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dell Latitude 5420
 * IFRAH ADNAN
 **/
public interface BankOperationRepo extends JpaRepository<Operations,Long> {
}
