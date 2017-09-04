package map.tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import entity.livingEntity.zombie.Zombie;
import entity.livingEntity.zombie.Zombie.ZombieType;
import gameState.InGame;

public class SpawnTile extends Tile {

	/** Delay in milliseconds between each zombie spawn */
	private final long SPAWN_DELAY = 1000;
	/** Time elapsed since the last spawn */
	private long timeElapsedSinceLastSpawn;

	/** Queue which holds which zombies should be spawned at this tile */
	private HashMap<ZombieType, Integer> spawnQueue;

	/** Target tile to walk to */
	private Tile barrierTarget;

	public SpawnTile(int x, int y, int z, Tile barrierTarget) {

		super(x, y, z, null, false, TileType.ZOMBIE_SPAWN);

		this.barrierTarget = barrierTarget;
		this.timeElapsedSinceLastSpawn = 0;
		this.spawnQueue = new HashMap<ZombieType, Integer>();

	}
	
	public void setSpawnQueue(HashMap<ZombieType, Integer> spawnQueue) {
		this.spawnQueue = spawnQueue;
	}

	public void update() {

		if (spawnQueue.isEmpty())
			return;

		timeElapsedSinceLastSpawn += System.currentTimeMillis();

		if (timeElapsedSinceLastSpawn >= SPAWN_DELAY) {
			spawn();
			timeElapsedSinceLastSpawn = 0;
		}

	}

	public void spawn() {

		ZombieType type = getRandomZombieType();
		switch (type) {
		case NORMAL:
			Zombie zombie = new Zombie(this, barrierTarget);
			InGame.getGame().getWaveController().addZombie(zombie);
			break;

		case INFECTED:
			break;
		}

	}

	private ZombieType getRandomZombieType() {

		// Get a random entry from the queue
		Random random = new Random();
		List<Entry<ZombieType, Integer>> entries = new ArrayList<Entry<ZombieType, Integer>>(spawnQueue.entrySet());
		Entry<ZombieType, Integer> entry = entries.get(random.nextInt(entries.size()));

		// Update bookkeeping
		int value = entry.getValue();
		if (value > 1) {
			value--;
			entry.setValue(value);
		} else {
			spawnQueue.remove(entry.getKey());
		}

		return entry.getKey();
	}

}
