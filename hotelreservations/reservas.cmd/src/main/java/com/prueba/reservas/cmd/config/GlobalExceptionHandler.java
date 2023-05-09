package com.prueba.reservas.cmd.config;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.prueba.reservas.common.dto.BaseResponse;

//@ControllerAdvice(basePackages ="com.prueba" )
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	private final Logger logger=Logger.getLogger(GlobalExceptionHandler.class.getName());
		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request) {
			logger.log(Level.WARNING,MessageFormat.format("Could not generate search because validate error - {0} ", ex.getMessage()));
			return handleExceptionInternal(
				      ex, new BaseResponse(ex.toString()),
				      headers, status, request);
//			return new ResponseEntity<>( MessageFormat.format("Could not generate search because validate error - {0} ", ex.toString()),
//					HttpStatus.BAD_REQUEST);
//			return  new ResponseEntity<>(new BaseResponse(ex.toString()),HttpStatus.BAD_REQUEST);
		}
		
		
		@ExceptionHandler(ParseException.class)
		protected ResponseEntity<Object> handleMethodArgumentNotValid(ParseException ex) {
			logger.log(Level.WARNING,MessageFormat.format("Could not generate search because validate error - {0} ", ex.getMessage()));
//			return handleExceptionInternal(
//				      ex, new BaseResponse(ex.toString()),
//				      new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
			return new ResponseEntity<>(new BaseResponse(ex.toString()),HttpStatus.BAD_REQUEST);
		}
}
