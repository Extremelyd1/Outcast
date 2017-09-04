package map.tile;

import org.newdawn.slick.Image;

/**
 *
 * Represents a barrier tile that can be broken by zombies
 * and (not yet) be repaired by players.
 *
 */
public class BarrierTile extends Tile {

	private int health;

	private Image brokenTexture;

	private boolean broken;

	public BarrierTile(int x, int y, int z, String texturePath, TileType type) {

		super(x, y, z, texturePath, true, TileType.BARRIER);

	}

	/** Apply damage to the barrier */
	public void damage(int amount) {

		health = Math.max(health - amount, 0);
		
		if (health == 0) {
			
			// ded
			
		}

	}

	public int getHealth() {
		return health;
	}

	public Image getBrokenTexture() {
		return brokenTexture;
	}

	public boolean isBroken() {
		return broken;
	}

}
