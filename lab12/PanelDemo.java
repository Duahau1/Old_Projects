import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelDemo {
	private JPanel basepanel;
	private Color[] currentcolor= {Color.GREEN,Color.YELLOW,Color.RED};
	private int count;
	private JButton button;
	public PanelDemo(){
		basepanel = new JPanel();
		basepanel.setPreferredSize(new Dimension(200,200));
		button= new JButton();
		basepanel.add(button);
		basepanel.setLayout(new GridLayout(1,1));
		count=0;
		button.setBackground(currentcolor[count]);
		ButtonListener b = new ButtonListener();
		button.addActionListener(b);
	}

	public JPanel getPanel() {
		return basepanel;
	}
	public class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			count++;
			if(count>= currentcolor.length) { 
				count=0;}
			button.setBackground(currentcolor[count]);
			}
		}


	

}
