package gameplay.entities.player;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Player {
	
	private static final float	x, y;
	private static final String	IDP;
	
	private Image				sprite;
	private Rectangle			bounds;
	
	static {
		x = 500;
		y = 390;
		IDP = "gameplay/entities/player/sprites/";
	}
	
	public Player() throws SlickException {
		sprite = new Image(IDP + "Right 1.png");
		bounds = new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
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
	
}
