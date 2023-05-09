package com.prueba.reservas.cmd.infrastruture;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.prueba.cqrs.core.commands.BaseCommand;
import com.prueba.cqrs.core.commands.CommandHandlerMethod;
import com.prueba.cqrs.core.infrastructure.CommandDispatcher;

@Service
public class SearchCommandDispatcher implements CommandDispatcher{
	
	private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>>routes= new HashMap<>();
	
	/**
	 * Agrega los handlers
	 */
	@Override
	public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
		
		var handlers=routes.computeIfAbsent(type, c-> new LinkedList<>());
		handlers.add(handler);
	}

	@Override
	public void send(BaseCommand command) {
	
		var handlers=routes.get(command.getClass());
		if(handlers==null || handlers.size()==0)
			throw new RuntimeException("The command handler was not registered");
		if (handlers.size()>1)
			throw new RuntimeException("You cannot send a command that has more handler");
		handlers.get(0).handle(command);
		
	}
}