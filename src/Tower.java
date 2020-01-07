/*
 * 
 * Tower.java
 * 
 * 
 */



import java.awt.Rectangle;
import java.util.ArrayList;

public class Tower {

	// variables
	private Rectangle border;
	private int x, y;
	double health = 500;
	private ArrayList<Troops> troops = new ArrayList<Troops>();
	private Gun gun;
	private int For;
	private int money = 100;
	private int bullets = 0;
	private int limit = 10;

	// constructor
	public Tower(int For) {
		gun = new Gun(For);
		this.x = 10;
		this.y = 150;
		if (For == 2) {
			this.x = 850;
		}

		this.For = For;
		border = new Rectangle(x, y, 150, 300);

	}

	// getters and setters
	public void addBullets(int x) {
		this.bullets += x;

	}

	public int getBullets() {
		return this.bullets;
	}

	public void addMoney(int x) {
		this.money += x;
	}

	public int getMoney() {
		return this.money;
	}

	public Rectangle getBorder() {
		return this.border;
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

	public ArrayList<Troops> getTroops() {
		return this.troops;

	}

	public Gun getGun() {
		return this.gun;
	}

	// method that moves troops
	public void moveTroops() {

		for (int i = 0; i < troops.size(); i++) {
			boolean cont = true;
			try {
				int temp = troops.get(i).getX();
			} catch (Exception e) {
				cont = false;
			}
			if (cont) {
				if (this.For == 1) {

					troops.get(i).setX(troops.get(i).getX() + 3);
					if (troops.get(i).getBorder()
							.intersects(Game.getPlayer2().getBorder())) {
						troops.get(i).setX(troops.get(i).getX() - 3);
					}

				} else if (For == 2) {
					troops.get(i).setX(troops.get(i).getX() - 3);

					if (troops.get(i).getBorder()
							.intersects(Game.getPlayer1().getBorder())) {
						troops.get(i).setX(troops.get(i).getX() + 3);
					}
				}

			}
		}

		// pushes troops backward when they hit each other
		for (int i = 0; i < Game.getPlayer1().getTroops().size(); i++) {
			for (int i2 = 0; i2 < Game.getPlayer2().getTroops().size(); i2++) {
				try {
					if (Game.getPlayer2()
							.getTroops()
							.get(i2)
							.getBorder()
							.intersects(
									Game.getPlayer1().getTroops().get(i)
											.getBorder())) {
						Game.getPlayer1()
								.getTroops()
								.get(i)
								.setX(Game.getPlayer1().getTroops().get(i)
										.getX()
										- Game.getPlayer2().getTroops().get(i2)
												.getPush());
						Game.getPlayer2()
								.getTroops()
								.get(i2)
								.setX(Game.getPlayer2().getTroops().get(i2)
										.getX()
										+ Game.getPlayer1().getTroops().get(i)
												.getPush());

					}

				} catch (Exception e) {
				}
			}
		}

	}

	// health getter
	public double getHealth() {
		return health;
	}

	public void updateHealth(double damage) {
		this.health -= damage;

	}

	// updates troop borders
	public void updateTroopBorders() {
		for (int i = 0; i < troops.size(); i++) {
			troops.get(i).updateBorder();
		}
	}

	// shoot method
	public void shoot() {
		if (bullets > 0) {
			gun.getShots().add(
					new Shots(For, gun.getXY1()[0], gun.getXY1()[1], gun
							.getXY2()[0], gun.getXY2()[1]));
			Sounds.shot();
			bullets -= 1;
		}
	}

	// adds troops method
	public void addTroop(int Type, int For) {
		int cost = 150;
		if (Type == 2) {
			cost = 300;
		}
		if (For == 1) {
			if (Game.getPlayer1().getMoney() >= cost
					&& Game.getPlayer1().getTroops().size() <= limit) {
				Game.getPlayer1().getTroops().add(new Troops(Type, For));
				Game.getPlayer1().addMoney(cost * -1);
				Sounds.train();
			}

		} else {
			if (Game.getPlayer2().getMoney() >= cost
					&& Game.getPlayer2().getTroops().size() <= limit) {
				Game.getPlayer2().getTroops().add(new Troops(Type, For));
				Game.getPlayer2().addMoney(cost * -1);
				Sounds.train();
			}

		}

	}

	// collusion method tht checks if any troops are hitting the tower
	public void collusion() {
		this.updateTroopBorders();

		for (int i = 0; i < troops.size(); i++) {
			if (For == 2) {
				if (troops.get(i).getBorder()
						.intersects(Game.getPlayer1().getBorder())) {
					Game.getPlayer1().updateHealth(2);
					Sounds.hit();
				}

			} else {
				if (troops.get(i).getBorder()
						.intersects(Game.getPlayer2().getBorder())) {
					Game.getPlayer2().updateHealth(2);
					Sounds.hit();
				}

			}

		}

		// detects of andy gun shots hit troops
		for (int i = 0; i < this.gun.getShots().size(); i++) {

			try {
				if (this.For == 1) {

					function(Game.getPlayer2(), new Rectangle(gun.getShots()
							.get(i).getXY2()[0],
							gun.getShots().get(i).getXY2()[1], 1, 1),
							gun.getShots(), i, Game.getPlayer1());

				} else {

					function(Game.getPlayer1(), new Rectangle(gun.getShots()
							.get(i).getXY2()[0],
							gun.getShots().get(i).getXY2()[1], 1, 1),
							gun.getShots(), i, Game.getPlayer2());

				}
			} catch (Exception e) {

			}

		}
		

	}

	public void function(Tower obj, Rectangle r, ArrayList<Shots> shot,
			int index, Tower obj2) {
		boolean loop = true;
		for (int i = 0; i < obj.getTroops().size() && loop; i++) {
			if (obj.getTroops().get(i).getBorder().intersects(r)) {
				obj.getTroops().remove(i);
				shot.remove(index);
				obj2.addMoney(150);
				loop = false;
				Sounds.explode();
			}

		}

	}

}
