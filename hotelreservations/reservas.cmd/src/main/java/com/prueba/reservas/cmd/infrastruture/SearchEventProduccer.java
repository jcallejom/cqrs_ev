package com.prueba.reservas.cmd.infrastruture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.prueba.cqrs.core.events.BaseEvent;
import com.prueba.cqrs.core.produces.EventProducer;

@Service
public class SearchEventProduccer implements EventProducer {
	@Autowired
	private KafkaTemplate<String,Object> kafkaTemplate;
	@Override
	public void produce(String topic, BaseEvent event) {
		kafkaTemplate.send(topic,event);
		
	}
}
