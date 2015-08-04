package com.events;

public class Event {
	public static String ACTION = "action";
	private String mEventType = ACTION;
	private Object mTarget;
	
	public Event()
	{
		mEventType = ACTION;
	}
	public Event(String eventType)
	{
		mEventType = eventType;
	}
	static public Event defaultEvent(Object target)
	{
		return new Event(ACTION, target);
	}
	public Event(String eventType, Object target)
	{
		mEventType = eventType;
		mTarget = target;
	}
	public String getEventType()
	{
		return mEventType;
	}
	public Object getTarget()
	{
		return mTarget;
	}
	public Event clone()
	{
		return new Event(mEventType, mTarget);
	}
	public String toString()
	{
		return mEventType;
	}
	public boolean equals(Event obj)
	{
		return obj.mEventType.equals(mEventType) && obj.mTarget.equals(obj.mTarget);
	}
}
