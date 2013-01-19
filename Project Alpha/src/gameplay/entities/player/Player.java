package gameplay.entities.player;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player {
	
	private static double		x, y;
	private static final String	IDP;
	
	private Image[]				sprites;
	private Rectangle2D			boundingBox;
	private double				yVel;
	
	public enum PLAYER_MOVEMENT_STATE {
		Standing, Walking, Jumping
	};
	
	private PLAYER_MOVEMENT_STATE	movementState;
	
	public enum PLAYER_WALKING_STATE {
		Left, Right
	};
	
	private PLAYER_WALKING_STATE	walkingState;
	
	private Animation				rightWalking;
	private Animation				leftWalking;
	
	static {
		IDP = "gameplay/entities/player/sprites/";
	}
	
	public Player() throws SlickException {
		sprites = new Image[] { newImage("Right 1"), newImage("Right 2"), newImage("Left 1"), newImage("Left 2") };
		rightWalking = new Animation(new Image[] { sprites[0], sprites[1] }, 500);
		rightWalking.setAutoUpdate(false);
		rightWalking.setLooping(true);
		leftWalking = new Animation(new Image[] { sprites[2], sprites[3] }, 500);
		leftWalking.setAutoUpdate(false);
		leftWalking.setLooping(true);
		x = Display.getWidth() / 2 - sprites[0].getWidth() / 2;
		y = Display.getHeight() / 2 - sprites[0].getHeight() / 2;
		boundingBox = new Rectangle.Double(x, y, sprites[0].getWidth(), sprites[0].getHeight());
		setMovementState(PLAYER_MOVEMENT_STATE.Jumping);
		setWalkingState(PLAYER_WALKING_STATE.Right);
	}
	
	private Image newImage(String name) throws SlickException {
		return new Image(IDP + name + ".png");
	}
	
	public void draw() {
		if (getWalkingState() == PLAYER_WALKING_STATE.Right)
			rightWalking.draw((float) x, (float) y);
		else if (getWalkingState() == PLAYER_WALKING_STATE.Left)
			leftWalking.draw((float) x, (float) y);
	}
	
	public void update(int delta) {
		if (getMovementState() == PLAYER_MOVEMENT_STATE.Walking) {
			if (getWalkingState() == PLAYER_WALKING_STATE.Right) {
				rightWalking.update(delta);
			} else if (getWalkingState() == PLAYER_WALKING_STATE.Left) {
				leftWalking.update(delta);
			}
		}
	}
	
	public static double getY() {
		return y;
	}
	
	public static double getX() {
		return x;
	}
	
	public Rectangle2D getBoundingBox() {
		return boundingBox;
	}
	
	public void setBoundingBox(Rectangle2D boundingBox) {
		this.boundingBox = boundingBox;
	}
	
	public void moveBoundingBoxX(double amount) {
		boundingBox.setRect(boundingBox.getX() + amount, boundingBox.getY(), boundingBox.getWidth(), boundingBox.getHeight());
	}
	
	public void moveBoundingBoxY(double amount) {
		boundingBox.setRect(boundingBox.getX(), boundingBox.getY() + amount, boundingBox.getWidth(), boundingBox.getHeight());
		
	}
	
	public double getyVel() {
		return yVel;
	}
	
	public void setyVel(double yVel) {
		this.yVel = yVel;
	}
	
	public void incrementyVel(double amount) {
		yVel += amount;
	}
	
	public PLAYER_MOVEMENT_STATE getMovementState() {
		return movementState;
	}
	
	public void setMovementState(PLAYER_MOVEMENT_STATE movementState) {
		this.movementState = movementState;
	}
	
	public PLAYER_WALKING_STATE getWalkingState() {
		return walkingState;
	}
	
	public void setWalkingState(PLAYER_WALKING_STATE walkingState) {
		this.walkingState = walkingState;
	}
	
	public Animation getLeftWalking() {
		return leftWalking;
	}
	
	public void setLeftWalking(Animation leftWalking) {
		this.leftWalking = leftWalking;
	}
	
}
