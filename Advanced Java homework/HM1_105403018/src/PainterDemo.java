/*
康瀚中
105403018
資管2A
 */
import javax.swing.JFrame;
public class PainterDemo
{
	public static void main(String[] args)
	{
		Painter painter=new Painter();
		painter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		painter.setSize(500,500);
		painter.setVisible(true);
	}
}