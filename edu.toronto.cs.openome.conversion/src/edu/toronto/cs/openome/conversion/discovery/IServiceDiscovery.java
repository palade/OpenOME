package edu.toronto.cs.openome.conversion.discovery;

import java.util.List;
import java.util.Map;

public interface IServiceDiscovery {
	public Map<String, List<String>> findService(String owlFilePath,
			String iopeFilePath, String bpelFilePath);
}
