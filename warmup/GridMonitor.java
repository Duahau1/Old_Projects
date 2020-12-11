import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * @author vannguyen
 * Name: Van Nguyen 
 * Project 1 GridMonitor 
 * CS221 Section 3
 */
/**
 * @author vannguyen
 *This Constructor implements the GridMonitorInterface.
 *It reads in a file and creates a 2D array based off
 *the numbers given in the file.
 */
public class GridMonitor implements GridMonitorInterface {

	private String filename="";
	private File file;
	private int num1;
	private int num2;
	private double[][]grid;
	private double[][]gridsum;
	private double[][]average;
	private double[][]deltagrid;
	private double[][]grids;
	private double[][]temp;
	private double[][]averages;
	private double[][]deltagrids;
	private double[][]upperbound;
	private double[][]lowerbound;
	private boolean[][]check;
	private boolean[][]checks;
	private String[][]safeop;

	public GridMonitor(String filename) throws FileNotFoundException{
		this.filename=filename;

		file= new File(filename);

		Scanner filescan = new Scanner(file);
		num1=filescan.nextInt();
		num2=filescan.nextInt();
	
		grid = new double[num1][num2];

		for(int row=0;row<num1;row++) {
			for(int col=0;col<num2;col++) {
				if (filescan.hasNextDouble()) {
					grid[row][col]=filescan.nextDouble();

				}
			}
		}

	}
	/**
	 * Returns the original base grid, as read from file.
	 * 
	 * @return base grid
	 */

