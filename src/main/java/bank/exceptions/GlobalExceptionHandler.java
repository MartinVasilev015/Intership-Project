package bank.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler
{
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Object> handleCustomSException(CustomException ce)
	{
		
		String[] errorMessage = ce.getMessage().split(",");
		
		//String customErrorText = errorMessage.split(",");
		ExceptionDetails details = new ExceptionDetails(errorMessage[0], errorMessage[1]);
		
		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
	}

	
	
	
	@ExceptionHandler(Exception.class)
	public ExceptionDetails handleGlobalException(Exception e, WebRequest webRequest)
	{
		return new ExceptionDetails("01", e.getMessage());
	}
}
