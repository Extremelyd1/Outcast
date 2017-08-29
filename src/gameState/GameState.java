package gameState;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface GameState {

	public void init(GameContainer container) throws SlickException;
	
	public void update(GameContainer container, int delta) throws SlickException;
	
	public void render(GameContainer container, Graphics g) throws SlickException;
	
}
