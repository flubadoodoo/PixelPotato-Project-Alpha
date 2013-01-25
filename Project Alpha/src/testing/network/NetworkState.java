package testing.network;

import helper.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

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
	
	private Text			text;
	
	public NetworkState(int state) {
		NetworkState.state = state;
	}
	
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		System.out.println("In init");
		try {
			// Check for server, if succeeded, then become a client
			setNetworkState(NETWORK_STATE.Client);
			System.out.println("Looking for server...");
			socket = new Socket();
			socket.setSoTimeout(10);
			InetSocketAddress endPoint = new InetSocketAddress("192.168.1.103", 1234);
			//for (int i = 0; i < 256; i++) {
			//socket = new Socket(InetAddress.getByName("192.168.1." + i), 1234);
			socket.connect(endPoint);
			/*try {
				System.out.println("trying");
				endPoint = new InetSocketAddress("192.168.1." + i, 1234);
				socket.connect(endPoint, 10);
				System.out.println(gc.getTime());
			} catch (IOException e) {
				System.out.println(i);
			}*/
			//}
			outputWritter = new PrintWriter(socket.getOutputStream(), true);
			inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
				outputWritter = new PrintWriter(clientSocket.getOutputStream(), true);
				inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				System.out.println("Connected to Client at: " + clientSocket.getInetAddress().getHostAddress() + " on port: " + clientSocket.getPort());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		text = new Text((networkState == NETWORK_STATE.Server) ? "Server" : "Client", "Walkway", "Bold", 50, new Color(Color.white), 100, 100);
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		text.drawString();
	}
	
	public void update(GameContainer gc, StateBasedGame game, int arg2) throws SlickException {
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_BACK)) {
			game.enterState(Main.getMainMenuState(), new FadeOutTransition(), new FadeInTransition());
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			try {
				if (networkState == NETWORK_STATE.Server) {
					serverSocket.close();
					clientSocket.close();
				}
				if (networkState == NETWORK_STATE.Client) {
					socket.close();
				}
				outputWritter.close();
				inputReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			gc.exit();
		}
		if (networkState == NETWORK_STATE.Client) {
			if (socket.isConnected()) {
				if (input.isKeyPressed(Input.KEY_A)) {
					outputWritter.println("A");
				}
				if (input.isKeyPressed(Input.KEY_B)) {
					outputWritter.println("B");
				}
				if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
					outputWritter.println(input.getMouseX());
				}
			}
		}
		if (networkState == NETWORK_STATE.Server) {
			if (clientSocket.isConnected()) {
				try {
					text.setString(inputReader.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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
