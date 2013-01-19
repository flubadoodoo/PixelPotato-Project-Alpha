package gameplay.entities.player;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player {
	
	private static final double	x, y;
	private static final String	IDP;
	
	private Image				sprite;
	private Rectangle2D			boundingBox;
	private double				yVel;
	
	static {
		x = 500;
		y = 390;
		IDP = "gameplay/entities/player/sprites/";
	}
	
	public Player() throws SlickException {
		sprite = new Image(IDP + "Right 1.png");
		boundingBox = new Rectangle.Double(x, y, sprite.getWidth(), sprite.getHeight());
	}
	
	public void draw() {
		sprite.draw((float) x, (float) y);
	}
	
	public static double getY() {
		return y;
	}
	
	public static double getX() {
		return x;
	}
	
	public Rectangle2D getBoundingBox() {
		return boundingBox;
	}
	
	public void setBoundingBox(Rectangle2D boundingBox) {
		this.boundingBox = boundingBox;
	}
	
	public void moveBoundingBoxX(double amount) {
		boundingBox.setRect(boundingBox.getX() + amount, boundingBox.getY(), boundingBox.getWidth(), boundingBox.getHeight());
	}
	
	public void moveBoundingBoxY(double amount) {
		boundingBox.setRect(boundingBox.getX(), boundingBox.getY() + amount, boundingBox.getWidth(), boundingBox.getHeight());
		
	}
	
	public double getyVel() {
		return yVel;
	}
	
	public void setyVel(double yVel) {
		this.yVel = yVel;
	}
	
	public void incrementyVel(double amount) {
		yVel += amount;
	}
	
}
