package game;

import java.util.Random;
import java.util.Scanner;
import cards.*;

/**
 * I was inspired when playing Orlog dice game, which is a mini game in Assassin Creed Valhalla.
 * This program is a dice game, copying from Assassin Creed Valhalla, that runs *only in console*,
 * and only implements the core logic. Probably in the future, GUI will be implemented for this game,
 * or it will run on a browser.
 * 
 * The main class that contains the main method printing a menu, prompting user to start a new game,
 * or read the rules about the game, as well as the game-running methods.
 * @author Hiro
 *
 */
public class Game {

	protected static Player[] player = new Player[] { new Player("Player1"), new Player("Palyer2") };
	private static Gods[] gods;//TODO
	protected static Scanner input = new Scanner(System.in);;
	private static int coin; //The coin decides who plays first. If it's obverse, player1 plays first.
	private static Random coinToss = new Random();
	private static int winner; //1 means player1 wins, while 2 means player2 wins.

    /**
     * Main method that prints a menu.
     * @param args
     */
	public static void main(String[] args) {

		System.out.println("Welcome to Orlog.");
		int option;
		do {
			System.out.print("Enter 1 to start the game.\n" + "Enter 2 to read the rule.\n" + "> ");
			option = input.nextInt();
			switch (option) {
			case 1:
				printLine();
				System.out.println("Toss a coin.");
				tossCoin();
				if (coin == 1) {
					System.out.println("Obverse face up. " + player[0].getName() + " plays first.");
					gameRun(player[0], player[1]);
				} else {
					System.out.println("Reverse face up. " + player[1].getName() + " plays first.");
					gameRun(player[1], player[0]);
				}
				break;

			case 2:
				gameRule();
				break;
			}
		} while (option == 2); //will loop if user asked to view the rules.
	}

	/**
	 * Runs the game. 
	 * Contains 3 stage:
	 * Roll Stage
	 * Gods Favor Stage //TODO
	 * Resolution Stage.
	 * 
	 * Game will be ended as soon as any player's HP turns to 0, and the opposite player wins.
	 * 
	 * @param player1 The player plays first.
	 * @param player2 The player plays later.
	 */
	public static void gameRun(Player player1, Player player2) {
		
		while(true) {
			printLine();
			System.out.println(player1.getName() + " now has the coin and plays first in this round");
			printLine();
			System.out.println("ROLL STAGE");
			rollStage(player1, player2);
			printLine();
			System.out.println("RESOLUTION STAGE");
			//First part of Resolution Stage
			resolutionStage(player1, player2);
			
			//Second Part of Resolution Stage
			calcAxe(player1, player2);
			if(player2.getHealth() == 0) {
				winner = 1;
				break;				
			}
			calcArrow(player1, player2);
			if(player2.getHealth() == 0) {
				winner = 1;
				break;				
			}
			printLine();
			calcAxe(player2, player1);
			if(player1.getHealth() == 0) {
				winner = 2;
				break;				
			}
			calcArrow(player2, player1);
			if(player1.getHealth() == 0) {
				winner = 2;
				break;				
			}
			printLine();

			calcSteal(player1, player2);
			calcSteal(player2, player1);
			printLine();
			//Reset Dices
			player1.nextStage(); 
			player2.nextStage();
			System.out.println("SWAP THE COIN.");
			//Player who has the coin plays first in a full round.
			printLine();
			System.out.println(player2.getName() + " now has the coin and plays first in this round");
			printLine();
			System.out.println("ROLL STAGE");
			rollStage(player2, player1);
			printLine();
			System.out.println("RESOLUTION STAGE");
			resolutionStage(player2, player1);
			calcAxe(player2, player1);
			if(player1.getHealth() == 0) {
				winner = 2;
				break;				
			}
			calcArrow(player2, player1);
			if(player1.getHealth() == 0) {
				winner = 2;
				break;				
			}
			printLine();
			calcAxe(player1, player2);
			if(player2.getHealth() == 0) {
				winner = 1;
				break;				
			}
			calcArrow(player1, player2);
			if(player2.getHealth() == 0) {
				winner = 1;
				break;				
			}
			printLine();

			calcSteal(player2, player1);
			calcSteal(player1, player2);
			player1.nextStage();
			player2.nextStage();
		}
		//Game ends when the loop breaks.
		System.out.println("GAME OVER");
		if(winner == 1) {
			System.out.println(player1.getName() +" wins.");
		}else {
			System.out.println(player2.getName() +" wins.");
		}
		
	}
	
