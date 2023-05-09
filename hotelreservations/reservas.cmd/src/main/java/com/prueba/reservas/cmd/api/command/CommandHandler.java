package com.prueba.reservas.cmd.api.command;

public interface CommandHandler {
	void handle (SaveSearchCommand command);
	void handle (ChangeCheckInCommand command);
	void handle (DeleteSearchCommand command);
}
