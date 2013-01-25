package testing.particles;

import java.awt.Point;

import org.newdawn.slick.Image;
import org.newdawn.slick.particles.Particle;
import org.newdawn.slick.particles.ParticleEmitter;
import org.newdawn.slick.particles.ParticleSystem;

public class MenuParticleEmitter implements ParticleEmitter {

	private Point	origin;
	private int		particleSize;
	private int		timer;
	private int		emissionRate;
	private float	particleSpeed;
	private int		particleLife;

	private boolean	enabled;

	public MenuParticleEmitter (Point origin, int particleSize) {
		this.origin = origin;
		this.particleSize = particleSize;
		enabled = true;
		emissionRate = 100;
		timer = 0;
		particleSpeed = 0.5f;
		particleLife = 1500;
	}

	// check if completed
	public boolean completed () {
		return false;
	}

	// get image to draw
	public Image getImage () {
		return null;
	}

	// check if enabled
	public boolean isEnabled () {
		return enabled;
	}

	// if particles should maintain orientation
	public boolean isOriented () {
		return false;
	}

	// clear back to default
	public void resetState () {

	}

	// should be enabled
	public void setEnabled (boolean enabled) {
		this.enabled = enabled;
	}

	// update, produce any particles using system
	public void update (ParticleSystem system, int delta) {
		if (timer >= emissionRate) {
			Particle p = system.getNewParticle (this, particleLife);
			p.setColor (1, 1, 1, 1);
			p.setPosition ((float) origin.getX (), (float) Math.sin (Math.toRadians (origin.getX ())) * 15 + 400);
			p.setSize (particleSize);
			timer = 0;
		}
		timer += delta;
	}

	// update a single particle
	public void updateParticle (Particle p, int delta) {
		p.setPosition (p.getX () + (particleSpeed * delta), (float) Math.sin (Math.toRadians (p.getX ())) * 15 + 400);
		p.setColor (1, 1, 1, p.getLife () / particleLife);
	}

	// use additive blending
	public boolean useAdditive () {
		return false;
	}

	// use points on its own settings or not (use the system)
	public boolean usePoints (ParticleSystem system) {
		return false;
	}

	// no longer produce particles, mark as complete
	public void wrapUp () {

	}

	public void resume () {

	}

}
