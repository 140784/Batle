package battleFields;

import java.awt.Color;
import java.awt.Graphics;



public class Eagle implements actionFields.Drawable, actionFields.Destroyable {

	private int x;
	private int y;
	private BattleField battleField;
	private Graphics g;
	public Eagle(BattleField bf, int x, int y) {
		this.x = x;
		this.y = y;
		this.battleField = bf;
	}

	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0));
		g.fillRect(x, y, 64, 64);

	}

	
	public void destroy() {
		battleField.updateQuadrant(y, x, "R");
		System.out.println("!!! GAME OVER !!!");

	}
	
	
}
