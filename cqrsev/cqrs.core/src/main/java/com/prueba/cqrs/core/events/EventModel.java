package com.prueba.cqrs.core.events;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "eventStore")
public class EventModel {
	@Id
	private String id;
	private Date timeStamp;
	//muy importante nos va a permitir obtener la lista de eventos que se deben entregar a un determinado agragate es decir cuando reejcutemos un evenstore
	private String aggregateIdentifier;
	private String aggregateType;
	private int version;
	private String eventType;
	//Propiedad que capture el evento
	private BaseEvent eventData;
}
