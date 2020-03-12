/*
康瀚中
105403018
資管2A
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.security.SecureRandom;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Fish extends JLabel implements Animal, Runnable, MouseListener
{
	private boolean dirFlag = true, updownFlag = false, selected = false;
	private int x,y,r,r1 = 0,index;//index為判斷自己在FishList裡的index
	private Icon left;
	private Icon right;
	private URL[][] imageURL = new URL[2][9];
	private Icon[][] img = new ImageIcon[2][9];
	private Timer t1;
	private SecureRandom rand = new SecureRandom();
	
	public Fish(int x,int y)
	{
		this.x = x-100;
		this.y = y-100;
		r = rand.nextInt(2);
		if(r == 1)
		{
			this.dirFlag = false;
		}
		imageURL[0][0] = Fish.class.getResource("1.png");
		imageURL[1][0] = Fish.class.getResource("2.png");
		imageURL[0][1] = Fish.class.getResource("3.png");
		imageURL[1][1] = Fish.class.getResource("4.png");
		imageURL[0][2] = Fish.class.getResource("5.png");
		imageURL[1][2] = Fish.class.getResource("6.png");
		imageURL[0][3] = Fish.class.getResource("7.png");
		imageURL[1][3] = Fish.class.getResource("8.png");
		imageURL[0][4] = Fish.class.getResource("9.png");
		imageURL[1][4] = Fish.class.getResource("10.png");
		imageURL[0][5] = Fish.class.getResource("11.png");
		imageURL[1][5] = Fish.class.getResource("12.png");
		imageURL[0][6] = Fish.class.getResource("13.png");
		imageURL[1][6] = Fish.class.getResource("14.png");
		imageURL[0][7] = Fish.class.getResource("15.png");
		imageURL[1][7] = Fish.class.getResource("16.png");
		imageURL[0][8] = Fish.class.getResource("17.png");
		imageURL[1][8] = Fish.class.getResource("18.png");
		for(int i = 0; i < 2; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				img[i][j] = new ImageIcon(imageURL[i][j]);
			}
		}
		this.setIcon(img[r][r1 = rand.nextInt(9)]);//會出現隨機魚種 大小
		this.setBounds(x, y, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());     
		addMouseListener(this);
	}
	
	public boolean isSelected()
	{
		return selected;
	}
	
	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}
	
	@Override
	public void run()
	{
		t1 = new Timer(rand.nextInt(100)+100, new ActionListener()
		{
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(rand.nextInt(10)==1)
                {//隨機轉向
                    if(r==1)
                    {
                        Fish.this.dirFlag = !Fish.this.dirFlag;
                        r=0;
                        Fish.this.setIcon(img[r][r1]);
                    }
                    else
                    {
                        Fish.this.dirFlag = !Fish.this.dirFlag;
                        r = 1;
                        Fish.this.setIcon(img[r][r1]);
                    }
                }
                if(Fish.this.dirFlag)
                {//用directFlag決定方向(左右)
                    if((x-10) > 0)
                    {
                        x-=10;
                    }
                    else
                    {
                        Fish.this.dirFlag = !Fish.this.dirFlag;
                        r = 1;
                        Fish.this.setIcon(img[r][r1]);
                        x+=10;
                    }
                    
                }
                else
                {
                    if((x+Fish.this.getIcon().getIconWidth()+20) < 800)
                    {
                        x+=10;
                    }
                    else
                    {
                        Fish.this.dirFlag = !Fish.this.dirFlag;
                        r=0;
                        Fish.this.setIcon(img[r][r1]);
                        x-=10;
                    }
                   
                }
                if(Fish.this.updownFlag)
                {//用updownFlag決定方向(上下)
                    if(rand.nextInt(1)==0)
                    {
                        if((y-10) > 0)
                        {
                            y-=10;
                        }
                        else
                        {
                            Fish.this.updownFlag = !Fish.this.updownFlag;
                            y+=10;
                        }
                    }
                    
                }
                else
                {
                    if(rand.nextInt(1)==0)
                    {
                        if((y+10+Fish.this.getIcon().getIconWidth()) < 720)
                        {
                            y+=10;
                        }
                        else
                        {
                            Fish.this.updownFlag = !Fish.this.updownFlag;
                            y-=10;
                        }
                    }
                }
                 Fish.this.setLocation(x, y);
                        
            }
        
        });
        t1.start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}