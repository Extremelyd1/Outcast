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
		this.radius = 16;

	}

	@Override
	public void update(int delta) {

		// Check if the position must be updated
		if (motionX != 0 || motionY != 0) {

			// Save old coordinates
			double _x = x;
			double _y = y;
			
			// Apply motion
			x += motionX * delta;
			y += motionY * delta;
			
			// Check if move is valid, if not, revert motion
			if (game.getMap().isSolid(game.getMap().getOverlappingTiles(this))) {
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
