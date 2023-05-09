package com.prueba.reservas.query.infrastructure.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.reservas.common.events.ChangedCheckInEvent;
import com.prueba.reservas.common.events.DeletedSearchEvent;
import com.prueba.reservas.common.events.SearchSavedEvent;
import com.prueba.reservas.query.domain.SearchRepository;
import com.prueba.reservas.query.domain.SearchsEntity;

@Service
public class SearchEventHandler implements EventHandler {
	@Autowired
	private SearchRepository repository;
	
	@Override
	public void on(SearchSavedEvent event) {
		var search=SearchsEntity.builder()
				.id(event.getId())
				.hotelId(event.getHotelId())
				.checkIn(event.getCheckIn())
				.checkOut(event.getCheckOut())
				.ages(event.getAges())
				.createdDate(event.getCreatedDate())
				.build();
		repository.save(search);
		
	}

	@Override
	public void on(ChangedCheckInEvent event) {
		var search=repository.findById(event.getId());
		if(search.isEmpty()) {
			return;
		}
		search.get().setCheckIn(event.getCheckIn());
		repository.save(search.get());
	}

	@Override
	public void on(DeletedSearchEvent event) {
		repository.deleteById(event.getId());
	}

}
