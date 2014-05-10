/** 
 * 
 *  CODE WRITTEN BY ZENEK
 *  PUBLISHED FOR STUDY PURPOSES ONLY
 *  
 */


package main;

import mapper.Mover;
import objects.Obj;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;


public class main extends Applet  implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	
	
	public static void wait(int time){
		try{
			Thread.sleep(time);
		}
		catch(Exception e){}
	}
	
	int sx=600,sy=400;
	
	static public Applet applet;
	Timer timer = new Timer();
	Mover mover=new Mover();
	
	Image bufor;
	Graphics bg;
	
	public void init() {
		applet=this;
		applet.setSize(sx,sy);
		applet.setBackground(Color.black);
		
		objects.Obj.ids=0;
		
		bufor=createImage(sx, sy);
		bg=bufor.getGraphics();
		
		wait(500);
		
		   addMouseListener(this);
		   addMouseMotionListener(this);
		timer.scheduleAtFixedRate(mover, 10,10);
	}	
	public void update(Graphics g)
	{
		bg.clearRect(0, 0, sx, sy);
		paint(bg);
		g.drawImage(bufor, 0, 0, applet);
	}
	public void paint(Graphics g)
	{
		rysuj(g);
	}
	private void rysuj(Graphics g) {
		
		for(Obj o:mover.map.objects)
		{
			if(o.isdot)
			{
			g.setColor(Color.RED);
			g.fillOval((int)o.x-4,(int) o.y-4, (int)o.w, (int)o.h);
			}
			else
			{
				g.setColor(Color.WHITE);
				g.fillRect((int)o.x,(int) o.y,(int) o.w,(int)o.h);
			}
		}
		
	}
	public void mousePressed(MouseEvent me)
	{
	}
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseClicked(MouseEvent me) {
		int mouseX = me.getX();
		int mouseY = me.getY();
		mover.reset();
		mover.makepaths(mouseX,mouseY);
		repaint();
		
	}
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent arg0) {
		mover.hardReset();
		
	}
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
