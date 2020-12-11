import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * @author vannguyen
 */
public class MineWalkerPanel {
	private int in;
	private boolean setmines;
	private LitePeg[][] pegs;
	private Color[][] color;
	private JButton[] button;
	private ArrayList<Point> mines;
	private ArrayList<Point> visitedmines;
	private Point io;
	private Point p;
	private int ge, t;
	private Point m;
	private ArrayList<Point> path;
	private int vn;
	public JPanel panel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private int index;
	private boolean point;
	private boolean liveadd;
	//all the 4 value
	private int click;
	private int cn;
	private int cm;
	private int cd;
	//---------------------
	private Integer[] mx;
	private Integer[] nx;
	private RandomWalk ran;
	private int z;
	private int random_m=0;
	private int random_n=0;
	private JTextField gridsize;
	private JSlider slide;
	private JButton showpath;
	private JButton showmine;
	private int x=0;
	private int y;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JButton giveup;
	private JLabel lives;
	private JTextField live;
	private int width=20;
	private int height=20;
	private int minecount;
	private int row=0;
	private int col=0;
	private JTextField score;
	private Point blink= new Point(0,0);
	/**
	 * @param width of the grid
	 */
	public MineWalkerPanel(int width, int height) {
		//DECLARE

		this.height=height;
		this.width= width;
		path = new ArrayList<Point>();
		m= new Point(0,width-1);
		point=true;
		setmines=true;
		liveadd=true;
		ran= new RandomWalk(width);
		ran.createWalk();
		click=0;
		in=0;
		visitedmines= new ArrayList<Point>();
		panel = new JPanel();
		panel1= new JPanel();
		panel2= new JPanel();
		panel3= new JPanel();



		t = 0;

		//PANEL1

		panel.setPreferredSize(new Dimension(600,600));
		panel.setBackground(Color.LIGHT_GRAY);
		panel1.setBorder(BorderFactory.createTitledBorder("Mine Field"));
		panel1.setPreferredSize(new Dimension (400,300));
		panel1.setLayout(new GridLayout(width, height));

		pegs = new LitePeg [width][height];
		index =0;
		button= new JButton[width*width];



		ClickListener cl = new ClickListener();
		for (int i = 0; i < pegs.length; i++)
		{
			for(int j = 0; j < pegs[0].length; j++)
			{
				pegs[j][i] = new LitePeg();

				// Add the option's button to this GridOption panel.
				pegs[j][i].getButton();
				pegs[j][i].getButton().setEnabled(false);
				pegs[j][i].getButton().addActionListener(cl);
				panel1.add(pegs[j][i].getButton());

				index++;

			}	
		}
		pegs[0][height-1].setColor(Color.BLUE);
		pegs[width-1][0].setColor(Color.BLUE);
		pegs[0][width-2].getButton().setEnabled(true);
		pegs[1][width-1].getButton().setEnabled(true);
		panel.setLayout(new BorderLayout());
		panel.add(panel1,BorderLayout.CENTER);



		//PANEL 2


		panel.add(panel2,BorderLayout.EAST);
		panel2.setPreferredSize(new Dimension (120,120));
		label1= new JLabel("Score:");
		score = new JTextField(5);

		score.setText("500");
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2.add(Box.createHorizontalGlue());
		panel2.add(label1);
		score.setMaximumSize(new Dimension(70,40));
		panel2.add(score);

		panel2.add(Box.createRigidArea(new Dimension(20, 5)));




		// lives

		lives = new JLabel();
		lives.setText("Lives:");
		live= new JTextField(5);
		live.setText("5");
		live.setMaximumSize(new Dimension(70,40));
		panel2.add(lives);
		panel2.add(live);
		panel2.add(Box.createRigidArea(new Dimension(20, 5)));
		//		live.addActionListener(cl);

		// button
		giveup = new JButton("Give Up ?");
		giveupListener ol = new giveupListener();
		giveup.addActionListener(ol);
		panel2.add(giveup);		
		panel2.add(Box.createRigidArea(new Dimension(20, 5)));

		// label 6
		label6= new JLabel("Key:");	
		panel2.add(Box.createHorizontalGlue());
		panel2.add(label6);

		// label 2
		label2= new JLabel();	
		label2.setOpaque(true);
		label2.setBackground(Color.GREEN);
		label2.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
		label2.setText("0 Nearby Mines");

		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setVerticalAlignment(JLabel.CENTER);
		panel2.add(Box.createHorizontalGlue());
		panel2.add(label2);

		//label 3
		label3= new JLabel();	
		label3.setOpaque(true);
		label3.setBackground(Color.YELLOW);
		label3.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
		label3.setText("1 Nearby Mines");
		label3.setHorizontalAlignment(JLabel.CENTER);
		label3.setVerticalAlignment(JLabel.CENTER);
		panel2.add(Box.createHorizontalGlue());
		panel2.add(label3);

		// label 4
		label4= new JLabel();	
		label4.setOpaque(true);
		label4.setBackground(new Color(255,153,0));
		label4.setBorder(BorderFactory.createEmptyBorder(20, 10, 10,10));
		label4.setText("2 Nearby Mines");
		label4.setHorizontalAlignment(JLabel.CENTER);
		label4.setVerticalAlignment(JLabel.CENTER);
		panel2.add(Box.createHorizontalGlue());
		panel2.add(label4);

		//label 5
		label5= new JLabel("3 Nearby Mines");	
		label5.setOpaque(true);
		label5.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
		label5.setBackground(Color.RED);
		label5.setHorizontalAlignment(JLabel.CENTER);
		label5.setVerticalAlignment(JLabel.CENTER);
		label5.setText("3 Nearby Mines");
		panel2.add(Box.createHorizontalGlue());
		panel2.add(label5);

		// label 7
		label7= new JLabel();	
		label7.setOpaque(true);
		label7.setBackground(Color.BLACK);
		label7.setForeground(Color.WHITE);
		label7.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
		label7.setText("Exploded Mines");
		label7.setHorizontalAlignment(JLabel.CENTER);
		label7.setVerticalAlignment(JLabel.CENTER);
		panel2.add(Box.createHorizontalGlue());
		panel2.add(label7);


		//panel 3
		panel3.setPreferredSize(new Dimension (100,50));
		panel.add(panel3,BorderLayout.SOUTH);

		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

		gridsize= new JTextField(5);
		gridsize.setText(" "+width);
		gridsize.setBorder(BorderFactory.createTitledBorder("Grid Size"));


		// slider
		slide= new JSlider(JSlider.HORIZONTAL,0, 20, 0);
		slide.setBorder(BorderFactory.createTitledBorder("Percent Mines"));
		slide.setMinorTickSpacing(2);
		slide.setMajorTickSpacing(10);
		slide.setPaintTicks(true);
		slide.setSnapToTicks(true);
		slide.setPaintLabels(true);

		panel3.add(Box.createHorizontalGlue());
		panel3.add(slide);
		panel3.add(Box.createHorizontalGlue());
		showpath= new JButton("Show Path ");
		showmine= new JButton(" Show Mine");
		slide.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				y= slide.getValue();
				System.out.println(y);
			}
		});


		// grid size
		gridsize.add(Box.createVerticalGlue());
		ExpansionListener l = new ExpansionListener();

		gridsize.setEditable(true);
		panel3.add(gridsize);
		gridsize.setOpaque(true);
		ExpansionListener ex= new ExpansionListener();
		gridsize.addActionListener(ex);
		// button

		//show path
		ShowpathListener lp= new ShowpathListener();
		panel3.add(Box.createHorizontalGlue());
		panel3.add(showpath);
		showpath.addActionListener(lp);
		//	showpath.addActionListener(cl);

		//show mine
		ShowmineListener ls= new ShowmineListener();
		panel3.add(Box.createHorizontalGlue());
		panel3.add(showmine);
		showmine.addActionListener(ls);

		startAnimation();
		reset();
		mines();




	}
	/**
	 * @return panel
	 */
	public JPanel getPanel() {
		return panel;
	}


	public class ExpansionListener implements ActionListener {
		/**
		 * @param action that expand the gird panel
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JTextField gridsize = (JTextField)arg0.getSource();
			String s= gridsize.getText();
			try {x= Integer.parseInt(s.trim());
			System.out.println("The entered text is: " + x);
			width=x;
			height=x;
			}
			catch(NumberFormatException n){
				System.out.println("Error");
			}
		}


	}
	private void playSound(String soundName) {

		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File(soundName).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			// throws an exception error
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}

	}

	/**
	 * reset all 
	 */
	public void reset() {

		playSound("Button_Press_4-Marianne_Gagnon-570460555.wav"); 
		visitedmines= new ArrayList<Point>();
		path = new ArrayList<Point>();
		
		ran.getPath().clear();
		
		ran= new RandomWalk(width);
		ran.createWalk();
		click=0;
		vn=0;
		z=0;
		index=0;
		in=0;

		live.setText("5");
		score.setText("500");
		setmines=true;
		m= new Point(0,width-1);
		showpath.setText("Show Path");
		showmine.setText("Show Mines");

		ClickListener cl = new ClickListener();
		
		if (width==20 && height==20) {
		
			for (int i = 0; i < pegs.length; i++)
			{
				for(int j = 0; j < pegs[0].length; j++)
				{
					pegs[j][i].resetColor(); 
					pegs[j][i].getButton();
					pegs[j][i].getButton().setEnabled(false);

					pegs[j][i].getButton().addActionListener(cl);

					index++;

				}

			}
		}

		pegs[0][pegs.length-1].setColor(Color.BLUE);
		pegs[pegs.length-1][0].setColor(Color.BLUE);
		pegs[0][pegs.length-2].getButton().setEnabled(true);
		pegs[1][pegs.length-1].getButton().setEnabled(true);

		if (width!=20 &&height!=20) {	
			panel.remove(panel1);
			panel1= new JPanel();
			panel1.setLayout(new GridLayout(width, height));
			panel1.setBorder(BorderFactory.createTitledBorder("Mine Field"));
			panel1.setPreferredSize(new Dimension (400,300));
			index=0;
			pegs = new LitePeg[width][height];

			for (int i = 0; i <pegs.length; i++)
			{
				for(int j = 0; j < pegs[0].length; j++)
				{
					pegs[j][i] = new LitePeg();
					pegs[j][i].getButton().addActionListener(cl);

					pegs[j][i].getButton().setEnabled(false);
					panel1.add(pegs[j][i].getButton());

				}	
			}

			pegs[0][pegs.length-1].setColor(Color.BLUE);
			pegs[pegs.length-1][0].setColor(Color.BLUE);
			pegs[0][pegs.length-2].getButton().setEnabled(true);
			pegs[1][pegs.length-1].getButton().setEnabled(true);
			panel.add(panel1,BorderLayout.CENTER);
			panel1.revalidate();

		}
	}
	/**
	 * create mines
	 */
	public void mines() {

		vn=1;
		z=1;
		mx = new Integer[width+y];
		nx = new Integer[width+y];
		Random rand = new Random();
		mines = new ArrayList<Point>();
		
		for(int i=0;i<width+y;i++){
			random_m = rand.nextInt(width);
			random_n = rand.nextInt(width);
			for(Point c: ran.getPath()) {
				if (random_m==c.x && random_n==c.y) {

					random_m = rand.nextInt(width);
					random_n = rand.nextInt(width);
					
			
				}
				
				else {

					mx[i]=random_m;
					nx[i]=random_n;	

				}

				
			}


		}
		System.out.println();
		for(int g=0; g<width+y;g++) {
			p= new Point(mx[g],nx[g]);
			if (mines.contains(p)) {
				System.out.println("lala"+random_m+"lal"+random_n);
				random_m = rand.nextInt(width);
				random_n = rand.nextInt(width);
				mx[g]=random_m;
				nx[g]=random_n;	
				p= new Point(mx[g],nx[g]);
				System.out.println("lala"+random_m+"lal"+random_n);
				mines.add(p);
			}
			else {mines.add(p);}
		}
		System.out.println();

		for(Point c:mines) {
			System.out.print(" "+c);

		}
		for(int col = 0; col < width+y; col++) {
			System.out.println(mx[col]+" "+ nx[col]);
		}




	}
	/**
	 * show mines
	 */
	public void showmines() {
		for(int i=0;i<mx.length;i++) {
			pegs[mx[i]][nx[i]].setColor(Color.BLACK);

		}
		playSound("Button_Press_4-Marianne_Gagnon-570460555.wav"); 

	}
	/**
	 * show path
	 */
	public void showpath() {
		playSound("Button_Press_4-Marianne_Gagnon-570460555.wav"); 
		ge=0;
		System.out.print(ran);
		while(ge!=ran.getsize()-2) {

			ge++;
			pegs[ran.getPointx(ge)][ran.getPointy(ge)].setColor(Color.WHITE);



		}
	}

	public class ShowpathListener implements ActionListener{
		/**
		 * @param action that show path
		 */
		@Override
		public void actionPerformed(ActionEvent pol) {
			// TODO Auto-generated method stub

			JButton showpath= (JButton)pol.getSource();
			//show path

			if (vn%2!=0) {
				showpath.setText("Hide Path");
				showpath();}
			else {
				showpath.setText("Show Path");
				ge=0;
				//hide path
				while(ge!=ran.getsize()-2) {
					ge++;
					pegs[ran.getPointx(ge)][ran.getPointy(ge)].setColor(0);
				}
			}
			vn++;
		}
	}
	public class giveupListener implements ActionListener{
		/**
		 * @param action that give up
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			playSound("ahem_x.wav");
			showpath();
			showmines();
			JFrame parent = new JFrame();
			JOptionPane.showMessageDialog(parent, "I wish you could be more patient");
			JButton no= (JButton)arg0.getSource();

			reset();
			mines();

		}

	}

	public class ShowmineListener implements ActionListener{
		/**
		 * @param action that show mines
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton showmine= (JButton)e.getSource();

			//reveal mines
			if(z%2!=0) {
				showmine.setText("Hide Mine");
				showmines();}

			//hide mines
			else {
				for(int i=0;i<mx.length;i++) {
					pegs[mx[i]][nx[i]].setColor(0);
				}
				showmine.setText("Show Mine");
			}
			z++;	
		}
	}
	private class TimerActionListener implements ActionListener
	{
		/**
		 * @param action that blink the current location tile
		 */
		public void actionPerformed(ActionEvent evt)
		{

			t++;

			if (in==3) {
				if (t%2==0) {
					pegs[m.x][m.y].getButton().setBackground(Color.PINK);
				}
				else {
					pegs[m.x][m.y].getButton().setBackground(Color.MAGENTA);
				}

			}
			if (in==0) {
				if (t%2 == 0) {
					pegs[m.x][m.y].getButton().setBackground(new Color(0,100,0));
				}
				else {
					pegs[m.x][m.y].getButton().setBackground(new Color(154,205,50));
				}

			}
			if (in==1) {
				if (t%2 == 0) {
					pegs[m.x][m.y].getButton().setBackground(new Color(255,255,51));
				}
				else {
					pegs[m.x][m.y].getButton().setBackground(new Color(255,215,0));
				}

			}	
			if (in==2) {
				if (t%2 == 0){
					pegs[m.x][m.y].getButton().setBackground(new Color(255,99,71));
				}
				else {
					pegs[m.x][m.y].getButton().setBackground(new Color(255,228,225));
				}

			}

		}

	}

	/**
	 * Create an animation thread that runs periodically
	 */
	private void startAnimation()
	{
		TimerActionListener delay = new TimerActionListener();
		new Timer(100, delay).start();
	}

	public class ClickListener implements ActionListener {
		/**
		 * @param action that is triggered when you click the gird panel buttons
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			JButton butt= (JButton)e.getSource();

			// case 1: going to the right
			if (m.x+1<width&&butt.equals(pegs[m.x+1][m.y].getButton())) {
				// in case the butt step on is a bo
				for (Point h: mines ) {
					if (butt.equals(pegs[h.x][h.y].getButton())) {
						setmines=false;
						io= new Point(h.x,h.y);

						pegs[h.x][h.y].getButton().setEnabled(false);
						m.x=h.x-1;
						path.add(m);

					}

				}
				if(setmines==true) { 
					// start to change the color according to the number of mines near by
					for(Point c: mines) {


						if (c.x==m.x+2&&c.y==m.y) {
							click++;

						}	
						else if (c.x==m.x+1&&c.y==m.y-1) {
							click++;

						}
						else if (c.x==m.x+1&&c.y==m.y+1) {
							click++;

						}
						if (click==3) {
							butt.setBackground(Color.RED);
							in=3;

						}

						else if(click==2) {
							butt.setBackground(new Color(255,153,0));
							in=2;

						}
						else if (click==1) {
							butt.setBackground(Color.YELLOW);
							in=1;

						}
						else if (click<1) {
							butt.setBackground(Color.GREEN);
							in=0;

						}
						System.out.println("click"+click);

					}

					//the old point
					if(m.y-1>-1) {
						pegs[m.x][m.y-1].getButton().setEnabled(false);
					}

					if (m.x-1>-1) {
						pegs[m.x-1][m.y].getButton().setEnabled(false);
					}

					if (m.y+1<width) {
						pegs[m.x][m.y+1].getButton().setEnabled(false);
					}		
					if(m.x-1>-1) {
						pegs[m.x-1][m.y].getButton().setEnabled(false);
					}
					if (pegs[m.x][m.y].getColor().equals(new Color(0,100,0))||pegs[m.x][m.y].getColor().equals(new Color(154,205,50))) {
						pegs[m.x][m.y].setColor(Color.GREEN);
					}
					if (pegs[m.x][m.y].getColor().equals(new Color(255,255,51))||pegs[m.x][m.y].getColor().equals(new Color(255,215,0))) {
						pegs[m.x][m.y].setColor(Color.YELLOW);
					}
					if (pegs[m.x][m.y].getColor().equals(new Color(255,99,71))||pegs[m.x][m.y].getColor().equals(new Color(255,228,225))) {
						pegs[m.x][m.y].setColor(new Color(255,153,0));
					}
					if (pegs[m.x][m.y].getColor().equals(Color.PINK)||pegs[m.x][m.y].getColor().equals(Color.MAGENTA)) {
						pegs[m.x][m.y].setColor(Color.RED);
					}

					m.x+=1;
					m= new Point(m.x,m.y);
					path.add(m);
					// the new point
					if(m.x+1<width) {
						pegs[m.x+1][m.y].getButton().setEnabled(true);
					}
					if (m.x-1>-1) {
						pegs[m.x-1][m.y].getButton().setEnabled(true);
					}
					if(m.y-1>-1) {
						pegs[m.x][m.y-1].getButton().setEnabled(true);
					}
					if (m.y+1<width) {
						pegs[m.x][m.y+1].getButton().setEnabled(true);
					}			
					pegs[m.x][m.y].getButton().setEnabled(false);

				}
			}
			// case2 : moving up 
			if (m.y-1>-1&&butt.equals(pegs[m.x][m.y-1].getButton())) {
				// if step on mine decrease lives
				for (Point h: mines ) {
					if (butt.equals(pegs[h.x][h.y].getButton())) {
						setmines=false;
						io= new Point(h.x,h.y);

						pegs[h.x][h.y].getButton().setEnabled(false);
						m.y=h.y+1;
						path.add(m);

					}

				}
				if(setmines==true) { 
					for(Point r: mines) {


						if (r.x==m.x&&r.y==m.y-2) {
							click++;

						}	
						else if (r.x==m.x+1&&r.y==m.y-1) {
							click++;

						}
						else if (r.x==m.x-1&&r.y==m.y-1) {
							click++;
						}
						if (click==3) {
							butt.setBackground(Color.RED);
							in=3;

						}
						else if(click==2) {
							butt.setBackground(new Color(255,153,0));
							in=2;

						}
						else if (click==1) {
							butt.setBackground(Color.YELLOW);
							in=1;

						}
						else if (click<1) {
							butt.setBackground(Color.GREEN);
							in=0;

						}
						System.out.println("cn"+click);
					}	
					// the old point
					if(m.x+1<width) {
						pegs[m.x+1][m.y].getButton().setEnabled(false);
					}

					if (m.y-1>-1) {
						pegs[m.x][m.y-1].getButton().setEnabled(false);
					}
					if (m.x-1>-1) {
						pegs[m.x-1][m.y].getButton().setEnabled(false);
					}		
					if(m.y+1<width) {
						pegs[m.x][m.y+1].getButton().setEnabled(false);
					}
					if (pegs[m.x][m.y].getColor().equals(new Color(0,100,0))||pegs[m.x][m.y].getColor().equals(new Color(154,205,50))) {
						pegs[m.x][m.y].setColor(Color.GREEN);
					}
					if (pegs[m.x][m.y].getColor().equals(new Color(255,255,51))||pegs[m.x][m.y].getColor().equals(new Color(255,215,0))) {
						pegs[m.x][m.y].setColor(Color.YELLOW);
					}
					if (pegs[m.x][m.y].getColor().equals(new Color(255,99,71))||pegs[m.x][m.y].getColor().equals(new Color(255,228,225))) {
						pegs[m.x][m.y].setColor(new Color(255,153,0));
					}
					if (pegs[m.x][m.y].getColor().equals(Color.PINK)||pegs[m.x][m.y].getColor().equals(Color.MAGENTA)) {
						pegs[m.x][m.y].setColor(Color.RED);
					}


					m.y-=1;
					m= new Point(m.x,m.y);
					path.add(m);
					// the new point
					if(m.y-1>-1) {
						pegs[m.x][m.y-1].getButton().setEnabled(true);
					}
					if (m.x-1>-1) {
						pegs[m.x-1][m.y].getButton().setEnabled(true);
					}
					if(m.x+1<width) {
						pegs[m.x+1][m.y].getButton().setEnabled(true);
					}
					if (m.y+1<width) {
						pegs[m.x][m.y+1].getButton().setEnabled(true);
					}			
					pegs[m.x][m.y].getButton().setEnabled(false);

				}
			}
			//case 3: moving down 
			if (m.y+1<width&&butt.equals(pegs[m.x][m.y+1].getButton())) {
				// if step on mine decrease the lives 
				for (Point h: mines ) {
					if (butt.equals(pegs[h.x][h.y].getButton())) {
						setmines=false;
						io= new Point(h.x,h.y);

						pegs[h.x][h.y].getButton().setEnabled(false);
						m.y=h.y-1;
						path.add(m);

					}

				}
				if(setmines==true) { 
					for(Point v: mines) {

						if (v.x==m.x&&v.y==m.y+2) {
							click++;

						}	
						else if (v.x==m.x-1&&v.y==m.y+1) {
							click++;

						}
						else if (v.x==m.x+1&&v.y==m.y+1) {
							click++;

						}
						if (click==3) {
							butt.setBackground(Color.RED);
							in=3;

						}
						else if(click==2) {
							butt.setBackground(new Color(255,153,0));
							in=2;

						}
						else if (click==1) {
							butt.setBackground(Color.YELLOW);
							in=1;

						}
						else if (click<1) {
							butt.setBackground(Color.GREEN);
							in=0;

						}
						System.out.println("cm"+click);

					}

					// the old point
					if (m.y+1<width) {
						pegs[m.x][m.y+1].getButton().setEnabled(false);
					}

					if (m.y-1>-1) {
						pegs[m.x][m.y-1].getButton().setEnabled(false);
					}

					if (m.x-1>-1) {
						pegs[m.x-1][m.y].getButton().setEnabled(false);
					}		
					if (m.x+1<width) {
						pegs[m.x+1][m.y].getButton().setEnabled(false);
					}
					if (pegs[m.x][m.y].getColor().equals(new Color(0,100,0))||pegs[m.x][m.y].getColor().equals(new Color(154,205,50))) {
						pegs[m.x][m.y].setColor(Color.GREEN);
					}
					if (pegs[m.x][m.y].getColor().equals(new Color(255,255,51))||pegs[m.x][m.y].getColor().equals(new Color(255,215,0))) {
						pegs[m.x][m.y].setColor(Color.YELLOW);
					}
					if (pegs[m.x][m.y].getColor().equals(new Color(255,99,71))||pegs[m.x][m.y].getColor().equals(new Color(255,228,225))) {
						pegs[m.x][m.y].setColor(new Color(255,153,0));
					}
					if (pegs[m.x][m.y].getColor().equals(Color.PINK)||pegs[m.x][m.y].getColor().equals(Color.MAGENTA)) {
						pegs[m.x][m.y].setColor(Color.RED);
					}


					m.y+=1;
					m= new Point(m.x,m.y);
					path.add(m);
					// the new point 
					if(m.y+1<width) {
						pegs[m.x][m.y+1].getButton().setEnabled(true);
					}
					if (m.x-1>-1) {
						pegs[m.x-1][m.y].getButton().setEnabled(true);
					}
					if(m.x+1<width) {
						pegs[m.x+1][m.y].getButton().setEnabled(true);
					}
					if (m.y-1>-1) {
						pegs[m.x][m.y-1].getButton().setEnabled(true);
					}			
					pegs[m.x][m.y].getButton().setEnabled(false);

				}
			}
			// case 4: moving to the left 
			if (m.x-1>-1&&butt.equals(pegs[m.x-1][m.y].getButton())) {
				for (Point h: mines ) {
					if (butt.equals(pegs[h.x][h.y].getButton())) {
						setmines=false;
						io= new Point(h.x,h.y);

						pegs[h.x][h.y].getButton().setEnabled(false);
						m.x=h.x+1;
						path.add(m);

					}

				}
				if(setmines==true) { 
					for(Point d: mines) {
						System.out.print("f");

						if (d.x==m.x-2&&d.y==m.y) {
							click++;

						}	
						else if (d.x==m.x-1&&d.y==m.y+1) {
							click++;

						}
						else if (d.x==m.x-1&&d.y==m.y-1) {
							click++;

						}
						if (click==3) {
							butt.setBackground(Color.RED);
							in=3;

						}
						else if(click==2) {
							butt.setBackground(new Color(255,153,0));
							in=2;

						}
						else if (click==1) {
							butt.setBackground(Color.YELLOW);
							in=1;

						}
						else if (click<1) {
							butt.setBackground(Color.GREEN);
							in=0;

						}
						System.out.println("cd"+click);

					}
					// the old point
					if (m.x-1>-1) {
						pegs[m.x-1][m.y].getButton().setEnabled(false);
					}
					if (m.x+1<width) {
						pegs[m.x+1][m.y].getButton().setEnabled(false);
					}
					if (m.y+1<width) {

						pegs[m.x][m.y+1].getButton().setEnabled(false);
					}		
					if (m.y-1>-1) {
						pegs[m.x][m.y-1].getButton().setEnabled(false);
					}
					if (pegs[m.x][m.y].getColor().equals(new Color(0,100,0))||pegs[m.x][m.y].getColor().equals(new Color(154,205,50))) {
						pegs[m.x][m.y].setColor(Color.GREEN);
					}
					if (pegs[m.x][m.y].getColor().equals(new Color(255,255,51))||pegs[m.x][m.y].getColor().equals(new Color(255,215,0))) {
						pegs[m.x][m.y].setColor(Color.YELLOW);
					}
					if (pegs[m.x][m.y].getColor().equals(new Color(255,99,71))||pegs[m.x][m.y].getColor().equals(new Color(255,228,225))) {
						pegs[m.x][m.y].setColor(new Color(255,153,0));
					}
					if (pegs[m.x][m.y].getColor().equals(Color.PINK)||pegs[m.x][m.y].getColor().equals(Color.MAGENTA)) {
						pegs[m.x][m.y].setColor(Color.RED);
					}

					m.x-=1;
					m= new Point(m.x,m.y);
					path.add(m);
					// the new point
					if(m.x-1>-1) {
						pegs[m.x-1][m.y].getButton().setEnabled(true);
					}
					if (m.x+1<width) {
						pegs[m.x+1][m.y].getButton().setEnabled(true);
					}
					if(m.y+1<width) {
						pegs[m.x][m.y+1].getButton().setEnabled(true);
					}
					if (m.y-1>-1) {
						pegs[m.x][m.y-1].getButton().setEnabled(true);
					}			
					pegs[m.x][m.y].getButton().setEnabled(false);
				}
			}
			click=0;
			liveadd=true;
			setmines=true;	
			for(Point f: path) {
				pegs[f.x][f.y].getButton().setEnabled(false);

			}



		}

	}
	public class LitePeg {
		private final Color [] COLORS= {Color.LIGHT_GRAY,Color.GREEN,Color.YELLOW,Color.ORANGE,Color.RED};
		private int ColorIndex;
		private JButton pegbutton;
		private Integer[] m;
		private Integer[] n;
		private int x =0;
		private int h=0;
		private int k=0;

		private Color c=Color.BLACK;
		/**
		 * create a single tile
		 */
		public LitePeg() {
			pegbutton=new JButton();
			ColorIndex=0;
			pegbutton.setBackground(COLORS[ColorIndex]);
			LitePegListener l= new LitePegListener();
			pegbutton.addActionListener(l);
		}
		/**
		 * @return the color of the button;
		 */
		public Color getColor() {
			return pegbutton.getBackground();
		}
		/**
		 * @param color index and set the tile to that color
		 */
		public void setColor(int x) {
			this.x = x;
			pegbutton.setBackground(COLORS[x]);

		}
		/**
		 * reset color
		 */
		public void resetColor() {
			ColorIndex=0;
			pegbutton.setBackground(COLORS[ColorIndex]);
		}
		/**
		 * change color
		 */
		public void changeColor() {
			ColorIndex++;
			pegbutton.setBackground(COLORS[ColorIndex]);
		}
		/**
		 * @return the button
		 */
		public JButton getButton() {
			return pegbutton;
		}
		/**
		 * @param color of your choice
		 */
		public void setColor(Color c) {
			this.c = c;
			pegbutton.setBackground(c);

		}

		public class LitePegListener implements ActionListener{
			/**
			 * @param action that happen when you click on any tiles
			 */
			@Override
			public void actionPerformed(ActionEvent u) {
				// TODO Auto-generated method stub
				JButton b=(JButton)u.getSource();
				if (b.equals(pegs[width-1][0].getButton())) {
					playSound("Ta Da-SoundBible.com-1884170640.wav"); 
					JFrame parents = new JFrame();
					JOptionPane.showMessageDialog(parents, "\tYou win" +"\n\tYour Score:"+score.getText());
					score.setText("500");
					reset();

				}
				h =	Integer.parseInt(live.getText().trim());
				// if step on mine decrease the lives
				k = Integer.parseInt(score.getText().trim());
				if (visitedmines.contains(io)) {
					liveadd=false;

				}

				else {visitedmines.add(io);

				}


				for (Point r: mines) {
					if (b.equals(pegs[r.x][r.y].getButton())) {
						playSound("explosion-01.wav"); 

						score.setText(""+ (k-10));

						if (liveadd==true) {
							live.setText(" "+(h-1));

						}

						point=false;

						b.setBackground(Color.BLACK);
						if (Integer.parseInt(live.getText().trim())==0) {	
							showpath();
							showmines();
							playSound("Hl2_Rebel-Ragdoll485-573931361.wav"); 
							JFrame parent = new JFrame();
							JOptionPane.showMessageDialog(parent, "\tYou are doom "+ "\n\tYour score:"+ score.getText());
							live.setText("5");
							score.setText("500");
							reset();

						}	
					}
					if (point==true) {

						score.setText(" "+(k+1));

					}				
				}

				liveadd=true;
				point=true;

			}

		}
	}

}
