package tanks;

import java.awt.Color;

import actionFields.ActionField;
import actionFields.Direction;
import battleFields.BattleField;

public class T34 extends AbstractTank {
	


	public T34(ActionField actionField, BattleField battleField) {
		super(actionField, battleField);
		tankColor= new Color(0,255,0);
		towerColor=new Color(255,0,0);
	}

	public T34(int x, int y, Direction direction, int crew, ActionField actionField, BattleField battleField) {
		super(x, y, direction, actionField, battleField);
		tankColor= new Color(0,255,0);
		towerColor=new Color(255,0,0);
	}
}
