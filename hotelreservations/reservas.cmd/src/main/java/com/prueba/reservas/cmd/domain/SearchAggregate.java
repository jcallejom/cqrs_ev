package com.prueba.reservas.cmd.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

import com.prueba.cqrs.core.domain.AggregateRoot;
import com.prueba.reservas.cmd.api.command.SaveSearchCommand;
import com.prueba.reservas.common.events.ChangedCheckInEvent;
import com.prueba.reservas.common.events.DeletedSearchEvent;
import com.prueba.reservas.common.events.SearchSavedEvent;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SearchAggregate extends AggregateRoot{
	private Boolean active;
	private Date checkIn;
	
	public SearchAggregate(SaveSearchCommand command) {
		try {
			raiseEvent(SearchSavedEvent.builder()
					.id(command.getId())
					.hotelId(command.getHotelId())
					.checkIn(new SimpleDateFormat("dd/MM/yyyy").parse(command.getCheckIn()))
					.checkOut(new SimpleDateFormat("dd/MM/yyyy").parse(command.getCheckOut()))
					//.ages(command.getAges())
					.ages(command.getAges().stream()
			                .map(Object::toString)
			                .collect(Collectors.joining(","))
							)
					.createdDate(new Date())
					.build());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void apply(SearchSavedEvent event) {
		this.id=event.getId();
		this.active=true;
	
	}

	public void changeCheckIn(String checkIn) {
		if(!this.active)
			throw new IllegalStateException("nose puede actualizar el checkin");
			try {
				raiseEvent( ChangedCheckInEvent.builder()
					.id(this.id)//el id ya esta dentro de la instancia
					.checkIn(new SimpleDateFormat("dd/MM/yyyy").parse(checkIn))
					.build());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void apply(ChangedCheckInEvent event) {
		this.id=event.getId();
		this.checkIn=event.getCheckIn();
	
	}
	public void deleteSearch() {
		if(!this.active)
			throw new IllegalStateException("la busqueda ya esta borrada");
			raiseEvent(DeletedSearchEvent.builder()
				.id(this.id)//el id ya esta dentro de la instancia
				.build());
		
	}
	public void apply(DeletedSearchEvent event) {
		this.id=event.getId();
		this.active=false;
	
	}

	public Date getCheckIn() {
		return checkIn;
	}
}
