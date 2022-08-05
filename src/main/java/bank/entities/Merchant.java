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
	private String merchantId;   
	private String fullName;            
	private String fullNameEn;         
	private String webName;             
	private String subscrNumberType;    
	private boolean active;               
	private int category;             
	private String categoryName;         
	private int merchantType;         
	private boolean merchantPartial;     
	private boolean merchantInvoice;    
	private String merchantShortDesc;   
	private String merchantLongDesc;   
	private String subscrNumberRe;   
	private String subscrNumberReHelp;	
}
