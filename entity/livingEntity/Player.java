package entity.livingEntity;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import gameState.InGame;

public class Player extends LivingEntity {

	private InGame game;

	public Player(int x, int y, InGame game) {

		this.x = x;
		this.y = y;
		this.game = game;
		this.speed = 0.5;

	}

	@Override
	public void update(int delta) {

		// Check if the position must be updated
		if (motionX != 0 || motionY != 0) {

			// Calculate new coordinates
			double _x = x + motionX * delta;
			double _y = y + motionY * delta;
			
			// Check if new coordinates are valid to walk to
			if (!game.getMap().isSolid((int) _x, (int) _y)) {
				x = _x;
				y = _y;
			}

		}

	}

	public void draw(Graphics g, int windowWidth, int windowHeight) {
		g.setColor(Color.green);
		g.fillOval(windowWidth / 2 - 16, windowHeight / 2 - 16, 32, 32);
	}

}
