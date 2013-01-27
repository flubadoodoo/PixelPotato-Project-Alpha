package network;

import helper.Text;
import helper.TextDrawable;

import java.io.IOException;
import java.util.Scanner;

import network.Network.ChatMessage;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.esotericsoftware.kryonet.Client;

public class ClientState extends BasicGameState {
	
	private static int		state;
	
	private Client			client;
	private Scanner			scanner;
	
	private Image			clientTitleBG;
	private Text			clientTitleText;
	
	private static ChatLog	chatLog;
	
	public void enter(GameContainer container, StateBasedGame game) {
		client = new Client();
		client.start();
		Network.register(client);
		try {
			client.connect(5000, Network.host, Network.port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		client.addListener(new ClientNetworkListener(client));
		new Thread() {
			public void run() {
				while (scanner.hasNextLine()) {
					String m = scanner.nextLine();
					ChatMessage message = new ChatMessage();
					message.message = m;
					client.sendTCP(message);
				}
			}
		}.start();
		
	}
	
	public ClientState(int state) {
		ClientState.state = state;
	}
	
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		clientTitleBG = new Image("network/Client bg.png");
		clientTitleText = new Text("Client", Text.SIZE.extralarge, 10 + (clientTitleBG.getWidth() / 2 - TextDrawable.getWidth("Server", Text.SIZE.extralarge) / 2), 35 + (clientTitleBG.getHeight() / 2 - TextDrawable.getHeight("Client", Text.SIZE.extralarge) / 2), Color.white);
		scanner = new Scanner(System.in);
		chatLog = new ChatLog();
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(clientTitleBG, 10, 35);
		TextDrawable.drawString(clientTitleText);
		chatLog.render(300, 200);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}
	
	public int getID() {
		return state;
	}
	
	public static int getStateID() {
		return ClientState.state;
	}
	
	public static int getState() {
		return state;
	}
	
	public static void setState(int state) {
		ClientState.state = state;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public Image getClientTitleBG() {
		return clientTitleBG;
	}
	
	public void setClientTitleBG(Image clientTitleBG) {
		this.clientTitleBG = clientTitleBG;
	}
	
	public Text getClientTitleText() {
		return clientTitleText;
	}
	
	public void setClientTitleText(Text clientTitleText) {
		this.clientTitleText = clientTitleText;
	}
	
	public Scanner getScanner() {
		return scanner;
	}
	
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public static ChatLog getChatLog() {
		return chatLog;
	}
	
	public static void setChatLog(ChatLog chatLog) {
		ClientState.chatLog = chatLog;
	}
	
}
