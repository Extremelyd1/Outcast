package entity.livingEntity;

import entity.Entity;

public class LivingEntity extends Entity {

	protected int health;
	
	protected double motionX;
	protected double motionY;
	protected double speed;
	
	protected double radius;
	
	public void setMotionX(double motionX) {
		this.motionX = motionX;
	}
	
	public void setMotionY(double motionY) {
		this.motionY = motionY;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public double getRadius() {
		return radius;
	}
	
}
