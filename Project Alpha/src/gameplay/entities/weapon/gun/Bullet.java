package gameplay.entities.weapon.gun;

import gameplay.entities.player.Player;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class Bullet {
	
	private double			centerX, centerY;
	private double			angle;
	
	private Shape			bounds;
	private AffineTransform	translate;
	
	public Bullet(double angle) {
		this.angle = angle;
		centerX = Player.getWeaponX();
		centerY = Player.getWeaponY();
		double width = AutomaticGun.getBulletWidth();
		double height = AutomaticGun.getBulletWidth();
		int x = (int) (centerX - width / 2);
		int y = (int) (centerY - height / 2);
		Polygon notRotated = new Polygon(new int[] { x, (int) (x + width), (int) (x + width), x }, new int[] { y, y, (int) (y + height), (int) (y + height) }, 4);
		AffineTransform transform = new AffineTransform();
		transform.rotate(angle, notRotated.getBounds().getCenterX(), notRotated.getBounds().getCenterY());
		bounds = transform.createTransformedShape(notRotated);
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
		translate = new AffineTransform();
		translate.translate(x, y);
		bounds = translate.createTransformedShape(bounds);
	}
	
	public double getAngle() {
		return angle;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public Shape getBounds() {
		return bounds;
	}
	
	public void setBounds(Shape bounds) {
		this.bounds = bounds;
	}
	
}
