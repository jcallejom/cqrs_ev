package com.prueba.cqrs.core.infrastructure;

import java.util.List;

import com.prueba.cqrs.core.events.BaseEvent;

public interface EventStore {
	void saveEvents(String aggregateId,Iterable<BaseEvent> events,int expectedVersion);
	List<BaseEvent> getEvent(String aggregateId);
}
