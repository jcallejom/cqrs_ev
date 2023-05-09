package com.prueba.reservas.cmd.api.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.cqrs.core.handlers.EventSourcingHandler;
import com.prueba.reservas.cmd.domain.SearchAggregate;

@Service
public class SearchCommandHandler implements CommandHandler{
	
	@Autowired
	private EventSourcingHandler<SearchAggregate> eventSourcingHandler;

	@Override
	public void handle(SaveSearchCommand command) {
		var aggregate= new SearchAggregate(command);
		eventSourcingHandler.save(aggregate);
		
		
	}

	@Override
	public void handle(ChangeCheckInCommand command) {
		//estoy llamado aun aggreagate que ya existe por su id
		var aggregate=eventSourcingHandler.getById(command.getId());
		aggregate.changeCheckIn(command.getCheckIn());
		eventSourcingHandler.save(aggregate);
		
	}

	@Override
	public void handle(DeleteSearchCommand command) {
		var aggregate=eventSourcingHandler.getById(command.getId());
		aggregate.deleteSearch();
		eventSourcingHandler.save(aggregate);
		
	}

}
