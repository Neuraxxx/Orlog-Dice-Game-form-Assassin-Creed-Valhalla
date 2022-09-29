package dices;


public class DiceB extends Dice {

	private int randomNum;

	
	
	public void roll() {
		randomNum = rd.nextInt(6);
	}
	
	public int getDot() {
		return randomNum;
	}
	
	public boolean checkMana() {
		if(randomNum == 2 || randomNum == 4) {
			return true;
		}else {
			return false;
		}	
	}
	
	public String showResult() {
		String upFace = null;
		switch(randomNum) {
		case 0: 
		case 1:
		    upFace = face[0];
		break;
		case 2:
			upFace = "||" + face[1] + "||";
		break;
		case 3:
			upFace = face[2];
		break;
		case 4:
			upFace = "||" + face[3] + "||";
		break;
		case 5:
			upFace = face[4];
		default:
		}
		return upFace;
	}
	
	public void showDice() {
		System.out.println("This Dice has faces:"
				); //TODO
	}
}
