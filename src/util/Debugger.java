package util;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import gameState.InGame;

public class Debugger {

	private final int INDENT_WIDTH = 40;
	private final int START_X = 10;
	private final int START_Y = 10;

	private boolean verbose;
	private InGame game;

	private int x;
	private int y;

	public Debugger(InGame game) {

		this.verbose = true;
		this.game = game;
		this.x = 100;
		this.y = 100;

	}

	public boolean getVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public void showDebug(Graphics g, GameContainer container) {

		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.HALF_UP);

		g.setColor(Color.white);
		g.drawString("Debug information", x, y);
		newLine(g);
		g.drawString("FPS   : " + container.getFPS(), x, y);
		newLine(g);
		g.drawString("Memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()), x, y);
		newLine(g);
		g.drawString("Player information", x, y);
		newLine(g);
		indent();
		g.drawString("x: " + df.format(game.getPlayer().getX()), x, y);
		newLine(g);
		g.drawString("y: " + df.format(game.getPlayer().getY()), x, y);
		newLine(g);
		g.drawString("health: " + game.getPlayer().getHealth(), x, y);
		newLine(g);
		g.drawString("Tile: "
				+ game.getMap().getTiles((int) game.getPlayer().getX(), (int) game.getPlayer().getY())[0].getType(), x,
				y);
		
		outdent();

		x = START_X;
		y = START_Y;
	}

	private void newLine(Graphics g) {
		y += g.getFont().getLineHeight();
	}

	private void indent() {
		x += INDENT_WIDTH;
	}

	private void outdent() {
		x -= INDENT_WIDTH;
	}

}
