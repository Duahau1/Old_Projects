import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TrafficLight {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame frame= new JFrame ("Traffic Light");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PanelDemo p= new PanelDemo();
		frame.getContentPane().add(p.getPanel());
		
		frame.pack();
		frame.setVisible(true);
		
		 
	}

}
