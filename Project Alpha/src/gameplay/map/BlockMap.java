package gameplay.map;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class BlockMap {
	
	private TiledMap			tmap;
	private int					mapWidth, mapHeight;
	private int					squareCornerOffset[]	= { 1, 1, 15, 1, 15, 15, 1, 15 };
	private ArrayList<Block>	blocks;
	
	public BlockMap(String ref) throws SlickException {
		setBlocks(new ArrayList<Block>());
		setTmap(new TiledMap(ref, "res/tile"));
		setMapWidth(getTmap().getWidth() * getTmap().getTileWidth());
		setMapHeight(getTmap().getHeight() * getTmap().getTileHeight());
		
		for (int x = 0; x < getTmap().getWidth(); x++) {
			for (int y = 0; y < getTmap().getHeight(); y++) {
				if (getTmap().getTileId(x, y, 0) == 1) {
					getBlocks().add(new Block(x * getTmap().getTileWidth(), y * getTmap().getTileHeight(), getSquareCornerOffset(), "square"));
				}
			}
		}
		
	}
	
	public void render(int x, int y) {
		getTmap().render(x, y);
	}
	
	public TiledMap getTmap() {
		return tmap;
	}
	
	public void setTmap(TiledMap tmap) {
		this.tmap = tmap;
	}
	
	public int getMapWidth() {
		return mapWidth;
	}
	
	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}
	
	public int getMapHeight() {
		return mapHeight;
	}
	
	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}
	
	public int[] getSquareCornerOffset() {
		return squareCornerOffset;
	}
	
	public void setSquareCornerOffset(int squareCornerOffset[]) {
		this.squareCornerOffset = squareCornerOffset;
	}
	
	public ArrayList<Block> getBlocks() {
		return blocks;
	}
	
	public void setBlocks(ArrayList<Block> blocks) {
		this.blocks = blocks;
	}
	
}
