package demo.service;

import demo.dto.request.otp.OTPReq;
import demo.dto.response.otp.OTPRes;

public interface OTPService {
    OTPRes checkOTP(OTPReq otpReq);
}
