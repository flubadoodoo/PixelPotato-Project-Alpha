package splash;

import helper.Status;
import helper.Text;
import helper.Timer;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import core.Main;

public class SplashState extends BasicGameState {
	
	private static int			STATE;
	
	private Image[]				splashAnim;
	public Animation			anim;
	private long				startTime;
	private int					startDelay;
	
	private Text				watermark;
	
	private DeferredResource	nextResource;
	private boolean				splashAnimLoaded;
	private boolean				allResourcesLoaded;
	private long				loadBarDelayTimeStart	= -1;
	private int					total					= -1;
	
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
		LoadingList.setDeferredLoading(true);
		splashAnimLoaded = false;
		allResourcesLoaded = false;
		splashAnim = new Image[28];
		for (int i = 0; i < 28; i++)
			splashAnim[i] = new Image("splash/images/Splash " + (i) + ".png");
		anim = new Animation(splashAnim, 1000 / 20);
		anim.setAutoUpdate(false);
		anim.stopAt(splashAnim.length - 1);
		
		watermark = new Text(Status.getProjectStatus(), "Walkway", "Bold", 20, Color.BLACK, 0, 10);
		watermark.setX(Display.getWidth() - watermark.getWidth() - 10);
	}
	
	private void initTimers(GameContainer gc) {
		startTime = -1;
		startDelay = 1000;
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException {
		if (splashAnimLoaded) {
			anim.draw();
			if (anim.isStopped()) {
				if (loadBarDelayTimeStart == -1) {
					loadBarDelayTimeStart = gc.getTime();
				}
				if (Timer.timeElapsed(gc.getTime(), loadBarDelayTimeStart) > 2000) {
					if (total == -1) {
						total = LoadingList.get().getRemainingResources();
					}
					int loaded = total - LoadingList.get().getRemainingResources();
					g.setColor(new org.newdawn.slick.Color(0f, 0f, 0f, 1f));
					g.fillRect((Display.getWidth() - total) / 2, 150, loaded, 20);
					g.drawRect((Display.getWidth() - total) / 2, 150, total, 20);
				}
			}
		}
		watermark.drawString();
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException {
		if (nextResource != null) {
			try {
				nextResource.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			nextResource = null;
		}
		if (LoadingList.get().getRemainingResources() > 0) {
			nextResource = LoadingList.get().getNext();
			if (LoadingList.get().getRemainingResources() <= LoadingList.get().getTotalResources() - 29 && !splashAnimLoaded) {
				splashAnimLoaded = true;
			}
		} else if (!allResourcesLoaded) {
			allResourcesLoaded = true;
		}
		if (gc.getInput().isKeyDown(Input.KEY_ESCAPE))
			gc.exit();
		if (splashAnimLoaded) {
			if (startTime == -1) {
				startTime = gc.getTime();
			}
			if (allResourcesLoaded) {
				sb.enterState(Main.getMainMenuState(), new FadeOutTransition(), new FadeInTransition());
			}
			if (gc.getTime() - startTime > startDelay) {
				anim.update(delta);
			}
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
