package core;

import gameplay.core.GameplayState;
import mainmenu.MainMenuState;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import splash.SplashState;

public class Main extends StateBasedGame {
	
	// States
	private static final int	SPLASH_STATE;
	private static final int	MAIN_MENU_STATE;
	private static final int	GAMEPLAY_STATE;
	
	static {
		SPLASH_STATE = 0;
		MAIN_MENU_STATE = 1;
		GAMEPLAY_STATE = 2;
	}
	
	// Main
	public static void main(String[] args) throws SlickException {
		AppGameContainer game = new AppGameContainer(new Main("Simple Game"));
		game.setDisplayMode(1280, 720, true);
		game.setVSync(true);
		game.setMouseGrabbed(true);
		game.setTargetFrameRate(60);
		game.setShowFPS(true);
		game.start();
	}
	
	// Constructor
	public Main(String title) {
		super(title);
	}
	
	// Initialize States
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new SplashState(SPLASH_STATE));
		this.addState(new MainMenuState(MAIN_MENU_STATE));
		this.addState(new GameplayState(GAMEPLAY_STATE));
		this.enterState(GAMEPLAY_STATE);
	}
	
	public static int getMainMenuState() {
		return MAIN_MENU_STATE;
	}
	
	public static int getGameplayState() {
		return GAMEPLAY_STATE;
	}
	
}