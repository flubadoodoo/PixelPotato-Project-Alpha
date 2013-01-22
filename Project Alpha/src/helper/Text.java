package helper;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class Text {
	
	private UnicodeFont	text;
	private double		x, y;
	private String		string;
	private Color		color;
	
	@SuppressWarnings("unchecked")
	public Text(String string, String fontName, String fontType, int size, Color color, double x, double y) throws SlickException {
		this.color = color;
		text = new UnicodeFont("fonts/" + fontName + "/" + fontType + ".ttf", size, false, false);
		text.getEffects().add(new ColorEffect());
		text.addAsciiGlyphs();
		text.loadGlyphs();
		this.x = x;
		this.y = y;
		this.string = string;
	}
	
	public void drawString() {
		
		text.drawString((int) x, (int) y, string, color);
	}
	
	public void setString(String string) {
		this.string = string;
	}
	
	public int getWidth() {
		return text.getWidth(string);
	}
	
	public int getHeight() {
		return text.getHeight(string);
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getX() {
		return x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getY() {
		return y;
	}
	
	public void incrementY(double amount) {
		y += amount;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setAlpha(float value) {
		color.a = value;
	}
}
