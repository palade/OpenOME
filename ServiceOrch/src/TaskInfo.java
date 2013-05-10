import java.util.ArrayList;
import java.util.List;

public class TaskInfo {
	private String taskIOPE;
	private List<ProfileInfo> candidateServices;

	public TaskInfo() {
		candidateServices = new ArrayList<ProfileInfo>();
	}

	public String getTaskIOPE() {
		return taskIOPE;
	}

	public void setTaskIOPE(String taskIOPE) {
		this.taskIOPE = taskIOPE;
	}

	public List<ProfileInfo> getcandidateServices() {
		return candidateServices;
	}

	public void setcandidateServices(List<ProfileInfo> candidateServices) {
		this.candidateServices = candidateServices;
	}

	public void addCandidateServices(ProfileInfo service) {
		this.candidateServices.add(service);
	}
}
