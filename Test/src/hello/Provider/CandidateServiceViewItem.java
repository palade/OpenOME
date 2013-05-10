package hello.Provider;

public class CandidateServiceViewItem {

	private String serviceName;
	private String actor;
	private String category;
	private String input;
	private String output;
	private String context;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public CandidateServiceViewItem(String serviceName, String actor, String category,
			String input, String output, String context) {
		super();
		this.serviceName = serviceName;
		this.actor = actor;
		this.category = category;
		this.input = input;
		this.output = output;
		this.context = context;
	}

}
