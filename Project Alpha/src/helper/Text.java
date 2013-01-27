package helper;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

public class Text {
	
	public static enum SIZE {
		small, medium, large, extralarge
	};
	
	private String	string;
	private SIZE	size;
	private float	x, y;
	private Color	color;
	
	public Text(String string, Text.SIZE size, float x, float y, Color color) throws SlickException {
		this.string = string;
		this.size = size;
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public void setString(String string) {
		this.string = string;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getX() {
		return x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getY() {
		return y;
	}
	
	public void incrementY(float amount) {
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
	
	public String getString() {
		return string;
	}
	
	public SIZE getSize() {
		return size;
	}
	
	public void setSize(SIZE size) {
		this.size = size;
	}
	
	public float getWidth() {
		return TextDrawable.getWidth(string, size);
	}
	
	public float getHeight() {
		return TextDrawable.getHeight(string, size);
	}
	
}
