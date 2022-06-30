package demo.dto.response.merchant;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import demo.dto.response.IResponseData;
import lombok.Data;
import demo.model.Merchant;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Data
public class MerchantRes implements IResponseData {
    Merchant merchant;
    int transactionId;
}
