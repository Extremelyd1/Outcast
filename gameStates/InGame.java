package gameStates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import entities.livingEntities.Player;
import input.InGameKeyListener;
import map.Map;

public class InGame implements GameState {

	private Map map;
	private Player player;
	
	public Player getPlayer() {
		return player;
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		
		map = new Map();
		player = new Player(400, 400);
		
		container.getInput().addKeyListener(new InGameKeyListener(this));
		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		player.update(delta);
		
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		
		map.render(g, container.getWidth(), container.getHeight(), player);
		player.draw(g, container.getWidth(), container.getHeight());
		
	}

}
