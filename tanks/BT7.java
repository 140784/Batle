package tanks;

import actionFields.ActionField;
import actionFields.Direction;
import battleFields.BattleField;

public class BT7 extends AbstractTank  {

	private int speed = super.getSpeed() * 2;

	public BT7(ActionField actionField, BattleField battleField) {
		super(actionField, battleField);
	}

	public BT7(int x, int y, Direction direction, int crew, ActionField actionField, BattleField battleField) {
		super(x, y, direction, actionField, battleField);
	}

	public int getSpeed() {
		return speed;
		
		
	}
}
