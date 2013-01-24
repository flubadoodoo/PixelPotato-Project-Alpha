package testing.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
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

public class NetworkServerState extends BasicGameState {
	
	private static int		state;
	
	private ServerSocket	serverSocket;
	private Socket			clientSocket;
	private BufferedReader	inputReader;
	private PrintWriter		outputWritter;
	
	public NetworkServerState(int state) {
		NetworkServerState.state = state;
	}
	
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		System.out.println("server init");
		try {
			System.out.println("creating server");
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("Test", 1234));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("attempting to connect to client");
			clientSocket = serverSocket.accept();
			System.out.println("Client connected");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (clientSocket != null)
			System.out.println("Connected to client at: " + clientSocket.getInetAddress().getHostName() + " on port: " + clientSocket.getPort());
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
