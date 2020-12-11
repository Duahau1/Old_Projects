
public class PairOfDice {
	private Die die1;
	private Die die2;
	private int sides;
	private boolean done;
	PairOfDice(){
	die1=new Die();
	die2=new Die();
	}
	PairOfDice(int numSides){
		this.sides=numSides;
		die1=new Die(sides);
		die2=new Die(sides);
		
	}
	public int getFaceValue1() {
		int x =die1.getFaceValue();
		return x;
	}
	public int getFaceValue2() {
		int x =die2.getFaceValue();
		return x;
	}
	public int getTotal() {
	int sum= die1.getFaceValue()+die2.getFaceValue();
	return sum;
	}
	public int roll() {
		die1.roll();
		die2.roll();
		int sum= die1.getFaceValue()+die2.getFaceValue();
		return sum;
	}
	public String toString() {
	return getTotal()+ "("+ getFaceValue1() +"+" +getFaceValue2()+")"; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
