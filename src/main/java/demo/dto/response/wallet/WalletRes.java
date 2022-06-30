package demo.dto.response.wallet;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import demo.dto.response.IResponseData;
import lombok.Data;
import demo.model.Wallet;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class WalletRes implements IResponseData {
    Wallet wallet;
    int otpId = -1;
}
