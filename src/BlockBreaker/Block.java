package BlockBreaker;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Block extends Rectangle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image pic;
	int dx=3;
	int dy=-3;
	boolean powerup=false;
	boolean destroyed=false;
	Rectangle left,right;
Block(int a,int b,int w,int h,String s){
	x=a;
	y=b;
	width=w;
	height=h;
	left =new Rectangle(a-1,b,1,h);
	right=new Rectangle(a+w+1,b,1,h);
	pic = Toolkit.getDefaultToolkit().getImage(s);
}
public void draw(Graphics g, Component c)
{
	if(BlockBreakerPanel.destroy>=32)
		g.drawString("Congratulations \n you have successfully completed the game", 80, 270);
	else if(!destroyed)
	g.drawImage(pic, x, y, width, height, c);
}
}