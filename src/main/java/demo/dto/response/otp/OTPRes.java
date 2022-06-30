package demo.dto.response.otp;


import demo.dto.response.IResponseData;
import lombok.Data;

@Data
public class OTPRes implements IResponseData {
    boolean isValid;
}
