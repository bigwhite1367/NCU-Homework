/*
康瀚中
105403018
資管2A
*/
import javax.swing.JFrame;

public class RunFishBowl 
{
	public static void main(String[] args)
	{
		JFrame fishBowlFrame = new FishBowlFrame();
		fishBowlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fishBowlFrame.setSize(800,800);
		fishBowlFrame.setVisible(true);
	}
}