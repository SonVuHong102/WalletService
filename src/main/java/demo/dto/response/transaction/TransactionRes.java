package demo.dto.response.transaction;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import demo.dto.response.IResponseData;
import lombok.Data;
import demo.model.Transaction;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Data
public class TransactionRes implements IResponseData {
    Transaction transaction;
    boolean success = false;
}
