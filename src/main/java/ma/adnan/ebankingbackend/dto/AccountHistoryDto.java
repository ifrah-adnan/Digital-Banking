package ma.adnan.ebankingbackend.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Dell Latitude 5420
 * IFRAH ADNAN
 **/
@Data

public class AccountHistoryDto {
    private  String accountId;
    private double balance;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    public List<AccountOperationDto> accountOperationDtos;
}
