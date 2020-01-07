import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {
	private static Clip main;

	// playing a sound
	public static void reload() {

		playSound(new File("src/sounds/reload.wav").getAbsolutePath());
	}

	public static void playSound(String type) {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File(type));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	public static void shot() {

		playSound(new File("src/sounds/shot.wav").getAbsolutePath());
	}

	public static void explode() {
		playSound(new File("src/sounds/explode.wav").getAbsolutePath());
	}

	public static void train() {
		playSound(new File("src/sounds/train.wav").getAbsolutePath());
	}

	public static void hit() {
		playSound(new File("src/sounds/tankHit.wav").getAbsolutePath());
	}

	public static void main() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("src/sounds/main.wav").getAbsoluteFile());
			main = AudioSystem.getClip();
			main.open(audioInputStream);
			main.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	public static void stopMain() {
		try {
			main.stop();
			main.close();
			main.flush();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
		playSound("sounds\\victory.wav");

	}

}
