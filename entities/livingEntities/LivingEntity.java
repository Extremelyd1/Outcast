package entities.livingEntities;

import entities.Entity;

public class LivingEntity extends Entity {

	protected int health;
	
	protected double motionX;
	protected double motionY;
	protected double speedX;
	protected double speedY;
	
	public void setMotionX(double motionX) {
		this.motionX = motionX;
	}
	
	public void setMotionY(double motionY) {
		this.motionY = motionY;
	}
	
}
