
/**
 * @author Tyler Cambron
 * 
 */
public class ChatBot {
	protected String name;
	protected ChatResponse response;
	protected boolean active;
	
	public ChatBot(String name) {
		setName(name);
		setActive(true);
	}
	
	public String getName() {
		return name;
	}

	public ChatResponse getResponse() {
		return response;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setResponse(ChatResponse response) {
		this.response = response;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public void sendMessage(String msg) {
		System.out.println(this.getName() + ": " + msg);
	}

	public void interact(ChatResponse response) {
		if (response.getMessage().contains("stop " + getName().toLowerCase())) {
			sendMessage("Shutting down...");
			setActive(false);
			return;
		}
		setResponse(response);
		sendMessage(response.getResponse());
		return;
	}
}
