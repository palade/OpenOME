package org.eclipse.bpel.ui.event;

public interface IPublisher {
	public void add(IObserver i);
	public void notifyEvent();
	public void setName(String name);
	public String getName();
}
