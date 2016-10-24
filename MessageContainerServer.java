

import java.io.Serializable;
import java.util.ArrayList;

abstract public class MessageContainerServer {
	
	abstract public void setMessage(Object message);

	abstract public Object getMessage();

}

@SuppressWarnings("serial")
class ChatMessage extends DataObjectServer implements Serializable {

	String message;

	public void setMessage(String message) {
		this.message = (String) message;
	}

	public String getMessage() {
		return message;
	}

}



@SuppressWarnings("serial")
class UserMessage2 implements Serializable {

	String message;

	public void setMessage(String message) {
		this.message = (String) message;
	}

	public String getMessage() {
		return message;
	}

}


@SuppressWarnings("serial")
class LineMessage extends MessageContainerServer implements Serializable {

	ArrayList<Line> message;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setMessage(Object message) {
		this.message = (ArrayList) message;
	}

	public Object getMessage() {
		return message;
	}

}


@SuppressWarnings("serial")
class Line implements Serializable {
	int startx, starty, endx, endy;

	public Line() {
	}

	public Line(int sx, int sy, int ex, int ey) {
		setStartX(sx);
		setStartY(sy);
		setEndX(ex);
		setEndY(ey);
	}

	public void setStartX(int sx) {
		startx = sx;
	}

	public void setStartY(int sy) {
		starty = sy;
	}

	public void setEndX(int ex) {
		endx = ex;
	}

	public void setEndY(int ey) {
		endy = ey;
	}

	public int getStartX() {
		return startx;
	}

	public int getStartY() {
		return starty;
	}

	public int getEndX() {
		return endx;
	}

	public int getEndY() {
		return endy;
	}
}
