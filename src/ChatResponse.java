
/**
 * @author Tyler Cambron
 * 
 */
public class ChatResponse implements ChatTone {
	protected String message;
	protected String response;
	protected ChatResponse lastResponse;
	
	public ChatResponse(String message, ChatResponse lastResponse, String botName) {
		setMessage(message.toLowerCase());
		setLastResponse(lastResponse);
		setResponse(getResponseFromMessage(this, botName));
	}
	
	public String getMessage() {
		return message;
	}
	public String getResponse() {
		return response;
	}
	public ChatResponse getLastResponse() {
		return lastResponse;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public void setLastResponse(ChatResponse lastResponse) {
		this.lastResponse = lastResponse;
	}
}
