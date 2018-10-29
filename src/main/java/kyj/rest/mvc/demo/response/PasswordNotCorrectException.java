package kyj.rest.mvc.demo.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="Post Password is NOT CORRECT")
public class PasswordNotCorrectException extends RuntimeException{
	private static final long serialVersionUID = -4818407658428129739L;
	
	String message;
	
	public PasswordNotCorrectException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
