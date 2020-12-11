import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * TracerGUI draws and display the solution for the provided CircuitBoard
 * 
 * @author Van Nguyen
 *
 */
@SuppressWarnings("serial")
public class CircuitGUI extends JPanel {
	// A cell with 'T' indicates a valid move (i.e it's part of the path)
	private final char TRACE = 'T';
	private ArrayList<TraceState> bestPathSolutions;
	private JList<String> solutionList;
	private JMenuItem aboutClick;
	private JPanel gridPanel;
	JLabel grid[][];

	/**
	 * CONSTRUCTOR: draw and display the best paths on the provided circuit board
	 * 
	 * @param bestPaths
	 *            best path solutions for the provided board
	 * @param originalCircuit
	 *            board that the best path solution corresponds to
	 */
	public CircuitGUI(ArrayList<TraceState> bestPaths, CircuitBoard originalCircuit) {
		bestPathSolutions = bestPaths;
		aboutClick = new JMenuItem("About");
		setLayout(new BorderLayout());
		DisplayWindow();

		// show the original grid with no visible path
		drawgrid(originalCircuit, false);
		displayGUI();
	}

	/**
	 * Create JFrame to display the GUI including the menu bar
	 */
	private void displayGUI() {
		JFrame frame = new JFrame("Circuit Tracer");
		frame.setPreferredSize(new Dimension(800, 700));

		JMenuBar menuBar = new JMenuBar();
		MenuListener clickMenu = new MenuListener();
		// Just for the authentication		
		JMenu helpMenu = new JMenu("Help");
		aboutClick.addActionListener(clickMenu);
		menuBar.add(helpMenu);
		helpMenu.add(aboutClick);
		frame.setJMenuBar(menuBar);			
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Display the solution list, Set up right JPanel to display usage info
	 */
	private void DisplayWindow() {
		JPanel Panel1 = new JPanel();
		Panel1.setLayout(new BoxLayout(Panel1, BoxLayout.Y_AXIS));
		// List all the possible solutions 
		String[] soldata = new String[bestPathSolutions.size()];
		for (int i = 0; i < soldata.length; i++) {
			soldata[i] = "Solution " + i;
		}

		solutionList = new JList<>(soldata);
		solutionList.setFont(new Font("Georgia", Font.PLAIN, 16));
		solutionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		solutionList.setBackground(Color.ORANGE);
		solutionList.setLayoutOrientation(JList.VERTICAL);
	
		solutionList.addListSelectionListener(new ListListener());

		
		// more than 7 solutions, have JScroll
		JScrollPane jScrollPane = new JScrollPane(solutionList);
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		Panel1.add(jScrollPane);

		// main panel for all the navigating process 
		JPanel mainInfoPanel = new JPanel();
		mainInfoPanel.setLayout(new BoxLayout(mainInfoPanel, BoxLayout.Y_AXIS));

		// Icon and definition
		ImageIcon imageIcon = new ImageIcon("Numbers-1-icon.png");
		ImageIcon openspace= new ImageIcon("Numbers-2-icon.png");
		ImageIcon walkspace= new ImageIcon("icons8-foot-step-48.png");

		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		labelPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		final JLabel START = new JLabel(imageIcon);
		START.setOpaque(true);
		JLabel start= new JLabel("Start");

		final JLabel END = new JLabel(openspace);
		END.setOpaque(true);
		JLabel end= new JLabel("End");

		final JLabel SHORTESTPATH = new JLabel(walkspace);
		SHORTESTPATH.setOpaque(true);
		JLabel path= new JLabel("Path");

		labelPanel.add(start);
		labelPanel.add(Box.createRigidArea(new Dimension(20, 5)));
		labelPanel.add(START);
		labelPanel.add(Box.createRigidArea(new Dimension(20, 15)));
		labelPanel.add(end);
		labelPanel.add(Box.createRigidArea(new Dimension(20, 5)));
		labelPanel.add(END);
		labelPanel.add(Box.createRigidArea(new Dimension(20, 15)));
		labelPanel.add(path);
		labelPanel.add(Box.createRigidArea(new Dimension(20, 5)));
		labelPanel.add(SHORTESTPATH);


		JPanel buttonPanel = new JPanel();
		mainInfoPanel.add(labelPanel, BorderLayout.CENTER);
		mainInfoPanel.add(buttonPanel);
		mainInfoPanel.add(Box.createRigidArea(new Dimension(110, 280)));

		Panel1.add(mainInfoPanel);

		add(Panel1, BorderLayout.EAST);
	}


	/**
	 * Draw the circuit panel
	 * 
	 * @param originalCircuit
	 *            circuit board to draw
	 * @param showSolution
	 *            display the correct path
	 */
	private void drawgrid(CircuitBoard originalCircuit, boolean visible) {
		if (gridPanel != null) {
			remove(gridPanel);
		}

		gridPanel = new JPanel(new GridLayout(originalCircuit.numRows(), originalCircuit.numCols()));
		gridPanel.setBorder(BorderFactory.createTitledBorder("Circuit Tracer"));
		grid = new JLabel[originalCircuit.numRows()][originalCircuit.numCols()];
		char[][] board = originalCircuit.getBoard();
		ImageIcon imageIcon = new ImageIcon("Numbers-1-icon.png");
		ImageIcon openspace= new ImageIcon("Numbers-2-icon.png");
		ImageIcon walkspace= new ImageIcon("icons8-foot-step-48.png");

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {

				grid[i][j] = new JLabel(Character.toString(board[i][j]));
				if(Character.toString(board[i][j]).equals("1")) {
					grid[i][j].setText(null);
					grid[i][j].setIcon(imageIcon);
				}
				else if (Character.toString(board[i][j]).equals("2")) {
					grid[i][j].setText(null);
					grid[i][j].setIcon(openspace);
				}
				else if (Character.toString(board[i][j]).equals("T")) {
					grid[i][j].setText(null);
					grid[i][j].setIcon(walkspace);
				}

				grid[i][j].setBorder( BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED));
				grid[i][j].setHorizontalAlignment(SwingConstants.CENTER);

				if (visible) {
					if (board[i][j] == TRACE) {
						grid[i][j].setBorder(BorderFactory.createEtchedBorder(Color.GREEN, Color.CYAN) );
					}
				}
				gridPanel.add(grid[i][j]);
			}
		}
		// highlight start & finish position on grid
		grid[originalCircuit.getStartingPoint().x][originalCircuit.getStartingPoint().y].setBackground(Color.BLUE);
		grid[originalCircuit.getEndingPoint().x][originalCircuit.getEndingPoint().y].setBackground(Color.CYAN);
		add(gridPanel, BorderLayout.CENTER);
		revalidate();
	}


	/**
	 * The menu listener class to interact when you click on the item on the menu bar
	 * 
	 * @return the setup menu with Help
	 */
	private class MenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem source = (JMenuItem) e.getSource();
			if (source.getText().equals("Help")) {
				System.exit(0);
			} else {
				playSound("Ta Da-SoundBible.com-1884170640.wav"); 
				JOptionPane.showMessageDialog(gridPanel, "Hurray- V Nguyen finished her final project",
						"About This Circuit Tracer", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	/**
	 * The method playsound when you click on the help button on menu bar
	 * 
	 * 
	 */
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
	 * List listener class. Update grid view when a new solution is selected
	 * 
	 * @author Van Nguyen
	 *
	 */
	private class ListListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			int selectionIndex = solutionList.getSelectedIndex();
			drawgrid(bestPathSolutions.get(selectionIndex).getBoard(), true);
		}
	}
}