package gameplay.notification;

import helper.Text;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class NotificationCenter {
	
	private ArrayList<Notification>	notifications;
	private Image					notificationBackground;
	private Text					message;
	
	public NotificationCenter() throws SlickException {
		notifications = new ArrayList<Notification>();
		notificationBackground = new Image("gameplay/notification/NotificationBG.png");
		message = new Text("", "Walkway", "Bold", 15, Color.white, 0, 0);
	}
	
	public void addNotification(String string) throws SlickException {
		for (int i = 0; i < notifications.size(); i++) {
			notifications.get(i).incrementY(-notifications.get(i).getHeight() - notifications.get(i).getHeight() / 4);
		}
		message.setString(string);
		notifications.add(new Notification(string, 10, Display.getHeight() - 10, notificationBackground.getWidth(), notificationBackground.getHeight(), message.getWidth(), message.getHeight()));
	}
	
	public void draw() {
		for (Notification notification : notifications) {
			notificationBackground.setAlpha(notification.getBackgroundAlpha());
			notificationBackground.draw((float) (notification.getX()), (float) (notification.getY() - notificationBackground.getHeight()));
			message.setString(notification.getMessage());
			message.setX(notification.getMessageX());
			message.setY(notification.getMessageY());
			message.setAlpha(notification.getBackgroundAlpha());
			message.drawString();
			message.setAlpha(1f);
		}
	}
	
	public void update(int delta) {
		for (int i = 0; i < notifications.size(); i++) {
			notifications.get(i).update(delta);
			if (notifications.get(i).getLife() > Notification.getMaxNotificationLife())
				notifications.remove(i);
		}
	}
	
	public ArrayList<Notification> getNotifications() {
		return notifications;
	}
	
	public void setNotifications(ArrayList<Notification> notifications) {
		this.notifications = notifications;
	}
	
}
