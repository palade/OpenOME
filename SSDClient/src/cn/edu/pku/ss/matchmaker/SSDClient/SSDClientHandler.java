package cn.edu.pku.ss.matchmaker.SSDClient;

import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

import cn.edu.pku.ss.matchmaker.thrift.ResponseInfo;
import cn.edu.pku.ss.matchmaker.thrift.ServiceDiscoverer.AsyncClient.getServices_call;

public class SSDClientHandler implements AsyncMethodCallback<getServices_call> {
	private List<ResponseInfo> responseInfoList;
	
	public List<ResponseInfo> getResponseInfoList() {
		return responseInfoList;
	}
	
	@Override
	public void onError(Exception exception) {
		// TODO Auto-generated method stub
		exception.printStackTrace();
	}

	@Override
	public void onComplete(getServices_call response) {
		// TODO Auto-generated method stub
//		System.out.println("client handler");
//		try {
//			if (responseInfoList != null)
//				responseInfoList.clear();
//			responseInfoList = response.getResult();
//			if (responseInfoList != null) {
//				System.out.println("not null");
//			} else {
//				System.out.println("result is null");
//			}
//			
//		} catch (TException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
