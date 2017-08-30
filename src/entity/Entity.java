package entity;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Entity {

	protected int id;
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

	public int getId() {
		return id;
	}

	/**
	 * Update method which is called every update tick. Returns true if entity
	 * should be removed
	 * 
	 * @param delta
	 *            Time elapsed since last update in milliseconds
	 * @return True if the entity should be removed, false otherwise
	 */
	public boolean update(int delta) {
		return false;
	}

}
