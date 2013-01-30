package network;

import java.util.ArrayList;

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
		kryo.register(ClientPosition.class);
		kryo.register(ArrayList.class);
		kryo.register(float[].class);
		kryo.register(IDSet.class);
	}
	
	public static class ClientPosition {
		public int		ID;
		public float	locX;
		public float	locY;
	}
	
	public static class IDSet {
		public int	ID;
	}
	
}
