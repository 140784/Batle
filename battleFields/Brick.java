package battleFields;

import java.awt.Color;
import java.awt.Graphics;



public class Brick implements actionFields.Drawable, actionFields.Destroyable {

	private int x;
	private int y;

	private BattleField battleField;

	public Brick(BattleField bf, int x, int y) {
		this.x = x;
		this.y = y;
		this.battleField = bf;

	}

	public void draw(Graphics g) {
		g.setColor(new Color(240, 126, 48));
		g.fillRect(x, y, 64, 64);
		g.setColor(new Color(255, 255, 255));
		g.drawLine(x, y, x + 64, y); // main up horisont
		g.drawLine(x, y + 64, x + 64, y + 64); // main down horisont
		g.drawLine(x, y + 16, x + 64, y + 16); // first horisont
		g.drawLine(x, y + 32, x + 64, y + 32); // second horisont
		g.drawLine(x, y + 48, x + 64, y + 48);
		g.drawLine(x, y + 64, x + 64, y + 64);
		g.drawLine(x + 16, y, x + 16, y + 16);
		g.drawLine(x + 48, y + 16, x + 48, y + 32);
		g.drawLine(x + 16, y + 32, x + 16, y + 48);
		g.drawLine(x + 48, y + 48, x + 48, y + 64);
	}
	
	public void destroy() {
		battleField.updateQuadrant(y, x, " ");
	}
}
