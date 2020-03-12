 /*
康瀚中
105403018
資管2A
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Painter2 extends JFrame
{	
	private final JPanel tools;//工具列的panel
	private final JLabel status;//狀態列的Label
	private final PaintPanel canvas;//畫布的PaintPanel
	private final BorderLayout blLayout;//BorderLayout的宣告
	//1.繪圖工具
	private final JLabel jlPaintTool;
	//2.筆刷
	private final JComboBox<PaintTool> jcbBrush;//PaintTools為enum，在JComboBox做泛型
	//3.筆刷大小
	private final JLabel jlBrushSize;
	private final String[] strBrushSize={"小","中","大"};
	private final JRadioButton[] jrdBrushSize; 
	private final ButtonGroup bgBrushSize;
	//4.填滿
	private final JCheckBox jcbFill;
	//5按鈕
	private final JButton[] jbToolGroup;
	
	private Color color;
	
	public Painter2()	
	{
		super("小畫家");
		setLayout(new BorderLayout());//設定Frame的Layout
		
		blLayout=new BorderLayout(); //實體化BorderLayout
		tools=new JPanel();//實體化工具列的panel
		tools.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tools.setLayout(new GridLayout(10,1));//設定工具列的layout方式
		//1
		jlPaintTool=new JLabel("[繪圖工具]");//實體化[繪圖工具]的label
		tools.add(jlPaintTool);//將[戶頹工具]加入工具列的panel
		
		//2
		jcbBrush=new JComboBox<PaintTool>(PaintTool.values());//將PaintTools中的值取出
		jcbBrush.addItemListener
		(
				new ItemListener()
				{
					@Override
					public void itemStateChanged(ItemEvent event)
					{
						
						if(event.getStateChange() == ItemEvent.SELECTED)
						{
							if(jcbBrush.getSelectedIndex() == 0 && jcbFill.isSelected() == true)
							{
								jcbFill.doClick();
							}
							canvas.setTool(jcbBrush.getSelectedIndex());
							//System.out.println(jcbBrush.getSelectedIndex());
							JOptionPane.showMessageDialog(null,String.format("你點選了:%s",jcbBrush.getSelectedObjects()));
						}
					}
				}
		);//將jcbBrush加上Listener
		tools.add(jcbBrush);//將jcbBrush加入工具列
		
		//3
		jlBrushSize=new JLabel("[筆刷大小]");//將[筆刷大小]的label實體化
		tools.add(jlBrushSize);//將[筆刷大小]加入工具列
		
		jrdBrushSize=new JRadioButton[3];//實體化筆刷大小JRadioButton的物件陣列
		
		for(int counter=0;counter<jrdBrushSize.length;counter++)//將筆刷大小JRadioButton逐一實體化富讓筆刷大小的字串陣列並且加上Listener
		{
			if(counter==0)
			{
				jrdBrushSize[counter]=new JRadioButton(strBrushSize[counter],true);
				jrdBrushSize[counter].addItemListener(new JRadioButtonListener(counter));
			}
			else
			{
				jrdBrushSize[counter]=new JRadioButton(strBrushSize[counter],false);
				jrdBrushSize[counter].addItemListener(new JRadioButtonListener(counter));
			}
		}
		
		bgBrushSize=new ButtonGroup();//將ButtonGroup實體化
		//將筆刷大小的JRadioButton加入ButtonGroup
		bgBrushSize.add(jrdBrushSize[0]);
		bgBrushSize.add(jrdBrushSize[1]);
		bgBrushSize.add(jrdBrushSize[2]);
		//將筆刷大小JRadioButton加入工具列
		tools.add(jrdBrushSize[0]);
		tools.add(jrdBrushSize[1]);
		tools.add(jrdBrushSize[2]);
		
		//4
		jcbFill=new JCheckBox("填滿");//將填滿的JCheckBox實體化
		jcbFill.addItemListener//將填滿加上Listener
		(
				new ItemListener()
				{

					@Override
					public void itemStateChanged(ItemEvent event)
					{
						if(canvas.getTool() == 0)
						{
							canvas.setFill(false);
							jcbFill.setSelected(false);
						}
						else
						{
							if(event.getStateChange()  == ItemEvent.SELECTED)
							{
								canvas.setFill(true);
								System.out.println("填滿了");
							}
							else
							{
								canvas.setFill(false);
								System.out.println("你取消了填滿");
							}
						}
					}
					
				}
		);
		tools.add(jcbFill);//將填滿加入工具列
		
		//5
		jbToolGroup=new JButton[3];//將按鈕陣列實體化
		//將各個按鈕實體化
		jbToolGroup[0]=new JButton("前景色");
		
		jbToolGroup[1]=new JButton("後景色");
		jbToolGroup[1].setBackground(Color.BLACK);//將"後景色"加上背景顏色
		jbToolGroup[2]=new JButton("清除畫面");
		
		
		//將各個按鈕加上listener
		jbToolGroup[0].addActionListener(new ButtonHandler(0));
		jbToolGroup[1].addActionListener(new ButtonHandler(1));
		jbToolGroup[2].addActionListener(new ButtonHandler(2));
		//將各個按鈕加入工具列
		tools.add(jbToolGroup[0]);
		tools.add(jbToolGroup[1]);
		tools.add(jbToolGroup[2]);
		add(tools,blLayout.WEST);//設定工具列的位置
		
		//畫布
		canvas=new PaintPanel();//在class PaintPanel 中做完畫布，所以在這裡直接實體化
		canvas.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		canvas.setBackground(Color.WHITE);
		canvas.addMouseListener(new MouseHandler());//將畫布加上listener
		canvas.addMouseMotionListener(new MouseMotionHandler());//將畫布加上motionListener
		add(canvas,blLayout.CENTER);//設定畫布的位置
		
		//status bar
		status=new JLabel("游標位置:(,)");//實體化狀態列
		add(status,blLayout.SOUTH);//設定狀態列的位置
		
	}
	private class MouseHandler extends MouseAdapter
	{
		@Override
		 public void mouseExited(MouseEvent event)
		 {
			status.setText("畫布區外");
		 }
	}
	private class MouseMotionHandler extends MouseMotionAdapter//MouseHandler實作MouseListener和MouseMotionListener
	{
		
		@Override
		public void mouseDragged(MouseEvent event) //滑鼠拖曳時，狀態列座標隨之改變
		{
			
			status.setText(String.format("游標位置:(%d,%d)",event.getX(),event.getY()));
		}

		@Override
		public void mouseMoved(MouseEvent event)//滑鼠移動時，狀態列座標隨之改變
		{
			
			status.setText(String.format("游標位置:(%d,%d)",event.getX(),event.getY()));
		}
		
	}
	private class JRadioButtonListener implements ItemListener//JRadioButton實作ItemListener
	{
		private final int index;
		public JRadioButtonListener(int index)//設定JRadioButton的建構子，設定傳入值為JRadioButton的陣列index
		{
			this.index=index;
		}
		@Override
		public void itemStateChanged(ItemEvent event)//利用index來找到相對應的字串陣列值放入JOptionPane
		{
			if(event.getStateChange() == ItemEvent.SELECTED)
			{
				JOptionPane.showMessageDialog(null,String.format("你點選了:%s",strBrushSize[index]));
				switch(index)
				{
					case 0:
					{
						canvas.setSize(1);
						break;
					}
					case 1:
					{
						canvas.setSize(10);
						break;
					}
					case 2:
					{
						canvas.setSize(30);
						break;
					}
				}
			}
		}
		
	}
	private class ButtonHandler implements ActionListener//ButtonHandler實作ActionListner
	{
		private final int index;
		public ButtonHandler(int index)
		{
			this.index=index;
		}
		@Override
		public void actionPerformed(ActionEvent event) 
		{
			JOptionPane.showMessageDialog(null,String.format("你點選了:%s",event.getActionCommand()));
			if(index==0)//設定筆刷顏色
			{
				color=JColorChooser.showDialog(jbToolGroup[index],"Choose a color",color);
				jbToolGroup[index].setBackground(color);
				canvas.setForeColor(color);
			}
			else if(index==1)//設定背影色
			{
				color=JColorChooser.showDialog(jbToolGroup[index],"Choose a color",color);
				jbToolGroup[index].setBackground(color);
				canvas.setBackground(color);
			}
			else if(index==2)//清空畫布，回復成白色畫布
			{
				canvas.clear();
				jbToolGroup[1].setBackground(Color.BLACK);
				canvas.setBackground(Color.WHITE);
			}
		}
		
	}
}