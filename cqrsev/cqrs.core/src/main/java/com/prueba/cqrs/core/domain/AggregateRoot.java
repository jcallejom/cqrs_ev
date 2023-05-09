package com.prueba.cqrs.core.domain;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.prueba.cqrs.core.events.BaseEvent;

public abstract class AggregateRoot {
	protected String id;
	private int version= -1;
	private final List<BaseEvent> changes= new ArrayList<>();
	private final Logger logger=Logger.getLogger(AggregateRoot.class.getName());
	
	public String getId() {
		return id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<BaseEvent> geUncommitedChanges() {
		return this.changes;
	}
	//todos los cambios confirmados
	public void markChangesAsCommited() {
		 this.changes.clear();
	}
	
	//necesitamos un metodo aplicar cambios 
	//para controlar el estado de los eventos (concurrencia)
	//una vez se ejecute el metodo podre agregar el evento a mi lista de changes
	//hace dos cosas ejecutar el metodo aplly y si el evento es nuevo se añade a la lista de changes
	protected void applyChange(BaseEvent event , Boolean isNewEvent) {
		try {
			var method=getClass().getDeclaredMethod("apply", event.getClass());
			method.setAccessible(true);//accemos acesible este Method
			method.invoke(this, event);
		}catch(NoSuchMethodException e) {
			logger.log(Level.WARNING,MessageFormat.format("apply method was not found for {0}",event.getClass().getName()));
		}catch(Exception e) {
			logger.log(Level.SEVERE,"Errors applying the event to the aggregate",e);
		}finally {
			if(isNewEvent) {
				changes.add(event);
			}
		}
	
	}
	
	//cada vez que llama a un nuevo evento y applyChange
	public void raiseEvent(BaseEvent event) {
		applyChange(event, true);
	}
	//Lo llama el EventSourcingHandler
	//cada vez que queramos reprocesar evento, le pasamos la coleccion de eventosy applyChange y no es nuevo =false
	public void replayEvent(Iterable<BaseEvent> events) {
		events.forEach(event ->applyChange(event, false));
		
	}
}
