package demo.service;


import demo.dto.request.transaction.TransactionReq;
import demo.dto.response.transaction.TransactionRes;

public interface TransactionService {
    TransactionRes getTransaction(TransactionReq transactionReq);
    TransactionRes transactionSuccess(TransactionReq transactionReq);
}
