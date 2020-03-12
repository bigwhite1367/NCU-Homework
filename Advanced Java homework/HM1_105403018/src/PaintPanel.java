/*
康瀚中
105403018
資管2A
 */
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class PaintPanel extends JPanel 
{
	private final ArrayList<Point> points =new ArrayList<Point>();
	
	public PaintPanel()
	{
		addMouseMotionListener
		(
			new MouseMotionAdapter()
			{
				@Override
				public void mouseDragged(MouseEvent event)
				{
					points.add(event.getPoint());
					repaint();//repaint JFrame
				}
			}
		);
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(Point point:points)
		{
			g.fillOval(point.x,point.y ,4,4);
		}
	}

}