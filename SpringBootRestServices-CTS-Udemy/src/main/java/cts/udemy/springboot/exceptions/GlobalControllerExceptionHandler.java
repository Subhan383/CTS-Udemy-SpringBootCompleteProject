package cts.udemy.springboot.exceptions;

import java.util.Date;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetail customErrorDetail = new CustomErrorDetail(new Date(),
				"Method Argument Not Valid Exception Occured", ex.getMessage());

		return new ResponseEntity<>(customErrorDetail, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetail customErrorDetail = new CustomErrorDetail(new Date(), "Method  Not Allowed Exception Occured",
				ex.getMessage());

		return new ResponseEntity<>(customErrorDetail, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public final ResponseEntity<Object> handleUserNameNotFoundException(UsernameNotFoundException ex,
			WebRequest request) {

		CustomErrorDetail customErrorDetail = new CustomErrorDetail(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(customErrorDetail, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstrainstViolationException(ConstraintViolationException ex,
			WebRequest request) {

		CustomErrorDetail customErrorDetail = new CustomErrorDetail(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(customErrorDetail, HttpStatus.BAD_REQUEST);
	}

}
