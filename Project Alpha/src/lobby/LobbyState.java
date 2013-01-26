package lobby;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LobbyState extends BasicGameState {
	
	private static int	state;
	
	public LobbyState(int state) {
		LobbyState.state = state;
	}
	
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
	}
	
	public int getID() {
		return LobbyState.state;
	}
	
}
