package battleFields;

import java.awt.Color;
import java.awt.Graphics;



public class Rock implements actionFields.Drawable, actionFields.Destroyable {
	
	private int x;
	private int y;
	private BattleField battleField;
	
	public Rock(BattleField battleField, int x, int y) {
		this.x = x;
		this.y = y;
		this.battleField = battleField;
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(165, 42, 42));
		g.fillRect(x, y, 64, 64);
		
	}
	
	
	public void destroy() {
		battleField.updateQuadrant(y, x, " ");
		
	}
		
	

}
