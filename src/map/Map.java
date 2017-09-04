package map;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

import entity.livingEntity.LivingEntity;
import entity.livingEntity.Player;
import map.tile.Tile;
import map.tile.TileType;

public class Map {

	/** The width and height of a tile in pixels */
	private final int TILE_DIMENSION = 32;
	/** The maximum layer depth of the map */
	private final int MAX_DEPTH = 5;
	/** The number of tiles that should be drawn in the height */
	private final int TILES_IN_HEIGHT = 10;

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

	/**
	 * Renders the background to the screen, centered at the player
	 * 
	 * @param g
	 *            Graphics context
	 * @param container
	 *            Game container
	 * @param player
	 *            The player
	 * @throws SlickException
	 */
	public void render(Graphics g, GameContainer container, Player player) throws SlickException {
		// Calculate what to draw from the background image
		int pixelsPerTile = container.getHeight() / TILES_IN_HEIGHT;
		double tilesInWidth = container.getWidth() / pixelsPerTile;
		int widthToDraw = (int) (tilesInWidth * TILE_DIMENSION);
		int heightToDraw = TILES_IN_HEIGHT * TILE_DIMENSION;

		// Calculate bounds of the rectangle to draw
		int x1 = (int) player.getX() - widthToDraw / 2;
		int y1 = (int) player.getY() - heightToDraw / 2;
		int x2 = (int) player.getX() + widthToDraw / 2;
		int y2 = (int) player.getY() + heightToDraw / 2;

		g.drawImage(background, 0, 0, container.getWidth(), container.getHeight(), x1, y1, x2, y2);
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
			Log.error("The passed z value is not between 0 and MAX_DEPTH", new Exception("Depth value out of bounds"));
			return null;
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
	 * Returns a list with all tiles that fall in the bounding box
	 * 
	 * @param x1
	 *            X coordinate of the upper left corner
	 * @param y1
	 *            Y coordinate of the upper left corner
	 * @param x2
	 *            X coordinate of the lower right corner
	 * @param y2
	 *            Y coordinate of the lower right corner
	 * @return All tiles that fall in this bounding box
	 */
	public Tile[][] getOverlappingTiles(int x1, int y1, int x2, int y2) {
		int _x1 = x1 / TILE_DIMENSION;
		int _y1 = y1 / TILE_DIMENSION;
		int _x2 = x2 / TILE_DIMENSION;
		int _y2 = y2 / TILE_DIMENSION;
		ArrayList<Tile[]> tiles = new ArrayList<Tile[]>();

		for (int i = _x1; i <= _x2; i++) {
			for (int j = _y1; j <= _y2; j++) {
				tiles.add(tileData[i][j]);
			}
		}

		Tile[][] tileArray = new Tile[tiles.size()][MAX_DEPTH];
		return tiles.toArray(tileArray);
	}

	/**
	 * Returns all tiles that overlap with the entity
	 * 
	 * @param entity
	 *            Entity in question
	 * @return All tiles that overlap with this entity
	 */
	public Tile[][] getOverlappingTiles(LivingEntity entity) {
		double x1 = entity.getX() - entity.getRadius();
		double y1 = entity.getY() - entity.getRadius();
		double x2 = entity.getX() + entity.getRadius();
		double y2 = entity.getY() + entity.getRadius();

		return getOverlappingTiles((int) x1, (int) y1, (int) x2, (int) y2);
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

	/**
	 * Returns whether a tile in the array of tiles is solid.
	 * 
	 * @param tiles
	 *            The array of tiles
	 * @return True if one tile in the array is solid, false otherwise
	 */
	public boolean isSolid(Tile[][] tiles) {

		for (Tile[] stack : tiles) {
			for (Tile tile : stack) {
				if (tile == null)
					continue;

				if (tile.isSolid())
					return true;
			}
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
