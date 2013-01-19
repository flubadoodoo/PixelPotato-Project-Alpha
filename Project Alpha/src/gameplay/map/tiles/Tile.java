package gameplay.map.tiles;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tile {
	
	private double		x, y;
	private int			scale;
	private Image		image;
	private String		IDP	= "gameplay/map/tiles/";
	private Rectangle2D	boundingBox;
	
	public Tile(double d, double y, int scale) throws SlickException {
		this.x = d;
		this.y = y;
		this.scale = scale;
		image = new Image(IDP + ((scale == 29) ? "Tile 29.png" : (scale == 62) ? "Tile 62.png" : "Tile 128.png"));
		boundingBox = new Rectangle.Double(d, y, scale, scale);
	}
	
	public void drawTile(float xOff, float yOff) {
		image.draw((float) (x + xOff), (float) (y + yOff));
	}
	
	// GETTERS & SETTERS
	
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
	
	public int getScale() {
		return scale;
	}
	
	public void setScale(int scale) {
		this.scale = scale;
	}
	
	public Rectangle2D getBoundingBox() {
		return boundingBox;
	}
	
	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}
	
	public void incrementX(double amount) {
		boundingBox.setFrame(new Rectangle2D.Double(boundingBox.getX() + amount, boundingBox.getY(), boundingBox.getWidth(), boundingBox.getHeight()));
	}
	
	public void incrementY(double amount) {
		boundingBox.setFrame(new Rectangle2D.Double(boundingBox.getX(), boundingBox.getY() + amount, boundingBox.getWidth(), boundingBox.getHeight()));
	}
}
