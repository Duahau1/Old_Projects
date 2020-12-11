import java.util.Scanner;

public class reverse {
	private String x;
	public reverse () {
		this.x=x;
	}

	public String reverseString(String x) { 
		String result="";
		for(int i=x.length()-1;i>=0;i--) { 
			result+=x.charAt(i);}
		return result;
	}

	public static int maxOfTwo(int x,int y) {
		int max=Math.max(x, y);
		if(x==y) {
			return x+""+y;
		}
		else return max;
	}

	
	
	
}


