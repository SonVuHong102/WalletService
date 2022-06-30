package demo.service;

import demo.dto.request.payment.PaymentReq;
import demo.dto.request.transaction.TransactionReq;
import demo.dto.request.wallet.WalletReq;
import demo.dto.response.merchant.MerchantRes;
import demo.dto.response.payment.PaymentRes;
import demo.dto.response.transaction.TransactionRes;
import demo.dto.response.wallet.WalletRes;

public interface WalletService {
    PaymentRes payment(PaymentReq paymentReq);
    MerchantRes merchantVerify(PaymentReq paymentReq);
    WalletRes walletCheck(WalletReq walletReq);
    WalletRes sendOTP(WalletReq walletReq);
    WalletRes otpAuthenticate(WalletReq walletReq);
    WalletRes balanceCheck(WalletReq walletReq);
    TransactionRes makeTransaction(TransactionReq transactionReq);
    TransactionRes getTransaction(TransactionReq transactionReq);
    PaymentRes transactionchecking(Integer sp_merchantId,long sp_amount,Integer sp_orderId,String sp_secureHash);
}
