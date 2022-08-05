package bank.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)

public class Merchant
{
	@JsonProperty("merchant_id")
	private String merchantId;
	
	@JsonProperty("full_name")
	private String fullName; 
	
	@JsonProperty("full_name_en")
	private String fullNameEn;  
	
	@JsonProperty("web_name")
	private String webName; 
	
	@JsonProperty("subscr_number_type")		
	private String subscrNumberType;   
	
	@JsonProperty("active")	
	private boolean active;      
	
	@JsonProperty("category")	
	private int category;  
	
	@JsonProperty("category_name")	
	private String categoryName;
	
	@JsonProperty("merchant_type")	
	private int merchantType;   
	
	@JsonProperty("merchant_partial")	
	private boolean merchantPartial; 
	
	@JsonProperty("merchant_invoice")	
	private boolean merchantInvoice; 
	
	@JsonProperty("merchant_short_desc")	
	private String merchantShortDesc; 
	
	@JsonProperty("merchant_long_desc")	
	private String merchantLongDesc;   
	
	@JsonProperty("subscr_number_re")	
	private String subscrNumberRe;   
	
	@JsonProperty("subscr_number_re_help")	
	private String subscrNumberReHelp;	
}

//custom names in response (in docs)