package gameplay.entities.player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;

import core.Main;

public class Player {
	
	private static float	speed;
	
	private float			x, y;
	
	private SpriteSheet		spriteSheet;
	private Animation		animation;
	private Polygon			boundingBox;
	
	static {
		speed = 0.3f;
	}
	
	public Player() throws SlickException {
		spriteSheet = new SpriteSheet("gameplay/entities/player/sprites/player.png", 32, 32);
		x = Main.getWidth() / 2 - 16;
		y = Main.getHeight() / 2 - 16;
		animation = new Animation();
		animation.setAutoUpdate(true);
		for (int i = 0; i < 3; i++) {
			animation.addFrame(spriteSheet.getSprite(i, 0), 150);
		}
		boundingBox = new Polygon(new float[] { x, y, x + 32, y, x + 32, y + 32, x, y + 32 });
	}
	
	public void draw() {
		animation.draw(x, y);
	}
	
	public void update(int delta) {
		
	}
	
	public float getY() {
		return y;
	}
	
	public float getX() {
		return x;
	}
	
	public void move(float x, float y) {
		this.x += x;
		this.y += y;
		boundingBox.setLocation(boundingBox.getX() + x, boundingBox.getY() + y);
	}
	
	public static float getSpeed() {
		return speed;
	}
	
	public static void setSpeed(float speed) {
		Player.speed = speed;
	}
	
	public Polygon getBoundingBox() {
		return boundingBox;
	}
	
	public void setBoundingBox(Polygon boundingBox) {
		this.boundingBox = boundingBox;
	}
	
}
