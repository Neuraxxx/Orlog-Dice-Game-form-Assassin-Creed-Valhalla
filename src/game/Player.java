package game;
import java.util.ArrayList;

import cards.*;
import dices.*;


/**
 * This claas is the object type Player.
 * A player has 15 health and six dices originally.
 * If health goes to 0, the player lose.
 * 
 * @author Hiro
 *
 */
public class Player {

	private String name;
	private int health = 15;
	private int mana = 0;
	private int axe = 0;
    private int arrow = 0;
    private int helmet = 0;
    private int shield = 0;
    private int steal = 0;
    private ArrayList<Dice> dice= new ArrayList<Dice>(); //dice before selecting
    private ArrayList<Dice> diceChose= new ArrayList<Dice>();//selected dice, which will be calculated in resolution stage.
    private int[] godsIndex = new int[3];//TODO


    public Player(){	
	}
    
    /**
     * The constructor sets the name of the player,
     * and initialize dices.
     */
    public Player(String name) {
    	this.name = name;
    	dice.add(new DiceA());
		dice.add(new DiceB());
		dice.add(new DiceC());
		dice.add(new DiceD());
		dice.add(new DiceE());
		dice.add(new DiceF());
    }
    
  
  
    //Modifiers for the values.
    
	public void setHealth(int health) {
		this.health = this.health + health;
		if(this.health > 15) {
			this.health = 15;
		}else if( this.health < 0) {
			this.health = 0;
		}
	}
	
	public void setMana(int mana) {
		this.mana = this.mana + mana;
		if(this.mana > 15) {
			this.mana = 15;
		}else if( this.mana < 0) {
			this.mana = 0;
		}
	}
	
	public void setAxe(int axe) {
		this.axe = axe;
	}
	
	public void setArrow(int arrow) {
		this.arrow  = arrow;
	}
	
	public void setHelmet(int helmet) {
		this.helmet = helmet;
	}
	
	public void setShield(int shield) {
		this.shield = shield;
	}
	
	public void setSteal(int steal) {
		this.steal = steal;
	}
	
	//Accessors for the values
	
	public String getName() {
		return name;
	}
	public int getHealth() {
		return health;
	}
	
	public int getMana() {
		return mana;
	}
	
	public int getAxe() {
		return axe;
	}
	
	public int getArrow() {
		return arrow;
	}
	
	public int getHelmet() {
		return helmet;
	}
	
	public int getShield() {
		return shield;
	}
	
	public int getSteal() {
		return steal;
	}
	
	public int getDiceNum() {
		return dice.size();
	}
	
	//TODO
	public void setGods(int godIndex) {
		for(int i = 0; i < godsIndex.length; i++) {
			if(godsIndex[i] == 0) {
				godsIndex[i] = godIndex;
			}
		}
	}
	
	/**
	 * Roll each dice player currently has.
	 */
	public void rollDice() {
		for(int i = 0; i < dice.size(); i++) {
			dice.get(i).roll();
		}
	}
	
	/**
	 * Prompt the player to chose a dice.
	 * @param index the dice index
	 */
	public void chooseDice(int index) {
		
		diceChose.add(dice.get(index-1));
		dice.remove(index-1);
	}
	
	/**
	 * 
	 * @return the unselected dice with index.
	 */
	public String showAvailableDice() {
		String temp = "";
		if(dice.size() != 0) {
		for(int i = 1; i < dice.size() + 1; i++) {
			temp = temp + i + "." + dice.get(i-1).showResult() + " ";
		}
		}else {
			temp = "No dice is avaible.";
		}
		return temp;
	}
	
	/**
	 * 
	 * @return the selected dice
	 */
	public String showChosenDice() {
		String temp = "";
		for(int i = 0; i < diceChose.size(); i++) {
			temp = temp + diceChose.get(i).showResult() + " ";
		}
		return temp;
	}
	
	/**
	 * Calculate the selected dices.
	 */
	public void firstResolution() {
		for(int i =0; i < 6; i++) {
			switch(diceChose.get(i).getDot() - 1) {
			case 0:
			case 1:
				axe++;
				System.out.println( name +  " gets 1 axe.");
				break;
			case 2:
				arrow++;
				System.out.println( name +  " gets 1 arrow.");
				break;
			case 3:
				System.out.println( name +  " gets 1 helmet.");
				helmet++;
			    break;
			case 4:
				System.out.println( name +  " gets 1 shield.");
				shield++;
				break;
			case 5:
				System.out.println( name +  " gets 1 steal.");
				steal++;
				break;
			default:
			}
			if(diceChose.get(i).checkMana()) {
				mana++;
				System.out.println( name +  " gets 1 mana.");
			}
		} 
	}
	
	/**
	 * reset the dices to unselected status 
	 * and reset the values
	 */
	public void nextStage() {
		for(int i = 0; i < 6; i++) {
			dice.add(diceChose.get(0));
			diceChose.remove(0);	
		}
		axe = 0;
		arrow = 0;
		helmet = 0;
		shield = 0;
		steal = 0;	
	}
	
}
