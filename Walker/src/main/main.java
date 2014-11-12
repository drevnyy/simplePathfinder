

package main;

import mapper.Map;
import mapper.Mover;
import Walker.VacumWalker;
import objects.Dot;
import objects.Obj;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
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
	
	int sx=1000,sy=1000;
	
	static public Applet applet;
	Timer timer = new Timer();
	Mover mover=new Mover();
	
	Image bufor;
	Graphics bg;
	
	public void init() {
		applet=this; 
		applet.setSize(sx,sy);
		applet.setBackground(Color.black);
		
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
		//System.out.println(Map.terrain.size());
		synchronized(Map.terrain){
		for(Obj o:Map.terrain)
		{
			o.Draw(g);
		}}
		synchronized(Map.knownObjects){
		for(Obj o:Map.knownObjects)
		{
			o.Draw(g);
		}}
		synchronized(Map.dots){
		for(Dot d: Map.dots)
		{
			g.setColor(d.walk?Color.GREEN:Color.RED);
			g.fillRect(0+50*Map.dots.indexOf(d), 0, 50, 30);
	
			d.Draw(g);
			d.DrawPath(g);
		}}


		
	}

	
	public void mousePressed(MouseEvent me)
	{
	}
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub	
		Point dotPos=new Point();
		dotPos.x = arg0.getX();
		dotPos.y = arg0.getY();
		mover.reset();
		for(Dot d: Map.dots)
		{
			VacumWalker w=new VacumWalker(mover,d);
			w.makepaths(dotPos.x,dotPos.y,d);
		}
	}
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseClicked(MouseEvent me) {
		//int mouseX = me.getX();
		//int mouseY = me.getY();
		//mover.reset();
		//mover.makepaths(mouseX,mouseY);
		for(Dot d:Map.dots){
			VacumWalker w=new VacumWalker(mover,d);
			w.start();
		}

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
