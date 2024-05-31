package ma.adnan.ebankingbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.adnan.ebankingbackend.entities.BankAccount;

import java.util.List;

/**
 * @author Dell Latitude 5420
 * IFRAH soumia
 **/
@Data
public class CustomersDto {
    private Long id;
    private String name;
    private String email;

}
