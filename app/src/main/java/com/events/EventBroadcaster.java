package com.events;

import com.events.EventListener;

public interface EventBroadcaster {
	public void dispatchEvent(Event e);
	public void addEventListener(EventListener obj); // event type default
	public void addEventListener(String eventType, EventListener obj);
	public void removeEventListener(EventListener obj);
	public void removeEventListener(String eventType);
	public void removeAllListeners();
}
