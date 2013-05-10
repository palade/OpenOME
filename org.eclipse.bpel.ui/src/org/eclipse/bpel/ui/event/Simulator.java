package org.eclipse.bpel.ui.event;

import java.util.LinkedList;
import java.util.List;

public class Simulator implements Runnable{
	private IPublisher pb;
	private List list = new LinkedList();
	private int counter = 0;
	public Simulator()
	{
		list.add("If");
		list.add("Else If");
		list.add("BuyAirTicket_assign_1");
		list.add("BuyAirTicket_invoke");
		list.add("BuyAirTicket_assign_2");
		list.add("AboradCard_assign_1");
		list.add("AboradCard_invoke");
		list.add("AboradCard_assign_2");
		list.add("CheckLuggageSecurity_sequence");
		list.add("CheckLuggageSecurity_assign_1");
		list.add("CheckLuggageSecurity_invoke");

	}
	public void setPB(IPublisher p)
	{
		this.pb = p;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(counter != list.size())//list保存当前bpel模型中的所有活动节点名称
		{
			this.pb.setName((String)list.get(counter));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			counter ++;
		}
	}
	
 
}
