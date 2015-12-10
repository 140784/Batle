package actionFields;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import battleFields.BattleField;
import battleFields.Brick;
import battleFields.Eagle;
import battleFields.Rock;
import battleFields.Water;
import tanks.AbstractTank;
import tanks.T34;
import tanks.Tiger;
import actionFields.Direction;

public class ActionField extends JPanel {
	private boolean COLORDED_MODE = false;
	private Bullet bullet;
	private BattleField battleField;
	private AbstractTank defender;
	private Tiger agresor;

	public void runTheGame() throws Exception {
		
//		Thread.sleep(3000);

		agresor.turn(Direction.RIGHT);
		agresor.fire();
		agresor.fire();
		defender.turn(Direction.LEFT);
		defender.move();
		defender.move();
		defender.turn(Direction.UP);
		defender.fire();
		defender.moveRandom();
	}

	public void processTurn(AbstractTank tank) throws Exception {
		repaint();
	}

	public void processMove(AbstractTank tank) throws Exception {
		int step = 1;
		int left = 0;

		int quadrantY = Integer.parseInt((getQuadrant(tank.getX(), tank.getY()).substring(0, 1)));
		int quadrantX = Integer.parseInt((getQuadrant(tank.getX(), tank.getY()).substring(2)));

		tank.turn(tank.getDirection());

		if ((tank.getDirection() == Direction.UP && quadrantY >= 1
				&& battleField.scanQuadrant(quadrantY - 1, quadrantX) != " ")
				|| (tank.getDirection() == Direction.DOWN && quadrantY < 8
						&& battleField.scanQuadrant(quadrantY + 1, quadrantX) != " ")
				|| (tank.getDirection() == Direction.LEFT && quadrantX >= 1
						&& battleField.scanQuadrant(quadrantY, quadrantX - 1) != " ")
				|| (tank.getDirection() == Direction.RIGHT && quadrantX < 8
						&& battleField.scanQuadrant(quadrantY, quadrantX + 1) != " ")) {

			tank.wallOnKurs = true;

		} else {

			while (left < 64) {
				if (tank.getDirection() == Direction.UP && quadrantY >= 1) {
					tank.updateY(-step);
				} else if (tank.getDirection() == Direction.DOWN && quadrantY < 8) {
					tank.updateY(step);
				} else if (tank.getDirection() == Direction.LEFT && quadrantX >= 1) {
					tank.updateX(-step);
				} else if (tank.getDirection() == Direction.RIGHT && quadrantX < 8) {
					tank.updateX(step);
				}
				repaint();
				Thread.sleep(tank.getSpeed());
				left += step;
			}
			tank.wallOnKurs = false;
		}

	}

	public void processFire(Bullet bullet, AbstractTank tank) throws Exception {
		int step = 1;
		this.bullet = bullet;
		while (bullet.getX() > -25 && bullet.getX() < 590 && bullet.getY() > -25 && bullet.getY() < 590) {
			if (tank.getDirection() == Direction.UP) {
				bullet.updateY(-step);
				if (processInterception(tank) == true) {
					bullet.destroy();
				}
			} else if (tank.getDirection() == Direction.DOWN) {
				bullet.updateY(step);
				if (processInterception(tank) == true) {
					bullet.destroy();
				}
			} else if (tank.getDirection() == Direction.LEFT) {
				bullet.updateX(-step);
				if (processInterception(tank) == true) {
					bullet.destroy();
				}
			} else {
				bullet.updateX(step);
				if (processInterception(tank) == true) {
					bullet.destroy();
				}
			}
			repaint();
			Thread.sleep(bullet.getSpeed());
		}
	}

	public String findtank(AbstractTank tank) {
		return getQuadrant(tank.getX(), tank.getY());
	}

	public String findBullet(Bullet bullet) {
		return getQuadrant(bullet.getX(), bullet.getY());
	}

	public boolean processInterception(AbstractTank tank) throws Exception {
		int quadrantY = Integer.parseInt((getQuadrant(bullet.getX(), bullet.getY()).substring(0, 1)));
		int quadrantX = Integer.parseInt((getQuadrant(bullet.getX(), bullet.getY()).substring(2)));
		if (quadrantX >= 0 && quadrantX < 9 && quadrantY >= 0 && quadrantY < 9) {
			if (battleField.scanQuadrant(quadrantY, quadrantX) == "B") {
				Brick brick = new Brick(battleField, quadrantX, quadrantY);
				brick.destroy();
				return true;
			}
			if (battleField.scanQuadrant(quadrantY, quadrantX) == "E") {
				Eagle eagle = new Eagle(battleField, quadrantX, quadrantY);
				eagle.destroy();
				defender.destroy();
				agresor.destroy();
				agresor.destroy();
				return true;
			}
			if (battleField.scanQuadrant(quadrantY, quadrantX) == "R") {
				Rock rock = new Rock(battleField, quadrantX, quadrantY);
				if (tank instanceof Tiger) {
					rock.destroy();
				}
				return true;
			}

		} else if (findtank(tank).equals(findBullet(bullet))) {
			tank.destroy();
			return true;
		}

		// if (finddefender(defender).equals(findBullet(bullet))) {
		// defender.destroy();
		// return true;
		// }

		return false;
	}

