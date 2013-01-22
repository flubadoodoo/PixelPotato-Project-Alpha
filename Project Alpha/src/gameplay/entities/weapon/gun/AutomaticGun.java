package gameplay.entities.weapon.gun;

import gameplay.core.GameplayState;

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
	private double				roundsPerSecond;
	private double				timeBetweenRounds;
	private double				bulletTimer;
	private Image				bulletImage;
	
	private enum GUN_CLIP_STATE {
		Shootable, Empty
	};
	
	private GUN_CLIP_STATE	gunClipState;
	
	private enum GUN_BULLET_TIMER_STATE {
		Shootable, Hold
	};
	
	private GUN_BULLET_TIMER_STATE	gunBulletTimerState;
	
	public AutomaticGun(int clipSize, double firingVelocity, double roundsPerSecond) throws SlickException {
		this.clipSize = clipSize;
		roundsInClip = clipSize;
		this.firingVelocity = firingVelocity;
		this.setRoundsPerSecond(roundsPerSecond);
		timeBetweenRounds = 1000.0 / roundsPerSecond;
		bullets = new ArrayList<Bullet>();
		setBulletImage(new Image("gameplay/entities/weapon/gun/Bullet.png"));
		setBulletWidth(bulletImage.getWidth());
		setBulletHeight(bulletImage.getHeight());
		setGunClipState(GUN_CLIP_STATE.Shootable);
		gunBulletTimerState = GUN_BULLET_TIMER_STATE.Shootable;
		bulletTimer = 0.0;
	}
	
	public void update(int delta, double deltaX, double deltaY) {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).translateDelta(firingVelocity * Math.cos(bullets.get(i).getAngle()) * delta + deltaX, firingVelocity * Math.sin(bullets.get(i).getAngle()) * delta + deltaY);
			bullets.get(i).getBounds().getBounds().translate((int) deltaX, (int) deltaY);
		}
		bulletTimer += delta;
		if (bulletTimer >= timeBetweenRounds) {
			gunBulletTimerState = GUN_BULLET_TIMER_STATE.Shootable;
			bulletTimer = 0.0;
		}
	}
	
	public void draw() {
		for (Bullet bullet : bullets) {
			bulletImage.rotate(-bulletImage.getRotation());
			bulletImage.rotate((float) Math.toDegrees(bullet.getAngle()));
			bulletImage.draw((float) bullet.getCenterX(), (float) bullet.getCenterY());
		}
	}
	
	public void shoot(double angle) throws SlickException {
		if (roundsInClip > 0 && gunBulletTimerState == GUN_BULLET_TIMER_STATE.Shootable) {
			bullets.add(new Bullet(angle));
			roundsInClip--;
			gunBulletTimerState = GUN_BULLET_TIMER_STATE.Hold;
		} else if (roundsInClip == 0) {
			if (getGunClipState() != GUN_CLIP_STATE.Empty) {
				setGunClipState(GUN_CLIP_STATE.Empty);
				GameplayState.getNotificationCenter().addNotification("Clip Empty");
			}
		}
	}
	
	public void reload() {
		roundsInClip = clipSize;
		setGunClipState(GUN_CLIP_STATE.Shootable);
		gunBulletTimerState = GUN_BULLET_TIMER_STATE.Shootable;
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
	
	public GUN_BULLET_TIMER_STATE getGunBulletTimerState() {
		return gunBulletTimerState;
	}
	
	public void setGunBulletTimerState(GUN_BULLET_TIMER_STATE gunBulletTimerState) {
		this.gunBulletTimerState = gunBulletTimerState;
	}
	
	public double getBulletTimer() {
		return bulletTimer;
	}
	
	public void setBulletTimer(double bulletTimer) {
		this.bulletTimer = bulletTimer;
	}
	
	public double getRoundsPerSecond() {
		return roundsPerSecond;
	}
	
	public void setRoundsPerSecond(double roundsPerSecond) {
		this.roundsPerSecond = roundsPerSecond;
	}
	
	public double getTimeBetweenRounds() {
		return timeBetweenRounds;
	}
	
	public void setTimeBetweenRounds(double timeBetweenRounds) {
		this.timeBetweenRounds = timeBetweenRounds;
	}
	
	public GUN_CLIP_STATE getGunClipState() {
		return gunClipState;
	}
	
	public void setGunClipState(GUN_CLIP_STATE gunClipState) {
		this.gunClipState = gunClipState;
	}
	
}
