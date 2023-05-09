package com.prueba.reservas.cmd.api.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.prueba.cqrs.core.commands.BaseCommand;


public final class ChangeCheckInCommand extends BaseCommand{
	

	@NotBlank
	@NotNull(message = "checkIn cannot be null")
	private final String checkIn;
//	private Date checkIn;
	
	public ChangeCheckInCommand(String id, @NotBlank @NotNull(message = "checkIn cannot be null") String checkIn) {
		super(id);
		this.checkIn = checkIn;
	}

	public String getCheckIn() {
		return checkIn;
	}



	

}
