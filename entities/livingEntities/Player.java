package entities.livingEntities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Player extends LivingEntity {

	public Player(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.fillOval(x, y, 32, 32);
	}
	
}
