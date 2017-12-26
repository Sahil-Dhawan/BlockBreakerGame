package BlockBreaker;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class BlockBreakerPanel extends JPanel implements KeyListener{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
Block paddle;
Thread thread;
Animate animate;
static int destroy=0;
static int ballCount=0;
int size=25;
	ArrayList<Block> block=new ArrayList<Block>();
	ArrayList<Block> ball=new ArrayList<Block>();
	ArrayList<Block> powerup=new ArrayList<Block>();
BlockBreakerPanel(){
	destroy=0;
	ballCount=0;
	paddle=new Block(175,480,150,25,"images/paddle.png");
	for(int i=0;i<8;i++) {
		block.add(new Block((i*60+2),0,60,25,"images/blue.png"));
	}
	for(int i=0;i<8;i++) {
		block.add(new Block((i*60+2),25,60,25,"images/red.png"));
	}
	for(int i=0;i<8;i++) {
		block.add(new Block((i*60+2),50,60,25,"images/green.png"));
	}
	for(int i=0;i<8;i++) {
		block.add(new Block((i*60+2),75,60,25,"images/yellow.png"));
		
	}
	Random random=new Random();
	block.get(random.nextInt(32)).powerup=true;
	block.get(random.nextInt(32)).powerup=true;
	block.get(random.nextInt(32)).powerup=true;
	block.get(random.nextInt(32)).powerup=true;
	block.get(random.nextInt(32)).powerup=true;
	block.get(random.nextInt(32)).powerup=true;
	block.get(random.nextInt(32)).powerup=true;
	ball.add(new Block(237,437,25,25,"images/ball.png"));ballCount++;
	addKeyListener(this);
	setFocusable(true);

}
public void paintComponent(Graphics g)
{
	super.paintComponent(g);

	for (Block b: block)
		b.draw(g, this);
	for (Block b: ball)
		b.draw(g, this);
	for (Block b: powerup)
		b.draw(g, this);
	paddle.draw(g, this);
}
public void update() {
	
	for(Block p: powerup) {
		p.y+=1;
		if(p.intersects(paddle)&&!p.destroyed) {
			p.destroyed=true;
			ball.add(new Block(paddle.x+75,437,25,25,"images/ball.png"));
			ball.add(new Block(paddle.x+75,437,25,25,"images/ball.png"));
			ball.add(new Block(paddle.x+75,437,25,25,"images/ball.png"));
			ballCount+=3;
			
		}
	}
	for(Block b: ball) {
		b.x+=b.dx;
		
		if(b.x>(getWidth()-size)&&b.dx>0||b.x<0)
			b.dx*=-1;
		if(b.y<0||b.intersects(paddle))
			b.dy*=-1;
		
		if((b.y+=b.dy)>=600)ballCount--;
		for(Block ba :block) {
			if((ba.left.intersects(b)||ba.right.intersects(b))&&!ba.destroyed)
			{
				b.dx*=-1;
				ba.destroyed=true;
				destroy++;
				if(ba.powerup)
					powerup.add(new Block(ba.x,ba.y,25,19,"images/extra.png"));
				
			}
			else if(b.intersects(ba)&&!ba.destroyed)
			{
				ba.destroyed=true;
				destroy++;
				b.dy*=-1;
				if(ba.powerup)
					powerup.add(new Block(ba.x,ba.y,25,19,"images/extra.png"));
			}
			
		}
	}
	repaint();
}

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	if(e.getKeyCode()==KeyEvent.VK_ENTER) {
		if(destroy>=32&&thread!=null)
		{
			
			System.exit(10);
		}
		
	else {
		animate=new Animate(this);
		thread=new Thread(animate);
		thread.start();
	}
	}
	if(e.getKeyCode()==KeyEvent.VK_LEFT&&paddle.x>0) {
		paddle.x-=25;
	}
if(e.getKeyCode()==KeyEvent.VK_RIGHT&&paddle.x<(getWidth()-paddle.width)) {
	paddle.x+=25;
	}
}
@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

}
