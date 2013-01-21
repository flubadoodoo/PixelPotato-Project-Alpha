package gameplay.entities.weapon.gun;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AutomaticGun {
	
	private static int			bulletWidth;
	private static int			bulletHeight;
	
	private int					clipSize;
	private int					roundsInClip;
	private ArrayList<Bullet>	bullets;
	private double				firingVelocity;
	private Image				bulletImage;
	
	public AutomaticGun(int clipSize, double firingVelocity) throws SlickException {
		this.clipSize = clipSize;
		this.firingVelocity = firingVelocity;
		bullets = new ArrayList<Bullet>();
		setBulletImage(new Image("gameplay/entities/weapon/gun/Bullet.png"));
		setBulletWidth(bulletImage.getWidth());
		setBulletHeight(bulletImage.getHeight());
	}
	
	public void update(int delta, double deltaX, double deltaY) {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).translateDelta(firingVelocity * Math.cos(bullets.get(i).getAngle()) * delta + deltaX, firingVelocity * Math.sin(bullets.get(i).getAngle()) * delta + deltaY);
			bullets.get(i).getBounds().getBounds().translate((int) deltaX, (int) deltaY);
		}
	}
	
	public void draw() {
		for (Bullet bullet : bullets) {
			bulletImage.rotate(-bulletImage.getRotation());
			bulletImage.rotate((float) Math.toDegrees(bullet.getAngle()));
			bulletImage.draw((float) bullet.getCenterX(), (float) bullet.getCenterY());
		}
	}
	
	public void shoot(double angle) {
		bullets.add(new Bullet(angle));
	}
	
	public void reload() {
		roundsInClip = clipSize;
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
	
	public void decrementRoundsInClip() {
		this.roundsInClip--;
	}
	
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	
	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}
	
	public double getFiringVelocity() {
		return firingVelocity;
	}
	
	public void setFiringVelocity(double firingVelocity) {
		this.firingVelocity = firingVelocity;
	}
	
	public Image getBulletImage() {
		return bulletImage;
	}
	
	public void setBulletImage(Image bulletImage) {
		this.bulletImage = bulletImage;
	}
	
	public static int getBulletWidth() {
		return bulletWidth;
	}
	
	public static void setBulletWidth(int bulletWidth) {
		AutomaticGun.bulletWidth = bulletWidth;
	}
	
	public static int getBulletHeight() {
		return bulletHeight;
	}
	
	public static void setBulletHeight(int bulletHeight) {
		AutomaticGun.bulletHeight = bulletHeight;
	}
	
}
