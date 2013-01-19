package gameplay.map.tiles;

import gameplay.map.Map;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import org.newdawn.slick.SlickException;

public class Tile {
	
	private static final int	SMALL_SIZE	= 29;
	private static final int	MEDIUM_SIZE	= 62;
	private static final int	LARGE_SIZE	= 128;
	
	private double				x, y;
	private int					scale;
	private Rectangle2D			boundingBox;
	
	public Tile(double x, double y, int scale) throws SlickException {
		this.x = x;
		this.y = y;
		this.scale = scale;
		boundingBox = new Rectangle.Double(x - Map.getSPACE() / 2, y, scale + Map.getSPACE(), scale);
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
	
	public static int getSMALL_SIZE() {
		return SMALL_SIZE;
	}
	
	public static int getMEDIUM_SIZE() {
		return MEDIUM_SIZE;
	}
	
	public static int getLARGE_SIZE() {
		return LARGE_SIZE;
	}
	
}