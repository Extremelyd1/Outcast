package gameState;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import controller.WaveController;
import entity.livingEntity.Player;
import input.InGameKeyListener;
import map.Map;
import util.Debugger;

public class InGame implements GameState {

	private static InGame instance;

	private Map map;
	private Player player;

	private WaveController waveController;

	private Debugger debugger;

	public InGame() {

		instance = this;

		this.waveController = new WaveController();

	}

	public static InGame getGame() {
		return instance;
	}

	public Map getMap() {
		return map;
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	public void init(GameContainer container) throws SlickException {

		map = new Map();
		player = new Player(400, 400);
		
		this.debugger = new Debugger(this);

		container.getInput().addKeyListener(new InGameKeyListener(this));

		waveController.start();

	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {

		player.update(delta);

	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {

		map.render(g, container, player);
		player.draw(g, container.getWidth(), container.getHeight());
		
		if (debugger.getVerbose())
			debugger.showDebug(g, container);

	}

}
