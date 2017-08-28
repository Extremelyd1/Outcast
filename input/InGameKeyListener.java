package input;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

import gameStates.InGame;

public class InGameKeyListener implements KeyListener {

	public InGame inGameGameState;
	
	public InGameKeyListener(InGame inGameGameState) {
		
		this.inGameGameState = inGameGameState;
		
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
		
	}

	@Override
	public void keyReleased(int key, char c) {
		
	}
	
	

}
