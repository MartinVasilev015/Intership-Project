package bank.entities;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC, force=true)
@JsonInclude(Include.NON_NULL)
public class Transaction extends Payment
{
	@JsonProperty("fee")	
	private Integer fee;
	
	@JsonProperty("total")	
	private Integer total;
}
