package testing.network;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import core.Main;

public class NetworkState extends BasicGameState {
	
	private static int		state;
	
	private ServerSocket	serverSocket;
	private Socket			clientSocket;
	private BufferedReader	inputReader;
	private PrintWriter		outputWritter;
	
	public NetworkState(int state) {
		NetworkState.state = state;
	}
	
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_BACK)) {
			game.enterState(Main.getMainMenuState(), new FadeOutTransition(), new FadeInTransition());
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			gc.exit();
		}
	}
	
	public int getID() {
		return state;
	}
	
}
