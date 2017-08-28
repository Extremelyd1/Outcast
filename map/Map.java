package map;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Map {
	
	private final int TILE_DIMENSION = 32;

	private Image background;
	
	private int width;
	private int height;

	public Map() throws SlickException {

		generateBackground();
		
	}
	
	public Image getBackground() {
		return background;
	}
	
	public void generateBackground() throws SlickException {
		
		Color[][] colorData = getColorData();
		background = new Image(width, height);
		Graphics g = background.getGraphics();
		
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

			BufferedImage sourceImage = ImageIO.read(new File("res/map/map1.png"));
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
