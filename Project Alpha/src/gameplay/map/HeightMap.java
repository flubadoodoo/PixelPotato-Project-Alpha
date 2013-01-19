package gameplay.map;

import java.util.Random;

public class HeightMap {
	
	private int[]	map;
	private Random	random;
	private int		maxJump;
	
	public HeightMap(int width) {
		random = new Random();
		maxJump = 50;
		initializeMap(width);
	}
	
	private void initializeMap(int width) {
		map = new int[width];
		map[0] = 0;
		for (int i = 1; i < width; i++) {
			map[i] = (map[i - 1] + random.nextInt(maxJump + 1) - maxJump / 2);
		}
	}
	
	public int[] getMap() {
		return map;
	}
	
	public void setMap(int[] map) {
		this.map = map;
	}
	
}
