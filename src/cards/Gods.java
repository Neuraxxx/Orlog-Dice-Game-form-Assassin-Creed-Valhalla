package cards;
import java.util.Scanner;

import game.*;


public abstract class Gods extends Game{ //TODO

	private String name ="";
	private int index = 0;
	private int priority = 0;
	private Scanner input;
	protected Player[] player;
	
	public Gods() {
		player = super.player;
		input = super.input;
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public String getName() {
		return name;
	}
	
	public void favor(Player attacker, Player reciever, int i) {
		
	}
	

	public void showAbility() {
		
	}
}
