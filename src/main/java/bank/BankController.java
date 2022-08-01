package bank;
import org.springframework.http.MediaType;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import bank.businesslogic.BankManager;
import bank.entities.Bill;
import bank.entities.Merchant;
import bank.entities.Payment;
import bank.entities.Transaction;

@RestController
@RequestMapping("/merchants")
public class BankController
{
	BankManager bm = new BankManager();
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE })
	public ArrayList<Merchant> getAllMerchants() 
	{
		return bm.getMerchantsData();
	}
	
	@PostMapping()
	public Bill checkBill(
			@RequestHeader(value="merchant_id") String merchatId,
			@RequestHeader(value="subscr_number") String subscrNumber)
	{
		return bm.checkBill(merchatId, subscrNumber);
	}
	
	@PostMapping("/blocked")
	public Payment checkBillBlocked(
			@RequestHeader(value="transaction_id") String transactionId,
			@RequestHeader(value="dt") String dateTime,
			@RequestHeader(value="merchant_id") String merchatId,
			@RequestHeader(value="subscr_number") String subscrNumber)
	{
		return bm.checkBillBlocked(transactionId, dateTime, merchatId, subscrNumber);
	}
	
	@PostMapping("/pay")
	public Payment payBill(
			@RequestHeader(value="transaction_id") String transactionId,
			@RequestHeader(value="dt") String dateTime,
			@RequestHeader(value="amount") String amount,
			@RequestHeader(value="merchant_id") String merchatId,
			@RequestHeader(value="subscr_number") String subscrNumber)
	{
		Transaction t =  bm.checkBillBlocked(transactionId, dateTime, merchatId, subscrNumber);
		
		return bm.checkBillForPay(t, amount);
	}		
	
	@PostMapping("/reverse")
	public Payment reverseBill(
			@RequestHeader(value="transaction_id") String transactionId,
			@RequestHeader(value="dt") String dateTime,
			@RequestHeader(value="amount") String amount,
			@RequestHeader(value="merchant_id") String merchatId,
			@RequestHeader(value="subscr_number") String subscrNumber)
	{
		return payBill(transactionId, dateTime, amount, merchatId, subscrNumber);
	}
	
	@PostMapping("/deposit")
	public Payment checkDeposit(
			@RequestHeader(value="transaction_id") String transactionId,
			@RequestHeader(value="dt") String dateTime,
			@RequestHeader(value="amount") String amount,
			@RequestHeader(value="merchant_id") String merchatId,
			@RequestHeader(value="subscr_number") String subscrNumber)
	{
		return bm.checkDeposit(transactionId, dateTime, amount, merchatId, subscrNumber);
	}
	
	@PostMapping("/pay/deposit")
	public Payment payDeposit(
			@RequestHeader(value="transaction_id") String transactionId,
			@RequestHeader(value="dt") String dateTime,
			@RequestHeader(value="amount") String amount,
			@RequestHeader(value="merchant_id") String merchatId,
			@RequestHeader(value="subscr_number") String subscrNumber)
	{
		return payBill(transactionId, dateTime, amount, merchatId, subscrNumber);
	}
	
	@PostMapping("/deposit/reverse")
	public Payment reverseDeposit(
			@RequestHeader(value="transaction_id") String transactionId,
			@RequestHeader(value="dt") String dateTime,
			@RequestHeader(value="amount") String amount,
			@RequestHeader(value="merchant_id") String merchatId,
			@RequestHeader(value="subscr_number") String subscrNumber)
	{
		return payBill(transactionId, dateTime, amount, merchatId, subscrNumber);
	}
}
