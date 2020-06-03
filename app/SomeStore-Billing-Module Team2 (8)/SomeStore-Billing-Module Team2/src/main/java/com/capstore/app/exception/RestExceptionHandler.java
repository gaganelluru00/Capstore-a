package main.java.com.capstore.app.exception;

import javax.persistence.EntityNotFoundException;


import org.json.JSONException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import net.minidev.json.JSONObject;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

	
	// other exception handlers below
 @ExceptionHandler(UserAlreadyExistsException.class)
 public ResponseEntity<?> userAlreadyErrorMessage(UserAlreadyExistsException unf) {
		JSONObject obj = new JSONObject();
		obj.put("error", "true");
		obj.put("message", unf.getMessage());
		return ResponseEntity.ok().body(obj);
	}
 @ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> noUserErrorMessage(UserNotFoundException unf) {
		JSONObject obj = new JSONObject();
		obj.put("error", "true");
		obj.put("message", unf.getMessage());
		return ResponseEntity.ok().body(obj);
	}
 
 @ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<?> invalidUserErrorMessage(InvalidUserException unf) {
		JSONObject obj = new JSONObject();
		obj.put("error", "true");
		obj.put("message", unf.getMessage());
		return ResponseEntity.ok().body(obj);
	}
 
}

