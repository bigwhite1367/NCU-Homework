 /*
康瀚中
105403018
資管2A
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

public class IntegerFactorization
{
	public static void main(String[] args)
	{
		int naturalNum = 0;
		String userInput;
		while(true)
		{
			userInput = JOptionPane.showInputDialog(null,"請輸入正整數");
			if(userInput == null)
			{
				break;
			}
			try
			{
				naturalNum = Integer.parseInt(userInput);
			}
			catch(NumberFormatException numberFormatException)
			{
				JOptionPane.showMessageDialog(null, "請輸入正整數!");
				continue;
			}
			
			if (naturalNum == 1)
			{
				JOptionPane.showMessageDialog(null,"1不是質數，也不會有質因數");
				continue;
			}
			if(naturalNum >0)
			{
				break;
			}
			
			else
			{
				JOptionPane.showMessageDialog(null, "請輸入正整數!");
				continue;
			}
			
		}
		
		//待續(請先念iterator、HashSet)
		factorization(naturalNum);
	}
	private static void factorization(int naturalNum)
	{
		Set<Integer> hashSet = new LinkedHashSet<Integer>();
		int originNum = naturalNum;
		int start = 2;
		while( naturalNum > 1 )
		{
			while( naturalNum%start == 0 )
			{
				hashSet.add(start);
				naturalNum = naturalNum/start;
			}
			start ++;
		}
		Iterator<Integer> iterator = hashSet.iterator();
		if(hashSet.contains(originNum))
		{
			JOptionPane.showMessageDialog(null, String.format("%d是質數!",originNum));
		}
		else
		{
			while(iterator.hasNext())
			{
				JOptionPane.showMessageDialog(null,String.format("%d的質因數包含 : %d",originNum,iterator.next() ));
			}
		}
	}
}