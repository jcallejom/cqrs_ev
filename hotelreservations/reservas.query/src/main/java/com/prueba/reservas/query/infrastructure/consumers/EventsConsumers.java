package com.prueba.reservas.query.infrastructure.consumers;

import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

import com.prueba.reservas.common.events.ChangedCheckInEvent;
import com.prueba.reservas.common.events.DeletedSearchEvent;
import com.prueba.reservas.common.events.SearchSavedEvent;

public interface EventsConsumers {
	//una vez consuma el mensaje tengo que indicale a kafka que ya fue consumido
	//de eso se encarga Acknowledgment
	void consume(@Payload SearchSavedEvent event,Acknowledgment ack);
	void consume(@Payload ChangedCheckInEvent event,Acknowledgment ack);
	void consume(@Payload DeletedSearchEvent event,Acknowledgment ack);
	
}
