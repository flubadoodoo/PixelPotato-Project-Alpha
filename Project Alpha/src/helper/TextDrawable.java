package helper;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class TextDrawable {
	
	private static UnicodeFont	textSmall;
	private static UnicodeFont	textMedium;
	private static UnicodeFont	textLarge;
	private static UnicodeFont	textExtraLarge;
	
	@SuppressWarnings ("unchecked")
	public TextDrawable() throws SlickException {
		textSmall = new UnicodeFont("helper/font/Walkway/Bold.ttf", 8, false, false);
		textMedium = new UnicodeFont("helper/font/Walkway/Bold.ttf", 16, false, false);
		textLarge = new UnicodeFont("helper/font/Walkway/Bold.ttf", 32, false, false);
		textExtraLarge = new UnicodeFont("helper/font/Walkway/Bold.ttf", 64, false, false);
		textSmall.getEffects().add(new ColorEffect());
		textMedium.getEffects().add(new ColorEffect());
		textLarge.getEffects().add(new ColorEffect());
		textExtraLarge.getEffects().add(new ColorEffect());
		textSmall.addAsciiGlyphs();
		textMedium.addAsciiGlyphs();
		textLarge.addAsciiGlyphs();
		textExtraLarge.addAsciiGlyphs();
		textSmall.loadGlyphs();
		textMedium.loadGlyphs();
		textLarge.loadGlyphs();
		textExtraLarge.loadGlyphs();
	}
	
	public static void drawString(Text text) {
		TextDrawable.drawString(text, (int) text.getX(), (int) text.getY());
	}
	
	public static void drawString(Text text, int x, int y) {
		switch (text.getSize()) {
			case small:
				textSmall.drawString(x, y, text.getString(), text.getColor());
			case medium:
				textMedium.drawString(x, y, text.getString(), text.getColor());
				break;
			case large:
				textLarge.drawString(x, y, text.getString(), text.getColor());
				break;
			case extralarge:
				textExtraLarge.drawString(x, y, text.getString(), text.getColor());
				break;
		}
	}
	
	public static float getWidth(String string, Text.SIZE size) {
		switch (size) {
			case small:
				return textSmall.getWidth(string);
			case medium:
				return textMedium.getWidth(string);
			case large:
				return textLarge.getWidth(string);
			case extralarge:
				return textExtraLarge.getWidth(string);
			default:
				return 0;
		}
	}
	
	public static float getHeight(String string, Text.SIZE size) {
		switch (size) {
			case small:
				return textSmall.getHeight(string);
			case medium:
				return textMedium.getHeight(string);
			case large:
				return textLarge.getHeight(string);
			case extralarge:
				return textExtraLarge.getHeight(string);
			default:
				return 0;
		}
	}
	
}
