package demo.dto.request.transaction;


import demo.dto.request.IRequestData;
import lombok.Data;
import demo.model.Transaction;

@Data
public class TransactionReq extends Transaction implements IRequestData {
}
