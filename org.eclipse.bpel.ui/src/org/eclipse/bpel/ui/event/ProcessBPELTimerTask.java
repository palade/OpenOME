package org.eclipse.bpel.ui.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ProcessBPELTimerTask extends TimerTask {
	private IPublisher pb;

	private List<String> list = new ArrayList<String>();
	private int index;

	public ProcessBPELTimerTask() {

		list.add("ApplyCommit_invoke");
		list.add("TourPay_invoke");
		list.add("BookStaredHotel_invoke");
		list.add("RoomSelection_invoke");
		list.add("HotelPay_invoke");
		list.add("BuyAirTicket_invoke");
		list.add("AboradCard_invoke");
		list.add("CheckLuggageSecurity_invoke");
		list.add("SecurityCheck_invoke");
		list.add("Aborad_invoke");
		list.add("AssignGuide_invoke");
		list.add("ResuarantSelection_invoke");
		list.add("SeatBooking_invoke");
		list.add("SpotTicketBuy_invoke");
		list.add("Restaurant_invoke");
		

		index = 0;

	}

	public void setPB(IPublisher p) {
		this.pb = p;
	}

	@Override
	public void run() {
		if (index < list.size()) {
			String s = list.get(index++);
			pb.setName(s);
		}
	}

}
