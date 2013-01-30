package network;

import network.Network.ClientPosition;
import network.Network.IDSet;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class ServerNetworkListener extends Listener {
	
	private Server	server;
	
	public ServerNetworkListener(Server server) {
		this.setServer(server);
	}
	
	public void connected(Connection connection) {
		IDSet id = new IDSet();
		id.ID = connection.getID();
		server.sendToTCP(connection.getID(), id);
		ClientPosition pos = new ClientPosition();
		pos.ID = connection.getID();
		ServerState.getClientPositions().add(pos);
	}
	
	public void disconnected(Connection connection) {
		for (int i = 0; i < ServerState.getClientPositions().size(); i++) {
			if (ServerState.getClientPositions().get(i).ID == connection.getID()) {
				ServerState.getClientPositions().remove(i);
				break;
			}
		}
	}
	
	public void received(Connection connection, Object object) {
		if (object instanceof ClientPosition) {
			for (int i = 0; i < ServerState.getClientPositions().size(); i++) {
				if (((ClientPosition) object).ID == ServerState.getClientPositions().get(i).ID) {
					ServerState.getClientPositions().get(i).locX = ((ClientPosition) object).locX;
					ServerState.getClientPositions().get(i).locY = ((ClientPosition) object).locY;
					server.sendToAllTCP(ServerState.getClientPositions());
					break;
				}
			}
		}
	}
	
	public Server getServer() {
		return server;
	}
	
	public void setServer(Server server) {
		this.server = server;
	}
	
}
