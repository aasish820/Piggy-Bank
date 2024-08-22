package com.vani.loans.exception;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	@Autowired
	private MessageSource messageSource;
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String errorMessage = error.getDefaultMessage();
			String fieldName = ((FieldError) error).getField();
			errors.put(fieldName, errorMessage);
		}

		);
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(LoanNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleLoanNotFoundException(AccountNotFoundException ex) {
	    Locale locale = LocaleContextHolder.getLocale();
	    String message = messageSource.getMessage("resource.notfound", new Object[] { ex.getMessage() }, locale);
	    Map<String, String> error = new HashMap<>();
	    error.put("message", message);
	    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}



