package helper;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class TextDrawable {
	
	private static UnicodeFont	textSmall;
	private static UnicodeFont	textMedium;
	private static UnicodeFont	textLarge;
	
	@SuppressWarnings ("unchecked")
	public TextDrawable() throws SlickException {
		textSmall = new UnicodeFont("helper/font/Walkway/Bold.ttf", 8, false, false);
		textMedium = new UnicodeFont("helper/font/Walkway/Bold.ttf", 16, false, false);
		textLarge = new UnicodeFont("helper/font/Walkway/Bold.ttf", 24, false, false);
		textSmall.getEffects().add(new ColorEffect());
		textMedium.getEffects().add(new ColorEffect());
		textLarge.getEffects().add(new ColorEffect());
		textSmall.addAsciiGlyphs();
		textMedium.addAsciiGlyphs();
		textLarge.addAsciiGlyphs();
		textSmall.loadGlyphs();
		textMedium.loadGlyphs();
		textLarge.loadGlyphs();
	}
	
	public static void drawString(Text text) {
		switch (text.getSize()) {
			case small:
				textSmall.drawString((int) text.getX(), (int) text.getY(), text.getString(), text.getColor());
			case medium:
				textMedium.drawString((int) text.getX(), (int) text.getY(), text.getString(), text.getColor());
				break;
			case large:
				textLarge.drawString((int) text.getX(), (int) text.getY(), text.getString(), text.getColor());
				break;
		}
	}
	
	public static int getWidth(String string, Text.SIZE size) {
		switch (size) {
			case small:
				return textSmall.getWidth(string);
			case medium:
				return textMedium.getWidth(string);
			case large:
				return textLarge.getWidth(string);
			default:
				return 0;
		}
	}
	
	public static double getHeight(String string, Text.SIZE size) {
		switch (size) {
			case small:
				return textSmall.getHeight(string);
			case medium:
				return textMedium.getHeight(string);
			case large:
				return textLarge.getHeight(string);
			default:
				return 0;
		}
	}
}
