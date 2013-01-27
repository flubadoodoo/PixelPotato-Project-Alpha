package network;

import network.Network.ChatMessage;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class ServerNetworkListener extends Listener {
	
	private Server	server;
	
	public ServerNetworkListener(Server server) {
		this.setServer(server);
	}
	
	public void connected(Connection connection) {
		ChatMessage message = new ChatMessage();
		message.message = "Someone has joined.";
		server.sendToAllExceptTCP(connection.getID(), message);
	}
	
	public void disconnected(Connection connection) {
		ChatMessage message = new ChatMessage();
		message.message = "Someone has left.";
		server.sendToAllExceptTCP(connection.getID(), message);
	}
	
	public void received(Connection connection, Object object) {
		if (object instanceof ChatMessage) {
			server.sendToAllTCP(object);
		}
	}
	
	public Server getServer() {
		return server;
	}
	
	public void setServer(Server server) {
		this.server = server;
	}
	
}
