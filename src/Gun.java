import java.util.ArrayList;

public class Gun {
	// variables
	private int x1, y1, x2, y2, For;
	private ArrayList<Shots> shots = new ArrayList<Shots>();

	public Gun(int For) {
		this.For = For;
		if (For == 2) {
			x1 = 870;
			y1 = 240;
			x2 = 800;
			y2 = 240;
		} else {
			x1 = 140;
			y1 = 240;
			x2 = 200;
			y2 = 240;
		}

	}

	// getters and setters
	public int[] getXY1() {
		int[] n = { x1, y1 };
		return n;
	}

	public int[] getXY2() {
		int[] n = { x2, y2 };
		return n;
	}

	public void up() {
		y2 -= 5;
		if (slope() > 0.0 && For == 2) {
			y2 += 5;
		}

	}

	public void down() {

		y2 += 5;
		if (slope() < -1.0833333333333333 && For == 2) {
			y2 -= 5;
		}

	}

	public double slope() {

		return ((y1 + 0.0) - y2) / ((x1 + 0.0) - x2);

	}

	public void setXY1(int x, int y) {
		this.x1 = x;
		this.y1 = y;
	}

	public void setXY2(int x, int y) {
		this.x2 = x;
		this.y2 = y;
	}

	public ArrayList<Shots> getShots() {
		return this.shots;
	}

}
