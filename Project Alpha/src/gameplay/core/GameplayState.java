package gameplay.core;

import gameplay.entities.player.Player;
import gameplay.map.Map;
import gameplay.map.tiles.Tile;
import gameplay.notification.NotificationCenter;
import helper.Status;
import helper.Text;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import core.Main;

public class GameplayState extends BasicGameState {
	
	private static int					STATE;
	private static double				MOVE_SPEED;
	private static double				JUMP_STRENGTH;
	
	private Text						watermark;
	
	private static NotificationCenter	notificationCenter;
	
	private Map							map;
	private Player						player;
	
	//	private Shape							test;
	//	private org.newdawn.slick.geom.Polygon	testg;
	//	private Shape							rotated;
	
	static {
		JUMP_STRENGTH = -20.0;
		MOVE_SPEED = 0.01;
	}
	
	public GameplayState(int state) {
		GameplayState.STATE = state;
	}
	
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		gc.getGraphics().setClip(0, 0, Display.getWidth(), Display.getHeight());
		watermark = new Text(Status.getProjectStatus(), "Walkway", "Bold", 20, Color.white, 0, 10);
		watermark.setX(Display.getWidth() - watermark.getWidth() - 10);
		notificationCenter = new NotificationCenter();
		map = new Map(1000);
		player = new Player();
		notificationCenter.addNotification("Game started");
		/*int[] x = new int[] { 500, 600, 600, 500 };
		int[] y = new int[] { 510, 510, 600, 600 };
		test = new Polygon(x, y, 4);
		AffineTransform a = new AffineTransform();
		a.rotate(Math.toRadians(15), test.getBounds().getCenterX(), test.getBounds().getCenterY());
		rotated = a.createTransformedShape(test);
		testg = new org.newdawn.slick.geom.Polygon();
		PathIterator p = rotated.getPathIterator(null);
		while (!p.isDone()) {
			float[] coords = new float[6];
			int t = p.currentSegment(coords);
			System.out.println(coords[0] + ", " + coords[1]);
			testg.addPoint(coords[0], coords[1]);
			if (testg.getPointCount() == 4) {
				break;
			}
			p.next();
		}*/
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		watermark.drawString();
		map.drawMap();
		player.draw();
		notificationCenter.draw();
		//drawBoundingBoxes(g);
		//g.setColor(new org.newdawn.slick.Color(0f, 1f, 0f, 1f));
		//g.draw(testg);
	}
	
	@SuppressWarnings("unused")
	private void drawBoundingBoxes(Graphics g) {
		g.setColor(new org.newdawn.slick.Color(1f, 0f, 0f, 1f));
		Rectangle2D b = player.getBoundingBox();
		g.drawRect((float) b.getX(), (float) b.getY(), (float) b.getWidth(), (float) b.getHeight());
		for (Tile tile : map.getTiles()) {
			Rectangle2D t = tile.getBoundingBox();
			g.drawRect((float) t.getX(), (float) t.getY(), (float) t.getWidth(), (float) t.getHeight());
		}
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		double xOffp = map.getxOff();
		double yOffp = map.getyOff();
		handleInput(gc.getInput(), gc, game, delta);
		map.incrementYOff(player.getyVel());
		handleCollisions(player.getBoundingBox(), map.getTiles(), delta);
		player.update(delta, map.getxOff() - xOffp, map.getyOff() - yOffp);
		notificationCenter.update(delta);
	}
	
	private void handleCollisions(Rectangle2D boundingBox, Tile[] tiles, int delta) {
		boolean fall = true;
		for (Tile tile : tiles) {
			if (isOnScreen(tile)) {
				if (boundingBox.intersects(tile.getBoundingBox())) {
					Rectangle2D overlap = new Rectangle.Double();
					overlap.setRect(boundingBox.createIntersection(tile.getBoundingBox()));
					double xO = (player.getBoundingBox().getX() < overlap.getX()) ? overlap.getWidth() : -overlap.getWidth();
					if (overlap.getWidth() > overlap.getHeight()) {
						map.incrementYOff(overlap.getHeight());
						player.setyVel(0.0);
						fall = false;
						if (player.getMovementState() == Player.PLAYER_MOVEMENT_STATE.Jumping)
							player.setMovementState(Player.PLAYER_MOVEMENT_STATE.Standing);
					} else {
						map.incrementXOff(xO);
						player.setxVel(0.0);
					}
				}
				for (int i = 0; i < player.getGun().getBullets().size(); i++) {
					if (player.getGun().getBullets().get(i).getBounds().intersects(tile.getBoundingBox())) {
						player.getGun().getBullets().remove(i);
					}
				}
			}
		}
		if (fall) {
			player.incrementyVel(map.getGravity());
			if (player.getMovementState() != Player.PLAYER_MOVEMENT_STATE.Jumping)
				player.setMovementState(Player.PLAYER_MOVEMENT_STATE.Jumping);
		}
	}
	
	private void handleInput(Input input, GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			gc.exit();
		if (input.isKeyPressed(Input.KEY_BACK)) {
			game.enterState(Main.getMainMenuState(), new FadeOutTransition(), new FadeInTransition());
		}
		if (input.isKeyDown(Input.KEY_D)) {
			player.incrementxVel(-MOVE_SPEED * delta);
			if (player.getWalkingState() != Player.PLAYER_WALKING_STATE.Right)
				player.setWalkingState(Player.PLAYER_WALKING_STATE.Right);
		}
		if (input.isKeyDown(Input.KEY_A)) {
			player.incrementxVel(MOVE_SPEED * delta);
			if (player.getWalkingState() != Player.PLAYER_WALKING_STATE.Left)
				player.setWalkingState(Player.PLAYER_WALKING_STATE.Left);
		}
		if (!input.isKeyDown(Input.KEY_D) && !input.isKeyDown(Input.KEY_A)) {
			player.setxVel(player.getxVel() * 0.89);
			if (player.getxVel() < 0.001 && player.getxVel() > -0.001)
				player.setxVel(0.0);
		}
		if (player.getMovementState() != Player.PLAYER_MOVEMENT_STATE.Jumping) {
			if (input.isKeyDown(Input.KEY_W)) {
				player.setyVel(map.getGravity() * JUMP_STRENGTH);
				player.setMovementState(Player.PLAYER_MOVEMENT_STATE.Jumping);
			}
		}
		if (input.isKeyPressed(Input.KEY_N)) {
			notificationCenter.addNotification("" + gc.getTime());
		}
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			player.shoot(Math.toRadians(((new Vector2f((float) (input.getMouseX() - Player.getWeaponX()), (float) (input.getMouseY() - Player.getWeaponY())))).getTheta()));
		}
		if (input.isKeyPressed(Input.KEY_R)) {
			player.reload();
		}
		map.incrementXOff(player.getxVel());
	}
	
	private boolean isOnScreen(Tile tile) {
		return (tile.getX() + tile.getScale() >= -map.getxOff() && tile.getX() <= -map.getxOff() + Display.getWidth());
	}
	
	public int getID() {
		return STATE;
	}
	
	public static NotificationCenter getNotificationCenter() {
		return notificationCenter;
	}
	
	public static void setNotificationCenter(NotificationCenter notificationCenter) {
		GameplayState.notificationCenter = notificationCenter;
	}
	
}
