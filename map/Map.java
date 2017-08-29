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

public class Map {

	private final int TILE_DIMENSION = 32;

	private Image background;
	private Graphics g;

	private int width;
	private int height;

	public Map() throws SlickException {

		generateBackground();

	}

	public Image getBackground() {
		return background;
	}

	public void render(Graphics g, int windowWidth, int windowHeight, Player player) throws SlickException {

		// But why tho
		float x1 = (float) (player.getX() - windowWidth / 2);
		float y1 = (float) (player.getY() - windowHeight / 2);
		float x2 = x1 + windowWidth;
		float y2 = y1 + windowHeight;
		
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

	public void generateBackground() throws SlickException {

		Color[][] colorData = getColorData();
		background = new Image(width, height);
		g = background.getGraphics();

		for (int x = 0; x < colorData.length; x++) {
			for (int y = 0; y < colorData[x].length; y++) {

				Color grass = new Color(0, 127, 12);
				Color stone = new Color(128, 128, 128);
				Color planks = new Color(127, 82, 0);

				if (colorData[x][y].equals(grass)) {

					g.drawImage(new Image("res/sprites/grass.png"), x * 32, y * 32);

				} else if (colorData[x][y].equals(stone)) {

					g.drawImage(new Image("res/sprites/dirt.png"), x * 32, y * 32);

				} else if (colorData[x][y].equals(planks)) {

					g.drawImage(new Image("res/sprites/planks.png"), x * 32, y * 32);

				}

			}
		}

		g.flush();

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
