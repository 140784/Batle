package tanks;

import java.awt.Color;
import java.awt.Graphics;

import actionFields.ActionField;
import actionFields.Bullet;
import actionFields.Destroyable;
import actionFields.Direction;
import actionFields.Drawable;
import battleFields.BattleField;

public abstract class AbstractTank implements Drawable, Destroyable {
	private int x;
	private int y;
	private int crew;
	private Direction direction;
	private int speed = 8;
	Color tankColor;
	Color towerColor;
	public boolean wallOnKurs = false;
	ActionField actionField;
	BattleField battleField;

	public int getSpeed() {
		return speed;
	}

	public void destroy() {
		this.x = -100;
		this.y = -100;
	}

	public void draw(Graphics g){
		g.setColor(tankColor);
		g.fillRect(this.getX(), this.getY(), 64, 64);

		g.setColor(towerColor);
		if (this.getDirection() == Direction.UP) {
			g.fillRect(this.getX() + 20, this.getY(), 24, 34);
		} else if (this.getDirection() == Direction.DOWN) {
			g.fillRect(this.getX() + 20, this.getY() + 30, 24, 34);
		} else if (this.getDirection() == Direction.LEFT) {
			g.fillRect(this.getX(), this.getY() + 20, 34, 24);
		} else {
			g.fillRect(this.getX() + 30, this.getY() + 20, 34, 24);
		}
	}
	
	public void setWallOnKurs(boolean wallOnKurs) {
		this.wallOnKurs = wallOnKurs;
	}

	public AbstractTank(int x, int y, Direction direction, ActionField actionField, BattleField battleField) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.actionField = actionField;
		this.battleField = battleField;
	}

	public AbstractTank(ActionField actionField, BattleField battleField) {
		this.x = 4 * 64;
		this.y = 6 * 64;
		this.direction = Direction.UP;
		this.actionField = actionField;
		this.battleField = battleField;
	}

	public int getCrew() {
		return crew;
	}

	public void clean() throws Exception {

		long time = System.currentTimeMillis();
		int quadrantY = Integer.parseInt((actionField.getQuadrant(x, y).substring(0, 1)));
		int quadrantX = Integer.parseInt((actionField.getQuadrant(x, y).substring(2)));
		turn(Direction.UP);
		cleanLine(direction);
		turn(Direction.DOWN);
		cleanLine(direction);
		turn(Direction.LEFT);
		cleanLine(direction);
		turn(Direction.RIGHT);
		cleanLine(direction);
		if (quadrantY < 4) {
			moveToQuadrant(quadrantX + 1, 1);
		} else {
			moveToQuadrant(quadrantX + 1, 9);
		}
		turn(Direction.UP);
		cleanLine(direction);
		turn(Direction.DOWN);
		cleanLine(direction);
		turn(Direction.LEFT);
		cleanLine(direction);
		turn(Direction.RIGHT);
		cleanLine(direction);

		if (y == 0) {
			for (int i = 1; i < 9; i++) {
				turn(Direction.DOWN);
				move();
				turn(Direction.UP);
				cleanLine(direction);
				turn(Direction.DOWN);
				cleanLine(direction);
				turn(Direction.LEFT);
				cleanLine(direction);
				turn(Direction.RIGHT);
				cleanLine(direction);

			}
		} else {
			for (int i = 1; i <= 9; i++) {
				turn(Direction.UP);
				move();
				turn(Direction.UP);
				cleanLine(direction);
				turn(Direction.DOWN);
				cleanLine(direction);
				turn(Direction.LEFT);
				cleanLine(direction);
				turn(Direction.RIGHT);
				cleanLine(direction);
			}
		}

		System.out.println("Game Over");
		System.out.println(("Duration: " + (System.currentTimeMillis() - time) / 1000 + " sek"));
	}

	private void cleanLine(Direction direction) throws Exception {
		int quadrantY = Integer.parseInt((actionField.getQuadrant(x, y).substring(0, 1)));
		int quadrantX = Integer.parseInt((actionField.getQuadrant(x, y).substring(2)));
		turn(direction);
		if (direction == Direction.LEFT && quadrantX >= 1) {
			for (int i = 1; i <= quadrantX; i++) {
				if (battleField.scanQuadrant(quadrantY, quadrantX - i) != " ") {
					fire();
				}
			}
		} else if (direction == Direction.RIGHT && quadrantX < 8) {
			for (int i = 1; i <= 8 - quadrantX; i++) {
				if (battleField.scanQuadrant(quadrantY, quadrantX + i) != " ") {
					fire();
				}
			}
		} else if (direction == Direction.UP && quadrantY >= 1) {
			for (int i = 0; i <= quadrantY; i++) {
				if (battleField.scanQuadrant(quadrantY - i, quadrantX) != " ") {
					fire();
				}
			}
		} else if (direction == Direction.DOWN && quadrantY < 8) {
			for (int i = 1; i <= 8 - quadrantY; i++) {
				if (battleField.scanQuadrant(quadrantY + i, quadrantX) != " ") {
					fire();
				}
			}
		}
	}

	public void moveRandom() throws Exception {
		while (true) {
			String random = String.valueOf(System.currentTimeMillis());
			int directionRandom = Integer.parseInt(random.substring(random.length() - 1));
			if (directionRandom > 0 && directionRandom <= 9) {
				moveToQuadrant(directionRandom, directionRandom);
			}
		}

	}

	public void moveToQuadrant(int v, int h) throws Exception {
		actionField.processMoveToQuadrant(v, h, this);
	}

	public void turn(Direction direction) throws Exception {
		this.direction = direction;
		actionField.processTurn(this);
	}

	public void move() throws Exception {
		actionField.processMove(this);
	}

	public void fire() throws Exception {
		Bullet bullet = new Bullet(x + 25, y + 25, direction, this);
		actionField.processFire(bullet, this);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Direction getDirection() {
		return direction;
	}

	public void updateX(int x) {
		this.x += x;
	}

	public void updateY(int y) {
		this.y += y;
	}

}