	/**
	 * The roll stage contains 3 rounds. 
	 * Players will be asked to choose the dice at the first round and the second round.
	 * All dices will be automatically chosen at round 3.
	 * 
	 * @param player1 The player plays first.
	 * @param player2 The player plays later.
	 */
	public static void rollStage(Player player1, Player player2) {
			for (int round = 1; round <= 3; round++) {
				switch (round) {
				case 1: // 1st round
					printLine();
					player1.rollDice();
					System.out.println(
							player1.getName() + " rolled the dices.\nResults are: " + player1.showAvailableDice()
									+ "\nPlease choose Dices entering one by one. Press 7 to continue.");

					while (true) {
						int option = input.nextInt();
						if (option != 7) {
							player1.chooseDice(option);
							System.out.println(player1.showAvailableDice());
						} else {
							break;
						}
					}

					printLine();
					player2.rollDice();
					System.out.println(
							player2.getName() + " rolled the dices.\nResults are: " + player2.showAvailableDice()
									+ "\nPlease choose Dices entering one by one. Press 7 to continue.");

					while (true) {
						int option = input.nextInt();
						if (option != 7) {
							player2.chooseDice(option);
							System.out.println(player2.showAvailableDice());
						} else {
							break;
						}
					}

					break;

				case 2:// 2nd round
					printLine();
					player1.rollDice();
					System.out.println(player1.getName() + " has chosen dices: " + player1.showChosenDice()
							+ "\nAnd rolled the dices." + "\nResults are: " + player1.showAvailableDice()
							+ "\nPlease choose Dices entering one by one. Press 7 to continue.");

					while (true) {
						int option = input.nextInt();
						if (option != 7) {
							player1.chooseDice(option);
							System.out.println(player1.showAvailableDice());
						} else {
							break;
						}
					}

					printLine();
					player2.rollDice();
					System.out.println(player2.getName() + " has chosen dices: " + player2.showChosenDice()
							+ "\nAnd rolled the dices." + "\nResults are: " + player2.showAvailableDice()
							+ "\nPlease choose Dices entering one by one. Press 7 to continue.");

					while (true) {
						int option = input.nextInt();
						if (option != 7) {
							player2.chooseDice(option);
							System.out.println(player2.showAvailableDice());
						} else {
							break;
						}
					}
					break;

				case 3://3rd round
					printLine();
					player1.rollDice();
					System.out.println(player1.getName() + " has chosen dices: " + player1.showChosenDice()
							+ "\nAnd rolled the dices." + "\nResults are: " + player1.showAvailableDice()
							+ "\nAll rest of dices have been automatically chosen.");
					final int restDice1 = player1.getDiceNum();
					for (int i = 1; i <= restDice1; i++) {
						player1.chooseDice(1);
					}

					printLine();
					player2.rollDice();
					System.out.println(player2.getName() + " has chosen dices: " + player2.showChosenDice()
							+ "\nAnd rolled the dices." + "\nResults are: " + player2.showAvailableDice()
							+ "\nAll rest of dices have been automatically chosen.");
					final int restDice2 = player2.getDiceNum();
					for (int i = 1; i <= restDice2; i++) {
						player2.chooseDice(1);
					}

				}
			}
	}

