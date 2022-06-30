package demo.service.impl;

import demo.dto.request.otp.OTPReq;
import demo.dto.response.otp.OTPRes;
import demo.model.OTP;
import demo.repository.OTPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demo.service.OTPService;

@Service
public class OTPServiceImpl implements OTPService {

    @Autowired
    OTPRepository otpRepository;

    @Override
    public OTPRes checkOTP(OTPReq otpReq) {
        OTPRes otpRes = new OTPRes();
        OTP otp = otpRepository.checkOTP(otpReq.getId(),otpReq.getOtpCode());
        otpRes.setValid(otp != null);
        return otpRes;
    }
}
