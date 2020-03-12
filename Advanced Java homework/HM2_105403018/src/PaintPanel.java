/*
康瀚中
105403018
資管2A
 */
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PaintPanel extends JPanel 
{
	private int oldX,oldY,currentX,currentY;//起始座標和終點座標
	private Graphics2D g2d;
	private BufferedImage image;
	private Color forecolor = Color.BLACK;//預設筆刷顏色
	private int size,tools;
	private boolean fill;
	
	public PaintPanel()
	{
		image= new BufferedImage(2000,2000,BufferedImage.TYPE_INT_ARGB_PRE);
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionHandler());
		size = 1;
		tools = 0;
		fill = false;
		
	}
	@Override
	public void paintComponent(Graphics g)//將Graphics cast成Graphics2D
	{
		super.paintComponent(g);
		Graphics gg=image.createGraphics();
		g2d=(Graphics2D) gg;
		
		g2d.setPaint(forecolor);		//這樣筆刷才不會是透明的
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON );//修飾線條
		g2d.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));//設定端點形狀、筆刷大小
		
		g.drawImage(image,0,0,this);
	}
	public void setForeColor(Color forecolor)//讓Painter2能設定筆刷顏色
	{
		this.forecolor = forecolor;
	}
	public void setSize(int size)//讓Painter2設定筆刷大小
	{
		this.size = size;
	}
	public void setTool(int tools)//讓Painter2設定工具
	{
		this.tools = tools;
	}
	public void setFill(boolean fill)//讓Painter2設定填滿
	{
		this.fill = fill;
	}
	public int getTool()//讓Painter2取得工具
	{
		return tools;
	}
	public void clear()//清除畫布的功能
	{
		image = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_ARGB_PRE);
		repaint();
	}
	private class MouseHandler extends MouseAdapter//各式工具的起始值
	{
		@Override
		public void mousePressed(MouseEvent event)
		{
			switch(tools)
			{
				case 0:
				{
					oldX = event.getX();
					oldY = event.getY();
					g2d.setPaint(forecolor);
					g2d.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
					break;
				}
				case 1:
				{
					oldX = event.getX();
					oldY = event.getY();
					break;
				}
				case 2:
				{
					oldX = event.getX();
					oldY = event.getY();
					break;
				}
				case 3:
				{
					oldX = event.getX();
					oldY = event.getY();
					break;
				}
				case 4:
				{
					oldX = event.getX();
					oldY = event.getY();
					break;
				}
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent event)//各式工具的終值，並判斷筆刷大小、是否填滿等
		{
			switch(tools)
			{
				case 0:
				{
					break;
				}
				case 1:
				{
					currentX = event.getX();
					currentY = event.getY();
					g2d.setPaint(forecolor);
					if(fill)
					{
						g2d.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
						g2d.drawLine(oldX, oldY, currentX, currentY);
					}
					else
					{
						if(size == 1)
						{
							g2d.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,1.0f, new float[]{21f, 5f}, 0f));
							g2d.drawLine(oldX, oldY, currentX, currentY);
						}
						else if(size == 10)
						{
							g2d.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,1.0f, new float[]{21f, 15f}, 0f));
							g2d.drawLine(oldX, oldY, currentX, currentY);
						}
						else if(size == 30)
						{
							g2d.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER,1.0f, new float[]{21f, 50f}, 0f));
							g2d.drawLine(oldX, oldY, currentX, currentY);
						}
					}
					repaint();
					break;
				}
				case 2:
				{
					currentX = event.getX();
					currentY = event.getY();
					g2d.setPaint(forecolor);
					g2d.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
					if(fill)
					{
						g2d.fill(new Ellipse2D.Double(Math.min(oldX, currentX), Math.min(oldY, currentY), Math.abs(oldX - currentX), Math.abs(oldY - currentY)));
					}
					else
					{
						g2d.draw(new Ellipse2D.Double(Math.min(oldX, currentX), Math.min(oldY, currentY), Math.abs(oldX - currentX), Math.abs(oldY - currentY)));
					}
					repaint();
					break;
				}
				case 3:
				{
					currentX = event.getX();
					currentY = event.getY();
					g2d.setPaint(forecolor);
					g2d.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
					if(fill)
					{
						g2d.fill(new Rectangle2D.Double(Math.min(oldX, currentX), Math.min(oldY, currentY), Math.abs(oldX - currentX), Math.abs(oldY - currentY)));
					}
					else
					{
						g2d.draw(new Rectangle2D.Double(Math.min(oldX, currentX), Math.min(oldY, currentY), Math.abs(oldX - currentX), Math.abs(oldY - currentY)));
					}
					repaint();
					break;
				}
				case 4:
				{
					currentX = event.getX();
					currentY = event.getY();
					g2d.setPaint(forecolor);
					g2d.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
					if(fill)
					{
						g2d.fill(new RoundRectangle2D.Double(Math.min(oldX, currentX), Math.min(oldY, currentY), Math.abs(oldX - currentX), Math.abs(oldY - currentY),50,50));
					}
					else
					{
						g2d.draw(new RoundRectangle2D.Double(Math.min(oldX, currentX), Math.min(oldY, currentY), Math.abs(oldX - currentX), Math.abs(oldY - currentY),50,50));
					}
					repaint();
					break;
				}
				
			}
		}
	}
	private class MouseMotionHandler extends MouseMotionAdapter//拖曳過程中的座標更新
	{
		@Override
		public void mouseDragged(MouseEvent event)
		{
			
			switch(tools)
			{
				case 0:
				{
					currentX = event.getX();
					currentY = event.getY();
					g2d.drawLine(oldX, oldY, currentX, currentY);
					repaint();
					oldX = currentX;
					oldY = currentY;
					break;
				}
				case 1:
				{
					break;
				}
				case 2:
				{
					break;
				}
				case 3:
				{
					break;
				}
				case 4:
				{
					break;
				}
			}
		}
	}
}
