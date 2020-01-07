/*
 * 
 * This class is the main screen
 * 
 * it will show instructions and allow 
 * players to start the game
 * 
 */

import java.awt.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class MainScreen extends JPanel implements ActionListener {
	// variables
	private static JFrame f;
	private Image bg, manual;
	private int screen = 1;
	JButton next = new JButton("N e x t");

	// constructor
	public MainScreen() {
		Sounds.main();

		// variables
		next.setFont(new Font(null, Font.BOLD, 25));
		next.setBackground(Color.gray);
		next.setForeground(Color.orange);
		this.add(next);
		bg = Toolkit.getDefaultToolkit().createImage(new File("src/images/back.jpg").getAbsolutePath());
		manual = Toolkit.getDefaultToolkit().createImage(
				new File("src/images/instructions.jpg").getAbsolutePath());

		// adding action listener
		next.addActionListener(this);
	}

	// detects if buttns are pressed
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == next) {

			// changes the screen when button is clicked
			if (screen != 2 && screen == 1) {
				screen = 2;
				next.setText("Start");
				Sounds.reload();
			} else {
				// starts game if button is clicked for 3rd time
				Sounds.reload();
				f.setVisible(false);
				Game g = new Game();

			}

		}

	}

	// paint component method, displays graphics on screen
	public void paintComponent(Graphics g) {

		Dimension d = getSize();
       
		// adding images
		g.setColor(getBackground());
		// IMAGE
		g.drawImage(bg, 0, 0, 1200, 775, this);
		g.setColor(Color.black);
		next.setBounds(525, 550, 150, 50);
		if (screen == 2) {
			g.drawImage(manual, 300, 200, this);
		}

	}

	public static JFrame getFram() {
		return f;
	}

	// main method
	public static void main(String args[]) {
		f = new JFrame("Project");
		MainScreen dc = new MainScreen();
		f.getContentPane().add(dc);
		f.setSize(1200, 700);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.setVisible(true);
	}

}
