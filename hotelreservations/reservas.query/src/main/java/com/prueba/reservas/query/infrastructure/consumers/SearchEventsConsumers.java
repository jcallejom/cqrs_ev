package com.prueba.reservas.query.infrastructure.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.prueba.reservas.common.events.ChangedCheckInEvent;
import com.prueba.reservas.common.events.DeletedSearchEvent;
import com.prueba.reservas.common.events.SearchSavedEvent;
import com.prueba.reservas.query.infrastructure.handlers.EventHandler;

@Service
public class SearchEventsConsumers implements EventsConsumers {
	
	@Autowired
	private EventHandler eventHandler;
	private final String HOTEL_AVAILABILITY_SEARCHES="hotel_availability_searches";
	
	//@KafkaListener(topics="${spring.kafka.template.default-topic}",groupId = "${spring.kafka.consumer.group-id}")
	@KafkaListener(topics="SearchSavedEvent",groupId = "${spring.kafka.consumer.group-id}")
	@Override
	public void consume(SearchSavedEvent event, Acknowledgment ack) {
		eventHandler.on(event);
		ack.acknowledge();//una vez consuma el mensaje tengo que indicaler a a kafka que ya fue consumido
	}
	@KafkaListener(topics="ChangedCheckInEvent",groupId = "${spring.kafka.consumer.group-id}")
	@Override
	public void consume(ChangedCheckInEvent event, Acknowledgment ack) {
		eventHandler.on(event);
		ack.acknowledge();
		
	}
	@KafkaListener(topics="DeletedSearchEvent",groupId = "${spring.kafka.consumer.group-id}")
	@Override
	public void consume(DeletedSearchEvent event, Acknowledgment ack) {
		eventHandler.on(event);
		ack.acknowledge();
	}

}
