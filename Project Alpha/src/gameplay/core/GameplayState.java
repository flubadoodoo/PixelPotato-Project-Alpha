package gameplay.core;

import gameplay.entities.player.Player;
import gameplay.map.Block;
import gameplay.map.BlockMap;
import helper.Status;
import helper.Text;
import helper.TextDrawable;

import org.newdawn.slick.Color;
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
	
	private static int	STATE;
	
	private Text		watermark;
	
	private BlockMap	map;
	private Player		player;
	
	public GameplayState(int state) {
		GameplayState.STATE = state;
	}
	
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		watermark = new Text(Status.getProjectStatus(), Text.SIZE.large, 0, 10, Color.white);
		watermark.setX(Main.getWidth() - watermark.getWidth() - 10);
		
		map = new BlockMap("gameplay/map/map.tmx");
		player = new Player();
	}
	
	public void leave(GameContainer container, StateBasedGame game) {
		watermark = null;
		setPlayer(null);
	}
	
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		TextDrawable.drawString(watermark);
		map.render(0, 0);
		player.draw();
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		handleInput(gc.getInput(), gc, game, delta);
	}
	
	private void handleInput(Input input, GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			gc.exit();
		if (input.isKeyPressed(Input.KEY_BACK)) {
			game.enterState(Main.getMainMenuState(), new FadeOutTransition(), new FadeInTransition());
		}
		if (input.isKeyDown(Input.KEY_A)) {
			player.move(-Player.getSpeed() * delta, 0);
			if (playerCollision()) {
				player.move(Player.getSpeed() * delta, 0);
			}
		}
		if (input.isKeyDown(Input.KEY_D)) {
			player.move(Player.getSpeed() * delta, 0);
			if (playerCollision()) {
				player.move(-Player.getSpeed() * delta, 0);
			}
		}
		if (input.isKeyDown(Input.KEY_W)) {
			player.move(0, -Player.getSpeed() * delta);
			if (playerCollision()) {
				player.move(0, Player.getSpeed() * delta);
			}
		}
		if (input.isKeyDown(Input.KEY_S)) {
			player.move(0, Player.getSpeed() * delta);
			if (playerCollision()) {
				player.move(0, -Player.getSpeed() * delta);
			}
		}
	}
	
	private boolean playerCollision() {
		for (Block block : map.getBlocks()) {
			if (player.getBoundingBox().intersects(block.getBoundingBox())) {
				return true;
			}
		}
		return false;
	}
	
	public int getID() {
		return STATE;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public BlockMap getMap() {
		return map;
	}
	
	public void BlockMap(BlockMap map) {
		this.map = map;
	}
	
}
