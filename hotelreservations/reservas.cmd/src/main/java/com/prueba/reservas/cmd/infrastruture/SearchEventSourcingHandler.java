package com.prueba.reservas.cmd.infrastruture;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.cqrs.core.domain.AggregateRoot;
import com.prueba.cqrs.core.handlers.EventSourcingHandler;
import com.prueba.cqrs.core.infrastructure.EventStore;
import com.prueba.reservas.cmd.domain.SearchAggregate;

@Service
public class SearchEventSourcingHandler implements EventSourcingHandler<SearchAggregate>{
	
	@Autowired
	private EventStore eventStore;
	
	@Override
	public void save(AggregateRoot aggregate) {
		eventStore.saveEvents(aggregate.getId(), aggregate.geUncommitedChanges(), aggregate.getVersion());
		aggregate.markChangesAsCommited();
	}

	@Override
	public SearchAggregate getById(String id) {
		var aggregate=new SearchAggregate();
		var events=eventStore.getEvent(id);
		if(events!=null && !events.isEmpty()) {
			aggregate.replayEvent(events);
			var latestVersion=events.stream().map(x -> x.getVersion()).max(Comparator.naturalOrder());
			aggregate.setVersion(latestVersion.get());
		}
		
		return aggregate;
	}

}
