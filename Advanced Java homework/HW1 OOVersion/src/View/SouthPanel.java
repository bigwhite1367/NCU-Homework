package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SouthPanel extends JPanel 
{
	private static SouthPanel instance = null;
	private JLabel label;
	private SouthPanel()
	{
		setBackground(Color.BLACK);
		label = new JLabel("指標位置:(,)");
		label.setForeground(Color.WHITE);
		add(label);
	}
	public static SouthPanel getInstance()
	{
		if(instance == null)
		{
			return instance = new SouthPanel();
		}
		
		return instance;
		
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
}
