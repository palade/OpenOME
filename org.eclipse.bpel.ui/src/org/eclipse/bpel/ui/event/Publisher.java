package org.eclipse.bpel.ui.event;

import java.util.LinkedList;
import java.util.List;

public class Publisher implements IPublisher {
	private List<IObserver> obs = new LinkedList<IObserver>();
	private String name;

	@Override
	public void add(IObserver i) {
		obs.add(i);
	}

	@Override
	public void notifyEvent() {
		for (IObserver ob : obs)
			ob.update(getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		notifyEvent();
	}

}
