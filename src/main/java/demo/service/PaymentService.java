package demo.service;


import demo.dto.request.payment.PaymentReq;
import demo.dto.response.payment.PaymentRes;
import demo.utils.exception.ApplicationException;

public interface PaymentService {
    PaymentRes payment(PaymentReq paymentReq) throws ApplicationException;
    PaymentRes getTransaction(PaymentReq paymentReq) throws ApplicationException;
}
