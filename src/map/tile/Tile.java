package map.tile;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tile {

	/** Coordinates of the tile */
	protected int x;
	protected int y;
	protected int z;

	/** Texture of the tile */
	protected Image texture;

	/** Direction of the tile */
	protected TileDirection direction;

	/** Specifies whether the tile is solid (walkable) or not */
	protected boolean solid;

	protected TileType type;

	public Tile(int x, int y, int z, String texturePath, boolean solid, TileType type) {

		this.x = x;
		this.y = y;
		this.z = z;
		this.solid = solid;
		this.type = type;

		loadTexture(texturePath);

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

	public Image getTexture() {
		return texture;
	}

	public boolean isSolid() {
		return solid;
	}

	public TileType getType() {
		return type;
	}

	/**
	 * Loads the texture from the texture path
	 * 
	 * @param texturePath
	 *            Relative path to the texture
	 */
	public void loadTexture(String texturePath) {
		if (texturePath == null)
			return;

		try {
			texture = new Image(texturePath);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Direction of the tile
	 */
	public enum TileDirection {
		// 0 degrees
		NORTH,
		// 90 degrees rotated clockwise
		EAST,
		// 180 degrees (flipped in the y direction)
		SOUTH,
		// 270 degrees rotated clockwise
		WEST
	}

}
