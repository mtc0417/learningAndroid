package com.events;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class EventBroadcasterImpl implements EventBroadcaster{
	private Map<String, EventListener> mListeners;
	private Object mMutex;
	public EventBroadcasterImpl()
	{
		mListeners = new TreeMap<String, EventListener>();
		mMutex = new Object();
	}
	public void dispatchEvent(Event e) // all events are dispatched on main thread
	{
		final ArrayList<EventListener> listeners = new ArrayList<EventListener>();
		synchronized(mMutex)
		{
			for(Entry<String, EventListener> entry : mListeners.entrySet())
			{
				if(entry.getKey().equals(e.getEventType()))
				{
					listeners.add(entry.getValue());
				}
			}
		}
		for(int i = 0; i < listeners.size(); i++)
		{
			final Event et = e.clone();
			final EventListener listener = listeners.get(i);
			new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    listener.onEvent(et);
                }
            });
            /*
			new Thread(new Runnable() {
		        public void run() {
		        	listener.onEvent(et);
		        }
		    }).start();
		    */
		}
	}
	public void addEventListener(String eventType, EventListener obj)
	{
		synchronized(mMutex)
		{
			mListeners.put(eventType, obj);
		}
	}
	public void addEventListener(EventListener obj)
	{
		synchronized(mMutex)
		{
			mListeners.put(Event.ACTION, obj);
		}
	}
	public void removeEventListener(EventListener obj)
	{
		synchronized(mMutex)
		{
			ArrayList<String> keys = new ArrayList<String>();
			for(Entry<String, EventListener> entry : mListeners.entrySet())
			{
				if(entry.getValue().equals(obj))
				{
					keys.add(entry.getKey());
				}
			}
			for(int i = 0; i < keys.size(); i++)
			{
				mListeners.remove(keys.get(i));
			}
		}
	}
	public void removeEventListener(String eventType)
	{
		synchronized(mMutex)
		{
			mListeners.remove(eventType);
		}
	}
	public void removeAllListeners()
	{
		synchronized(mMutex)
		{
			mListeners.clear();
		}
	}
}
