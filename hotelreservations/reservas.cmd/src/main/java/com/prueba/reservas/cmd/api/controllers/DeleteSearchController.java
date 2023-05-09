package com.prueba.reservas.cmd.api.controllers;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.cqrs.core.infrastructure.CommandDispatcher;
import com.prueba.reservas.cmd.api.command.DeleteSearchCommand;
import com.prueba.reservas.cmd.api.vo.SaveSearchResponse;
import com.prueba.reservas.common.dto.BaseResponse;

@RestController
@RequestMapping(path="/v1/delete")
public class DeleteSearchController {
	private final Logger logger=Logger.getLogger(DeleteSearchController.class.getName());
	
	@Autowired 
	private CommandDispatcher commandDispatcher;
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<BaseResponse> openAccount(@Valid @PathVariable(value="id") String id){
	
		
		try {	
			commandDispatcher.send(new DeleteSearchCommand(id));
			return new ResponseEntity<BaseResponse>(new BaseResponse("The search has been removed successfully"),HttpStatus.OK);
		}catch (IllegalStateException e) {
			logger.log(Level.WARNING,MessageFormat.format("Could not removed search - {0} ", e.toString()));
			return new ResponseEntity<BaseResponse>(new BaseResponse(e.toString()),HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			var safeErrorMessage=MessageFormat.format("Errors while processing the request - {0} ", id);
			logger.log(Level.SEVERE,safeErrorMessage,e);
			return new ResponseEntity<BaseResponse>(new BaseResponse(safeErrorMessage),HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
}
