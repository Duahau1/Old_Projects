import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * CS 121 Project 1: Traffic Animation
 *
 * Animates a [This is the Fourth of July Scene when soldiers stand and salute)
 *
 * @author vannguyen
 * @author vannguyen
 */
@SuppressWarnings("serial")
public class TrafficAnimation extends JPanel
{
	// This is where you declare constants and variables that need to keep their
	// values between calls	to paintComponent(). Any other variables should be
	// declared locally, in the method where they are used.

	/**
	 * A constant to regulate the frequency of Timer events.
	 * Note: 100ms is 10 frames per second - you should not need
	 * a faster refresh rate than this
	 */
	private final int DELAY = 50; //milliseconds

	/**
	 * The anchor coordinate for drawing / animating. All of your vehicle's
	 * coordinates should be relative to this offset value.
	 */
	private int xOffset = -2000 ;
	
	/**
	 * The number of pixels added to xOffset each time paintComponent() is called.
	 */
	private int stepSize = 10;

	private final Color BACKGROUND_COLOR = new Color(0,191,252);

	/* This method draws on the panel's Graphics context.
	 * This is where the majority of your work will be.
	 *
	 * (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g)
	{
		
		
		// Get the current width and height of the window.
		int width = getWidth(); // panel width
		int height = getHeight(); // panel height
		// Calculate the new xOffset position of the moving object.
		
				xOffset  = (xOffset+ stepSize) % width;
				
		// Fill the graphics page with the background color.
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, width, height);
		
		//This draw the road
		int x = width/5;
		int y= height/4;
		g.setColor(new Color(211,211,211));
		g.fillRect(0, height/2, width, height/5);
		
		
		// 	This draw the pavement 
		g.setColor(new Color(245,245,220));
		g.fillRect(0,7*height/10, width, 3*height/10);
		
		// This draw paint on the road
		g.setColor(new Color(255,165,0));
		g.fillRect(0, 3*height/5, x/2, height/40);
		g.setColor(new Color(255,165,0));
		g.fillRect(x, 3*height/5, x/2, height/40);
		g.setColor(new Color(255,165,0));
		g.fillRect(2*x, 3*height/5, x/2, height/40);
		g.setColor(new Color(255,165,0));
		g.fillRect(3*x, 3*height/5, x/2, height/40);
		g.setColor(new Color(255,165,0));
		g.fillRect(4*x, 3*height/5, x/2, height/40);
		g.setColor(new Color(255,165,0));
		g.fillRect((int)(4.75*x), 3*height/5, x/2, height/40);
		
		
		// This draws background theme
		g.setColor(new Color(212,39,41));
		g.fillRect(0,0, x, height/2);
		g.setColor(new Color(1,87,174));
		g.fillRect(x,0, x, height/2);
		g.setColor(new Color(255,255,255));
		g.fillRect(2*x,0, x, height/2);
		g.setColor(Color.RED);
		g.fillRect(3*x,0, x, height/2);
		g.setColor(new Color(0,6,84));
		g.fillRect(4*x,0, x, height/2);
		

		// Insert a string 
		g.setColor(Color.BLACK);
		String str = "Stand up and Salute!!!!!";
		g.setFont(new Font("Courier New", Font.BOLD+ Font.ITALIC,y/5));
        g.drawString(str,(int)(1.5*x), height/6);
		
      //This draw rectangle1
      	g.setColor(new Color(29,33,13));
      	g.fillRect(xOffset+width/25,23*height/72,3*width/25,height/9);
        
        // This draw rectangle2  
		g.setColor(new Color(29,33,13));
		g.fillRect(xOffset,(int)(1.5*y),width/5 ,height/9 );
		
		// This draw a banner on rect2
		g.setColor(Color.WHITE);
		String ban = "TEAM USA";
		g.setFont(new Font("Courier New", Font.BOLD+ Font.ITALIC,y/5));
        g.drawString(ban,xOffset, 31*height/72);
		
		//This draw 2 triangles 
		g.setColor(new Color(125,138,53));
		g.drawLine(xOffset, (int)(1.5*y),xOffset-width/10,(int)(35*y/18));
		g.drawLine(xOffset,(int)(35*y/18), xOffset-width/10,(int)(35*y/18));
		g.drawLine(xOffset+width/5, (int)(1.5*y),xOffset+3*width/10, 35*y/18);
		g.drawLine(xOffset,35*y/18, xOffset+3*width/10, 35*y/18);
		
		//This draw 2 triangles
		g.drawLine(xOffset+width/25,23*height/72, xOffset+width/50,(int)(1.5*y));
		g.drawLine(xOffset+4*width/25, 23*height/72,xOffset+9*width/50, (int)(1.5*y));
		
		// This draw rectangle3
		g.fillRect(xOffset+4*width/25, 23*height/72, width/10, height/40);
		
		//This draw a rectangle4
		g.setColor(new Color(25,28,11));
		g.fillRect(xOffset+13*width/50,557*height/1800 ,7*width/90, (int)(1.5*height/35));
		//This draw a missile launched from the tank
		g.setColor(new Color(255,215,0));
		g.fillArc(xOffset+58*width/225,557*height/1800 ,xOffset+11*width/90, (int)(1.5*height/35),0, 45);
		
		//This draw 2 wheels
		g.setColor(Color.BLACK);
		g.fillOval(xOffset-width/10, (int)(35*y/18),3*width/50 , 9*height/100);
		g.fillOval(xOffset+6*width/25,(int)(35*y/18), 3*width/50, 9*height/100);
		
		// This draw person1 watching the tank passing by
		g.setColor(new Color(205,183,158));
		g.fillOval(x, 3*y, 3*width/50, 9*height/100);
		g.setColor(new Color(125,138,53));
		g.fillRoundRect(x, 21*height/25, 3*width/40, 9*height/60,180,90);
		g.setColor(new Color(125,138,53));
		g.fillArc((int)(0.95*x), 3*y, 4*width/50, 9*height/100, 0, 180);
		g.setColor(Color.BLACK);
		g.fillOval(19*width/80,183*height/200, width/80 , height/80);
		g.fillOval(19*width/80, 47*height/50, width/80, height/80);
		g.fillOval(19*width/80, 89*height/100, width/80, height/80);
		
		// This draw person2 watching the tank passing by
		g.setColor(new Color(205,183,158));
		g.fillOval((int)(1.5*x), 3*y, 3*width/50, 9*height/100);
		g.setColor(new Color(125,138,53));
		g.fillRoundRect((int)(1.5*x), 21*height/25, 3*width/40, 9*height/60,180,90);
		g.setColor(new Color(125,138,53));
		g.fillArc((int)(1.45*x), 3*y, 4*width/50, 9*height/100, 0, 180);
		g.setColor(Color.BLACK);
		g.fillOval(27*width/80,183*height/200, width/80 , height/80);
		g.fillOval(27*width/80, 47*height/50, width/80, height/80);
		g.fillOval(27*width/80, 89*height/100, width/80, height/80);
		
		// This draw person3 watching the tank passing by
		g.setColor(new Color(205,183,158));
		g.fillOval((int)(2*x), 3*y, 3*width/50, 9*height/100);
		g.setColor(new Color(125,138,53));
		g.fillRoundRect((int)(2*x), 21*height/25, 3*width/40, 9*height/60,180,90);
		g.setColor(new Color(125,138,53));
		g.fillArc((int)(1.95*x), 3*y, 4*width/50, 9*height/100, 0, 180);
		g.setColor(Color.BLACK);
		g.fillOval(7*width/16,183*height/200, width/80 , height/80);
		g.fillOval(7*width/16, 47*height/50, width/80, height/80);
		g.fillOval(7*width/16, 89*height/100, width/80, height/80);
		
		// This drawperson4 watching the tank passing by
		g.setColor(new Color(205,183,158));
		g.fillOval((int)(2.5*x), 3*y, 3*width/50, 9*height/100);
		g.setColor(new Color(125,138,53));
		g.fillRoundRect((int)(2.5*x), 21*height/25, 3*width/40, 9*height/60,180,90);
		g.setColor(new Color(125,138,53));
		g.fillArc((int)(2.45*x), 3*y, 4*width/50, 9*height/100, 0, 180);
		g.setColor(Color.BLACK);
		g.fillOval(43*width/80,183*height/200, width/80 , height/80);
		g.fillOval(43*width/80, 47*height/50, width/80, height/80);
		g.fillOval(43*width/80, 89*height/100, width/80, height/80);
		
		// This draw person5 watching the tank passing by
		g.setColor(new Color(205,183,158));
		g.fillOval((int)(3*x), 3*y, 3*width/50, 9*height/100);
		g.setColor(new Color(125,138,53));
		g.fillRoundRect((int)(3*x), 21*height/25, 3*width/40, 9*height/60,180,90);
		g.setColor(new Color(125,138,53));
		g.fillArc((int)(2.95*x), 3*y, 4*width/50, 9*height/100, 0, 180);
		g.setColor(Color.BLACK);
		g.fillOval(51*width/80,183*height/200, width/80 , height/80);
		g.fillOval(51*width/80, 47*height/50, width/80, height/80);
		g.fillOval(51*width/80, 89*height/100, width/80, height/80);
		
		// Put your code above this line. This makes the drawing smoother.
		Toolkit.getDefaultToolkit().sync();
	}
	

	//==============================================================
	// You don't need to modify anything beyond this point.
	//==============================================================


	/**
	 * Starting point for this program. Your code will not go in the
	 * main method for this program. It will go in the paintComponent
	 * method above.
	 *
	 * DO NOT MODIFY this method!
	 *
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		// DO NOT MODIFY THIS CODE.
		JFrame frame = new JFrame ("Traffic Animation");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TrafficAnimation());
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Constructor for the display panel initializes necessary variables.
	 * Only called once, when the program first begins. This method also
	 * sets up a Timer that will call paint() with frequency specified by
	 * the DELAY constant.
	 */
	public TrafficAnimation()
	{
		// Do not initialize larger than 800x600. I won't be able to
		// grade your project if you do.
		int initWidth = 600;
		int initHeight = 400;
		setPreferredSize(new Dimension(initWidth, initHeight));
		this.setDoubleBuffered(true);

		//Start the animation - DO NOT REMOVE
		startAnimation();
	}

	/**
	 * Create an animation thread that runs periodically.
	 * DO NOT MODIFY this method!
	 */
	private void startAnimation()
	{
		ActionListener timerListener = new TimerListener();
		Timer timer = new Timer(DELAY, timerListener);
		timer.start();
	}

	/**
	 * Repaints the graphics panel every time the timer fires.
	 * DO NOT MODIFY this class!
	 */
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
}
