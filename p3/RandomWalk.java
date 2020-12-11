import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
/**
 * @author vanguyen
 */
public class RandomWalk {
	Random ran = new Random();
	private int size;
	private boolean done;
	private int x, y, r;
	private long seed;
	

	ArrayList<Point> path = new ArrayList<Point>();
	
	public RandomWalk(int size) {
		this.size = size;
		x=0;
		y=size-1;
		this.x = x;
		this.y = y;
		
	}

	public RandomWalk(int size, long seed) {
		x=0;
		y=size-1;
		this.size = size;
		this.seed = seed;
		this.x = x;
		this.y = y;
	}

	public void step() {
		if(x==0 && y== size-1) {
			Point p1= new Point(0,size-1);
			path.add(p1);
			}
			if (y == 0) {
			x++;
		} else if (x == size - 1) {
			y--;
		} else {
			r = ran.nextInt(2);
			if (r == 1) {
				x++;
			} else
				y--;
		}
		if (x == size - 1 && y == 0) {
			done = true;
		}
		Point p0 = new Point(x, y);
		path.add(p0);
	}

	public void createWalk() {
		done = false;
		if (seed>0) {
			ran.setSeed(seed);
		}
		while (!done) {
			step();
		}	
	}
	

	public boolean isDone() {
		return done;
	}

	public ArrayList<Point> getPath() {
		return path;
	}

	public String toString() {
		String result = "";
		for (Point c : path) {
			result += "["+(int)(c.getX())+","+(int)(c.getY())+"]";	
		}
		return result;
	}

}
