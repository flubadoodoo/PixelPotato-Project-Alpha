package splash;

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
import helper.Status;
import helper.Text;
import helper.Timer;

public class SplashState extends BasicGameState {
	
	private static int	STATE;
	
	private Image[]		splashAnim;
	public Animation	anim;
	private long		startTime, endTime;
	private int			startDelay;
	
	private Text		watermark;
	
	static {
		STATE = -1;
	}
	
	public SplashState(int state) {
		SplashState.STATE = state;
	}
	
	public void init(GameContainer gc, StateBasedGame sb) throws SlickException {
		initSplashAnim();
		initTimers(gc);
	}
	
	private void initSplashAnim() throws SlickException {
		splashAnim = new Image[28];
		for (int i = 0; i < 28; i++)
			splashAnim[i] = new Image("splash/images/Splash " + (i) + ".png");
		anim = new Animation(splashAnim, 1000 / 20);
		anim.setAutoUpdate(false);
		anim.stopAt(splashAnim.length - 1);
		
		watermark = new Text("Project PixelPotato" + Status.getProjectStatus(), "Walkway", "Bold", 20, Color.BLACK, 1000, 10);
	}
	
	private void initTimers(GameContainer gc) {
		startTime = gc.getTime();
		endTime = -1;
		
		startDelay = 1000;
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException {
		anim.draw();
		watermark.drawString();
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException {
		if (gc.getInput().isKeyDown(Input.KEY_ESCAPE))
			gc.exit();
		if (anim.isStopped()) {
			if (endTime == -1)
				endTime = gc.getTime();
			if (Timer.timeElapsed(gc.getTime(), endTime) > 2000) {
				sb.enterState(Main.getMainMenuState(), new FadeOutTransition(), new FadeInTransition());
			}
		} else if (gc.getTime() - startTime > startDelay) {
			anim.update(delta);
		}
	}
	
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		for (int i = 0; i < splashAnim.length; i++)
			splashAnim[i].destroy();
		splashAnim = null;
		anim = null;
	}
	
	public int getID() {
		return STATE;
	}
	
}
