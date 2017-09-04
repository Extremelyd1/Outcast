package entity;

import item.Item;

/**
 * Represents an pickup enity that is scattered around the map.
 * Can be picked up by the player.
 */
public class Pickup extends Entity {

	/** The range in which the entity will be triggered */
	protected final static int PICKUP_RANGE = 8;
	
	/** The item this pickup represents */
	private Item item;
	
	public Pickup(int x, int y, Item item) {
		
		this.x = x;
		this.y = y;
		
		this.item = item;
		
	}
	
	public Item getItem() {
		return item;
	}
	
}
