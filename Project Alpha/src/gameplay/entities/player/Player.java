package gameplay.entities.player;

import java.awt.Rectangle;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player {
	
	private static final float	x, y;
	private static final String	IDP;
	private final float			yAcc;
	
	private Image				sprite;
	private Rectangle			bounds;
	private float				yVel;
	
	static {
		x = Display.getWidth() / 2;
		y = Display.getHeight() / 2;
		IDP = "gameplay/entities/player/sprites/";
	}
	
	public Player() throws SlickException {
		sprite = new Image(IDP + "Right 1.png");
		bounds = new Rectangle(sprite.getWidth(), sprite.getHeight());
		bounds.setLocation((int) x, (int) y);
		setyVel(0.0f);
		yAcc = -0.01f;
	}
	
	public void draw() {
		sprite.draw(x, y); // Fix the map offset in the bounds
	}
	
	public static float getY() {
		return y;
	}
	
	public static float getX() {
		return x;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	public float getyVel() {
		return yVel;
	}
	
	public void setyVel(float yVel) {
		this.yVel = yVel;
	}
	
	public void incrementYVel(float amount) {
		yVel += amount;
	}
	
	public float getyAcc() {
		return yAcc;
	}
	
}
