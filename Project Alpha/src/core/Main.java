package core;

import helper.Status;
import helper.TextDrawable;
import mainmenu.MainMenuState;
import network.ClientState;
import network.LobbyState;
import network.ServerState;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

public class Main extends StateBasedGame {
	
	// States
	private static final int	MAIN_MENU_STATE;
	private static final int	LOBBY_STATE;
	private static final int	SERVER_STATE;
	private static final int	CLIENT_STATE;
	
	private static final int	WIDTH;
	private static final int	HEIGHT;
	
	static {
		MAIN_MENU_STATE = 0;
		LOBBY_STATE = 1;
		SERVER_STATE = 2;
		CLIENT_STATE = 3;
		
		WIDTH = 1280;
		HEIGHT = 720;
	}
	
	// Main
	public static void main(String[] args) throws SlickException {
		AppGameContainer game = new AppGameContainer(new ScalableGame(new Main(Status.getProjectStatus()), getWidth(), getHeight(), true));
		game.setDisplayMode(1280, 720, false);
		game.setVSync(true);
		game.setMouseGrabbed(true);
		game.setTargetFrameRate(60);
		game.setShowFPS(true);
		Log.setVerbose(false);
		game.start();
	}
	
	// Constructor
	public Main(String title) throws SlickException {
		super(title);
	}
	
	// Initialize States
	public void initStatesList(GameContainer gc) throws SlickException {
		@SuppressWarnings ("unused")
		TextDrawable text = new TextDrawable();
		this.addState(new MainMenuState(MAIN_MENU_STATE));
		this.addState(new LobbyState(LOBBY_STATE));
		this.addState(new ServerState(SERVER_STATE));
		this.addState(new ClientState(CLIENT_STATE));
		this.enterState(LOBBY_STATE);
	}
	
	public static int getMainMenuState() {
		return MAIN_MENU_STATE;
	}
	
	public static int getGameplayState() {
		return SERVER_STATE;
	}
	
	public static int getWidth() {
		return WIDTH;
	}
	
	public static int getHeight() {
		return HEIGHT;
	}
	
}