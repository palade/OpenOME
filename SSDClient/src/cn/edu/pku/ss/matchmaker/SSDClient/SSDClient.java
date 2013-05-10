package cn.edu.pku.ss.matchmaker.SSDClient;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TFramedTransport;
import cn.edu.pku.ss.matchmaker.thrift.RequestInfo;
import cn.edu.pku.ss.matchmaker.thrift.ResponseInfo;
import cn.edu.pku.ss.matchmaker.thrift.ServiceDiscoverer;
import org.apache.thrift.transport.TSocket;

public class SSDClient {

	/**
	 * @param args
	 */
	private static final String ip = "127.0.0.1";
	private static final int port = 8989;
	private static final int clientTimeout = 30000;
	private TTransport transport = null;
	private static Logger logger = Logger.getLogger(cn.edu.pku.ss.matchmaker.SSDClient.SSDClient.class);
    
    
    public ServiceDiscoverer.Client initSyncClient() {
    	transport = new TFramedTransport(new TSocket(ip, port, clientTimeout));
		TProtocol protocol = new TCompactProtocol(transport);
		ServiceDiscoverer.Client client = new ServiceDiscoverer.Client(protocol);
		
    	return client;
    }
    
    public void close() {
    	transport.close();
    }
   
    
    public void open() {
    	try {
			transport.open();
		} catch (TTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	public static void main(String[] args) throws TException  {
		// TODO Auto-generated method stub
		SSDClient ssdClient = new SSDClient();
		ServiceDiscoverer.Client syncClient = null;
	
		if ((syncClient = ssdClient.initSyncClient()) == null) {
			logger.error("failed to init client");
			return ;
		}
		
		ssdClient.open();
		
		for (int j = 0; j < 10; ++j) {
		RequestInfo requestinfo = new RequestInfo();
//		requestinfo.setOwlsContentIsSet(true);
//		requestinfo.owlsURI = "http://localhost:8080/juddiv3/owl-s/1.1/BravoAirProfile.owl";
		
//		List<ResponseInfo> responseInfoList = syncClient.ge.getServices(requestinfo);
//        if (responseInfoList == null) {
//        	logger.error("failed to get query results");
//        	return ;
//        }
//		
//		for (int i = 0; i < responseInfoList.size(); ++i) {
//			ResponseInfo responseInfo = responseInfoList.get(i);
//			if (responseInfo != null) {
//				System.out.println("serviceKey: " + responseInfo.getServiceKey());
//				if (responseInfo.getWsdlURI() != null)
//					System.out.println("wsdlURI: " + responseInfo.getWsdlURI());
//			}
//		}
			
		}
		ssdClient.close();
		
	}

}