	/**
	 * The first part of the resolution stage.
	 * Calculates dices for both player, and adds on the values.
	 * TODO some gods' favor will be implemented here
	 * 
	 * @param player1 The player plays first.
	 * @param player2 The player plays later.
	 */
	public static void resolutionStage(Player player1, Player player2) {
		printLine();
		System.out.println(player1.getName() + " now has dices: \n" + player1.showChosenDice());
		System.out.println(player1.getName() + " Health: " + player1.getHealth() + "  Mana: " + player1.getMana());
		player1.firstResolution();
		printLine();
		System.out.println(player2.getName() + " now has dices:\n" + player2.showChosenDice());
		System.out.println(player2.getName() + " Health: " + player2.getHealth() + "  Mana: " + player2.getMana());
		player2.firstResolution();
		printLine();
	}
	
	/**
	 *TODO
	 * @param player1
	 * @param player2
	 */
	public static void godsFavorStage(Player player1, Player player2) {
		//TODO
	}
	/**
	 * The second part of resolution stage. 
	 * Calculates the axe of player1 and the helmet of player2.
	 * If player1's axes are greater than player2's helmet, deal
	 * the equivalent damage to player2.
	 * 
	 * @param player1 attacker
	 * @param player2 defender
	 */

	public static void calcAxe(Player player1, Player player2) {
		System.out.println(player1.getName() + " has " + player1.getAxe() + " axe(s)");
		System.out.println(player2.getName() + " has " + player2.getHelmet() + " helmet(s)");
		if (player1.getAxe() - player2.getHelmet() > 0) {
			player2.setHealth(-player1.getAxe() + player2.getHelmet());
			System.out.println(player1.getName() + " deals " + (player1.getAxe() - player2.getHelmet()) + " damage to "
					+ player2.getName());
		}
	}

	
	/**
	 * The second part of resolution stage. 
	 * Calculates the arrows of player1 and the shields of player2.
	 * If player1's arrows are greater than player2's shields, deal
	 * the equivalent damage to player2.
	 * 
	 * @param player1 attacker
	 * @param player2 defender
	 */
    public static void calcArrow(Player player1, Player player2) {
		System.out.println(player1.getName() + " has " + player1.getArrow() + " arrow(s)");
		System.out.println(player2.getName() + " has " + player2.getShield() + " shlield(s)");
		if (player1.getArrow() - player2.getShield() > 0) {
			player2.setHealth(-player1.getArrow() + player2.getShield());
			System.out.println(player1.getName() + " deals " + (player1.getArrow() - player2.getShield()) + " damage to "
					+ player2.getName());
		}
	}

    /**
	 * The second part of resolution stage. 
	 * Calculate the steals.
	 * 
	 * @param player1 attacker
	 * @param player2 defender
	 */
	public static void calcSteal(Player player1, Player player2) {
		System.out.println(player1.getName() + " has " + player1.getSteal() + " steal(s)");
		if (player1.getSteal() != 0) {
			if (player1.getSteal() > player2.getMana()) { //if attacker's steals are greater than defender's mana, return the correct result.
				System.out
						.println(player1.getName() + " grabs " + player2.getMana() + " mana from" + player2.getName());
			} else {
				System.out
						.println(player1.getName() + " grabs " + player1.getSteal() + " mana from" + player2.getName());
			}
		}
	}
	
	/**
	 * Toss the coin at the beginning of a game
	 * 
	 */
	public static void tossCoin() {
		coin = coinToss.nextInt(2);
	}

	/**
	 * Print the link to the official web site of game rules.
	 */
	public static void gameRule() {
		System.out.println("please visit :\n"
				+ "https://vulkk.com/2020/12/01/assassins-creed-valhalla-orlog-guide-and-strategies/");
	}

	/**
	 * print a line consists of * in console for good readiness.
	 */
	public static void printLine() {
		String line = "";
		for (int i = 0; i < 100; i++) {
			line = line + "*";
		}
		System.out.println(line);
	}

}
