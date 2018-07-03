package fi.restservice.database;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class MobileNotFoundExceptions extends RuntimeException {

	public MobileNotFoundExceptions(Exception cause) {
		super(cause);
	}
	
}