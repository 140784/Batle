package battleFields;

import java.awt.Color;
import java.awt.Graphics;


public class Water  implements actionFields.Drawable{
	private int x;
	private int y;

	private BattleField battleField;

	public Water(BattleField bf, int x, int y) {
		this.x = x;
		this.y = y;
		this.battleField = bf;

	}

	public void draw(Graphics g) {
		g.setColor(new Color(51, 171, 248));
		g.fillRect(x, y, 64, 64);
		
	}
	
}
