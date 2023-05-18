package com.prueba.reservas.cmd.api.controllers;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.cqrs.core.infrastructure.CommandDispatcher;
import com.prueba.reservas.cmd.api.command.SaveSearchCommand;
import com.prueba.reservas.cmd.api.vo.SaveSearchResponse;
import com.prueba.reservas.common.dto.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path="/v1/search")
public class SaveSearchController {
	private final Logger logger=Logger.getLogger(SaveSearchController.class.getName());
	
	@Autowired 
	private CommandDispatcher commandDispatcher;
	@Operation(description = "save search bundled into Response", summary ="Return 400 if Could not generate search")
	  @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
	  @ApiResponse(responseCode = "500", description = "Internal error")})
	  @GetMapping
	@PostMapping
	public ResponseEntity<BaseResponse> openAccount(@Valid @RequestBody SaveSearchCommand command){
		var id=UUID.randomUUID().toString();
		command.setId(id);
		try {	
			commandDispatcher.send(command);
			return new ResponseEntity<BaseResponse>(new SaveSearchResponse("The search has been saved successfully",id),HttpStatus.CREATED);
		}catch (IllegalStateException e) {
			logger.log(Level.WARNING,MessageFormat.format("Could not generate search - {0} ", e.toString()));
			return new ResponseEntity<BaseResponse>(new BaseResponse(e.toString()),HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			var safeErrorMessage=MessageFormat.format("Errors while processing the request - {0} ", id);
			logger.log(Level.SEVERE,safeErrorMessage,e);
			return new ResponseEntity<BaseResponse>(new SaveSearchResponse(safeErrorMessage,id),HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
}
