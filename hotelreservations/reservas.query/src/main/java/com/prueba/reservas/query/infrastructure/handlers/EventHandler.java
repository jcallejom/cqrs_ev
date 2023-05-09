package com.prueba.reservas.query.infrastructure.handlers;

import com.prueba.reservas.common.events.ChangedCheckInEvent;
import com.prueba.reservas.common.events.DeletedSearchEvent;
import com.prueba.reservas.common.events.SearchSavedEvent;

public interface EventHandler {
	void on(SearchSavedEvent event);

	void on(ChangedCheckInEvent event);

	void on(DeletedSearchEvent event);
}
