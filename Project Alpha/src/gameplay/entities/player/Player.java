package gameplay.entities.player;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player {
	
	private static final float	x, y;
	private static final String	IDP;
	
	private Image				sprite;
	
	static {
		x = 500;
		y = 390;
		IDP = "gameplay/entities/player/sprites/";
	}
	
	public Player() throws SlickException {
		sprite = new Image(IDP + "Right 1.png");
	}
	
	public void draw() {
		sprite.draw(x, y);
	}
	
	public static float getY() {
		return y;
	}
	
	public static float getX() {
		return x;
	}
	
}
