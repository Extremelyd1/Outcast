package entities.livingEntities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Player extends LivingEntity {

	public Player(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	public void draw(Graphics g, int windowWidth, int windowHeight) {
		g.setColor(Color.green);
		g.fillOval(windowWidth / 2 - 16, windowHeight / 2 - 16, 32, 32);
	}
	
}
