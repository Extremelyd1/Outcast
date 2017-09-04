package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entity.livingEntity.zombie.Zombie;
import entity.livingEntity.zombie.Zombie.ZombieType;
import map.tile.SpawnTile;

public class WaveController {

	private WavePhase wavePhase;
	private int waveNumber;

	/** All zombies in the current wave */
	private List<Zombie> zombies;

	/** All tiles zombies can spawn on */
	private List<SpawnTile> spawnTiles;

	public WaveController() {

		this.waveNumber = 0;
		this.wavePhase = WavePhase.IDLE;
		this.zombies = new ArrayList<Zombie>();
		this.spawnTiles = new ArrayList<SpawnTile>();

	}

	public void update(int delta) {

		updateZombies(delta);

		switch (wavePhase) {
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

	public void startWave() {

		SpawnTile spawnTile = spawnTiles.get(0);

		HashMap<ZombieType, Integer> spawnQueue = new HashMap<ZombieType, Integer>();
		spawnQueue.put(ZombieType.NORMAL, 1);

		spawnTile.setSpawnQueue(spawnQueue);

	}

	public void addZombie(Zombie zombie) {
		zombies.add(zombie);
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
