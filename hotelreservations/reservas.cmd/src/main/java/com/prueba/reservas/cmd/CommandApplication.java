package com.prueba.reservas.cmd;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.prueba.cqrs.core.infrastructure.CommandDispatcher;
import com.prueba.reservas.cmd.api.command.ChangeCheckInCommand;
import com.prueba.reservas.cmd.api.command.CommandHandler;
import com.prueba.reservas.cmd.api.command.DeleteSearchCommand;
import com.prueba.reservas.cmd.api.command.SaveSearchCommand;

@SpringBootApplication
public class CommandApplication {
	@Autowired
	private CommandHandler commandHandler;
	@Autowired
	private CommandDispatcher commandDispatcher;

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}
	@PostConstruct
	public void registerHandlers() {
		commandDispatcher.registerHandler(SaveSearchCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(ChangeCheckInCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(DeleteSearchCommand.class, commandHandler::handle);
	}
}
