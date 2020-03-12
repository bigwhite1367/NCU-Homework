/*
康瀚中
105403018
資管2A
*/
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.SecureRandom;
import java.util.ArrayList;

import javax.swing.JPanel;

public class FishBowl extends JPanel 
{
	private ArrayList<Fish> FishList = new ArrayList<Fish>(); //存放魚
	private ArrayList<Turtle> TurtleList = new ArrayList<Turtle>(); //存放烏龜
	private ArrayList<Thread> FThreadList = new ArrayList<Thread>(); //存放魚的執行緒
	private ArrayList<Thread> TThreadList = new ArrayList<Thread>(); //存放烏龜執行緒
	private ArrayList<Integer> SelectedFishList = new ArrayList<Integer>(); //存放選取魚
	private ArrayList<Integer> SelectedTurtleList = new ArrayList<Integer>(); //存放選取烏龜
	private SecureRandom rand = new SecureRandom();
	private int fishAmount = 0;
	private int turtleAmount = 0;
	private int flag = 0;
	
	public FishBowl()
	{
		this.setBackground(Color.cyan);
		addMouseListener(new MouseHandler());
	}
	
	public void setFlag(int flag)
	{
		this.flag = flag;
	}
	
	public int getFishAmount()
	{
		return fishAmount;
	}

	public int getTurtleAmount()
	{
		return turtleAmount;
	}
	
	public void clear()
	{
		removeAll();
		repaint();
		for(int i = 0; i < FThreadList.size(); i++)
		{
			FThreadList.get(i).interrupt();
		}
		for(int i = 0; i < TThreadList.size(); i++)
		{
			TThreadList.get(i).interrupt();
		}
		FishList.clear();
		TurtleList.clear();
		FThreadList.clear();
		TThreadList.clear();
		fishAmount = 0;
		turtleAmount = 0;
		FishBowlFrame.status.setText(String.format("目前功能:%s 魚數量: %d 烏龜數量:  %d", "移除全部", fishAmount/2, turtleAmount/2));
	}
	
	public void clearSelected()
	{
		for(int i = 0; i < SelectedFishList.size(); i++)
		{
			FThreadList.get(SelectedFishList.get(i)).interrupt();
			FThreadList.remove(SelectedFishList.get(i));
			remove(FishList.get(SelectedFishList.get(i)));
			FishList.remove(SelectedFishList.get(i));
		}
		fishAmount -= SelectedFishList.size();
		System.out.println(SelectedFishList.size());
		SelectedFishList.clear();
		System.out.println(SelectedFishList.size());
		for(int i = 0; i < SelectedTurtleList.size(); i++)
		{
			TThreadList.get(SelectedTurtleList.get(i)).interrupt();
			TThreadList.remove(SelectedTurtleList.get(i));
			remove(TurtleList.get(SelectedTurtleList.get(i)));
			TurtleList.remove(SelectedTurtleList.get(i));
		}
		turtleAmount -= SelectedTurtleList.size();
		System.out.println(SelectedTurtleList.size());
		SelectedTurtleList.clear();
		System.out.println(SelectedTurtleList.size());
		repaint();
		FishBowlFrame.status.setText(String.format("目前功能:%s 魚數量: %d 烏龜數量:  %d", "移除選取", fishAmount, turtleAmount));
	}

	
	private class MouseHandler extends MouseAdapter
	{
		@Override
		public void mousePressed(MouseEvent e)
		{	
			if( flag == 1) //新增魚
			{
					fishAmount++;
					System.out.println(fishAmount);
					System.out.println(FishList.size());
					FishList.add(new Fish(e.getX(),e.getY()));
					FishList.get(FishList.size()-1).addMouseListener(
																		new MouseAdapter()
																		{
																			@Override
																			public void mousePressed(MouseEvent e) 
																			{
																				if(!FishList.get(FishList.indexOf(e.getSource())).isSelected())
																				{
																					SelectedFishList.add(FishList.indexOf(e.getSource()));
																					System.out.println(SelectedFishList);
																					FishList.get(FishList.indexOf(e.getSource())).setSelected(true);
																				}
																			}

																		}
													
																	);
					add(FishList.get(FishList.size()-1));
					FThreadList.add(new Thread(FishList.get(FishList.size()-1)));
					FThreadList.get(FishList.size()-1).start();
					
					FishBowlFrame.status.setText(String.format("目前功能:%s 魚數量: %d 烏龜數量:  %d", "新增魚", fishAmount, turtleAmount));
			}
			
			else if(flag == 2) //新增烏龜
			{
				turtleAmount++;
				System.out.println(TurtleList.size());
				TurtleList.add(new Turtle(e.getX(), e.getY()));
				TurtleList.get(TurtleList.size()-1).addMouseListener(
																		new MouseAdapter()
																		{
																			@Override
																			public void mousePressed(MouseEvent e)
																			{
																				if(!TurtleList.get(TurtleList.indexOf(e.getSource())).isSelected())
																				{
																					SelectedTurtleList.add(TurtleList.indexOf(e.getSource()));
																					TurtleList.get(TurtleList.indexOf(e.getSource())).setSelected(true);
																				}
																			}
																		}
																	);
				add(TurtleList.get(TurtleList.size()-1));
				TThreadList.add(new Thread(TurtleList.get(TurtleList.size()-1)));
				TThreadList.get(TurtleList.size()-1).start();
				repaint();
				FishBowlFrame.status.setText(String.format("目前功能:%s 魚數量: %d 烏龜數量:  %d", "新增烏龜", fishAmount, turtleAmount));
			}
			else if(flag == 3)
			{
				
			}
			else if(flag == 4) //刪除所有
			{
				
			}
		}
		
	
	}
}