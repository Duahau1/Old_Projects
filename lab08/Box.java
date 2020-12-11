import java.text.DecimalFormat;

public class Box {
	private double width;
	private double height;
	private double depth;
	private boolean full;
	private double volume;
	private double surfaceA;
	DecimalFormat df = new DecimalFormat ("0.##");
	public Box(double width, double height, double depth) {
		this.width =width;
		this.height =height;
		this.depth =depth;
		full= false;
	}
	public boolean isEmpty() {
				if (full=true) {
		System.out.println("full");
		}
		else System.out.println("empty");
				return full;		
		

	}
	public void setisEmpty(boolean full) {
		this.full=full;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width=width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height =height;
	}
	public double getDepth() {
		return depth;
	}
	public void setDepth(double depth) {
		this.depth =depth;
	}	
	public double Volume() {	
		volume= height*depth*width; 
		return  volume;
	}
	public double surfaceArea() {
		surfaceA= 2*(height*depth+height*width+width*depth);
		return surfaceA;
	}



	public String toString() {

		return"\n smallbox's width: " + df.format(width) +"\n smallbox's height: " + df.format(height) +"\n smallbox's depth: " + df.format(depth) + 
				"\n smallbox's volume: " + df.format(Volume()) + "\n smallbox's surface Area: "+ df.format(surfaceArea()) + "\n smallbox reports its full status as: "+ isEmpty() ;
	}

}
