package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Outcast {

	public static void main(String[] args) {

		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new GameController("Outcast"));
			appgc.setDisplayMode(1280, 720, false);
			appgc.setVerbose(false);
			appgc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

}
