import java.util.ArrayList;

public class Shots {

	// variables
	private int For, x1, y1, x2, y2;
	public static short num = 0;

	// constructor
	public Shots(int For, int x1, int y1, int x2, int y2) {
		this.For = For;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		num += 1;

	}

	// getters and setters
	public int getFor() {
		return For;
	}

	public int[] getXY1() {
		int[] n = { x1, y1 };
		return n;
	}

	public int[] getXY2() {
		int[] n = { x2, y2 };
		return n;
	}

	// slopes
	public double slope() {

		return ((y1 + 0.0) - y2) / ((x1 + 0.0) - x2);

	}

	public int[] slope2() {
		int[] a = { y2 - y1, x2 - x1 };
		return a;
	}

	public int[] slope3() {
		int[] a = { y1 - y2, x1 - x2 };
		return a;
	}

	// move method

	public void move() {
		if (For == 2) {
			int[] Slope = slope2();
			if (slope() == 0.0) {
				x1 -= 50;
				x2 -= 50;
			} else if (slope() < 0) {

				y1 += Slope[0];
				x1 += Slope[1];

				y2 += Slope[0];
				x2 += Slope[1];
			}

			// y2 -= Slope[0];
			// x2 -= Slope[1];

		} else {
			int[] Slope = slope3();
			if (slope() == 0.0) {
				x1 += 50;
				x2 += 50;
			} else {
				y1 -= Slope[0];
				x1 -= Slope[1];
				y2 -= Slope[0];
				x2 -= Slope[1];
			}

		}

	}

}
