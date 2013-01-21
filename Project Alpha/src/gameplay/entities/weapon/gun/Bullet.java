package gameplay.entities.weapon.gun;

import gameplay.entities.player.Player;

public class Bullet {
	
	private double	centerX, centerY;
	private double	angle;
	
	public Bullet(double angle) {
		this.angle = angle;
		centerX = Player.getWeaponX();
		centerY = Player.getWeaponY();
	}
	
	public double getCenterX() {
		return centerX;
	}
	
	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}
	
	public double getCenterY() {
		return centerY;
	}
	
	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}
	
	public void translateDelta(double x, double y) {
		centerX += x;
		centerY += y;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
}
