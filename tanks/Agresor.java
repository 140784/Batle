package tanks;

import actionFields.ActionField;
import actionFields.Direction;
import battleFields.BattleField;

public class Agresor extends AbstractTank {
	
	public Agresor(int x, int y, Direction direction, int crew, ActionField actionField, BattleField battleField) {
		super(x, y, direction, actionField, battleField);
	}

	public Agresor(ActionField actionField, BattleField battleField) {
		super(actionField, battleField);
	}
}
