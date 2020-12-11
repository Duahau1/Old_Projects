import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class BoxTest {

	public static void main (String[] arg) {
		final int NUMBOX= 4;
		Random rand = new Random();
		Box smallBox = new Box(4,5,2);
		if (smallBox.isEmpty()== true) {
			System.out.println("A full "+ smallBox.getWidth()+ " x "+ smallBox.getHeight() +" x " + smallBox.getDepth() +" box ");
		}
		System.out.print("An empty "+ smallBox.getWidth()+ " x "+ smallBox.getHeight() +" x " + smallBox.getDepth() +" box ");	
		System.out.println(smallBox);
		System.out.println("");
		System.out.println("========Change smallbox using it's setters========\r\n");

		/*double m,n,d,v,s;
		m =smallBox.getWidth();
		n= smallBox.getHeight();
		d=smallBox.getDepth();
		System.out.println(n);
		System.out.println(m);
		System.out.println(d);
		v=smallBox.Volume();
		s=smallBox.surfaceArea();
		System.out.println(v);
		System.out.println(s);*/
		smallBox.setWidth(2);
		smallBox.setHeight(3);
		smallBox.setDepth(1);
		smallBox.setisEmpty(true);
		if (smallBox.isEmpty()== true) {
			System.out.print("A full "+ smallBox.getWidth()+ " x "+ smallBox.getHeight() +" x " + smallBox.getDepth() +" box ");
		}
		else System.out.print("An empty "+ smallBox.getWidth()+ " x "+ smallBox.getHeight() +" x " + smallBox.getDepth() +" box ");
		System.out.println(smallBox);
		System.out.println("");
		System.out.println("========Create 5 boxes========\r\n");
		DecimalFormat df = new DecimalFormat ("0.##");
		ArrayList<Box> box = new ArrayList<Box>();
		for (int i=0; i<=NUMBOX;i++) {
			double  z,x,c;
			z=rand.nextDouble();
			x=rand.nextDouble();
			c=rand.nextDouble();
			Box b = new Box(z,x,c);
			box.add(b);
			System.out.print("Box"+i +":");
			if (b.isEmpty()== true) {
				System.out.println("A full "+ df.format(z)+ " x "+ df.format(x) +" x " +  df.format(c)  +" box ");
			}
			else System.out.println("An empty "+ df.format(z)+ " x "+ df.format(x) +" x " + df.format(c) +" box ");

		}
		System.out.println("");
		System.out.println("========Find the largest box========\r\n");

		double a=0;
		Box f = new Box(0,0,0);
		boolean g =true;

		System.out.println("Largest Box");
		for (Box n : box) {
			if (n.Volume()>a) {
				a=n.Volume();		
			}	

		}
		
		for (Box c: box) {
			if (c.Volume()==a) {
				if (c.isEmpty()== true) {
					System.out.print("A full "+ df.format(c.getWidth())+ " x "+ df.format(c.getHeight()) +" x " + df.format(c.getDepth()) +" box with volume "+df.format(c.Volume())+" and surface area "+df.format(c.surfaceArea()));
				}
				else System.out.print("An empty "+ df.format(c.getWidth())+ " x "+ df.format(c.getHeight()) +" x " + df.format(c.getDepth()) +" box with volume "+df.format(c.Volume())+" and surface area "+df.format(c.surfaceArea()));

			}
		}







	}	
}
