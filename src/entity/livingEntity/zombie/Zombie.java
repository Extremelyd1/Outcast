package entity.livingEntity.zombie;

import entity.livingEntity.LivingEntity;
import map.tile.Tile;

public class Zombie extends LivingEntity {

	/** Target tile where the zombie should walk to */
	private Tile target;

	/** The behaviour the zombie currently follows */
	private ZombieBehaviour behaviour;

	public Zombie(Tile spawn, Tile target) {

		this.x = spawn.getX();
		this.y = spawn.getY();
		this.speed = 0.1;
		this.target = target;
		this.behaviour = ZombieBehaviour.WALKING_TO_BARRIER;

	}

	@Override
	public boolean update(int delta) {

		switch (behaviour) {

		case WALKING_TO_BARRIER:
			walkToBarrier();
			break;
		case BREACHING_BARRIER:
			breachBarrier();
			break;
		case WALK_TO_PLAYER:
			walkToPlayer();
			break;
		case IDLE:
			break;

		}

		return false;
	}

	private void walkToBarrier() {

		if (Math.abs(x - target.getX()) < speed) {
			motionX = target.getX() - x;
		} else if (x < target.getX()) {
			motionX = speed;
		} else if (x > target.getX()) {
			motionX = -speed;
		} else {
			motionX = 0;
		}
		
		if (Math.abs(y - target.getY()) < speed) {
			motionY = target.getY() - y;
		} else if (y < target.getY()) {
			motionY = speed;
		} else if (y > target.getY()) {
			motionY = -speed;
		} else {
			motionY = 0;
		}

	}

	private void breachBarrier() {

	}

	private void walkToPlayer() {

	}

	/**
	 * All different zombie types
	 */
	public enum ZombieType {
		// Normal zombie, eats brains, nothing special
		NORMAL,
		// Zombie that loves running marathons, very quick
		INFECTED
	}

	/**
	 * Enum that defines the possible behaviours that a zombie can have
	 */
	public enum ZombieBehaviour {
		// The zombie is walking to the barrier
		WALKING_TO_BARRIER,
		// The zombie is breaching the barrier
		BREACHING_BARRIER,
		// The zombie is searching for the player and walks towards him
		WALK_TO_PLAYER,
		// The zombie is not searching but instead wanders around / stands still
		IDLE
	}

}
