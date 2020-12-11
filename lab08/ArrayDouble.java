import java.util.Random;

public class ArrayDouble {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Random ran= new Random();
	double[] balances= new double[10];
	for(int i=0; i<balances.length;i++) {
	
	balances[i]=ran.nextDouble();
	
	}
	double sum=0;
	
	for(int i=0; i<balances.length;i++) {
	sum+= balances[i];
		
	}
	System.out.println("lala"+sum);
	
	}

}
