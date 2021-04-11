package monopoly.model;

public class Dice {
	private int dice;
	
	public Dice() {
		dice = (int)(Math.random() * 6) + 1;
	}
	
	public int getDice() {
		return dice;
	}
}
