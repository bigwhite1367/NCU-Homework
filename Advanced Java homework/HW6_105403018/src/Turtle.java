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

public class Turtle extends JLabel implements Animal ,Runnable, MouseListener
{
	private int x, y, r1 = 0, r, index;
	private Icon[][] img = new ImageIcon[2][3];
	private boolean dirFlag = true, stopFlag = false, selected = false;
	private Icon left;
	private Icon right;
	private URL[][] imageURL = new URL[2][3];
	private Timer t1;
	private SecureRandom rand = new SecureRandom();


	public Turtle(int x,int y)
	{
		this.x = x;
		this.y = y;
		imageURL[0][0] = Turtle.class.getResource("w2.png");
		imageURL[1][0] = Turtle.class.getResource("w.png");
		imageURL[0][1] = Turtle.class.getResource("w2a.png");
		imageURL[1][1] = Turtle.class.getResource("wa.png");
		imageURL[0][2] = Turtle.class.getResource("w2b.png");
		imageURL[1][2] = Turtle.class.getResource("wb.png");
		
		for(int i = 0; i < 2 ; i++)
		{
			for(int j = 0; j < 3 ; j++)
			{
				img[i][j] = new ImageIcon(imageURL[i][j]);
			}
		}
		r = rand.nextInt(2);
        if (r == 1) {
            this.dirFlag = false;
        }
        this.setIcon(img[r][r1 = rand.nextInt(3)]);
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
	    public void run() {
	        t1 = new Timer(rand.nextInt(100) + 100, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (y == 580) {
	                    if (rand.nextInt(8) == 1) {
	                        if (r == 1) {
	                            Turtle.this.dirFlag = !Turtle.this.dirFlag;
	                            r = 0;
	                            Turtle.this.setIcon(img[r][r1]);
	                        } else {
	                            Turtle.this.dirFlag = !Turtle.this.dirFlag;
	                            r = 1;
	                            Turtle.this.setIcon(img[r][r1]);
	                        }
	                    }
	                    if (Turtle.this.dirFlag) {
	                        if ((x - 10) > 0) {
	                            x -= 10;
	                        } else {
	                            Turtle.this.dirFlag = !Turtle.this.dirFlag;
	                            r = 1;
	                            Turtle.this.setIcon(img[r][r1]);
	                            x += 10;
	                        }
	                        Turtle.this.setLocation(x, y);
	                    } else {
	                        if ((x + Turtle.this.getIcon().getIconWidth() + 20) < 800) {
	                            x += 10;
	                        } else {
	                            Turtle.this.dirFlag = !Turtle.this.dirFlag;
	                            r = 0;
	                            Turtle.this.setIcon(img[r][r1]);
	                            x -= 10;
	                        }
	                        Turtle.this.setLocation(x, y);
	                    }
	                } else {
	                    y += 10;
	                    if (y > 580) {
	                        y = 580;
	                    }
	                    Turtle.this.setLocation(x, y);
	                }
	            }

	        });
	        t1.start();
	    }

	    public Timer getTimer() {
	        return t1;
	    }

	    public void setIndex(int i) {
	        this.index = i;
	    }

	    public int getIndex() {
	        return index;
	    }

	    @Override
	    public void mouseClicked(MouseEvent e) {
	        System.out.println("13");
	    }

	    @Override
	    public void mousePressed(MouseEvent e) {
	    }

	    @Override
	    public void mouseReleased(MouseEvent e) {
	    }

	    @Override
	    public void mouseEntered(MouseEvent e) {
	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
	    }


}
