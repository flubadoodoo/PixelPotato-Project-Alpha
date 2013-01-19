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
		watermark = new Text("Project PixelPotato " + Status.getProjectStatus(), "Walkway", "Bold", 20, Color.WHITE, 1000, 10);
		map = new Map(1000);
		player = new Player();
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		watermark.drawString();
		map.drawMap();
		player.draw();
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		Input input = gc.getInput();
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
		if (input.isKeyPressed(Input.KEY_UP)) {
			map.incrementYOff(50);
		}
		map.incrementYOff(player.getyVel());
		//		handlePlayerEnvironmentCollisionMethod1(player.getBounds(), map.getTiles(), delta);
		handlePlayerEnvironmentCollisionMethod2(player.getBounds(), map.getTiles(), delta);
		
	}
	
	private void handlePlayerEnvironmentCollisionMethod2(Rectangle bounds, Tile[] tiles, int delta) {
		for (Tile tile : tiles) {
			if (isOnScreen(tile)) {
				if (player.getBounds().intersects(tile.getBounds())) {
					Rectangle2D overlap = player.getBounds().createIntersection(tile.getBounds());
					if (overlap.getWidth() > overlap.getHeight()) {
						map.incrementYOff(overlap.getHeight());
						System.out.println(player.getyVel());
						player.setyVel(0.0f);
						System.out.println(player.getyVel());
					} else {
						map.incrementXOff(-overlap.getWidth());
					}
				} else {
					player.incrementYVel(player.getyAcc() * delta * 0.001f);
				}
			}
		}
	}
	
	/*private void handlePlayerEnvironmentCollisionMethod1(Rectangle playerBounds, Tile[] tiles, int delta) {
		boolean fall = true;
		for (Tile tile : tiles) {
			if (isOnScreen(tile)) {
				// NO CLUE
				//				System.out.println(playerBounds.getX() + 1);
				for (int i = 0; i < playerBounds.getHeight() - 2; i++) {
					if (tile.getBounds().contains(playerBounds.getMinX(), playerBounds.getY() + i)) {
						map.incrementXOff(player.getBounds().getMinX() - tile.getBounds().getMaxX());
						break;
					}
					if (tile.getBounds().contains(playerBounds.getMaxX(), playerBounds.getY() + i)) {
						map.incrementXOff(player.getBounds().getMaxX() - tile.getBounds().getMinX());
						break;
					}
				}
				if ((tile.getBounds().contains(playerBounds.getMinX() + 2, playerBounds.getMaxY())) || (tile.getBounds().contains(playerBounds.getMaxX() - 2, playerBounds.getMaxY()))) {
					fall = false;
					map.incrementYOff(player.getBounds().getMaxY() - tile.getBounds().getMinY());
					player.setyVel(0.0f);
					break;
				}
			}
		}
		if (fall) {
			player.incrementYVel(player.getyAcc() * delta);
		}
	}
	
	private void handleX(Rectangle playerBounds, Rectangle tileBounds) {
		for (int i = 0; i < playerBounds.getHeight(); i++) {
			if (tileBounds.contains(playerBounds.getX(), playerBounds.getY() + i)) {
				// Left of player | move player right
				
			}
		}
	}*/
	
	private boolean isOnScreen(Tile tile) {
		return (tile.getX() + tile.getScale() >= -map.getxOff() && tile.getX() <= -map.getxOff() + Display.getWidth());
	}
	
	public int getID() {
		return STATE;
	}
	
}
