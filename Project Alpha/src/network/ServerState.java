package network;

import helper.Text;
import helper.TextDrawable;

import java.io.IOException;
import java.util.ArrayList;

import network.Network.ClientPosition;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.esotericsoftware.kryonet.Server;

public class ServerState extends BasicGameState {
	
	private static int							state;
	
	private Image								serverTitleBG;
	private Text								serverTitleText;
	
	private Server								server;
	
	private static ArrayList<ClientPosition>	clientPositions;
	
	public ServerState(int state) {
		ServerState.state = state;
	}
	
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		serverTitleBG = new Image("network/Server bg.png");
		serverTitleText = new Text("Server", Text.SIZE.extralarge, 10 + (serverTitleBG.getWidth() / 2 - TextDrawable.getWidth("Server", Text.SIZE.extralarge) / 2), 35 + (serverTitleBG.getHeight() / 2 - TextDrawable.getHeight("Server", Text.SIZE.extralarge) / 2), Color.white);
		
		clientPositions = new ArrayList<Network.ClientPosition>();
		
		server = new Server();
		Network.register(server);
		server.addListener(new ServerNetworkListener(server));
		try {
			server.bind(Network.port);
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(serverTitleBG, 10, 35);
		TextDrawable.drawString(serverTitleText);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
		server.sendToAllTCP(ServerState.getClientPositions());
	}
	
	public int getID() {
		return state;
	}
	
	public static int getStateID() {
		return state;
	}
	
	public Server getServer() {
		return server;
	}
	
	public void setServer(Server server) {
		this.server = server;
	}
	
	public static ArrayList<ClientPosition> getClientPositions() {
		return ServerState.clientPositions;
	}
	
	public static void setClientPositions(ArrayList<ClientPosition> clientPositions) {
		ServerState.clientPositions = clientPositions;
	}
	
}
