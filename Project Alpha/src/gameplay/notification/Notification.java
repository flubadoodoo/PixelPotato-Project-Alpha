package gameplay.notification;

import org.newdawn.slick.SlickException;

public class Notification {
	
	private static final int	FADE_IN_TIME;
	private static final int	FADE_OUT_TIME;
	private static final int	MAX_NOTIFICATION_LIFE;
	
	private double				x, y;
	private double				width, height;
	private long				life;
	private String				message;
	private double				messageX, messageY, messageWidth, messageHeight;
	
	static {
		FADE_IN_TIME = 500;
		FADE_OUT_TIME = 1000;
		MAX_NOTIFICATION_LIFE = 5000;
	}
	
	public Notification(String string, double x, double y, double width, double height, double messageWidth, double messageHeight) throws SlickException {
		setMessage(string);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.setMessageX(x + (width / 2 - messageWidth / 2));
		this.setMessageY(y - (height / 3) - (height / 2 - messageHeight) / 2);
		this.messageWidth = messageWidth;
		this.messageHeight = messageHeight;
		life = 0;
	}
	
	public float getBackgroundAlpha() {
		if (life <= FADE_IN_TIME) {
			return (float) life / (float) FADE_IN_TIME;
		} else if (life >= MAX_NOTIFICATION_LIFE - FADE_OUT_TIME) {
			return (float) (MAX_NOTIFICATION_LIFE - life) / (float) FADE_OUT_TIME;
		} else {
			return 1f;
		}
	}
	
	public void update(int delta) {
		life += delta;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void incrementY(double amount) {
		y += amount;
		messageY += amount;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public long getLife() {
		return life;
	}
	
	public void setLife(long life) {
		this.life = life;
	}
	
	public double getMessageWidth() {
		return messageWidth;
	}
	
	public void setMessageWidth(double messageWidth) {
		this.messageWidth = messageWidth;
	}
	
	public double getMessageHeight() {
		return messageHeight;
	}
	
	public void setMessageHeight(double messageHeight) {
		this.messageHeight = messageHeight;
	}
	
	public static int getMaxNotificationLife() {
		return MAX_NOTIFICATION_LIFE;
	}
	
	public static int getFadeInTime() {
		return FADE_IN_TIME;
	}
	
	public static int getFadeOutTime() {
		return FADE_OUT_TIME;
	}
	
	public double getMessageX() {
		return messageX;
	}
	
	public void setMessageX(double messageX) {
		this.messageX = messageX;
	}
	
	public double getMessageY() {
		return messageY;
	}
	
	public void setMessageY(double messageY) {
		this.messageY = messageY;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
