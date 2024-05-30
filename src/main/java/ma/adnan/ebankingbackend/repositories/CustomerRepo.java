package ma.adnan.ebankingbackend.repositories;

import ma.adnan.ebankingbackend.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dell Latitude 5420
 * IFRAH ADNAN
 **/
public interface CustomerRepo extends JpaRepository<Customers,Long> {

}
