package bank.entities;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"count", "merchants"})
public class getMerchantResponseEntity
{
	@JsonProperty("merchants_count")
	@Setter(AccessLevel.NONE)
	private int count;
	
	private List<Merchant> merchants;
	
	public getMerchantResponseEntity(List<Merchant> merchants)
	{
		this.merchants = merchants;
		count = merchants.size();
	}
}
