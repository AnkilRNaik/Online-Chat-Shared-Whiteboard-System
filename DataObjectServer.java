
public class DataObjectServer {
	private String message;

	public DataObjectServer() {
	}

	public DataObjectServer(String message) {
		setMessage(message);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
