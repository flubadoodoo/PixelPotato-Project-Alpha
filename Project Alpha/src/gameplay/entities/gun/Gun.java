package gameplay.entities.gun;

public class Gun {
	
	private int	clipSize;
	private int	roundsInClip;
	
	public Gun(int clipSize, int roundsInClip) {
		this.clipSize = clipSize;
		this.roundsInClip = roundsInClip;
	}
	
	public int getClipSize() {
		return clipSize;
	}
	
	public void setClipSize(int clipSize) {
		this.clipSize = clipSize;
	}
	
	public int getRoundsInClip() {
		return roundsInClip;
	}
	
	public void setRoundsInClip(int roundsInClip) {
		this.roundsInClip = roundsInClip;
	}
	
}
