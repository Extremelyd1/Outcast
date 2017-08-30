package controller;

import java.util.ArrayList;
import java.util.List;

import entity.livingEntity.zombie.Zombie;

public class WaveController {

	private WavePhase phase;

	private List<Zombie> zombies;

	public WaveController() {

		this.phase = WavePhase.IDLE;
		this.zombies = new ArrayList<Zombie>();

	}

	public void update(int delta) {

		updateZombies(delta);

		switch (phase) {
		case AWAITING_INTERVAL:
			break;
		case SPAWNING_ZOMBIES:
			break;
		case IDLE:
			break;

		}

	}

	private void updateZombies(int delta) {

		List<Zombie> toRemove = new ArrayList<Zombie>();

		for (Zombie zombie : zombies) {
			if (zombie.update(delta)) {
				toRemove.add(zombie);
			}
		}

		for (Zombie zombie : toRemove) {
			zombies.remove(zombie);
		}
	}

	public void start() {

		
		
	}

	public enum WavePhase {
		// The wave is waiting, no zombies are being spawned
		IDLE,
		// Zombies are being spawned
		SPAWNING_ZOMBIES,
		// Waiting for the next zombie spawn interval
		AWAITING_INTERVAL,
		// Awaiting the start of the wave
		AWAITING_START_WAVE
	}

}
