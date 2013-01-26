package lobby;

import java.io.IOException;
import java.util.Scanner;

import lobby.Packet.Packet0LoginRequest;
import lobby.Packet.Packet1LoginAnswer;
import lobby.Packet.Packet2Message;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;

public class LobbyState extends BasicGameState {
	
	private static int		state;
	private boolean			isServer;
	
	private Server			server;
	private Client			client;
	private static Scanner	scanner;
	
	public LobbyState(int state, boolean server) {
		LobbyState.state = state;
		isServer = server;
	}
	
	public void enter(GameContainer container, StateBasedGame game) {
		if (isServer) {
			server = new Server();
			registerPackets(server);
			NetworkListener networkListener = new NetworkListener();
			networkListener.init(null, isServer);
			server.addListener(networkListener);
			try {
				server.bind(1234);
				server.start();
				System.out.println("Server started");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			setScanner(new Scanner(System.in));
			client = new Client();
			registerPackets(client);
			NetworkListener networkListener = new NetworkListener();
			networkListener.init(client, isServer);
			client.addListener(networkListener);
			client.start();
			try {
				System.out.println("Please enter the specified I.P: ");
				client.connect(5000, getScanner().nextLine(), 1234);
				client.setKeepAliveTCP(50);
			} catch (IOException e) {
				e.printStackTrace();
				client.stop();
			}
		}
	}
	
	private void registerPackets(Object object) {
		Kryo kryo;
		if (isServer) {
			kryo = ((Server) object).getKryo();
		} else {
			kryo = ((Client) object).getKryo();
		}
		kryo.register(Packet0LoginRequest.class);
		kryo.register(Packet1LoginAnswer.class);
		kryo.register(Packet2Message.class);
	}
	
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}
	
	public int getID() {
		return LobbyState.state;
	}
	
	public static Scanner getScanner() {
		return scanner;
	}
	
	public static void setScanner(Scanner scanner) {
		LobbyState.scanner = scanner;
	}
	
}
