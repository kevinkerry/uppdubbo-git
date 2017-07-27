package com.csii.upp.event;

/**
 * 
 * @author 徐锦
 * 
 */
public interface EventHandler<T extends Event> {

	public void handler(T event);

	public Class<T> getAcceptedEventType();
}
