package edu.toronto.cs.openome.conversion.discovery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractServiceDiscovery implements IServiceDiscovery {

	@Override
	public Map<String, List<String>> findService(String owlFilePath,
			String iopeFilePath, String bpelFilePath) {

		// Mock to perform service discovery operation
		Map<String, List<String>> disResult = new HashMap<String, List<String>>();

		ArrayList<String> serviceList = new ArrayList<String>();
		serviceList.add("Service 1");
		serviceList.add("Service 2");
		serviceList.add("Service 3");
		disResult.put("Security", serviceList);

		return disResult;

	}
}
