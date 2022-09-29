package dices;
import java.util.Random;

/**
 * There are six different types of dices.
 * Each dice has 2 faces of axes, 1 face of the rest, and 2 faces with mana symbol distributed to
 * faces evenly on different kind of dices, except the axe face.
 * @author Hiro
 *
 */
public abstract class Dice {

	public Random rd = new Random();;
	
	/**
	 * An array of string stands for face names.
	 */
	protected final String[] face= new String[] {
			"Axe", "Arrow", "Helmet", "Shield", "Steal"};
	
	/**
	 * roll the dice by generating a random number from 1 to 6
	 */
	public abstract void roll();
	
	/**
	 * 
	 * @return the random number
	 */
	public abstract int getDot();
	
	/**
	 * if the random number stands for the face with manaSymbol,
	 * for example: Dice A's 2 and 3, Dice B's 2 and 4, return true
	 * else return false
	 * 
	 * @return true to get mana
	 */
	public abstract boolean checkMana();
	
	//TODO 
	public abstract String showResult();
	
}
