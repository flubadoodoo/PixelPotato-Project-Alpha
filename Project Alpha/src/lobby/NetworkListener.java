package lobby;

import lobby.Packet.Packet0LoginRequest;
import lobby.Packet.Packet1LoginAnswer;
import lobby.Packet.Packet2Message;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class NetworkListener extends Listener {
	
	private Client	client;
	
	private boolean	isServer;
	
	public void init(Client client, boolean isServer) {
		this.setClient(client);
		this.setServer(isServer);
	}
	
	public void connected(Connection connection) {
		System.out.println((isServer) ? "[SERVER] Someone has connected." : "[CLIENT] You have connected.");
		if (!isServer) {
			client.sendTCP(new Packet0LoginRequest());
		}
	}
	
	public void disconnected(Connection connection) {
		System.out.println((isServer) ? "[SERVER] Someone has disconnected." : "[CLIENT] You have disconnected.");
	}
	
	public void received(Connection connection, Object object) {
		if (isServer) {
			if (object instanceof Packet0LoginRequest) {
				Packet1LoginAnswer loginAnswer = new Packet1LoginAnswer();
				loginAnswer.accepted = true;
				connection.sendTCP(loginAnswer);
			}
			if (object instanceof Packet2Message) {
				String message = ((Packet2Message) object).message;
				System.out.println("Client: " + message);
			}
		} else {
			if (object instanceof Packet1LoginAnswer) {
				boolean answer = ((Packet1LoginAnswer) object).accepted;
				if (answer) {
					System.out.println("Please enter your first message for the server: \n");
					while (client.isConnected()) {
						Packet2Message message = new Packet2Message();
						message.message = LobbyState.getScanner().nextLine();
						client.sendTCP(message);
						System.out.println("Please enter another message: \n");
					}
					System.out.println("[CLIENT] You have disconnected.");
					client.close();
				} else {
					client.close();
				}
			}
		}
	}
	
	public boolean isServer() {
		return isServer;
	}
	
	public void setServer(boolean isServer) {
		this.isServer = isServer;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
}
