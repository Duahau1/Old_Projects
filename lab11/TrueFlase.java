import java.util.Arrays;

public class TrueFlase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int le=0;
		int le1=0;
		// array 1
		boolean [] flags= new boolean[7];
		for (int i =0; i<flags.length; i++) {
			if (i% 2==0) {
				flags[i]=true;
			}
			else flags[i]=false;
			le++;
		}
		System.out.println("Length: "+le);
		System.out.print("contents [ " );
		for(boolean c: flags) {
			System.out.print(c+ " , " );
		}
		System.out.println(" ] " );
		// array 2
		boolean[] flags2= Arrays.copyOf(flags,le-1 );

		System.out.println("Length: "+flags2.length);
		System.out.print("contents [ " );
		for(boolean c: flags2) {
			System.out.print(c+ " , " );

		}
		System.out.print(" ] " );



	}

}
