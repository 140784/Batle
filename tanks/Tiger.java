package tanks;

import java.awt.Color;

import actionFields.ActionField;
import actionFields.Direction;
import battleFields.BattleField;

public class Tiger extends AbstractTank {
	private int armor = 1;

	public Tiger(ActionField actionField, BattleField battleField) {
		super(actionField, battleField);
		tankColor = new Color(255, 0, 0);
		towerColor = new Color(0, 255, 0);
	}

	public Tiger(int x, int y, Direction direction, ActionField actionField, BattleField battleField) {
		super(x, y, direction, actionField, battleField);
		tankColor = new Color(255, 0, 0);
		towerColor = new Color(0, 255, 0);
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	@Override
	public void destroy() {
		if (armor > 0) {
			armor--;
		} else {
			super.destroy();
		}
	}
	
	@Override
	public void fire() throws Exception {
		// TODO Auto-generated method stub
		super.fire();
	}

}
