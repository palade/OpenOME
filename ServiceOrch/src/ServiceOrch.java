import java.util.List;
import java.util.Map;

public interface ServiceOrch {
	public void sendServiceOrchData(String bpelContent,
			Map<String, TaskInfo> tasksInfo);

}
