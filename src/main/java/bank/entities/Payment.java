package bank.entities;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Payment
{
	@JsonProperty("status")	
	private String status;
	
	@JsonProperty("amount")	
	private Integer amount;
	
	@JsonProperty("shortdesc")	
	private String shortDesc;
	
	@JsonProperty("longdesc")	
	private String longDesc;
	
}
