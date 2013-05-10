import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import EDU.cmu.Atlas.owls1_1.exception.NotInstanceOfServiceException;
import EDU.cmu.Atlas.owls1_1.parser.OWLSServiceParser;
import EDU.cmu.Atlas.owls1_1.service.OWLSServiceModel;
import EDU.cmu.Atlas.owls1_1.service.Service;
import EDU.cmu.Atlas.owls1_1.service.ServiceList;

public class Test {
	public static void main(String args[]) throws FileNotFoundException,
			NotInstanceOfServiceException {

		OWLSServiceParser p = new OWLSServiceParser();
		OWLSServiceModel serviceModel = p.read(new InputStreamReader(
				new FileInputStream("G:/BravoAirService.owl")));
		ServiceList sList = serviceModel.getServiceList();
		for (int i = 0; i < sList.size(); ++i) {
			Service s = sList.getNthService(i);
			System.out.println(s.getURI());
		}

		
		String ss = "hello";
		InputStream stream = new ByteArrayInputStream(ss.getBytes());
	}
}
