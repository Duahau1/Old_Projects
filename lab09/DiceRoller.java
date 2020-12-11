import java.util.Scanner;

public class DiceRoller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String yes="y";
		String no="n";
		Scanner scan =new Scanner(System.in);
		String input;
		int com=0;
		int us=0;
		int tie=0;
		Boolean done= false;
		while(!done) {

			PairOfDice dice= new PairOfDice(3);
			dice.roll();
			dice.getFaceValue1();
			dice.getFaceValue2();
			//user roll	
			int n1=dice.getTotal();
			System.out.println("User roll");
			System.out.println(dice);
			//computer roll	

			dice.roll();
			dice.getFaceValue1();
			dice.getFaceValue2();
			int n2=dice.getTotal();
			System.out.println("Computer roll");
			System.out.println(dice);
			// who win
			int max = Math.max(n1, n2);
			System.out.println(max);
			if(n1>n2) {
				us++;
				//System.out.println("u"+us);
				System.out.println("You wins:"+us+"Computer "+com+"Tie "+tie);
			}	
			else if(n1<n2) {
				com++;
				//System.out.println("c"+com);
				System.out.println("You wins:"+us+"Computer "+com+"Tie "+tie);
			}
			else if(n1==n2) {
				tie++;
			//System.out.println("c"+com);
			System.out.println("You wins:"+us+"Computer "+com+"Tie "+tie);}
			
					
			System.out.println(" Do you want to roll again? (Y)es to continue, anything else to quit.");
			input= scan.nextLine();
		if (input.equals(yes)) {
				done=false;}
		else done=true;
		
		}




	}

}
