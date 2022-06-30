package demo.dto.request.merchant;


import demo.dto.request.IRequestData;
import lombok.Data;

@Data
public class MerchantReq implements IRequestData {
    int merchantId;
    long amount;
    int orderId;
    String orderDescription;
    String returnUrl;
    String secureCode;
}
