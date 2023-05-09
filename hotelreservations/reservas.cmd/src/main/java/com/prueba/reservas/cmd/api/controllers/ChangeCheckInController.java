package com.prueba.reservas.cmd.api.controllers;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.cqrs.core.infrastructure.CommandDispatcher;
import com.prueba.reservas.cmd.api.command.ChangeCheckInCommand;
import com.prueba.reservas.cmd.api.command.DeleteSearchCommand;
import com.prueba.reservas.common.dto.BaseResponse;

@RestController
@RequestMapping(path="/v1/changecheckin")
public class ChangeCheckInController {
	private final Logger logger=Logger.getLogger(ChangeCheckInController.class.getName());
	
	@Autowired 
	private CommandDispatcher commandDispatcher;
	
	@PatchMapping(path="/{id}")
	public ResponseEntity<BaseResponse> openAccount(@Valid @PathVariable(value="id") String id,@RequestBody ChangeCheckInCommand command){
	
		
		try {	
			command.setId(id);
			commandDispatcher.send(command);
			return new ResponseEntity<BaseResponse>(new BaseResponse("The checkin has been change successfully"),HttpStatus.OK);
		}catch (IllegalStateException e) {
			logger.log(Level.WARNING,MessageFormat.format("Could not change checkin  - {0} ", e.toString()));
			return new ResponseEntity<BaseResponse>(new BaseResponse(e.toString()),HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			var safeErrorMessage=MessageFormat.format("Errors while processing the request - {0} ", id);
			logger.log(Level.SEVERE,safeErrorMessage,e);
			return new ResponseEntity<BaseResponse>(new BaseResponse(safeErrorMessage),HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
}
