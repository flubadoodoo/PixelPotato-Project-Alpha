package network;

import helper.Text;
import helper.TextDrawable;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

public class ChatLog {
	
	private ArrayList<Text>	log;
	
	public ChatLog() {
		log = new ArrayList<Text>();
	}
	
	public void addMessage(String string) throws SlickException {
		log.add(0, new Text(string, Text.SIZE.large, 0, 0, Color.white));
		if (log.size() > 10) {
			log.remove(log.size() - 1);
		}
	}
	
	public void render(int x, int y) {
		for (int i = 0; i < log.size(); i++) {
			TextDrawable.drawString(log.get(i), x, y + 200 - i * 30);
		}
	}
	
	public ArrayList<Text> getLog() {
		return log;
	}
	
	public void setLog(ArrayList<Text> log) {
		this.log = log;
	}
	
}
