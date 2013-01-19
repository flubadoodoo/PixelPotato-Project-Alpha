package gameplay.map;

import gameplay.map.tiles.Tile;

import java.awt.geom.Rectangle2D;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Map {
	
	private double		xOff, yOff;
	private Tile[]		tiles;
	
	// Temp vars
	private int			width;
	private int			SPACE;
	private Random		random;
	private HeightMap	heightMap;
	private double		gravity;
	private Image[]		tileImages;
	
	public Map(int width) throws SlickException {
		xOff = 0;
		yOff = 500;
		setWidth(width);
		SPACE = 8;
		random = new Random();
		setGravity(-0.2);
		heightMap = new HeightMap(width);
		setTiles(new Tile[width]);
		initTiles(heightMap.getMap());
		tileImages = new Image[] { new Image("gameplay/map/tiles/Tile 29.png"), new Image("gameplay/map/tiles/Tile 62.png"), new Image("gameplay/map/tiles/Tile 128.png") };
	}
	
	private void initTiles(int[] heightMap) throws SlickException {
		tiles[0] = new Tile(0, heightMap[0], getRandomTileScale());
		Rectangle2D boundingBox0 = tiles[0].getBoundingBox();
		boundingBox0.setFrame(boundingBox0.getX(), boundingBox0.getY() + yOff, boundingBox0.getWidth(), boundingBox0.getHeight());
		for (int i = 1; i < tiles.length; i++) {
			tiles[i] = new Tile(tiles[i - 1].getX() + tiles[i - 1].getScale() + SPACE, heightMap[i], getRandomTileScale());
			Rectangle2D boundingBox = tiles[i].getBoundingBox();
			boundingBox.setFrame(boundingBox.getX(), boundingBox.getY() + yOff, boundingBox.getWidth(), boundingBox.getHeight());
		}
	}
	
	private int getRandomTileScale() {
		int size = random.nextInt(8);
		return (size == 0) ? 128 : (size == 1 || size == 2) ? 62 : 29;
	}
	
	public void drawMap() {
		for (int i = 0; i < tiles.length; i++) {
			if (isOnMap(tiles[i]))
				tileImages[(tiles[i].getScale() == 29) ? 0 : (tiles[i].getScale() == 62) ? 1 : 2].draw((float) (tiles[i].getX() + xOff), (float) (tiles[i].getY() + yOff));
		}
	}
	
	private boolean isOnMap(Tile tile) {
		return (tile.getX() + tile.getScale() >= -xOff && tile.getX() <= -xOff + Display.getWidth());
	}
	
	// GETTERS & SETTERS
	
	public double getyOff() {
		return yOff;
	}
	
	public void setyOff(double yOff) {
		this.yOff = yOff;
	}
	
	public double getxOff() {
		return xOff;
	}
	
	public void setxOff(double xOff) {
		this.xOff = xOff;
	}
	
	public void incrementXOff(double amount) {
		xOff += amount;
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].incrementX(amount);
		}
	}
	
	public void incrementYOff(double amount) {
		yOff += amount;
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].incrementY(amount);
		}
	}
	
	public Tile[] getTiles() {
		return tiles;
	}
	
	public void setTiles(Tile[] tiles) {
		this.tiles = tiles;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public double getGravity() {
		return gravity;
	}
	
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}
	
	public Image[] getTileImages() {
		return tileImages;
	}
	
	public void setTileImages(Image[] tileImages) {
		this.tileImages = tileImages;
	}
	
}
