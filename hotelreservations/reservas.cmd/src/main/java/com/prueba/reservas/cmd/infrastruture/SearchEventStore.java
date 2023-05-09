package com.prueba.reservas.cmd.infrastruture;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.cqrs.core.events.BaseEvent;
import com.prueba.cqrs.core.events.EventModel;
import com.prueba.cqrs.core.exceptions.AggregateNotFoundException;
import com.prueba.cqrs.core.exceptions.ConcurrencyException;
import com.prueba.cqrs.core.infrastructure.EventStore;
import com.prueba.cqrs.core.produces.EventProducer;
import com.prueba.reservas.cmd.domain.EventStoreRepository;
import com.prueba.reservas.cmd.domain.SearchAggregate;

@Service
public class SearchEventStore implements EventStore{
	@Autowired
	private EventStoreRepository eventStoreRepository;

	@Autowired
	private EventProducer eventProducer;
	
	
	@Override
	public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
		//buscar la cadena de  eventos ya existentes
		var eventStream =eventStoreRepository.findByAggregateIdentifier(aggregateId);
		
		if(expectedVersion!=-1 && eventStream.get(eventStream.size()-1).getVersion()!=expectedVersion)
			throw new ConcurrencyException();
		
		var version =expectedVersion;
		for(var event: events) {
			version++;
			event.setVersion(version);
			var eventModel=EventModel.builder()
					.timeStamp(new Date())
					.aggregateIdentifier(aggregateId)
					.aggregateType(SearchAggregate.class.getTypeName())
					.version(version)
					.eventType(event.getClass().getTypeName())
					.eventData(event)
					.build();
			var persistEvent=eventStoreRepository.save(eventModel);
			//si la variable persistEvent es null significa que no se ha realizado la operacion
			//si no es null tengo que producir un evento para kafka
			if(!persistEvent.getId().isEmpty()) {
				//el nombre del topic deberia ser el mismo que el de la clase que genera este evento
				eventProducer.produce(event.getClass().getSimpleName(), event);
				//eventProducer.produce("${spring.kafka.template.default-topic}", event);
			}
		}
		
	}

	@Override
	public List<BaseEvent> getEvent(String aggregateId) {
		var eventStream =eventStoreRepository.findByAggregateIdentifier(aggregateId);
		if(eventStream==null && eventStream.isEmpty())
			throw new AggregateNotFoundException("the search is wrong");

		return eventStream.stream().map(x -> x.getEventData()).collect(Collectors.toList());
	}
}
