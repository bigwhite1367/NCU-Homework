/*
康瀚中
105403018
資管2A
*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FishBowlFrame extends JFrame
{
	
	private JPanel buttonPanel;
	private JButton addFish;
	private JButton addTurtle;
	private JButton deleteSelected;
	private JButton deleteAll;
	public static JLabel status;
	
	private FishBowl fishBowl;
	
	private int flag = 0;
	
	
	public FishBowlFrame()
	{
		super("Fish Bowl");
		setLayout(new BorderLayout());
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3,2));
		add(buttonPanel, BorderLayout.NORTH);
		
		addFish = new JButton("新增魚");
		addFish.addActionListener(new ButtonHandler(1));
		addFish.addMouseListener(new MouseHandler(1));
		buttonPanel.add(addFish);
		
		deleteSelected = new JButton("移除選取");
		deleteSelected.addActionListener(new ButtonHandler(3));
		deleteSelected.addMouseListener(new MouseHandler(3));
		buttonPanel.add(deleteSelected);
		
		addTurtle = new JButton("新增烏龜");
		addTurtle.addActionListener(new ButtonHandler(2));
		addTurtle.addMouseListener(new MouseHandler(2));
		buttonPanel.add(addTurtle);
		
		deleteAll = new JButton("移除全部");
		deleteAll.addActionListener(new ButtonHandler(4));
		deleteAll.addMouseListener(new MouseHandler(4));
		buttonPanel.add(deleteAll);
		
		status = new JLabel();
		status.setText(String.format("目前功能:          魚數量: 0  烏龜數量:  0"));
		status.setForeground(Color.BLUE);
		buttonPanel.add(status);
		
			
		
		fishBowl = new FishBowl();
		add(fishBowl, BorderLayout.CENTER);
		
	}
	
	private class MouseHandler extends MouseAdapter
	{	
		int flag;
		public MouseHandler(int flag)
		{
			this.flag = flag;
		}
		@Override
		public void mousePressed(MouseEvent e)
		{
			fishBowl.setFlag(flag);
			if(flag == 1)
			{
				status.setText(String.format("目前功能:%s 魚數量: %d 烏龜數量:  %d", addFish.getText(), fishBowl.getFishAmount(), fishBowl.getTurtleAmount()));
			}
			else if(flag == 2)
			{
				status.setText(String.format("目前功能:%s 魚數量: %d 烏龜數量:  %d", addTurtle.getText(), fishBowl.getFishAmount(), fishBowl.getTurtleAmount()));
			}
			else if(flag == 3)
			{
				status.setText(String.format("目前功能:%s 魚數量: %d 烏龜數量:  %d", deleteSelected.getText(), fishBowl.getFishAmount(), fishBowl.getTurtleAmount()));
			}
			else if(flag == 4)
			{
				status.setText(String.format("目前功能:%s 魚數量: %d烏龜數量:  %d", deleteAll.getText(), fishBowl.getFishAmount(), fishBowl.getTurtleAmount()));
			}
		}
	}
	private class ButtonHandler implements ActionListener
	{
		int flag;
		public ButtonHandler(int flag)
		{
			this.flag = flag;
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			fishBowl.setFlag(flag);
			if(flag == 1)
			{
				status.setText(String.format("目前功能:%s 魚數量: %d 烏龜數量:  %d", addFish.getText(), fishBowl.getFishAmount(), fishBowl.getTurtleAmount()));
			}
			else if(flag == 2)
			{
				status.setText(String.format("目前功能:%s 魚數量: %d 烏龜數量:  %d", addTurtle.getText(), fishBowl.getFishAmount(), fishBowl.getTurtleAmount()/2));
			}
			else if(flag == 3)
			{
				fishBowl.clearSelected();
			}
			else if(flag == 4)
			{
				fishBowl.clear();
				status.setText(String.format("目前功能:%s 魚數量: %d 烏龜數量:  %d", deleteAll.getText(), fishBowl.getFishAmount(), fishBowl.getTurtleAmount()));
			}
		}
		
	}
}