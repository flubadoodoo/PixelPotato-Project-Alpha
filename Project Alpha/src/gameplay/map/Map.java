package gameplay.map;

import java.util.Random;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.SlickException;

import gameplay.map.tiles.Tile;

public class Map {
	
	private float	xOff, yOff;
	private Tile[]	tiles;
	
	// Temp vars
	private int		width;
	private int		SPACE;
	private Random	random;
	
	public Map(int width) throws SlickException {
		xOff = 0;
		yOff = 450;
		setWidth(width);
		SPACE = 4;
		random = new Random();
		setTiles(new Tile[width]);
		initTiles();
	}
	
	private void initTiles() throws SlickException {
		tiles[0] = new Tile(0, -64, 64);
		for (int i = 1; i < tiles.length; i++) {
			int size = random.nextInt(5);
			if (size == 2 || size == 3)
				size = 1;
			if (size == 0 && size == 1)
				size = 0;
			if (size == 5)
				size = 3;
			tiles[i] = new Tile(tiles[i - 1].getX() + tiles[i - 1].getScale() + SPACE, (size == 0) ? -13 : (size == 1) ? -30 : -64, (size == 0) ? 13 : (size == 1) ? 30 : 64);
		}
	}
	
	public void drawMap() {
		for (int i = 0; i < tiles.length; i++) {
			if (isOnMap(tiles[i]))
				tiles[i].drawTileWithOffset(xOff, yOff);
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
	
}
