package com.healthify.api.exception;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author RAM
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public HashMap<String, Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
	    HashMap<String, Object> map = new HashMap<>();

	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String formattedTime = sdf.format(new Date());

	    map.put("Time", formattedTime);

	    List<Map<String, String>> errors = new ArrayList<>();
	    List<FieldError> fieldErrors = ex.getFieldErrors();

	    for (FieldError fieldError : fieldErrors) {
	        Map<String, String> errorDetail = new HashMap<>();

	        String fieldName = fieldError.getField();
	        String errorMessage = fieldError.getDefaultMessage();

	        // Check if a custom message is provided by the annotation
	        if (errorMessage == null || errorMessage.isEmpty()) {
	            // If not, use a generic message
	            errorMessage = "Validation failed for field '" + fieldName + "'";
	        }

	        errorDetail.put("field", fieldName);
	        errorDetail.put("message", errorMessage);

	        errors.add(errorDetail);
	    }

	    map.put("errors", errors);

	    return map;
	}



	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> badCredentialsException(BadCredentialsException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.OK);

	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<String> invalidCredientials(InvalidCredentialsException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.OK);
	}

	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<String> resourceAlreadyExists(ResourceAlreadyExistsException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.OK);
	}

	@ExceptionHandler(SomethingWentWrongException.class)
	public ResponseEntity<String> somethingWentWrongException(SomethingWentWrongException ex) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.OK);
	}
	
	

}
