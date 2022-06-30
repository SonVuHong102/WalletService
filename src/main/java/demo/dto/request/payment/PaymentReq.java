package demo.dto.request.payment;


import demo.dto.request.IRequestData;
import lombok.Data;

@Data
public class PaymentReq implements IRequestData {
    int sp_merchantId;
    long sp_amount;
    int sp_orderId;
    String sp_orderDescription;
    String sp_returnUrl;
    String sp_secureHash;
}
