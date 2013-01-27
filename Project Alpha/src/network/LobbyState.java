package network;

import helper.Text;
import helper.TextDrawable;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import core.Main;

public class LobbyState extends BasicGameState {
	
	private static int	state;
	
	private Image		lobbyBG, serverNotSelectedBG, clientNotSelectedBG, serverSelectedBG, clientSelectedBG;
	private Point		serverAnchor, clientAnchor;
	private Polygon		serverButton, clientButton;
	private Text		lobbyText, serverText, clientText;
	
	public LobbyState(int state) {
		LobbyState.state = state;
	}
	
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		container.setMouseGrabbed(false);
		lobbyBG = new Image("network/Lobby bg.png");
		serverNotSelectedBG = new Image("network/Server not selected bg.png");
		clientNotSelectedBG = new Image("network/Client not selected bg.png");
		serverSelectedBG = new Image("network/Server selected bg.png");
		clientSelectedBG = new Image("network/Client selected bg.png");
		serverAnchor = new Point(Main.getWidth() / 3 - serverNotSelectedBG.getWidth() / 2, Main.getHeight() / 2 - serverNotSelectedBG.getHeight() / 2);
		clientAnchor = new Point(Main.getWidth() * 2 / 3 - clientNotSelectedBG.getWidth() / 2, Main.getHeight() / 2 - clientNotSelectedBG.getHeight() / 2);
		serverButton = new Polygon(new float[] { serverAnchor.getX(), serverAnchor.getY(), serverAnchor.getX() + serverNotSelectedBG.getWidth(), serverAnchor.getY(), serverAnchor.getX() + serverNotSelectedBG.getWidth(), serverAnchor.getY() + serverNotSelectedBG.getHeight(), serverAnchor.getX(),
				serverAnchor.getY() + serverNotSelectedBG.getHeight() });
		clientButton = new Polygon(new float[] { clientAnchor.getX(), clientAnchor.getY(), clientAnchor.getX() + clientNotSelectedBG.getWidth(), clientAnchor.getY(), clientAnchor.getX() + clientNotSelectedBG.getWidth(), clientAnchor.getY() + clientNotSelectedBG.getHeight(), clientAnchor.getX(),
				clientAnchor.getY() + clientNotSelectedBG.getHeight() });
		lobbyText = new Text("Lobby", Text.SIZE.extralarge, 10 + (lobbyBG.getWidth() / 2 - TextDrawable.getWidth("Lobby", Text.SIZE.extralarge) / 2), 35 + (lobbyBG.getHeight() / 2 - TextDrawable.getHeight("Lobby", Text.SIZE.extralarge) / 2), Color.white);
		serverText = new Text("Server", Text.SIZE.extralarge, Main.getWidth() / 3 - TextDrawable.getWidth("Server", Text.SIZE.extralarge) / 2, Main.getHeight() / 2 - TextDrawable.getHeight("Server", Text.SIZE.extralarge) / 2, Color.white);
		clientText = new Text("Client", Text.SIZE.extralarge, Main.getWidth() * 2 / 3 - TextDrawable.getWidth("Client", Text.SIZE.extralarge) / 2, Main.getHeight() / 2 - TextDrawable.getHeight("Client", Text.SIZE.extralarge) / 2, Color.white);
	}
	
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		lobbyBG.destroy();
		serverNotSelectedBG.destroy();
		clientNotSelectedBG.destroy();
		serverSelectedBG.destroy();
		clientSelectedBG.destroy();
		lobbyBG = null;
		serverNotSelectedBG = null;
		clientNotSelectedBG = null;
		serverSelectedBG = null;
		clientSelectedBG = null;
		serverAnchor = null;
		clientAnchor = null;
		serverButton = null;
		clientButton = null;
		lobbyText = null;
		serverText = null;
		clientText = null;
	}
	
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(lobbyBG, 10, 35);
		g.drawImage(serverButton.contains(container.getInput().getMouseX(), container.getInput().getMouseY()) ? serverSelectedBG : serverNotSelectedBG, serverAnchor.getX(), serverAnchor.getY());
		g.drawImage(clientButton.contains(container.getInput().getMouseX(), container.getInput().getMouseY()) ? clientSelectedBG : clientNotSelectedBG, clientAnchor.getX(), clientAnchor.getY());
		TextDrawable.drawString(lobbyText);
		TextDrawable.drawString(serverText);
		TextDrawable.drawString(clientText);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
		if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (serverButton.contains(container.getInput().getMouseX(), container.getInput().getMouseY())) {
				game.enterState(ServerState.getStateID());
			}
			if (clientButton.contains(container.getInput().getMouseX(), container.getInput().getMouseY())) {
				game.enterState(ClientState.getStateID());
			}
		}
	}
	
	public int getID() {
		return LobbyState.state;
	}
	
	public static int getState() {
		return state;
	}
	
	public static void setState(int state) {
		LobbyState.state = state;
	}
	
	public Text getLobbyText() {
		return lobbyText;
	}
	
	public void setLobbyText(Text lobbyText) {
		this.lobbyText = lobbyText;
	}
	
	public Image getLobbyBG() {
		return lobbyBG;
	}
	
	public void setLobbyBG(Image lobbyBG) {
		this.lobbyBG = lobbyBG;
	}
	
	public Image getServerBG() {
		return serverNotSelectedBG;
	}
	
	public void setServerBG(Image serverBG) {
		this.serverNotSelectedBG = serverBG;
	}
	
	public Image getClientBG() {
		return clientNotSelectedBG;
	}
	
	public void setClientBG(Image clientBG) {
		this.clientNotSelectedBG = clientBG;
	}
	
	public Text getServerText() {
		return serverText;
	}
	
	public void setServerText(Text serverText) {
		this.serverText = serverText;
	}
	
	public Text getClientText() {
		return clientText;
	}
	
	public void setClientText(Text clientText) {
		this.clientText = clientText;
	}
	
	public Image getServerNotSelectedBG() {
		return serverNotSelectedBG;
	}
	
	public void setServerNotSelectedBG(Image serverNotSelectedBG) {
		this.serverNotSelectedBG = serverNotSelectedBG;
	}
	
	public Image getClientNotSelectedBG() {
		return clientNotSelectedBG;
	}
	
	public void setClientNotSelectedBG(Image clientNotSelectedBG) {
		this.clientNotSelectedBG = clientNotSelectedBG;
	}
	
	public Image getServerSelectedBG() {
		return serverSelectedBG;
	}
	
	public void setServerSelectedBG(Image serverSelectedBG) {
		this.serverSelectedBG = serverSelectedBG;
	}
	
	public Image getClientSelectedBG() {
		return clientSelectedBG;
	}
	
	public void setClientSelectedBG(Image clientSelectedBG) {
		this.clientSelectedBG = clientSelectedBG;
	}
	
	public Polygon getServerButton() {
		return serverButton;
	}
	
	public void setServerButton(Polygon serverButton) {
		this.serverButton = serverButton;
	}
	
	public Polygon getClientButton() {
		return clientButton;
	}
	
	public void setClientButton(Polygon clientButton) {
		this.clientButton = clientButton;
	}
	
}
