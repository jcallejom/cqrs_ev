package com.prueba.cqrs.core.infrastructure;

import com.prueba.cqrs.core.commands.BaseCommand;
import com.prueba.cqrs.core.commands.CommandHandlerMethod;

public interface CommandDispatcher {
	public <T extends BaseCommand> void registerHandler (Class<T> type, CommandHandlerMethod<T> handler);
	public void send(BaseCommand command);
	
}
