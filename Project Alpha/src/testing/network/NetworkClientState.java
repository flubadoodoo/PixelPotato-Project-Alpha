package testing.network;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class NetworkClientState extends BasicGameState {
	
	private static int		state;
	
	private Socket			socket;
	private BufferedReader	inputReader;
	private PrintWriter		outputWriter;
	
	public NetworkClientState(int state) {
		NetworkClientState.state = state;
	}
	
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		System.out.println("client init");
		/*		try {
			System.out.println("Creating connection to server");
			socket = new Socket(InetAddress.getByName("Test"), 1234);
			System.out.println("connection made");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("out of try");
		if (socket == null) {
			System.out.println("switching to server");
			//game.enterState(Main.getNetworkServerState(), new FadeOutTransition(), new FadeInTransition());
		} else
			System.out.println("Connected to server at: " + socket.getInetAddress().getHostName() + " on port: " + socket.getPort());*/
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		
	}
	
	public int getID() {
		return state;
	}
	
}
