import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JPanel;

public class LiteBriteBoard {
	private LitePeg[][] pegs;
	private JPanel gridpanel;
	private int width;
	private int height;
	
	private final Color[][] COLORS;
	public LiteBriteBoard(int width, int height) {
		this.width=width;
		this.height=height;
		
		gridpanel = new JPanel();
		COLORS= new Color [width][height];
		gridpanel.setLayout(new GridLayout(width, height, 1, 1));
		
		gridpanel.setPreferredSize(new Dimension(500, 500));
		pegs = new LitePeg [COLORS.length][COLORS[0].length];
		
		for (int i = 0; i < pegs.length; i++)
		{
			for(int j = 0; j < pegs[0].length; j++)
			{
				
				pegs[i][j] = new LitePeg();
				
				// Add the option's button to this GridOption panel.
				gridpanel.add(pegs[i][j].getButton());
			}
		
		
		}
	//eyes
	for (int i=0;i<2;i++)
	{pegs[4][6].changeColor();
	pegs[4][7].changeColor();
	pegs[5][6].changeColor();
	pegs[5][7].changeColor();
	pegs[4][12].changeColor();
	pegs[4][13].changeColor();
	pegs[5][12].changeColor();
	pegs[5][13].changeColor();
	}
	
	 
	
	//mouth
	pegs[10][5].changeColor();
	pegs[11][5].changeColor();
	pegs[12][5].changeColor();
	pegs[12][6].changeColor();
	pegs[12][7].changeColor();
	pegs[12][8].changeColor();
	pegs[12][9].changeColor();
	pegs[12][10].changeColor();
	pegs[12][11].changeColor();
	pegs[12][12].changeColor();
	pegs[12][13].changeColor();
	pegs[10][13].changeColor();
	pegs[11][13].changeColor();
	pegs[13][6].changeColor();
	pegs[13][7].changeColor();
	pegs[13][8].changeColor();
	pegs[13][9].changeColor();
	pegs[13][10].changeColor();
	pegs[13][11].changeColor();
	pegs[13][12].changeColor();
	
	//dot1
	for (int i=0; i<4;i++)
	pegs[10][1].changeColor();

	//dot2
	for (int i=0; i<5;i++)
	pegs[17][2].changeColor();
	//dot3
	for (int i=0; i<6;i++)
	pegs[18][8].changeColor();
	//dot4
	for (int i=0; i<7;i++)
	pegs[18][15].changeColor();
	
	}
	public void reset() {
		for (int i = 0; i < pegs.length; i++)
		{
			for(int j = 0; j < pegs[0].length; j++)
			{
				
				pegs[i][j].resetColor(); 
				
				
				// Add the option's button to this GridOption panel.
				gridpanel.add(pegs[i][j].getButton());
			}
		
		}
		//eyes
		for (int i=0;i<2;i++)
		{pegs[4][6].changeColor();
		pegs[4][7].changeColor();
		pegs[5][6].changeColor();
		pegs[5][7].changeColor();
		pegs[4][12].changeColor();
		pegs[4][13].changeColor();
		pegs[5][12].changeColor();
		pegs[5][13].changeColor();
		}
		
		 
		
		//mouth
		pegs[10][5].changeColor();
		pegs[11][5].changeColor();
		pegs[12][5].changeColor();
		pegs[12][6].changeColor();
		pegs[12][7].changeColor();
		pegs[12][8].changeColor();
		pegs[12][9].changeColor();
		pegs[12][10].changeColor();
		pegs[12][11].changeColor();
		pegs[12][12].changeColor();
		pegs[12][13].changeColor();
		pegs[10][13].changeColor();
		pegs[11][13].changeColor();
		pegs[13][6].changeColor();
		pegs[13][7].changeColor();
		pegs[13][8].changeColor();
		pegs[13][9].changeColor();
		pegs[13][10].changeColor();
		pegs[13][11].changeColor();
		pegs[13][12].changeColor();
		
		//dot1
		for (int i=0; i<4;i++)
		pegs[10][1].changeColor();

		//dot2
		for (int i=0; i<5;i++)
		pegs[17][2].changeColor();
		//dot3
		for (int i=0; i<6;i++)
		pegs[18][8].changeColor();
		//dot4
		for (int i=0; i<7;i++)
		pegs[18][15].changeColor();
		
	}
	
	
	public JPanel getJPanel() {
		return gridpanel;
	}

}
