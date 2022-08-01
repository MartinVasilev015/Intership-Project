package bank.entities;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)

public class Merchant
{
	private String merchant_id;   
	private String full_name;            
	private String full_name_en;         
	private String web_name;             
	private String subscr_number_type;    
	private boolean active;               
	private int category;             
	private String category_name;         
	private int merchant_type;         
	private boolean merchant_partial;     
	private boolean merchant_invoice;    
	private String merchant_short_desc;   
	private String merchant_long_desc;   
	private String subscr_number_re;   
	private String subscr_number_re_help;	
}
