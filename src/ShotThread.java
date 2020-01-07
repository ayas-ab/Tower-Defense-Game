/*
 * ShotsThreat.java
 * 
 * This class is for updating shot locations, and removing shots/troops 
 * when a shot hits a troop.
 * 
 * 
 * 
 */
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class ShotThread implements Runnable {
	private Thread GameThread;
	boolean running;
	private Tower Player1, Player2;

	public ShotThread() {
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

				updateShots();
				
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!Game.getStat()) {
			GameThread.stop();
		}

	}

	public Thread getThread() {
		return this.GameThread;
	}

	// updates shot coordinates
	public void updateShots() {
		
		for (int i = 0; i < Player2.getGun().getShots().size(); i++) {

				try {
				if (Player2.getGun().getShots().get(i).getXY2()[1] >= 600
						|| Player2.getGun().getShots().get(i).getXY2()[0] < 0) {
					Player2.getGun().getShots().remove(i);
					i--;
				} else {
					Player2.getGun().getShots().get(i).move();
				}
            
           } catch(Exception e){
            
            }
			
		}

		for (int i = 0; i < Player1.getGun().getShots().size(); i++) {
			try {
				if (Player1.getGun().getShots().get(i).getXY2()[1] >= 600
						|| Player1.getGun().getShots().get(i).getXY2()[0] >= 1000) {
					Player1.getGun().getShots().remove(i);
					i--;
				} else {
					Player1.getGun().getShots().get(i).move();
				}
			} catch (Exception e) {

			}

		}
	}
}
