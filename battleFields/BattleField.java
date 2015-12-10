package battleFields;

import java.util.Random;

public class BattleField {
	private int BF_WIDTH = 576;
	private int BF_HEIGHT = 576;
	
	private String[][] battleField = { 
			{ "B", "B", "B", "B", "B", "B", "B", "B", "B" },
			{ " ", " ", "R", " ", " ", " ", "R", " ", " " },
			{ "B", " ", " ", "W", "B", "W", " ", " ", "B" },
			{ "B", " ", " ", "B", "B", "B", " ", " ", "B" }, 
			{ " ", " ", " ", "W", " ", "W", " ", " ", " " },
			{ "B", "B", " ", " ", " ", " ", " ", "B", "B" },
			{ "B", "B", " ", " ", " ", " ", " ", "B", "B" },
			{ "B", "B", " ", "B", "B", "B", " ", "B", "B" }, 
			{ "B", "B", " ", "B", "E", "B", " ", "B", "B" } };
	
	public BattleField(){
		this.battleField= battleField;
	}
	
	public String scanQuadrant(int v, int h){
		return battleField[v][h];
	}
	
	public void updateQuadrant(int v, int h, String value){
		battleField[v][h]=value;
	}
	
	public int getDimentionX(){
		return battleField.length;
	}
	
	public int getDimentionY(){
		return battleField.length;
	}

	public int getBF_WIDTH() {
		return BF_WIDTH;
	}

	public int getBF_HEIGHT() {
		return BF_HEIGHT;
	}
	
	public String getAgresorLocation(){
		String str = String.valueOf(System.currentTimeMillis());
		int random = Integer.parseInt(str.substring(str.length()-1));
		String loc[]={"256_256","256_320"};
		if (random>=0 && random<=1){
			return loc[random];
		}
		return "256_64";
	}

	
	
}
