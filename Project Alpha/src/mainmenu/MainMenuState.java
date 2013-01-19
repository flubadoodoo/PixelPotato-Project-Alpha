package mainmenu;

import helper.Text;

import java.awt.Color;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState {
	
	private Text			text;
	private Image			watermark;
	
	private int				selectionIndex;
	
	private Image[]			selectionArrowFrames;
	private Animation		selectionArrow;
	
	private Image[]			soloSelection;
	private Image[]			coopSelection;
	private Image[]			optionsSelection;
	
	private static int		STATE;
	private static String	IDP;
	
	static {
		STATE = -1;
		IDP = "mainmenu/images/";
	}
	
	public MainMenuState(int STATE) {
		MainMenuState.STATE = STATE;
	}
	
	public void init(GameContainer gc, StateBasedGame sg) throws SlickException {
		text = new Text("Project PixelPotato Alpha 0.0.0", "Walkway", "Bold", 20, Color.BLACK, 1000, 10);
		watermark = new Image(IDP + "Main Menu Background.png");
		
		selectionIndex = 0;
		
		selectionArrowFrames = new Image[] { new Image(IDP + "selections/Arrow 1.png"), new Image(IDP + "selections/Arrow 2.png") };
		selectionArrow = new Animation(selectionArrowFrames, 500);
		
		soloSelection = new Image[] { new Image(IDP + "selections/Solo Deselected.png"), new Image(IDP + "selections/Solo Selected.png") };
		coopSelection = new Image[] { new Image(IDP + "selections/Coop Deselected.png"), new Image(IDP + "selections/Coop Selected.png") };
		optionsSelection = new Image[] { new Image(IDP + "selections/Options Deselected.png"), new Image(IDP + "selections/Options Selected.png") };
	}
	
	public void render(GameContainer gc, StateBasedGame sg, Graphics g) throws SlickException {
		watermark.draw();
		text.drawString();
		selectionArrow.draw(300, (selectionIndex == 0) ? 425 : (selectionIndex == 1) ? 500 : 575);
		drawSelections();
	}
	
	private void drawSelections() {
		soloSelection[(selectionIndex == 0) ? 1 : 0].draw(211, 400);
		coopSelection[(selectionIndex == 1) ? 1 : 0].draw(198, 475);
		optionsSelection[(selectionIndex == 2) ? 1 : 0].draw(150, 550);
	}
	
	public void update(GameContainer gc, StateBasedGame sg, int delta) throws SlickException {
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			gc.exit();
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			if (selectionIndex < 2)
				selectionIndex++;
		}
		if (input.isKeyPressed(Input.KEY_UP)) {
			if (selectionIndex > 0)
				selectionIndex--;
		}
		selectionArrow.update(delta);
	}
	
	public int getID() {
		return STATE;
	}
	
}
