package entities.livingEntities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Player extends LivingEntity {

	public Player(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	@Override
	public void update(int delta) {
		
		x += motionX * delta;
		y += motionY * delta;
		
	}
	
	public void draw(Graphics g, int windowWidth, int windowHeight) {
		g.setColor(Color.green);
		g.fillOval(windowWidth / 2 - 16, windowHeight / 2 - 16, 32, 32);
	}
	
}
