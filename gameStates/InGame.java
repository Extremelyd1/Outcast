package gameStates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import entities.livingEntities.Player;
import map.Map;

public class InGame implements GameState {

	private Map map;
	private Player player;
	
	@Override
	public void init(GameContainer container) throws SlickException {
		
		map = new Map();
		player = new Player(100, 100);
		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		
		map.getBackground().draw();
		player.draw(g);
		
	}

}
