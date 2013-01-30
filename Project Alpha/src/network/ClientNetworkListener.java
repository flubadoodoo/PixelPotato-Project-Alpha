package network;

import java.util.ArrayList;

import network.Network.ClientPosition;
import network.Network.IDSet;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientNetworkListener extends Listener {
	
	private Client	client;
	
	public ClientNetworkListener(Client client) {
		this.setClient(client);
	}
	
	public void connected(Connection connection) {
		
	}
	
	public void disconnected(Connection connection) {
		client.stop();
	}
	
	@SuppressWarnings ("unchecked")
	public void received(Connection connection, Object object) {
		if (object instanceof ArrayList) {
			ClientState.setAllClientPos((ArrayList<ClientPosition>) object);
		}
		if (object instanceof IDSet) {
			ClientState.setID(((IDSet) object).ID);
		}
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
}
