package map.tile;

import org.newdawn.slick.Image;

public class Tile {

	protected int x;
	protected int y;
	protected int z;
	
	protected Image texture;
	
	protected boolean solid;

	public Tile(int x, int y, int z, Image texture, boolean solid) {
		
		this.x = x;
		this.y = y;
		this.z = z;
		this.texture = texture;
		this.solid = solid;
		
	}
	
	public int getX() {
		
		return x;
		
	}
	
	public int getY() {
		
		return y;
		
	}
	
	public int getZ() {
		
		return z;
		
	}
	
}