	@Override
	public double[][] getBaseGrid() {
		// TODO Auto-generated method stub
		temp= new double[num1][num2];
		for(int row=0;row<num1;row++) {
			for(int col=0;col<num2;col++) {
				temp[row][col]= grid[row][col];
				}
			}
		return temp;
	}
	/**
	 * Returns a grid with the same dimensions as the base grid, but each element
	 * is the sum of the 4 surrounding base elements. For elements on a grid border,
	 * the base element's own value is used when looking out of bounds, as if there
	 * is a mirror surrounding the grid. This method is exposed for testing.
	 * 
	 * @return grid containing the sum of adjacent positions
	 */
	@Override
	public double[][] getSurroundingSumGrid() {
		// TODO Auto-generated method stub
		gridsum= new double[num1][num2];
		if (num1>1&&num2>1) {
			for(int rows=0;rows<num1;rows++) {
				for(int cols=0;cols<num2;cols++) {
					
					// case 1
					if(rows==0 &&cols==0) {
						gridsum[rows][cols]=grid[rows][cols+1]+grid[rows+1][cols]+grid[rows][cols]*2;

					}

					//case 2
					else if(rows==num1-1 && cols==0 ) {
						gridsum[rows][cols]=grid[rows-1][cols]+grid[rows][cols+1]+grid[rows][cols]*2;

					}
					//case 3
					else if(rows==0 && cols==num2-1 ) {
						gridsum[rows][cols]=grid[rows+1][cols]+grid[rows][cols-1]+grid[rows][cols]*2;

					}
					//case 4
					else if(rows==num1-1 && cols==num2-1 ) {
						gridsum[rows][cols]=grid[rows-1][cols]+grid[rows][cols-1]+grid[rows][cols]*2;

					}
					//case 5
					else if(rows==0 && cols<num2-1&&cols>0  ) {
						gridsum[rows][cols]=grid[rows][cols]+grid[rows][cols+1]+grid[rows+1][cols]+grid[rows][cols-1];

					}
					//case 6
					else if(rows==num1-1 && cols<num2-1&&cols>0  ) {
						gridsum[rows][cols]=grid[rows][cols]+grid[rows][cols+1]+grid[rows-1][cols]+grid[rows][cols-1];

					}
					//case 7
					else if(cols==0 && rows<num1-1&&rows>0  ) {
						gridsum[rows][cols]=grid[rows+1][cols]+grid[rows][cols+1]+grid[rows][cols]+grid[rows-1][cols];

					}
					//case 8
					else if(cols==num2-1 && rows<num1-1&&rows>0  ) {
						gridsum[rows][cols]=grid[rows][cols]+grid[rows][cols-1]+grid[rows+1][cols]+grid[rows-1][cols];

					}
					//case 9
					else {
						gridsum[rows][cols]=grid[rows+1][cols]+grid[rows][cols-1]+grid[rows-1][cols]+grid[rows][cols+1];
					}	
				}
			}
		}
		if(num1==1&&num2==1) {
			gridsum[0][0]=grid[0][0]*4;
		}
		
		return gridsum;
	}
	/**
	 * Returns a grid with the same dimensions as the base grid, but each element
	 * is the average of the 4 surrounding base elements. This method is exposed for 
	 * testing.
	 * 
	 * @return grid containing the average of adjacent positions
	 */
	@Override
	public double[][] getSurroundingAvgGrid() {
		// TODO Auto-generated method stub
		average= new double[num1][num2];
		average=this.getSurroundingSumGrid();
		for(int row=0;row<num1;row++) {
			for(int col=0;col<num2;col++) {
				average[row][col]=(average[row][col])/4;		
			}
		}

		return average;
	}
	/**
	 * Returns a grid with the same dimensions as the base grid, but each element 
	 * is the maximum delta from the average. For example, if the surrounding
	 * average at some coordinate is 2.0 and the maximum delta is 50% of the average,
	 * the delta value at the same coordinate in this grid will be 1.0. This method is
	 * exposed for testing.
	 * 
	 * @return grid containing the maximum delta from average of adjacent positions
	 */
	@Override
	public double[][] getDeltaGrid() {
		// TODO Auto-generated method stub
		deltagrid=new double[num1][num2];
		deltagrid=this.getSurroundingAvgGrid();
		for(int row=0;row<num1;row++) {
			for(int col=0;col<num2;col++) {
				deltagrid[row][col]=Math.abs((deltagrid[row][col])/2);		
			}
		}

		return deltagrid;
	}
	/**
	 * Returns a grid with the same dimensions as the base grid, but each element
	 * is a boolean value indicating if the cell is at risk. For example, if the cell
	 * at a coordinate is less than the surrounding average minus its maximum delta or
	 * greater than the surrounding average plus its maximum delta, the corresponding
	 * coordinate in this grid will be true. If the base cell value is within the safe
	 * range, however, the corresponding value in this grid will be false.
	 * 
	 * @return grid containing boolean values indicating whether the cell at this
	 * location is in danger of exploding
	 */
	@Override
	public boolean[][] getDangerGrid() {
		// TODO Auto-generated method stub
		grids= new double[num1][num2];
		grids=this.getBaseGrid();
		averages= new double[num1][num2];
		averages=this.getSurroundingAvgGrid();
		deltagrids=new double[num1][num2];
		deltagrids=this.getDeltaGrid();

		upperbound=new double[num1][num2];
		lowerbound=new double[num1][num2];

		check=new boolean[num1][num2];

		for(int row=0;row<num1;row++) {
			for(int col=0;col<num2;col++) {
				upperbound[row][col]=Math.abs(deltagrids[row][col]+Math.abs(averages[row][col]));		

			}
		}
		for(int row=0;row<num1;row++) {
			for(int col=0;col<num2;col++) {
				lowerbound[row][col]=Math.abs(Math.abs(averages[row][col])-deltagrids[row][col]);		

			}
		}
		for(int rows=0;rows<num1;rows++) {
			for(int cols=0;cols<num2;cols++) {
				if (Math.abs(grids[rows][cols])>upperbound[rows][cols]||Math.abs(grids[rows][cols])<lowerbound[rows][cols]) {
					check[rows][cols]=true;
				}
				else {
					check[rows][cols]=false;
				}
			}
		}


		return check;
	}
	/**
	 *Return a table with the state of the each slot
	 * 
	 * @return a safe operation grid which tells you directly whether the slot is safe or dangerous. 
	 */
	@Override
	public String toString() {
		checks= new boolean[num1][num2];
		checks=this.getDangerGrid();
		String table= "Safe Operation\n\n";
		String tab="";
		safeop=new String[num1][num2];
		for (int row=0;row<num1;row++) {
			for(int col=0;col<num2;col++) {
				if (checks[row][col]==true) {
					safeop[row][col]="Danger!  |";
					tab+=safeop[row][col];
					tab+=" ";
				}
				else {
					safeop[row][col]="SafeZone |";
					tab+=safeop[row][col];
					tab+=" ";
					if (col==num2-1) {
						tab+="\n\n";

					}
				}

			}
		}
		String print= table +tab;
		return print;

	}



}
