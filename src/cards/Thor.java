
package cards;

import java.util.Scanner;

import game.*;

public class Thor extends Gods{

	
	private String name = "Thor's Strike";
	protected Player[] player;
	private int index = 1;
	private int priority = 7;
	private Scanner input;
	
	public Thor() {
		super();
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
	
	public void favor(Player attacker, Player receiver, int i) {
		int option = input.nextInt();
		System.out.println("Options: \n"
				+ "1. -4# Deal 2 damage\n"
				+ "2. -8# Deal 5 damage\n "
				+ "3. -12# Deal 8 damage\n"
				+ "> ");
		switch(option) {
		case 1:
			if(attacker.getMana() < 4) {
				System.out.println("Not enough #");
			}else {
				attacker.setMana(-4);
				receiver.setHealth(-2);
			}
			break;
		
		case 2:
			if(attacker.getMana() < 8) {
				System.out.println("Not enough #");
			}else {
				attacker.setMana(-8);
				receiver.setHealth(-5);
			}
			break;
			
		case 3:
			if(attacker.getMana() < 12) {
				System.out.println("Not enough #");
			}else {
				attacker.setMana(-12);
				receiver.setHealth(-8);
			}
		}
	
	}
}
