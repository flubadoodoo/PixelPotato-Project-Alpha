package mainmenu;

import helper.Status;
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
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import core.Main;

public class MainMenuState extends BasicGameState {
	
	private Text			watermark;
	private Image			background;
	
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
		watermark = new Text("Project PixelPotato" + Status.getProjectStatus(), "Walkway", "Bold", 20, Color.BLACK, 1000, 10);
		background = new Image(IDP + "Main Menu Background.png");
		
		selectionIndex = 0;
		
		selectionArrowFrames = new Image[] { new Image(IDP + "selections/Arrow 1.png"), new Image(IDP + "selections/Arrow 2.png") };
		selectionArrow = new Animation(selectionArrowFrames, 500);
		
		soloSelection = new Image[] { new Image(IDP + "selections/Solo Deselected.png"), new Image(IDP + "selections/Solo Selected.png") };
		coopSelection = new Image[] { new Image(IDP + "selections/Coop Deselected.png"), new Image(IDP + "selections/Coop Selected.png") };
		optionsSelection = new Image[] { new Image(IDP + "selections/Options Deselected.png"), new Image(IDP + "selections/Options Selected.png") };
	}
	
	public void render(GameContainer gc, StateBasedGame sg, Graphics g) throws SlickException {
		background.draw();
		watermark.drawString();
		selectionArrow.draw(300, (selectionIndex == 0) ? 425 : (selectionIndex == 1) ? 500 : 575);
		drawSelections();
	}
	
	private void drawSelections() {
		soloSelection[(selectionIndex == 0) ? 1 : 0].draw(211, 400);
		coopSelection[(selectionIndex == 1) ? 1 : 0].draw(198, 475);
		optionsSelection[(selectionIndex == 2) ? 1 : 0].draw(150, 550);
	}
	
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
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
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			switch (selectionIndex) {
			case 0:
				game.enterState(Main.getGameplayState(), new FadeOutTransition(), new FadeInTransition());
				break;
			case 1:
				break;
			case 2:
				break;
			}
		}
		selectionArrow.update(delta);
	}
	
	public int getID() {
		return STATE;
	}
	
}
