package leveleditor;

import helper.Status;
import helper.Text;

import java.awt.Color;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LevelEditorState extends BasicGameState {
	
	private static int								STATE;
	
	private Text									watermark;
	
	private static double							MOVE_SPEED;
	private final static double						UNIT_TILE;
	private final static double						TILE_SPACE;
	private final static double						WHOLE_TILE;
	private final static int						SMALL	= 29;
	private final static int						MEDIUM	= 62;
	private final static int						BIG		= 128;
	private int										tileSizeSelection;
	
	private double									scale;
	
	private boolean									gridOn;
	
	private double									xOff, yOff;
	private ArrayList<ArrayList<ArrayList<LETile>>>	grid;
	private Image[]									tileImages;
	
	static {
		STATE = -1;
		MOVE_SPEED = 0.5;
		UNIT_TILE = 29.0;
		TILE_SPACE = 4.0;
		WHOLE_TILE = UNIT_TILE + TILE_SPACE;
	}
	
	public LevelEditorState(int levelEditorState) {
		LevelEditorState.STATE = levelEditorState;
	}
	
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		gc.setMouseGrabbed(false);
		watermark = new Text(Status.getProjectStatus(), "Walkway", "Bold", 20, Color.WHITE, 0, 10);
		watermark.setX(Display.getWidth() - watermark.getWidth() - 10);
		
		setTileSizeSelection(1);
		
		setScale(1.0);
		
		gridOn = true;
		
		xOff = Display.getWidth() / 2.0;
		yOff = Display.getHeight() / 2.0;
		grid = new ArrayList<ArrayList<ArrayList<LETile>>>();
		for (int i = 0; i < 4; i++)
			grid.add(new ArrayList<ArrayList<LETile>>());
		tileImages = new Image[] { new Image("gameplay/map/tiles/Tile 29.png"), new Image("gameplay/map/tiles/Tile 62.png"), new Image("gameplay/map/tiles/Tile 128.png") };
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		watermark.drawString();
		g.scale((float) getScale(), (float) getScale());
		if (gridOn)
			drawGrid(g);
		drawTiles(g);
	}
	
	private void drawTiles(Graphics g) {
		for (int quadrant = 0; quadrant < grid.size(); quadrant++) {
			for (ArrayList<LETile> xList : grid.get(quadrant)) {
				for (LETile tile : xList) {
					if (tile != null)
						drawLETile(tile, quadrant);
				}
			}
		}
	}
	
	private void drawGrid(Graphics g) throws SlickException {
		for (int i = 0; i < Display.getWidth() / getScale(); i += WHOLE_TILE) {
			g.setColor(new org.newdawn.slick.Color(1f, 1f, 1f, 0.1f));
			g.drawLine(i + (float) (xOff % (WHOLE_TILE)), 0, i + (float) (xOff % (WHOLE_TILE)), (float) (Display.getHeight() / getScale()));
			g.drawLine(i + (float) (-TILE_SPACE) + (float) (xOff % (WHOLE_TILE)), 0, i + (float) (-TILE_SPACE) + (float) (xOff % (WHOLE_TILE)), (float) (Display.getHeight() / getScale()));
			if (i < Display.getHeight() / getScale()) {
				g.drawLine(0, i + (float) (yOff % (WHOLE_TILE)), (float) (Display.getWidth() / getScale()), i + (float) (yOff % (WHOLE_TILE)));
				g.drawLine(0, i + (float) (TILE_SPACE) + (float) (yOff % (WHOLE_TILE)), (float) (Display.getWidth() / getScale()), i + (float) (TILE_SPACE) + (float) (yOff % (WHOLE_TILE)));
			}
			
			/*g.drawLine((float) ((i + xOff % WHOLE_TILE) * scale), 0, (float) ((i + xOff % WHOLE_TILE) * scale), Display.getHeight());
			g.drawLine((float) ((i + -TILE_SPACE + xOff % WHOLE_TILE) * scale), 0, (float) ((i + -TILE_SPACE + xOff % WHOLE_TILE) * scale), Display.getHeight());
			if (i < Display.getHeight() / scale) {
				g.drawLine(0, (float) ((i + yOff % WHOLE_TILE) * scale), Display.getWidth(), (float) ((i + yOff % WHOLE_TILE) * scale));
				g.drawLine(0, (float) ((i + TILE_SPACE + yOff % WHOLE_TILE) * scale), Display.getWidth(), (float) ((i + TILE_SPACE + yOff % WHOLE_TILE) * scale));
			}*/
		}
		drawOrigin(g);
	}
	
	private void drawOrigin(Graphics g) {
		g.setColor(new org.newdawn.slick.Color(1f, 0f, 0f, 0.25f));
		g.drawLine((float) xOff, (float) 0, (float) xOff, (float) (Display.getHeight() / getScale()));
		g.drawLine((float) 0, (float) yOff, (float) (Display.getWidth() / getScale()), (float) yOff);
		/*g.drawLine((float) (xOff * scale), (float) 0, (float) (xOff * scale), (float) Display.getHeight());
		g.drawLine((float) 0, (float) (yOff * scale), (float) Display.getWidth(), (float) (yOff * scale));*/
	}
	
	private void drawLETile(LETile tile, int quadrant) {
		int x = (quadrant == 1 || quadrant == 2) ? -1 : 0;
		int y = (quadrant == 2 || quadrant == 3) ? -1 : 0;
		tileImages[(tile.getSize() == SMALL) ? 0 : (tile.getSize() == MEDIUM) ? 1 : 2].draw((float) (xOff + 1 + (tile.getX() + x) * (WHOLE_TILE)), (float) (yOff - tile.getSize() - (tile.getY() + y) * (WHOLE_TILE)));
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		handleInput(gc.getInput(), gc, delta);
		//System.out.println(scale);
	}
	
	private void handleInput(Input input, GameContainer gc, int delta) {
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			gc.exit();
		if (input.isKeyDown(Input.KEY_W))
			yOff += MOVE_SPEED * delta;
		if (input.isKeyDown(Input.KEY_S))
			yOff -= MOVE_SPEED * delta;
		if (input.isKeyDown(Input.KEY_A))
			xOff += MOVE_SPEED * delta;
		if (input.isKeyDown(Input.KEY_D))
			xOff -= MOVE_SPEED * delta;
		if (input.isKeyPressed(Input.KEY_G))
			gridOn = (gridOn) ? false : true;
		if (input.isKeyPressed(Input.KEY_1))
			setTileSizeSelection(1);
		if (input.isKeyPressed(Input.KEY_2))
			setTileSizeSelection(2);
		if (input.isKeyPressed(Input.KEY_3))
			setTileSizeSelection(3);
		if (input.isKeyDown(Input.KEY_MINUS)) {
			setScale(getScale() - 0.005);
		}
		if (input.isKeyDown(Input.KEY_EQUALS)) {
			setScale(getScale() + 0.005);
		}
		if (input.isKeyPressed(Input.KEY_R))
			reset();
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			addTileToGridAt(getTileX(input.getMouseX()), getTileY(input.getMouseY()), getQuadrant(input.getMouseX(), input.getMouseY()));
	}
	
	private void reset() {
		setScale(1.0);
		xOff = Display.getWidth() / 2;
		yOff = Display.getHeight() / 2;
	}
	
	private int getQuadrant(int mouseX, int mouseY) {
		int mX = (int) (mouseX - xOff);
		int mY = (int) (mouseY - yOff);
		if (mX >= 0 && mY <= 0)
			return 0;
		else if (mX < 0 && mY < 0)
			return 1;
		else if (mX < 0 && mY > 0)
			return 2;
		else
			return 3;
	}
	
	private void addTileToGridAt(int x, int y, int quadrant) {
		int tileX = x;
		int tileY = y;
		x = Math.abs(x);
		y = Math.abs(y);
		for (int i = grid.get(quadrant).size(); i <= x + 1; i++) {
			grid.get(quadrant).add(i, new ArrayList<LETile>());
		}
		for (int i = 0; i < grid.get(quadrant).size(); i++) {
			for (int j = grid.get(quadrant).get(i).size(); j <= y + 1; j++) {
				grid.get(quadrant).get(i).add(null);
			}
		}
		if (grid.get(quadrant).get(x).get(y) == null)
			grid.get(quadrant).get(x).add(y, new LETile(tileX, tileY, (tileSizeSelection == 1) ? SMALL : (tileSizeSelection == 2) ? MEDIUM : BIG));
	}
	
	private int getTileX(int mouseX) {
		return (int) ((mouseX - xOff * getScale()) / (WHOLE_TILE * getScale()));
	}
	
	private int getTileY(int mouseY) {
		return (int) ((mouseY - yOff * getScale()) / -(WHOLE_TILE * getScale()));
	}
	
	public int getID() {
		return LevelEditorState.STATE;
	}
	
	public int getTileSizeSelection() {
		return tileSizeSelection;
	}
	
	public void setTileSizeSelection(int tileSizeSelection) {
		if (tileSizeSelection >= 1 && tileSizeSelection <= 3)
			this.tileSizeSelection = tileSizeSelection;
	}
	
	public double getScale() {
		return scale;
	}
	
	public void setScale(double scale) {
		if (scale <= 1.1 && scale >= 0.1)
			this.scale = scale;
	}
}
