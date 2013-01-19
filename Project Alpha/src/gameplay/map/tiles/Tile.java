package gameplay.map.tiles;

import java.awt.Rectangle;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tile {
	
	private float		x, y;
	private int			scale;
	private Image		image;
	private String		IDP	= "gameplay/map/tiles/";
	private Rectangle	bounds;
	
	public Tile(float x, float y, int scale) throws SlickException {
		this.x = x;
		this.y = y;
		this.scale = scale;
		image = new Image(IDP + ((scale == 29) ? "Tile 29.png" : (scale == 62) ? "Tile 62.png" : "Tile 128.png"));
		setBounds(new Rectangle(scale, scale));
		bounds.setLocation((int) x, (int) y);
	}
	
	public void drawTile(float xOff, float yOff) {
		image.draw(x + xOff, y + yOff);
	}
	
	// GETTERS & SETTERS
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public int getScale() {
		return scale;
	}
	
	public void setScale(int scale) {
		this.scale = scale;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	public void incrementX(double amount) {
		bounds.setLocation((int) (bounds.getX() + amount), (int) bounds.getLocation().getY());
	}
	
	public void incrementY(double d) {
		bounds.setLocation((int) bounds.getLocation().getX(), (int) (bounds.getY() + d));
	}
	
}
