package gameplay.core;

import gameplay.entities.player.Player;
import gameplay.map.Map;
import helper.Status;
import helper.Text;

import java.awt.Color;

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
	}
	
	public int getID() {
		return STATE;
	}
	
}
