package network;

import network.Network.ChatMessage;

import org.newdawn.slick.SlickException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientNetworkListener extends Listener {
	
	private Client	client;
	
	public ClientNetworkListener(Client client) {
		this.setClient(client);
	}
	
	public void connected(Connection connection) {
		try {
			ClientState.getChatLog().addMessage("[SERVER] Someone has connected.");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnected(Connection connection) {
		try {
			ClientState.getChatLog().addMessage("[SERVER] Someone has disconnected.");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		client.stop();
	}
	
	public void received(Connection connection, Object object) {
		if (object instanceof ChatMessage) {
			try {
				ClientState.getChatLog().addMessage(((ChatMessage) object).message);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
}
