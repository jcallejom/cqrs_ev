package com.prueba.cqrs.core.produces;

import com.prueba.cqrs.core.events.BaseEvent;

public interface EventProducer {
	void produce(String topic, BaseEvent event);
}
