package cards;

import java.util.Scanner;

import game.Player;

public class Idunn extends Gods {

	private String name = "Iðunn's Rejuvenation";
	protected Player[] player;
	private int index = 1;
	private int priority = 7;
	private Scanner input;
	
	public Idunn() {
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
				+ "1. -4# Heal 2 Health\n"
				+ "2. -7# Heal 4 Health\n "
				+ "3. -10# Heal 6 Health\n"
				+ "> ");
		switch(option) {
		case 1:
			if(attacker.getMana() < 4) {
				System.out.println("Not enough #");
			}else {
				attacker.setMana(-4);
				attacker.setHealth(2);
			}
			break;
		
		case 2:
			if(attacker.getMana() < 7) {
				System.out.println("Not enough #");
			}else {
				attacker.setMana(-7);
				attacker.setHealth(4);
			}
			break;
			
		case 3:
			if(attacker.getMana() < 10) {
				System.out.println("Not enough #");
			}else {
				attacker.setMana(-10);
				attacker.setHealth(6);
			}
		}
	
	}
}
