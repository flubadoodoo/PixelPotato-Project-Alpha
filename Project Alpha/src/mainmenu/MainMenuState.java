package mainmenu;

import helper.Status;
import helper.Text;
import helper.TextDrawable;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import core.Main;

public class MainMenuState extends BasicGameState {
	
	private Text				watermark;
	private Image				background;
	
	private int					selectionIndex;
	
	private Image[]				selectionArrowFrames;
	private Animation			selectionArrow;
	
	private ArrayList<Image[]>	selectionImages;
	private float				alignmentX;
	private float				alignmentY;
	private int					distanceBetweenSelections	= 74;
	
	private static int			STATE;
	private static String		IDP;
	
	static {
		STATE = -1;
		IDP = "mainmenu/images/";
	}
	
	public MainMenuState(int STATE) {
		MainMenuState.STATE = STATE;
	}
	
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		initializeStateState();
	}
	
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		destroyStateState();
	}
	
	public void initializeStateState() throws SlickException {
		initWatermark();
		initBackground();
		initSelections();
	}
	
	private void destroyStateState() throws SlickException {
		destroyWatermark();
		destroyBackground();
		destroySelections();
	}
	
	private void initWatermark() throws SlickException {
		watermark = new Text(Status.getProjectStatus(), Text.SIZE.large, 0, 10, Color.black);
		watermark.setX(Main.getWidth() - watermark.getWidth() - 10);
	}
	
	private void destroyWatermark() {
		watermark = null;
	}
	
	private void initBackground() throws SlickException {
		background = new Image(IDP + "Main Menu Background.png");
	}
	
	private void destroyBackground() throws SlickException {
		background.destroy();
		background = null;
	}
	
	private void initSelections() throws SlickException {
		initSelectionImages();
		initSelectionVars();
	}
	
	private void destroySelections() throws SlickException {
		destroySelectionImages();
	}
	
	private void initSelectionImages() throws SlickException {
		selectionArrowFrames = new Image[] { new Image(IDP + "selections/Arrow 1.png"), new Image(IDP + "selections/Arrow 2.png") };
		selectionArrow = new Animation(selectionArrowFrames, 500);
		
		selectionImages = new ArrayList<Image[]>(2);
		selectionImages.add(new Image[] { new Image(IDP + "selections/Solo Deselected.png"), new Image(IDP + "selections/Solo Selected.png") });
		selectionImages.add(new Image[] { new Image(IDP + "selections/Coop Deselected.png"), new Image(IDP + "selections/Coop Selected.png") });
	}
	
	private void destroySelectionImages() throws SlickException {
		for (int i = 0; i < selectionArrowFrames.length; i++) {
			selectionArrowFrames[i].destroy();
		}
		selectionArrowFrames = null;
		for (int i = 0; i < selectionImages.size(); i++) {
			for (int j = 0; j < selectionImages.get(i).length; j++) {
				selectionImages.get(i)[j].destroy();
			}
		}
		selectionImages = null;
	}
	
	private void initSelectionVars() {
		selectionIndex = 0;
		
		float largest = selectionImages.get(0)[0].getWidth();
		float height = selectionImages.get(0)[0].getHeight();
		if (selectionImages.size() > 1) {
			for (int i = 1; i < selectionImages.size(); i++) {
				largest = Math.max(selectionImages.get(i - 1)[0].getWidth(), selectionImages.get(i)[0].getWidth());
				height += distanceBetweenSelections;
			}
		}
		alignmentX = distanceBetweenSelections + largest;
		alignmentY = Main.getHeight() - distanceBetweenSelections - height;
	}
	
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
	}
	
	public void render(GameContainer gc, StateBasedGame sg, Graphics g) throws SlickException {
		background.draw();
		TextDrawable.drawString(watermark);
		drawSelections();
		selectionArrow.draw(alignmentX + distanceBetweenSelections / 2, (selectionIndex == 0) ? alignmentY + selectionImages.get(0)[0].getHeight() / 2 : alignmentY + distanceBetweenSelections + selectionImages.get(1)[0].getHeight() / 4 + 5);
	}
	
	private void drawSelections() {
		for (int i = 0; i < selectionImages.size(); i++) {
			selectionImages.get(i)[(selectionIndex == i) ? 1 : 0].draw(alignmentX - selectionImages.get(i)[0].getWidth(), alignmentY + i * distanceBetweenSelections);
		}
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			gc.exit();
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			if (selectionIndex < selectionImages.size() - 1)
				selectionIndex++;
		}
		if (input.isKeyPressed(Input.KEY_UP)) {
			if (selectionIndex > 0)
				selectionIndex--;
		}
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			switch (selectionIndex) {
				case 0:
					game.enterState(Main.getGameplayState(), new FadeOutTransition(), new FadeInTransition());
					break;
				case 1:
					break;
			}
		}
		selectionArrow.update(delta);
	}
	
	public int getID() {
		return STATE;
	}
	
}
