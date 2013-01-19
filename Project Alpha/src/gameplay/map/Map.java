package gameplay.map;

import gameplay.map.tiles.Tile;

import java.util.Random;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.SlickException;

public class Map {
	
	private float		xOff, yOff;
	private Tile[]		tiles;
	
	// Temp vars
	private int			width;
	private int			SPACE;
	private Random		random;
	private HeightMap	heightMap;
	
	public Map(int width) throws SlickException {
		xOff = 0;
		yOff = 500;
		setWidth(width);
		setSPACE(8);
		random = new Random();
		heightMap = new HeightMap(width);
		setTiles(new Tile[width]);
		initTiles(heightMap.getMap());
	}
	
	private void initTiles(int[] heightMap) throws SlickException {
		tiles[0] = new Tile(0, heightMap[0], getRandomTileScale());
		for (int i = 1; i < tiles.length; i++) {
			tiles[i] = new Tile(tiles[i - 1].getX() + tiles[i - 1].getScale() + getSPACE(), heightMap[i], getRandomTileScale());
			tiles[i].getBounds().setLocation((int) tiles[i].getBounds().getLocation().getX(), (int) (tiles[i].getBounds().getY() + yOff));
		}
	}
	
	private int getRandomTileScale() {
		int size = random.nextInt(8);
		return (size == 0) ? 128 : (size == 1 || size == 2) ? 62 : 29;
	}
	
	public void drawMap() {
		for (int i = 0; i < tiles.length; i++) {
			if (isOnMap(tiles[i]))
				tiles[i].drawTile(xOff, yOff);
		}
	}
	
	private boolean isOnMap(Tile tile) {
		return (tile.getX() + tile.getScale() >= -xOff && tile.getX() <= -xOff + Display.getWidth());
	}
	
	// GETTERS & SETTERS
	
	public float getyOff() {
		return yOff;
	}
	
	public void setyOff(float yOff) {
		this.yOff = yOff;
	}
	
	public float getxOff() {
		return xOff;
	}
	
	public void setxOff(float xOff) {
		this.xOff = xOff;
	}
	
	public void incrementXOff(double d) {
		xOff += d;
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].incrementX(d);
		}
	}
	
	public void incrementYOff(double d) {
		yOff += d;
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].incrementY(d);
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
	
	public int getSPACE() {
		return SPACE;
	}
	
	public void setSPACE(int sPACE) {
		SPACE = sPACE;
	}
	
}
