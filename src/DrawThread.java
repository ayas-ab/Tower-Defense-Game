/*
 * DrawingThread.java
 * 
 * This class is for drawing on the panel.
 * 
 * 
 * 
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DrawThread implements Runnable {
	private static Font font = new Font(null, Font.BOLD, 30);
	private Thread GameThread;
	boolean running = true;
	private Tower Player1, Player2;
	private static BufferStrategy strategy;
	private static BufferedImage BG, Towera, Towerb;

	public DrawThread() {
		try {
			BG = ImageIO.read(new File("src/images/bg.jpg"));
			Towera = ImageIO.read(new File("src/images/towera.png"));

			Towerb = ImageIO.read(new File("src/images/towerb.png"));

		} catch (Exception e) {
			System.out.print(e);
		}
		
		strategy = Game.getStrategy();
		Player1 = Game.getPlayer1();
		Player2 = Game.getPlayer2();
		running = Game.getStat();
		
		//starting the thread
		GameThread = new Thread(this);
		GameThread.start();

	}

	@Override
	public void run() {
		while (running) {
			try {
				Player1 = Game.getPlayer1();
				Player2 = Game.getPlayer2();
				draw();
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void draw() {
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		if (Game.getStat() == true) {
			BufferedImage TempTank;
			g.setColor(Color.black);
			g.fillRect(0, 0, 1100, 600);
			g.drawImage(BG, 0, 0, null);
			g.setColor(Color.BLUE);


			//drawing towers
			g.drawImage(Towera, Player1.getX(), Player1.getY(), 150, 300, null);
			g.drawImage(Towerb, Player2.getX(), Player2.getY(), 150, 300, null);
			
			//drawing troops
			for (int i = 0; i < Player2.getTroops().size(); i++) {
				try {
					TempTank = ImageIO.read(new File("src/images/"
							+ Player2.getTroops().get(i).getImage() + "b.png"));

					boolean cont = true;
					try {
						int t = Player2.getTroops().get(i).getX();
					} catch (Exception e) {
						cont = false;
						System.out.print(cont);
					}
					if (cont) {
						g.drawImage(TempTank,
								Player2.getTroops().get(i).getX(), Player2
										.getTroops().get(i).getY(), 130, 100,
								null);
					}
				} catch (IOException e) {

				}

			}

			for (int i = 0; i < Player1.getTroops().size(); i++) {
				try {
					TempTank = ImageIO.read(new File("src/images/"
							+ Player1.getTroops().get(i).getImage() + "a.png"));
					boolean cont = true;
					try {
						int t = Player1.getTroops().get(i).getX();
					} catch (Exception e) {
						cont = false;
						System.out.print(cont);
					}
					if (cont) {
						g.drawImage(TempTank,
								Player1.getTroops().get(i).getX(), Player1
										.getTroops().get(i).getY(), 130, 100,
								null);
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			//drawing health, money, and bulltes
			g.setColor(Color.red);
			g.setFont(new Font(null, Font.BOLD, 50));
			double hp1 = 0, hp2 = 0;
			if (Player1.getHealth() > 0) {
				hp1 = Player1.getHealth();
			}
			if (Player2.getHealth() > 0) {
				hp2 = Player2.getHealth();
			}
			g.drawString("Health: " + hp1, 70, 50);
			g.drawString("Health: " + hp2, 650, 50);
			g.setFont(new Font(null, Font.BOLD, 30));
			g.setColor(Color.GREEN);
			g.drawString("$: " + Player1.getMoney(), 70, 100);
			g.drawString("$: " + Player2.getMoney(), 650, 100);

			g.setColor(Color.black);
			g.drawString("Bullets: " + Player1.getBullets(), 210, 100);
			g.drawString("Bullets: " + Player2.getBullets(), 770, 100);

			g.setStroke(new BasicStroke(7));
			

			//drawing guns
			g.setColor(Color.black);
			g.drawLine(Player2.getGun().getXY1()[0],
					Player2.getGun().getXY1()[1], Player2.getGun().getXY2()[0],
					Player2.getGun().getXY2()[1]);
			g.drawLine(Player1.getGun().getXY1()[0],
					Player1.getGun().getXY1()[1], Player1.getGun().getXY2()[0],
					Player1.getGun().getXY2()[1]);

			//

			// drawing shots
			g.setColor(Color.yellow);
			for (int i = 0; i < Player2.getGun().getShots().size(); i++) {
				g.drawLine(Player2.getGun().getShots().get(i).getXY1()[0],
						Player2.getGun().getShots().get(i).getXY1()[1], Player2
								.getGun().getShots().get(i).getXY2()[0],
						Player2.getGun().getShots().get(i).getXY2()[1]);
			}
			g.setColor(Color.red);
			for (int i = 0; i < Player1.getGun().getShots().size(); i++) {
				g.drawLine(Player1.getGun().getShots().get(i).getXY1()[0],
						Player1.getGun().getShots().get(i).getXY1()[1], Player1
								.getGun().getShots().get(i).getXY2()[0],
						Player1.getGun().getShots().get(i).getXY2()[1]);
			}
			//
			strategy.show();

			// for end game
		} else {
			// for end game
			g.setColor(Color.black);
			g.fillRect(0, 0, 1100, 600);
			g.drawImage(BG, 0, 0, null);
			String s = "Player1 Won!";
			if (Player1.getHealth() < Player2.getHealth()) {
				s = "Player2 Won!";
			}

			g.setFont(font);
			g.setColor(Color.red);
			g.drawString(s, 430, 300);

			strategy.show();
         Sounds.stopMain();
			running = false;
			GameThread.stop();
			
		}
	}

	public Thread getThread() {
		return this.GameThread;
	}
}
