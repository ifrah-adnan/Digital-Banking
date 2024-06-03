package ma.adnan.ebankingbackend.repositories;

import ma.adnan.ebankingbackend.entities.Operations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Dell Latitude 5420
 * IFRAH ADNAN
 **/
public interface BankOperationRepo extends JpaRepository<Operations,Long> {
  List<Operations> findByBankAccount_Id(String accoundId);
  Page<Operations> findByBankAccount_Id(String accoundId, Pageable pageable);

}
