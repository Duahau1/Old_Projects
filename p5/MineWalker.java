import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;





	@SuppressWarnings("serial")
	public class MineWalker extends JPanel{
		private MineWalkerPanel board;
		private JButton newgamebutt;
		public MineWalker(int width, int height)
		{
			// Create new LiteBriteBoard with specified dimensions
			
			
			board = new MineWalkerPanel(width,height);
		
						
			// Create reset button and add ActionListener to it.
			newgamebutt = new JButton("New Game");
			newgamebutt.addActionListener(new ResetButtonListener());
			
			// Add sub-components to this main panel.
			
			this.add(newgamebutt,BorderLayout.SOUTH);
			
			this.add(board.getPanel());
			
		
		}
	
		private class ResetButtonListener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				board.reset();
				board.mines();
				
			}
		}
		
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame= new JFrame ("Mine Walker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		MineWalker mn= new MineWalker(20,20);
		
		frame.getContentPane().add(mn);
		

		
		
		frame.pack();
		frame.setVisible(true);
		
	}




	

}
