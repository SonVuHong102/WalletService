package demo.controller;


import demo.dto.request.payment.PaymentReq;
import demo.dto.response.payment.PaymentRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import demo.service.PaymentService;
import demo.utils.exception.ApplicationException;

@Controller
@CrossOrigin
@RequestMapping("/payment1/")
public class MainController extends BaseController {

    @Autowired
    PaymentService paymentService;

    @PostMapping(value = "getTransaction")
    @ResponseBody
    public ResponseEntity getTransaction(@RequestBody PaymentReq paymentReq) {
        try {
            PaymentRes response = paymentService.getTransaction(paymentReq);
            return success(response);
        } catch (ApplicationException e) {
            return error(e.getCode(), e.getMessage());
        }
    }

}
