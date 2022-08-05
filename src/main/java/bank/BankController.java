package bank;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import bank.businesslogic.BankManager;
import bank.entities.Bill;
import bank.entities.Payment;
import bank.entities.RequestBodyEntity;
import bank.entities.Transaction;
import bank.entities.getMerchantResponseEntity;
import bank.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/merchants")
@Slf4j
public class BankController
{
	BankManager bm = new BankManager();
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE })
	public getMerchantResponseEntity getAllMerchants() 
	{
		log.info("User entered getAllMerchants endpoint");
		return bm.getMerchantsData();
	}
	
	@PostMapping()
	public Bill checkBill(@RequestBody RequestBodyEntity requestEntity) throws CustomException
	{
		log.info("User entered checkBill endpoint");
		
		return bm.checkBill(requestEntity.merchantId, requestEntity.subscrNumber);
	}
	
	@PostMapping("/blocked")
	public Payment checkBillBlocked(@RequestBody RequestBodyEntity requestEntity) throws CustomException
	{
		Payment result = new Payment();
		
		result = bm.checkBillBlocked(requestEntity.transactionId, requestEntity.dateTime, 
					requestEntity.merchantId, requestEntity.subscrNumber);
		
		return result;
	}
	
	@PostMapping("/pay")
	public Payment payBill(@RequestBody RequestBodyEntity requestEntity) throws CustomException
	{
		Transaction t = bm.checkBillBlocked(requestEntity.transactionId, requestEntity.dateTime, 
					requestEntity.merchantId, requestEntity.subscrNumber);
				
		return bm.checkBillForPay(t, requestEntity.amount);
	}		
	
	@PostMapping("/reverse")
	public Payment reverseBill(@RequestBody RequestBodyEntity requestEntity) throws CustomException
	{	
		return payBill(requestEntity);
	}
	
	@PostMapping("/deposit")
	public Payment checkDeposit(@RequestBody RequestBodyEntity requestEntity) throws CustomException
	{	
		return bm.checkDeposit(requestEntity.transactionId, requestEntity.dateTime, 
				requestEntity.amount, requestEntity.merchantId, requestEntity.subscrNumber);
	}
	
	@PostMapping("/pay/deposit")
	public Payment payDeposit(@RequestBody RequestBodyEntity requestEntity) throws CustomException
	{
		return payBill(requestEntity);
	}
	
	@PostMapping("/deposit/reverse")
	public Payment reverseDeposit(@RequestBody RequestBodyEntity requestEntity) throws CustomException
	{
		return payBill(requestEntity);
	}
}