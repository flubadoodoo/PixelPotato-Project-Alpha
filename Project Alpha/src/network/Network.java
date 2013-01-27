package network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class Network {
	
	public static final String	host;
	public static final int		port;
	
	static {
		host = "127.0.0.1";
		port = 1234;
	}
	
	public static void register(EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(ChatMessage.class);
	}
	
	public static class ChatMessage {
		public String	message;
	}
	
}
