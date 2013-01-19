package helper;

public abstract class Timer {
	
	public static long timeElapsed(long current, long previous) {
		return (current - previous);
	}
	
}
