package demo.service.impl;

import demo.dto.request.merchant.MerchantReq;
import demo.dto.request.payment.PaymentReq;
import demo.dto.request.transaction.TransactionReq;
import demo.dto.request.wallet.WalletReq;
import demo.dto.response.merchant.MerchantRes;
import demo.dto.response.payment.PaymentRes;
import demo.dto.response.transaction.TransactionRes;
import demo.dto.response.wallet.WalletRes;
import demo.utils.exception.ApplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import demo.service.PaymentService;
import demo.utils.Constants;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public PaymentRes payment(PaymentReq paymentReq) throws ApplicationException {
        PaymentRes paymentRes = new PaymentRes();
        String uri = "http://localhost:8080/wallet/merchantVerify";
        RestTemplate restTemplate = new RestTemplate();
        MerchantReq merchantReq = new MerchantReq();
        BeanUtils.copyProperties(paymentReq,merchantReq);
        MerchantRes merchantRes = restTemplate.postForObject(uri,merchantReq, MerchantRes.class);
        if(merchantRes == null)
            throw new ApplicationException("ERR_0000");
        if(merchantRes.getMerchant() == null) {
            throw new ApplicationException("ERR_0001");
        }
        uri = "http://localhost:8080/wallet/walletCheck";
        WalletReq walletReq = new WalletReq();
        BeanUtils.copyProperties(paymentReq,walletReq);
        WalletRes walletRes = restTemplate.postForObject(uri,walletReq, WalletRes.class);
        if(walletRes == null)
            throw new ApplicationException("ERR_0000");
        if(walletRes.getWallet() == null) {
            throw new ApplicationException("ERR_0002");
        }
//        if(walletRes.getWallet().getBalance() < paymentReq.getAmount()) {
//            throw new ApplicationException("ERR_0003");
//        }
        uri = "http://localhost:8080/wallet/makeTransaction";
        TransactionReq transactionReq = new TransactionReq();
        BeanUtils.copyProperties(paymentReq,transactionReq);
        transactionReq.setSp_walletId(walletRes.getWallet().getId());
        TransactionRes transactionRes = restTemplate.postForObject(uri,transactionReq, TransactionRes.class);
        if(transactionRes == null)
            throw new ApplicationException("ERR_0000");
        if(transactionRes.getTransaction() == null) {
            throw new ApplicationException("ERR_0004");
        }
        paymentRes.setStatus(Constants.STATUS_DONE);
//        paymentRes.setReturnUrl(paymentReq.getReturnUrl());
        return paymentRes;
    }

    @Override
    public PaymentRes getTransaction(PaymentReq paymentReq) throws ApplicationException{
        PaymentRes paymentRes = new PaymentRes();
        String uri = "http://localhost:8080/wallet/getTransaction";
        TransactionReq transactionReq = new TransactionReq();
//        transactionReq.setId(paymentReq.getTransactionId());
        RestTemplate restTemplate = new RestTemplate();
        TransactionRes transactionRes = restTemplate.postForObject(uri,transactionReq, TransactionRes.class);
        if(transactionRes == null)
            throw new ApplicationException("ERR_0000");
        if(transactionRes.getTransaction() == null) {
            throw new ApplicationException("ERR_0005");
        }
        paymentRes.setTransaction(transactionRes.getTransaction());
        return paymentRes;
    }

}
