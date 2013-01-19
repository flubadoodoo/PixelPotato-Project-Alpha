package gameplay.core;

import gameplay.entities.player.Player;
import gameplay.map.Map;
import gameplay.map.tiles.Tile;
import helper.Status;
import helper.Text;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import core.Main;

public class GameplayState extends BasicGameState {
	
	private static int		STATE;
	private static float	MOVE_SPEED;
	
	private Text			watermark;
	
	private Map				map;
	private Player			player;
	
	static {
		MOVE_SPEED = 0.1f;
	}
	
	public GameplayState(int state) {
		GameplayState.STATE = state;
	}
	
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		watermark = new Text(Status.getProjectStatus(), "Walkway", "Bold", 20, Color.WHITE, 1000, 10);
		map = new Map(1000);
		player = new Player();
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		watermark.drawString();
		map.drawMap();
		player.draw();
		drawBoundingBoxes(g);
	}
	
	private void drawBoundingBoxes(Graphics g) {
		Rectangle2D b = player.getBoundingBox();
		g.drawRect((float) b.getX(), (float) b.getY(), (float) b.getWidth(), (float) b.getHeight());
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		handleInput(gc.getInput(), gc, game, delta);
		map.incrementYOff(player.getyVel());
		handleCollisions(player.getBoundingBox(), map.getTiles(), delta);
		if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
			player.setyVel(3);
		}
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
					} else {
						map.incrementXOff(xO);
					}
				}
			}
		}
		if (fall) {
			player.incrementyVel(map.getGravity());
		}
	}
	
	private void handleInput(Input input, GameContainer gc, StateBasedGame game, int delta) {
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			gc.exit();
		if (input.isKeyPressed(Input.KEY_BACK)) {
			game.enterState(Main.getMainMenuState(), new FadeOutTransition(), new FadeInTransition());
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			map.incrementXOff(-MOVE_SPEED * delta);
		}
		if (input.isKeyDown(Input.KEY_LEFT)) {
			map.incrementXOff(MOVE_SPEED * delta);
		}
	}
	
	private boolean isOnScreen(Tile tile) {
		return (tile.getX() + tile.getScale() >= -map.getxOff() && tile.getX() <= -map.getxOff() + Display.getWidth());
	}
	
	public int getID() {
		return STATE;
	}
	
}
