package testing.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

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
	
	// server
	private ServerSocket	serverSocket;
	private Socket			clientSocket;
	
	// client
	private Socket			socket;
	
	private BufferedReader	inputReader;
	private PrintWriter		outputWritter;
	
	private enum NETWORK_STATE {
		Server, Client
	};
	
	private NETWORK_STATE	networkState;
	
	public NetworkState(int state) {
		NetworkState.state = state;
	}
	
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		System.out.println("In init");
		try {
			// Check for server, if succeeded, then become a client
			setNetworkState(NETWORK_STATE.Client);
			System.out.println("Looking for server...");
			socket = new Socket(InetAddress.getByName("0.0.0.0"), 1234);
			// 			socket.connect(new InetSocketAddress("0.0.0.0", 5), 5000);
			if (socket != null)
				System.out.println("Connected to Server at: " + socket.getInetAddress().getHostAddress() + " on port: " + socket.getPort());
		} catch (UnknownHostException e) {
			System.out.println("Unknown Host: " + e.getLocalizedMessage());
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("No server, initializing server...");
			// Server is nonexistent, become a server
			setNetworkState(NETWORK_STATE.Server);
			try {
				serverSocket = new ServerSocket(1234);
				System.out.println("Server created at: " + serverSocket.getInetAddress().getHostAddress() + " on port: " + serverSocket.getLocalPort());
				System.out.println("Looking for client...");
				clientSocket = serverSocket.accept();
				System.out.println("Connected to Client at: " + clientSocket.getInetAddress().getHostAddress() + " on port: " + clientSocket.getPort());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		
	}
	
	public void update(GameContainer gc, StateBasedGame game, int arg2) throws SlickException {
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
	
	public NETWORK_STATE getNetworkState() {
		return networkState;
	}
	
	public void setNetworkState(NETWORK_STATE networkState) {
		this.networkState = networkState;
	}
	
}
