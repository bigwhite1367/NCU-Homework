package View;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Frame extends JFrame 
{
	public Frame(String frameName)
	{
		super(frameName);
		
		add(NorthPanel.getInstance(),BorderLayout.NORTH);
		add(CenterPanel.getInstance(),BorderLayout.CENTER);
		add(SouthPanel.getInstance(),BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,600);
		setVisible(true);
		
		
	}
}
