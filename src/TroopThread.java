
/*
 * TroopsThread.java
 * 
 * This class is used for making a thread that will be updating the 
 * troop's movement
 * 
 */

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class TroopThread implements Runnable {
	// variables
	private Thread GameThread;
	boolean running;
	private Tower Player1, Player2;

	public TroopThread() {
		// variables
		Player1 = Game.getPlayer1();
		Player2 = Game.getPlayer2();
		running = Game.getStat();
		GameThread = new Thread(this);
		GameThread.start();
	}

	@Override
	public void run() {

		while (Game.getStat()) {

			try {
				// moving troops
				moveTroops();
				Thread.sleep(50);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// stops the thread when game neds
		if (!Game.getStat()) {
			GameThread.stop();
		}

	}

	// thread getter
	public Thread getThread() {
		return this.GameThread;
	}

	// move troops method
	public void moveTroops() {
		Player1.moveTroops();
		Player2.moveTroops();

	}

}
