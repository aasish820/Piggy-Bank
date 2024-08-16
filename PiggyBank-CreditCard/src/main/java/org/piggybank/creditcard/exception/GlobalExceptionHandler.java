package org.piggybank.creditcard.exception;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
	@ExceptionHandler(CardNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(CardNotFoundException ex) {

		Locale locale = LocaleContextHolder.getLocale();

		String message = messageSource.getMessage("card.notfound", new Object[] { ex.getMessage().split(": ")[1] },
				locale);
		Map<String, String> error = new HashMap<>();
		error.put("message", message);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleAllExceptions(Exception ex) {

		Map<String, String> error = new HashMap<>();

		error.put("message", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
