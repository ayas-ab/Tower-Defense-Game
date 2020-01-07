import java.awt.Rectangle;

public class Troops {
	// variables
	private int x, y, health, For, push = 3;
	private Rectangle border;
	private String image = "tank1";

	//constructor
	public Troops(int Type, int For) {
		this.x = 90;
		this.y = 350;
		this.health = 200;
		if (For == 2) {
			x = 800;
			;
		}
		if (Type == 2) {
			image = "tank2";
			push = 10;
		}
		border = new Rectangle(x, y, 130, 100);
	}

	//getters and setters
	public String getImage() {
		return image;
	}

	public int getPush() {
		return this.push;
	}

	public Rectangle getBorder() {
		return border;
	}

	public void updateBorder() {
		border.x = this.x;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void reduceHealth(int n) {
		this.health -= n;
	}

	public double getHealth() {
		return health;
	}

}
