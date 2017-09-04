package entity.livingEntity;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import gameState.InGame;
import map.Map;

public class Player extends LivingEntity {

	public Player(int x, int y) {

		this.x = x;
		this.y = y;
		this.speed = 0.2;
		this.radius = 16;

	}

	@Override
	public boolean update(int delta) {

		// Check if the position must be updated
		if (motionX != 0 || motionY != 0) {
			applyMotion(delta);
		}

		return false;
	}

	private void applyMotion(int delta) {
		// Save current coordinates
		double _x = x;
		double _y = y;

		x += motionX * delta;

		Map map = InGame.getGame().getMap();

		if (map.isSolid(map.getOverlappingTiles(this))) {
			x = _x;
		}

		y += motionY * delta;
		if (map.isSolid(map.getOverlappingTiles(this))) {
			y = _y;
		}
	}

	public void draw(Graphics g, int windowWidth, int windowHeight) {
		g.setColor(Color.green);
		g.fillOval(windowWidth / 2 - 16, windowHeight / 2 - 16, 32, 32);
	}

}
