package demo.dto.request.wallet;


import demo.dto.request.IRequestData;
import lombok.Data;
import demo.model.Wallet;

@Data
public class WalletReq extends Wallet implements IRequestData {
    int merchantId;
    long amount;
    int orderId;
    int transactionId;
    String orderDescription;
    String returnUrl;
}
