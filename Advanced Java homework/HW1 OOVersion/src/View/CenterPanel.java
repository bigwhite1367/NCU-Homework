package View;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CenterPanel extends JPanel
{
	private static CenterPanel instance = null;
	
	
	private CenterPanel()
	{
		setBackground(Color.WHITE);
	}
	public static CenterPanel getInstance()
	{
		if(instance == null)
		{
			return instance = new CenterPanel();
		}
		
		return instance;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
}
