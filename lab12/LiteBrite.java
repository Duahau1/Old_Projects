import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Represents a LiteBrite game board GUI. 
 *
 * @author CS121 Instructors
 */
@SuppressWarnings("serial")
public class LiteBrite extends JPanel
{
	private LiteBriteBoard board;
	private JButton resetButton;
	
	/**
	 * Creates a new LiteBrite GUI with specified width and height.
	 * @param width The number of pegs in the horizontal axis.
	 * @param height The number of pegs in the vertical axis.
	 */
	public LiteBrite(int width, int height)
	{
		// Create new LiteBriteBoard with specified dimensions
		board = new LiteBriteBoard(width,height);
		
		// Create reset button and add ActionListener to it.
		resetButton = new JButton("Reset");
		resetButton.addActionListener(new ResetButtonListener());
		
		// Add sub-components to this main panel.
		this.add(board.getJPanel());
		this.add(resetButton);
	}
	
	/**
	 * The ActionListener for the button to reset the game.
	 */
	private class ResetButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			board.reset();
		}
	}
	
	/**
	 * Creates a JFrame and adds the main JPanel to the JFrame.
	 * @param args (unused)
	 */
	public static void main(String args[])
	{
		JFrame frame = new JFrame("Lite Brite");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new LiteBrite(20, 20));
		frame.pack();
		frame.setVisible(true);
	}
}
