package lobby;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class Network {
	
	public static final int	port;
	
	static {
		port = 12345;
	}
	
	public static void register(EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(RegisterName.class);
		kryo.register(UpdateNames.class);
		kryo.register(String[].class);
		kryo.register(ChatMessage.class);
	}
	
	public static class RegisterName {
		public String	name;
	}
	
	public static class UpdateNames {
		public String[]	names;
	}
	
	public static class ChatMessage {
		public String	message;
	}
	
}
