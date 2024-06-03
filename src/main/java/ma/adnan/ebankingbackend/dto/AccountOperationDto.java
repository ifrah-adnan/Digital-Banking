package ma.adnan.ebankingbackend.dto;

import lombok.Data;
import ma.adnan.ebankingbackend.enums.OperationType;

import java.util.Date;

/**
 * @author Dell Latitude 5420
 * IFRAH ADNAN
 **/
@Data
public class AccountOperationDto {
private  Long id;
private Date operationDate;
private double amount;
private OperationType type;
private String description;
}
