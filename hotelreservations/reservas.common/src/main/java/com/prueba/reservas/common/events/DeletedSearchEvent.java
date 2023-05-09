package com.prueba.reservas.common.events;

import com.prueba.cqrs.core.events.BaseEvent;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public final class DeletedSearchEvent extends BaseEvent{

}
