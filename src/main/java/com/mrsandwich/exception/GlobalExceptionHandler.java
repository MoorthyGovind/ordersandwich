package com.mrsandwich.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mrsandwich.constant.AppConstant;
import com.mrsandwich.dto.ResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handle the not valid field errors along with validation messages and get the
	 * list of multiple field errors.
	 * 
	 * @author Govindasamy.C
	 * @since 05-02-2020
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("date", LocalDateTime.now());
		body.put("status", status.value());

		// Get all errors for field valid
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());

		body.put("errors", errors);
		return new ResponseEntity<>(body, headers, HttpStatus.OK);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ResponseDto> orderNotFoundException(OrderNotFoundException ex) {
		ResponseDto errorDto = new ResponseDto();
		errorDto.setMessage(AppConstant.ORDER_NOT_FOUND);
		errorDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.OK).body(errorDto);
	}
	
	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<ResponseDto> itemNotFoundException(ItemNotFoundException ex) {
		ResponseDto errorDto = new ResponseDto();
		errorDto.setMessage(AppConstant.ORDER_NOT_FOUND);
		errorDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.OK).body(errorDto);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseDto> userNotFoundException(UserNotFoundException ex) {
		ResponseDto errorDto = new ResponseDto();
		errorDto.setMessage(AppConstant.ORDER_NOT_FOUND);
		errorDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.OK).body(errorDto);
	}

}
