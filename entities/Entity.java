package entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Entity {

	protected int ID;
	protected int x;
	protected int y;
	protected Image texture;

	public void draw(Graphics g) {
		g.drawImage(texture, x, y);
	}

}
