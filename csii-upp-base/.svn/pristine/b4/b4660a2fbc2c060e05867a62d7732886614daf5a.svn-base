package com.csii.upp.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csii.pe.core.PeRuntimeException;
import com.csii.pe.service.executor.Executor;

/**
 * 异步服务
 * @author 徐锦
 *
 */
public class DefaultEventManager implements EventManager {

	private Executor executor;
	
	public void setExecutor(Executor executor) {
		this.executor = executor;
	}

	@SuppressWarnings("rawtypes")
	private Map<Class, EventHandler> eventHandlers = new HashMap<Class, EventHandler>();

	private <T extends Event> void doService(final T event,
			final EventHandler<T> handler) throws InterruptedException {		
		executor.execute(new Runnable() {
			
			public void run() {
				handler.handler(event);
			}
		});
	}

	@SuppressWarnings("unchecked")
	public <T extends Event> void doService(final T event) {
		EventHandler<T> eventHandler;
		synchronized (eventHandlers){
			eventHandler = (EventHandler<T>) eventHandlers.get(event.getClass());
		}
		
		try {
			doService(event, eventHandler);
		} catch (InterruptedException e) {
			throw new PeRuntimeException(e.getMessage(),e);
		}

	}

	private void register(EventHandler<Event> handler) {
		synchronized (eventHandlers) {
			eventHandlers.put(handler.getAcceptedEventType(),  handler);
		}
	}
	
	@SuppressWarnings("unused")
	private void unregister(EventHandler<Event> handler) {
		synchronized (eventHandlers) {
			eventHandlers.remove(handler.getAcceptedEventType());
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setEventHandlers(List handlers) {
		for (Object handler : handlers) {
			register( (EventHandler) handler);
		}
	}
}
