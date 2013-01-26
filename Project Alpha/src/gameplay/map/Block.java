package gameplay.map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

public class Block {
	
	private Polygon	boundingBox;
	
	public Block(float x, float y, int shapeCornerOffset[], String type) {
		boundingBox = new Polygon(new float[] { x + shapeCornerOffset[0], y + shapeCornerOffset[1], x + shapeCornerOffset[2], y + shapeCornerOffset[3], x + shapeCornerOffset[4], y + shapeCornerOffset[5], x + shapeCornerOffset[6], y + shapeCornerOffset[7] });
	}
	
	public void update(int delta) {
		
	}
	
	public void draw(Graphics g) {
		g.draw(boundingBox);
	}
	
	public Polygon getBoundingBox() {
		return boundingBox;
	}
	
	public void setBoundingBox(Polygon boundingBox) {
		this.boundingBox = boundingBox;
	}
	
}
