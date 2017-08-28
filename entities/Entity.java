package entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Entity {

	protected int ID;
	protected double x;
	protected double y;
	protected Image texture;

	public void draw(Graphics g) {
		g.drawImage(texture, (int) x, (int) y);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void update(int delta) {

	}

}
