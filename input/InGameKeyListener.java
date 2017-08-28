package input;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

import entities.livingEntities.Player;
import gameStates.InGame;

public class InGameKeyListener implements KeyListener {

	public InGame gameState;

	public InGameKeyListener(InGame gameState) {

		this.gameState = gameState;

	}

	@Override
	public void inputEnded() {

	}

	@Override
	public void inputStarted() {

	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void setInput(Input input) {

	}

	@Override
	public void keyPressed(int key, char c) {

		Player player = gameState.getPlayer();

		switch (key) {

		case Input.KEY_W:
			player.setMotionY(-player.getSpeed());
			break;

		case Input.KEY_A:
			player.setMotionX(-player.getSpeed());
			break;

		case Input.KEY_S:
			player.setMotionY(player.getSpeed());
			break;

		case Input.KEY_D:
			player.setMotionX(player.getSpeed());
			break;

		}

	}

	@Override
	public void keyReleased(int key, char c) {

		Player player = gameState.getPlayer();

		switch (key) {

		case Input.KEY_W:
			player.setMotionY(0);
			break;

		case Input.KEY_A:
			player.setMotionX(0);
			break;

		case Input.KEY_S:
			player.setMotionY(0);
			break;

		case Input.KEY_D:
			player.setMotionX(0);
			break;

		}

	}

}
