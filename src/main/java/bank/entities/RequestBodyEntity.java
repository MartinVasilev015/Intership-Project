package bank.entities;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RequestBodyEntity
{
	public String transactionId;
	public String dateTime;
	public String merchantId;
	public String amount;
	public String subscrNumber;
}
