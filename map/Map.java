package map;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import entity.livingEntity.Player;
import map.tile.Tile;
import map.tile.Tile.TileType;

public class Map {

	/** The width and height of a tile in pixels */
	private final int TILE_DIMENSION = 32;
	/** The maximum layer depth of the map */
	private final int MAX_DEPTH = 5;

	/** Buffered background image */
	private Image background;
	/** graphics context for the background image */
	private Graphics gBackground;

	/** Width of the map in pixels */
	private int width;
	/** Height of the map in pixels */
	private int height;

	/** 3D array containing all tile data */
	private Tile[][][] tileData;

	public Map() throws SlickException {

		generateBackground();

	}

	public Image getBackground() {
		return background;
	}

	public void render(Graphics g, int windowWidth, int windowHeight, Player player) throws SlickException {

		int x1 = (int) (player.getX() - windowWidth / 2);
		int y1 = (int) (player.getY() - windowHeight / 2);
		int x2 = x1 + windowWidth;
		int y2 = y1 + windowHeight;

		if (x1 <= 0) {
			x1 = 0;
			x2 = windowWidth;
		}

		if (y1 <= 0) {
			y1 = 0;
			y2 = windowHeight;
		}

		if (x2 >= width) {
			x2 = width;
			x1 = width - windowWidth;
		}

		if (y2 >= height) {
			y2 = height;
			y1 = height - windowHeight;
		}

		g.drawImage(background, 0, 0, windowWidth, windowHeight, x1, y1, x2, y2);

	}

	/**
	 * Returns the tile at the specified location and layer
	 * 
	 * @param x
	 *            The x coordinate
	 * @param y
	 *            The y coordinate
	 * @param z
	 *            The z coordinate (layer)
	 * @return A single tile at the location given
	 */
	public Tile getTile(int x, int y, int z) {

		if (z < 0 || z >= MAX_DEPTH) {
			// TODO return error
		}

		int _x = x / TILE_DIMENSION;
		int _y = y / TILE_DIMENSION;
		return tileData[_x][_y][z];

	}

	/**
	 * Returns a stack (all layers) of all tiles at the specified location
	 * 
	 * @param x
	 *            The x coordinate
	 * @param y
	 *            The y coordinate
	 * @return Stack of all tiles at that position
	 */
	public Tile[] getTiles(int x, int y) {

		int _x = x / TILE_DIMENSION;
		int _y = y / TILE_DIMENSION;
		return tileData[_x][_y];

	}

	/**
	 * Returns whether the specified tile is solid or not. It checks for each
	 * tile in the stack at that x, y position if it is solid or not. This
	 * method only returns true when no tile in the stack is solid.
	 * 
	 * @param x
	 *            The x position of the tile
	 * @param y
	 *            The y position of the tile
	 * @return True if no tile in the stack is solid, false otherwise
	 */
	public boolean isSolid(int x, int y) {

		Tile[] tiles = getTiles(x, y);

		for (Tile tile : tiles) {
			if (tile == null)
				continue;

			if (tile.isSolid())
				return true;
		}
		return false;

	}

	public void generateBackground() throws SlickException {

		// Get the color data from the source image
		Color[][] colorData = getColorData();

		// Create background image and get graphics context
		background = new Image(width, height);
		gBackground = background.getGraphics();

		// Initialise tile data array
		tileData = new Tile[width][height][MAX_DEPTH];

		// Loop through all color data
		for (int x = 0; x < colorData.length; x++) {
			for (int y = 0; y < colorData[x].length; y++) {

				// Define which colour means what type
				// TODO This is ugly, should be done better
				Color grass = new Color(0, 127, 12);
				Color stone = new Color(128, 128, 128);
				Color planks = new Color(127, 82, 0);

				// Calculate actual tile coordinates on the map
				int _x = x * TILE_DIMENSION;
				int _y = y * TILE_DIMENSION;

				Tile tile = null;

				// Check which color data matches and create tile
				if (colorData[x][y].equals(grass)) {
					tile = new Tile(_x, _y, 0, "res/sprites/grass2.png", false, TileType.GRASS);
				} else if (colorData[x][y].equals(stone)) {
					tile = new Tile(_x, _y, 0, "res/sprites/dirt.png", true, TileType.DIRT);
				} else if (colorData[x][y].equals(planks)) {
					tile = new Tile(_x, _y, 0, "res/sprites/planks.png", false, TileType.PLANKS);
				}

				// Save data and draw the tile on the background
				tileData[x][y][0] = tile;
				gBackground.drawImage(tile.getTexture(), tile.getX(), tile.getY());

			}
		}

		// Important! Flush the graphics
		gBackground.flush();

	}

	public Color[][] getColorData() {

		Color[][] colorData = null;

		try {

			BufferedImage sourceImage = ImageIO.read(new File("res/map/map.png"));
			colorData = new Color[sourceImage.getWidth()][sourceImage.getHeight()];
			width = sourceImage.getWidth() * TILE_DIMENSION;
			height = sourceImage.getHeight() * TILE_DIMENSION;

			for (int x = 0; x < sourceImage.getWidth(); x++) {
				for (int y = 0; y < sourceImage.getHeight(); y++) {
					colorData[x][y] = new Color(sourceImage.getRGB(x, y));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return colorData;
	}

}
