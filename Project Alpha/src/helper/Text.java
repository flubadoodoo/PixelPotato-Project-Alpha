package helper;

import java.awt.Color;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class Text {
	
	private UnicodeFont	text;
	private int			x, y;
	private String		string;
	
	@SuppressWarnings("unchecked")
	public Text(String string, String fontName, String fontType, int size, Color color, int x, int y) throws SlickException {
		text = new UnicodeFont("fonts/" + fontName + "/" + fontType + ".ttf", size, false, false);
		text.getEffects().add(new ColorEffect(color));
		text.addAsciiGlyphs();
		text.loadGlyphs();
		this.x = x;
		this.y = y;
		this.string = string;
	}
	
	public void drawString() {
		text.drawString(x, y, string);
	}
	
	public void setString(String string) {
		this.string = string;
	}
	
	public int getWidth() {
		return text.getWidth(string);
	}
	
	public void setX(int x) {
		this.x = x;
	}
}
