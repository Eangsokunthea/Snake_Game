import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePlay extends JPanel implements KeyListener, ActionListener{
	
	private int[] snackeXlength = new int[750];
	private int[] snackeYlength = new int[750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon faceright;
	private ImageIcon facedown;
	private ImageIcon faceup;
	private ImageIcon faceleft;
	
	private int lengthofsnacke = 3;
	
	private Timer timer ;
	private int delay = 100;
	private ImageIcon snackeimage;
	private ImageIcon titleImage;
	private ImageIcon bodysnack;
	
	private int score = 0;
	private int moves = 0;
	
	private int [] enemyxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,
			350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,
			775,800,825,850 };
	private int [] enemyypos = { 75,100,125,150,175,200,225,250,275,300,325,
			350,375,400,425,450,475,500,525,550,575,600,625 };
	
	private ImageIcon enemyimage;
	
	private Random random = new Random();
	
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	
	
	
	public GamePlay()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
	}
	public void paint(Graphics q) 
	{
		if(moves == 0)
		{
			snackeXlength[2] = 50;
			snackeXlength[1] = 75;
			snackeXlength[0] = 100;
			
			snackeYlength[2] = 100;
			snackeYlength[1] = 100;
			snackeYlength[0] = 100;
		}
		//draw title image border
		q.setColor(Color.white);
		q.drawRect(24, 10, 851, 55);
		
		//draw the title image
		titleImage = new ImageIcon("snacketitle.jpg");
		titleImage.paintIcon(this, q, 25, 11);
		
		//draw border for gameplay
		q.setColor(Color.white);
		q.drawRect(24, 74, 851, 577);
		
		//draw background for the gameplay
		q.setColor(Color.black);
		q.fillRect(25, 75, 850, 575);
		
		//draw scores
		q.setColor(Color.white);
		q.setFont(new Font("arial", Font.PLAIN, 14));
		q.drawString("Scores: "+score, 780, 30);
		
		//draw Length
		q.setColor(Color.white);
		q.setFont(new Font("arial", Font.PLAIN, 14));
		q.drawString("Lenght: "+lengthofsnacke, 780, 50);
		
		
		
		
		faceright = new ImageIcon("faceright.png");
		faceright.paintIcon(this, q, snackeXlength[0], snackeYlength[0]);
		
		for(int a = 0; a < lengthofsnacke; a++)
		{
			if(a == 0 && right)
			{
				faceright = new ImageIcon("faceright.png");
				faceright.paintIcon(this, q, snackeXlength[a], snackeYlength[a]);
			}
			if(a == 0 && left)
			{
				faceleft = new ImageIcon("faceleft.png");
				faceleft.paintIcon(this, q, snackeXlength[a], snackeYlength[a]);		
			}
			if(a == 0 && down)
			{
				facedown = new ImageIcon("facedown.png");
				facedown.paintIcon(this, q, snackeXlength[a], snackeYlength[a]);
			}
			if(a == 0 && up)
			{
				faceup = new ImageIcon("facedown.png");
				faceup.paintIcon(this, q, snackeXlength[a], snackeYlength[a]);
			}
			if(a != 0)
			{
				bodysnack = new ImageIcon("bodysnack.png");
				bodysnack.paintIcon(this, q, snackeXlength[a], snackeYlength[a]);
			}
			
		}
		
		enemyimage = new ImageIcon("enemy.png");
		
		if((enemyxpos[xpos] == snackeXlength[0] && enemyypos[ypos] == snackeYlength[0]))
		{
			score++;
			lengthofsnacke++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
		}
		
		enemyimage.paintIcon(this, q, enemyxpos[xpos], enemyypos[ypos]);
		
		for(int b = 1; b < lengthofsnacke; b++)
		{
			if(snackeXlength[b] == snackeXlength[0] && snackeYlength[b] == snackeYlength[0])
				{
					right = false;
					left = false;
					up = false;
					down = false;
					
					q.setColor(Color.white);
					q.setFont(new Font("arial", Font.BOLD,50));
					q.drawString("Game Over", 300, 300);
					
					q.setColor(Color.yellow);
					q.setFont(new Font("arial", Font.BOLD,50));
					q.drawString("Space to RESTART", 350, 340);
				}
		}	
		
		
		q.dispose();
		
	}	
	@Override
	public void actionPerformed(ActionEvent e) {
		//todo autu-generated method stub
		
		timer.start();
		if(right)
		{
			for(int r = lengthofsnacke - 1; r >= 0; r--)
			{
				snackeYlength[r+1] = snackeYlength[r];
			}
			for(int r = lengthofsnacke; r >= 0; r--)
			{
				if(r == 0)
				{
					snackeXlength[r] = snackeXlength[r] + 25;
				}
				else
				{
					snackeXlength[r] = snackeXlength[r-1];
				}
				if(snackeXlength[r] > 850)
				{
					snackeXlength[r] = 25;
				}
			}
			
			repaint();
		}
		if(left)
		{
			for(int r = lengthofsnacke - 1; r >= 0; r--)
			{
				snackeYlength[r+1] = snackeYlength[r];
			}
			for(int r = lengthofsnacke; r >= 0; r--)
			{
				if(r == 0)
				{
					snackeXlength[r] = snackeXlength[r] - 25;
				}
				else
				{
					snackeXlength[r] = snackeXlength[r-1];
				}
				if(snackeXlength[r] <25)
				{
					snackeXlength[r] = 850;
				}
			}
			
			repaint();
			
		}
		if(up)
		{

			for(int r = lengthofsnacke - 1; r >= 0; r--)
			{
				snackeXlength[r+1] = snackeXlength[r];
			}
			for(int r = lengthofsnacke; r >= 0; r--)
			{
				if(r == 0)
				{
					snackeYlength[r] = snackeYlength[r] + 25;
				}
				else
				{
					snackeYlength[r] = snackeYlength[r-1];
				}
				if(snackeYlength[r] < 75)
				{
					snackeYlength[r] = 625;
				}
			}
			
			repaint();
			
		}
		if(down)
		{
			for(int r = lengthofsnacke - 1; r >= 0; r--)
			{
				snackeXlength[r+1] = snackeXlength[r];
			}
			for(int r = lengthofsnacke; r >= 0; r--)
			{
				if(r == 0)
				{
					snackeYlength[r] = snackeYlength[r] - 25;
				}
				else
				{
					snackeYlength[r] = snackeYlength[r-1];
				}
				if(snackeYlength[r] > 625)
				{
					snackeYlength[r] = 75;
				}
			}
			
			repaint();
			
		}
			
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		//todo autu-generated method stub
			
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		//todo autu-generated method stub
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			moves = 0;
			score = 0;
			lengthofsnacke = 3;
			repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			moves++;
			right = true;
			if(!left)
			{
				right = true;
			}
			else 
			{
				right = false;
				left = true;
			}
			
			up = false;
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			moves++;
			left = true;
			if(!right)
			{
				left = true;
			}
			else 
			{
				left = false;
				right = true;
			}
			
			up = false;
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			moves++;
			up = true;
			if(!up)
			{
				up = true;
			}
			else 
			{
				up = false;
				down = true;
			}
			
			left = false;
			right = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			moves++;
			down = true;
			if(!down)
			{
				down = true;
			}
			else 
			{
				down = false;
				up = true;
			}
			
			left = false;
			right = false;
		}		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//todo autu-generated method stub
		
	}
		

}
