package demo.service.impl;


import demo.dto.request.payment.PaymentReq;
import demo.dto.request.transaction.TransactionReq;
import demo.dto.request.wallet.WalletReq;
import demo.dto.response.merchant.MerchantRes;
import demo.dto.response.payment.PaymentRes;
import demo.dto.response.transaction.TransactionRes;
import demo.dto.response.wallet.WalletRes;
import demo.repository.MerchantRepository;
import demo.model.Merchant;
import demo.model.OTP;
import demo.model.Transaction;
import demo.model.Wallet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import demo.repository.OTPRepository;
import demo.repository.TransactionRepository;
import demo.repository.WalletRepository;
import demo.service.EmailService;
import demo.service.WalletService;
import demo.utils.Constants;
import demo.utils.OTPGenerator;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    OTPRepository otpRepository;

    @Autowired
    EmailService emailService;

    @Override
    public PaymentRes payment(PaymentReq paymentReq) {
        PaymentRes paymentRes = new PaymentRes();
        // Xac minh xong
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(paymentReq,transaction);
        return null;

    }

    @Override
    public MerchantRes merchantVerify(@RequestBody PaymentReq paymentReq) {
        MerchantRes merchantRes = new MerchantRes();
        Merchant merchant = merchantRepository.getMerchant(paymentReq.getSp_merchantId());
        if(merchant != null) {
//            if(paymentReq.getSp_secureHash().compareTo(HashTool.sha256hash(merchant.getSecureCode()))!=0)
//                return merchantRes;
            merchantRes.setMerchant(merchant);
            Transaction transaction = new Transaction();
            BeanUtils.copyProperties(paymentReq,transaction);
            transaction.setStatus(Constants.STATUS_PENDING);
            transaction = transactionRepository.save(transaction);
            merchantRes.setTransactionId(transaction.getId());
        }
        return merchantRes;
    }

    @Override
    public WalletRes walletCheck(WalletReq walletReq) {
        WalletRes walletRes = new WalletRes();
        Wallet wallet = walletRepository.walletCheck(walletReq.getPhoneNumber(),walletReq.getPassword());
        walletRes.setWallet(wallet);
        return walletRes;
    }

    @Override
    public WalletRes sendOTP(WalletReq walletReq) {
        WalletRes walletRes = new WalletRes();
        String otpNum = OTPGenerator.genOTP();
        emailService.sendSimpleMessage(walletReq.getEmail(),walletReq.getOwner() + " OTP",otpNum);
        OTP otp = new OTP();
        otp.setOtpCode(otpNum);
        otp.setWalletId(walletReq.getId());
        otp.setTransactionId(walletReq.getTransactionId());
        otp = otpRepository.save(otp);
        walletRes.setOtpId(otp.getId());
        return walletRes;
    }

    @Override
    public WalletRes otpAuthenticate(WalletReq walletReq){
        WalletRes walletRes = new WalletRes();
        return null;
    }

    @Override
    public WalletRes balanceCheck(WalletReq walletReq){
        WalletRes walletRes = new WalletRes();
        return null;
    }

    @Override
    public TransactionRes makeTransaction(TransactionReq transactionReq) {
        TransactionRes transactionRes = new TransactionRes();
//        Wallet wallet = walletRepository.getById(transactionReq.getWalletId());
//        long walletAmount = wallet.getBalance() - transactionReq.getAmount();
//        walletRepository.makeTransaction(wallet.getId(),walletAmount);
//        Merchant merchant = merchantRepository.getMerchant(transactionReq.getMerchantId());
//        long merchantAmount = merchant.getBalance() + transactionReq.getAmount();
//        merchantRepository.makeTransaction(transactionReq.getMerchantId(),merchantAmount);
//        wallet = walletRepository.getById(wallet.getId());
//        Transaction transaction = new Transaction();
//        BeanUtils.copyProperties(transactionReq,transaction);
//        transaction.setStatus(Constants.STATUS_DONE);
//        transaction.setWalletId(wallet.getId());
//        transactionRepository.save(transaction);
//        transactionRes.setTransaction(transaction);
        return transactionRes;
    }

    @Override
    public TransactionRes getTransaction(TransactionReq transactionReq) {
        TransactionRes transactionRes = new TransactionRes();
        Transaction transaction = transactionRepository.getById(transactionReq.getId());
        transactionRes.setTransaction(transaction);
        return transactionRes;
    }

    @Override
    public PaymentRes transactionchecking(Integer sp_merchantId,long sp_amount,Integer sp_orderId,String sp_secureHash) {
        PaymentRes paymentRes = new PaymentRes();
        Transaction transaction = transactionRepository.getTransaction(sp_merchantId,sp_orderId);
        paymentRes.setTransaction(transaction);
        return paymentRes;
    }
}
