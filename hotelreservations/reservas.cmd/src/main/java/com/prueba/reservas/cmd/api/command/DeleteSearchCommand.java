package com.prueba.reservas.cmd.api.command;

import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.prueba.cqrs.core.commands.BaseCommand;

import lombok.Data;

//@Validated
public final class DeleteSearchCommand extends BaseCommand{


	public DeleteSearchCommand(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	

}
