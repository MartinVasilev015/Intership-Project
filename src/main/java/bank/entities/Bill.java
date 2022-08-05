package bank.entities;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC, force=true)
@JsonInclude(Include.NON_NULL)
public class Bill extends Payment
{
	
	@JsonProperty("validto")
	private String validTo;
	
	@JsonProperty("secondid")
	private String secondId;
}
