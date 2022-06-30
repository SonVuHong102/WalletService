package demo.service.impl;


import demo.dto.request.transaction.TransactionReq;
import demo.dto.response.transaction.TransactionRes;
import demo.repository.MerchantRepository;
import demo.repository.TransactionRepository;
import demo.service.TransactionService;
import demo.model.Merchant;
import demo.model.Transaction;
import demo.model.Wallet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demo.repository.WalletRepository;
import demo.utils.Constants;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    WalletRepository walletRepository;

    @Override
    public TransactionRes getTransaction(TransactionReq transactionReq) {
        TransactionRes transactionRes = new TransactionRes();
        Transaction transaction = transactionRepository.getById(transactionReq.getId());
        transactionRes.setTransaction(transaction);
        return transactionRes;
    }

    @Override
    public TransactionRes transactionSuccess(TransactionReq transactionReq) {
        TransactionRes transactionRes = new TransactionRes();
        Wallet wallet = walletRepository.getById(transactionReq.getSp_walletId());
        if(wallet.getBalance() < transactionReq.getSp_amount())
            return transactionRes;
        long walletAmount = wallet.getBalance() - transactionReq.getSp_amount();
        walletRepository.makeTransaction(wallet.getId(),walletAmount);
        Merchant merchant = merchantRepository.getMerchant(transactionReq.getSp_merchantId());
        long merchantAmount = merchant.getBalance() + transactionReq.getSp_amount();
        merchantRepository.makeTransaction(merchant.getId(),merchantAmount);
        transactionReq.setStatus(Constants.STATUS_DONE);
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionReq,transaction);
        transactionRepository.save(transaction);
        transactionRes.setSuccess(true);
        return transactionRes;
    }
}
