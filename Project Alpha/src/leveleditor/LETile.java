package leveleditor;

public class LETile {
	
	private int	x, y, size;
	
	public LETile(int x, int y, int size) {
		this.setX(x);
		this.setY(y);
		this.setSize(size);
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
