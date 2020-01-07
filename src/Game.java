/*
 * Game.java
 * 
 * This is the programs main class
 * 
 */

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class Game extends Canvas implements Runnable, KeyListener {

	// variables
	private static BufferStrategy strategy;
	private Thread GameThread;
	private TroopThread TroopsThread;
	private DrawThread DrawingsThread;
	private ShotThread ShotsThread;
	private static boolean running = true;
	private static Tower Player1, Player2;
	private int spawn = 0;
	private long gStart = System.currentTimeMillis();
	private Random rand = new Random();
	private static boolean endGame = false;
	private JFrame container = new JFrame("Tower Defence");

	public Game() {
		// create a frame to contain our game
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(1000, 520));
		panel.setLayout(null);
		container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// setup our canvas size and put it into the content of the frame
		setBounds(0, 0, 1200, 600);
		// adding canvas
		panel.add(this);
		setIgnoreRepaint(true);
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		createBufferStrategy(2);
		this.requestFocus();
		this.addKeyListener(this);
		this.setFocusable(true);
		strategy = getBufferStrategy();
		Player1 = new Tower(1);
		Player2 = new Tower(2);
		GameThread = new Thread(this);
		GameThread.start();

		// These 3 classes will run different threads
		// to make the program less lagy
		TroopsThread = new TroopThread();
		ShotsThread = new ShotThread();
		DrawingsThread = new DrawThread();

	}

	@Override
	public void run() {
		// while game is active

		while (running) {

			// checks if any plyer has health below 0
			if (Player1.getHealth() < 0 || Player2.getHealth() < 0) {
				running = false;
			}

			try {
				// checkes elapsed time, then adds bullets
				long gEnd = System.currentTimeMillis();
				long gDelta = gEnd - gStart;
				double elapsedSeconds = gDelta / 1000.0;
				if (elapsedSeconds >= 3) {
					Player1.addBullets(this.rand.nextInt(2) + 1);
					Player2.addBullets(this.rand.nextInt(2) + 1);
					gStart = System.currentTimeMillis();
					Sounds.reload();
				}
				// checks for collusion
				this.collusion();
				// adds money
				Player1.addMoney(5);
				Player2.addMoney(5);
				
				// makes the thread stop for 110 milli seconds
				Thread.sleep(110);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// some getters
	public static boolean getStat() {
		return running;
	}

	public static BufferStrategy getStrategy() {
		return strategy;
	}

	// collusion method
	public void collusion() {
		Player2.collusion();
		Player1.collusion();
	}

	// some getters
	public static Tower getPlayer1() {
		return Player1;

	}

	public static Tower getPlayer2() {
		return Player2;
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	// method that detects released keys
	@Override
	public void keyReleased(KeyEvent e) {
		// gun controls and troops training
		switch (e.getKeyCode()) {

		case KeyEvent.VK_I:
			Player2.getGun().up();

			break;

		case KeyEvent.VK_P:
			Player2.getGun().down();

			break;

		case KeyEvent.VK_Q:
			Player1.getGun().up();

			break;

		case KeyEvent.VK_E:
			Player1.getGun().down();

			break;

		case KeyEvent.VK_W:
			Player1.shoot();

			break;

		case KeyEvent.VK_O:
			Player2.shoot();
			break;

		case KeyEvent.VK_1:
			Player1.addTroop(1, 1);
			break;

		case KeyEvent.VK_2:
			Player1.addTroop(2, 1);
			break;

		case KeyEvent.VK_MINUS:
			Player2.addTroop(1, 2);
			break;

		case KeyEvent.VK_0:
			Player2.addTroop(2, 2);
			break;

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
