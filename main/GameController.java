package main;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import gameStates.GameState;
import gameStates.InGame;

public class GameController extends BasicGame {
	
	private GameState gameState;
	
	public GameController(String title) {
		super(title);
		
		gameState = new InGame();
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		
		gameState.init(container);
		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		gameState.update(container, delta);
		
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		
		gameState.render(container, g);
		
	}

}
