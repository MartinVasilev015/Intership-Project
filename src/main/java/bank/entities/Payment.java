package bank.entities;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Payment
{
	private String status;
	private Integer amount;
	private String shortdesc;
	private String longdesc;
	
	public Payment() {}
}
