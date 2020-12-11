import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
/**
 * @author vanguyen
 */
public class RandomWalkEC2 {
	Random ran = new Random();
	private int size;
	private boolean done;
	private int x, y, r;
	private long seed;
	


	ArrayList<Point> path = new ArrayList<Point>();

	public RandomWalkEC2(int size) {
		this.size = size;
		x=0;
		y=size-1;
		this.x = x;
		this.y = y;
		
	}

	public RandomWalkEC2(int size, long seed) {
		x=0;
		y=size-1;
		this.size = size;
		this.seed = seed;
		this.x = x;
		this.y = y;
		
	}
	public void step() {


		Random ran = new Random();

//		if(walk>100) {
//			path.clear();
//			x=0;
//			y=size-1;
//			walk=0;
//		}

		// case1

		if(x==0 && y== size-1) {	

			r=ran.nextInt(2);
			Point p1= new Point(0,size-1);
			path.add(p1);
			if (r==0) {
				x++;
				Point p0= new Point(x,y);
				path.add(p0);
			}
			else  {
				y--;
				Point p0= new Point(x,y);
				path.add(p0);
			}
		}
		// case 1.5
		else if (x==size-1 && y<size-1 &&y>0) {
			r=ran.nextInt(10);
			if (r==0) {
				x--;
				
			}
			else if (r>=1&&r<=9) {
				y--;
				
			}
			else {
				y++;

			}
			if (path.contains(new Point(x,y))) {
				path.remove(path.size()-1);
				}
			else{Point p0= new Point(x,y);
			path.add(p0);}
		}

		// case2
		else if (x==0 && y<size-1 &&y>0) {
			r=ran.nextInt(10);
			if (r<=5&&r>=0) {
				x++;
				
			}
			else if (r>5&&r<9) {
				y--;
				
				

			}
			else {
				y++;
				

			}
			if (path.contains(new Point(x,y))) {
				path.remove(path.size()-1);
				}
			else{Point p0= new Point(x,y);
			path.add(p0);}

		}


		// case3
		else if (x==0 && y==0) {
			r=ran.nextInt(10);
			if (r<=8&&r>=0) {
				x++;
				
			}


			else {
				y++;	
				

			}
			if (path.contains(new Point(x,y))) {
				path.remove(path.size()-1);
				}
			else{Point p0= new Point(x,y);
			path.add(p0);}
		}
		// case4
		if (y==0 && x<size-1 &&x>0) {
			r=ran.nextInt(10);
			if (r>=0&&r<=7) {
				x++;
				
				

			}
			else if (r==8) {
				x--;
				

			}
			else {
				y++;

		}
			if (path.contains(new Point(x,y))) {
				path.remove(path.size()-1);
				}
			else{Point p0= new Point(x,y);
			path.add(p0);}
			
		}

		// case5
		else if (x<size-1 && y==size-1 &&x>0) {
			r=ran.nextInt(10);
			if (r>=0 &&r<=5) {
				x++;
				

			}
			else if (r<9&&r>5) {
				y--;
				
				
			}
			else {
				x--;

			}

			if (path.contains(new Point(x,y))) {
				path.remove(path.size()-1);
				
			}
			else {Point p0= new Point(x,y);
			path.add(p0);}
		}
		// case6
		else if (x==size-1 && y==size-1) {
			r=ran.nextInt(10);
			if (r<=8&&r>=0) {
				y--;
				

			}
			else {
				x--;	

			}

			if (path.contains(new Point(x,y))) {
				path.remove(path.size()-1);
				
			}
			else{Point p0= new Point(x,y);
			path.add(p0);}
		}

		// case7
		else if (y==0 && x<size-1 &&x>0) {
			r=ran.nextInt(10);
			if (r<=7&&r>=0) {
				y--;
				

			}
			else if (r==8) {
				y++;
				

			}
			else {
				x--;


			}

			if (path.contains(new Point(x,y))) {
				path.remove(path.size()-1);
				
			}
			else{Point p0= new Point(x,y);
			path.add(p0);}

		}

		// case8
		else if (y>0 && x<size-1 &&x>0&&y<size-1) {
			r=ran.nextInt(10);
			if (r>=0&&r<=4) {
				x++;
				

			}
			else if (r==8) {
				x--;
				
			}
			else if(r>4&&r<8) {
				y--;
				
			}
			else {
				y++;

			}
			if (path.contains(new Point(x,y))) {
				path.remove(path.size()-1);
				
			}
			else {Point p0= new Point(x,y);
			path.add(p0);}
		}

		// case10
		else if (x == size - 1 && y == 0) {
			done = true;
		}

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
