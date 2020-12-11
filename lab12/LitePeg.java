import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class LitePeg {
	private final Color [] COLORS= {Color.BLACK,Color.RED,Color.GREEN,Color.YELLOW,Color.BLUE,Color.CYAN,Color.PINK,Color.ORANGE};
	private int ColorIndex;
	private JButton pegbutton;

	public LitePeg() {
		pegbutton=new JButton();
		ColorIndex=0;
		pegbutton.setBackground(COLORS[ColorIndex]);
		LitePegListener l= new LitePegListener();
		pegbutton.addActionListener(l);
	}
	public Color getColor() {
		return pegbutton.getBackground();
	}
	public void resetColor() {
		ColorIndex=0;
		pegbutton.setBackground(COLORS[ColorIndex]);
	}
	public void changeColor() {
		ColorIndex++;
		pegbutton.setBackground(COLORS[ColorIndex]);
	}
	public JButton getButton() {
		return pegbutton;
	}
public class LitePegListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ColorIndex++;
		if(ColorIndex>= COLORS.length) { 
			ColorIndex=0;}
		pegbutton.setBackground(COLORS[ColorIndex]);
		}
		
		
	}




}
