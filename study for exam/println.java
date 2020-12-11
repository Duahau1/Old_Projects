import java.text.DecimalFormat;
import java.util.Scanner;

public class println {
	private static int l=0;
	
	public static double amplitude(double[]value) {
		double min=value[0];
		double max= value[0];
		for (int i=0;i<value.length;i++) {
			if (value[i]<min) {
				min=value[i];
			}
			
		}
		for (int i=0;i<value.length;i++) {
			if (value[i]>max) {
				max=value[i];
			}
			
		}
		double distance= max- min;
	
		return distance;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	double []value= {12.3,14.6,15.6,12,13,15};
	DecimalFormat df= new DecimalFormat("#.00");
	double distance= amplitude(value);
	System.out.print("the distance is"+df.format(distance));
	}

}
