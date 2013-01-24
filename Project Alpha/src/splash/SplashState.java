package splash;

import helper.Status;
import helper.Text;
import helper.Timer;

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

public class SplashState extends BasicGameState {
	
	private static int	STATE;
	
	private Image[]		splashAnim;
	public Animation	anim;
	private long		startTime;
	private int			startDelay;
	private int			endDelay;
	
	private Text		watermark;
	
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
		
		watermark = new Text(Status.getProjectStatus(), "Walkway", "Bold", 20, Color.black, 0, 10);
		watermark.setX(Main.getWidth() - watermark.getWidth() - 10);
	}
	
	private void initTimers(GameContainer gc) {
		startTime = -1;
		startDelay = 1000;
		endDelay = 2000;
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException {
		anim.draw();
		watermark.drawString();
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException {
		if (gc.getInput().isKeyDown(Input.KEY_ESCAPE))
			gc.exit();
		if (anim.isStopped()) {
			if (startTime == -1) {
				startTime = gc.getTime();
			}
			if (Timer.timeElapsed(gc.getTime(), startTime) > endDelay) {
				sb.enterState(Main.getMainMenuState(), new FadeOutTransition(), new FadeInTransition());
			}
		}
		if (gc.getTime() - startTime > startDelay) {
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