	public String getQuadrant(int x, int y) {
		int numberX = x / 64;
		int numberY = y / 64;
		return (numberY + "_" + numberX);
	}

	public String getQuadrantXY(int v, int h) {
		return (v - 1) * 64 + "_" + (h - 1) * 64;
	}

	public void processMoveToQuadrant(int v, int h, AbstractTank tank) throws Exception {
		int coorX = Integer.parseInt(getQuadrantXY(v, h).substring(0, getQuadrantXY(v, h).indexOf("_")));
		int coorY = Integer.parseInt(getQuadrantXY(v, h).substring(getQuadrantXY(v, h).indexOf("_") + 1));
		if (tank.getX() < coorX) {
			while (tank.getX() < coorX) {
				tank.turn(Direction.RIGHT);
				if (tank.wallOnKurs == true) {
					tank.fire();
					tank.turn(Direction.DOWN);
				}
				tank.move();
			}
		} else {
			while (tank.getX() > coorX) {
				tank.turn(Direction.LEFT);
				if (tank.wallOnKurs == true) {
					tank.fire();
					tank.turn(Direction.RIGHT);
				}
				tank.move();
			}
		}
		if (tank.getY() < coorY) {
			while (tank.getY() < coorY) {
				tank.turn(Direction.DOWN);
				if (tank.wallOnKurs == true) {
					tank.fire();
					tank.turn(Direction.UP);
				}
				tank.move();
			}
		} else {
			while (tank.getY() > coorY) {
				tank.turn(Direction.UP);
				if (tank.wallOnKurs == true) {
					tank.fire();
					tank.turn(Direction.DOWN);
				}
				tank.move();
			}
		}
//		tank.wallOnKurs=false;
	}

	public ActionField() throws Exception {
		battleField = new BattleField();
		defender = new T34(this, battleField);
		bullet = new Bullet(-100, -100, Direction.UP);
		agresor = new Tiger(Integer.parseInt(battleField.getAgresorLocation().split("_")[0]),
				Integer.parseInt(battleField.getAgresorLocation().split("_")[1]), Direction.DOWN, this, battleField);

		JFrame frame = new JFrame("BATTLE FIELD, DAY 2");
		frame.setLocation(550, 50);
		frame.setMinimumSize(new Dimension(battleField.getBF_WIDTH() + 14, battleField.getBF_HEIGHT() + 36));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int i = 0;
		Color cc;
		for (int v = 0; v < 9; v++) {
			for (int h = 0; h < 9; h++) {
				if (COLORDED_MODE) {
					if (i % 2 == 0) {
						cc = new Color(252, 241, 177);
					} else {
						cc = new Color(233, 243, 255);
					}
				} else {
					cc = new Color(180, 180, 180);
				}
				i++;
				g.setColor(cc);
				g.fillRect(h * 64, v * 64, 64, 64);
			}
		}

		for (int j = 0; j < battleField.getDimentionX(); j++) {
			for (int k = 0; k < battleField.getDimentionY(); k++) {
				if (battleField.scanQuadrant(j, k) == "B") {
					String coordinates = getQuadrantXY(j + 1, k + 1);
					int separator = coordinates.indexOf("_");
					int y = Integer.parseInt(coordinates.substring(0, separator));
					int x = Integer.parseInt(coordinates.substring(separator + 1));
					Brick brick = new Brick(battleField, x, y);
					brick.draw(g);
				}
				if (battleField.scanQuadrant(j, k) == "W") {
					String coordinates = getQuadrantXY(j + 1, k + 1);
					int separator = coordinates.indexOf("_");
					int y = Integer.parseInt(coordinates.substring(0, separator));
					int x = Integer.parseInt(coordinates.substring(separator + 1));
					Water water = new Water(battleField, x, y);
					water.draw(g);
				}
				if (battleField.scanQuadrant(j, k) == "E") {
					String coordinates = getQuadrantXY(j + 1, k + 1);
					int separator = coordinates.indexOf("_");
					int y = Integer.parseInt(coordinates.substring(0, separator));
					int x = Integer.parseInt(coordinates.substring(separator + 1));
					Eagle eagle = new Eagle(battleField, x, y);
					eagle.draw(g);
				}
				if (battleField.scanQuadrant(j, k) == "R") {
					String coordinates = getQuadrantXY(j + 1, k + 1);
					int separator = coordinates.indexOf("_");
					int y = Integer.parseInt(coordinates.substring(0, separator));
					int x = Integer.parseInt(coordinates.substring(separator + 1));
					Rock rock = new Rock(battleField, x, y);
					rock.draw(g);
				}

				defender.draw(g);
				agresor.draw(g);
				bullet.draw(g);

			}
		}
	}
}