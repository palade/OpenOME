package cn.edu.pku.ss.matchmaker.SSDClient;

import org.apache.thrift.async.AsyncMethodCallback;

public class SSDCallBack implements AsyncMethodCallback {



	@Override
	public void onError(Exception exception) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onComplete(Object response) {
		// TODO Auto-generated method stub
		System.out.println("Hello--------------");
	}

}
