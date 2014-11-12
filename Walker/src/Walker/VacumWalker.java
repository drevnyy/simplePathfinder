package Walker;

import java.awt.Point;

import mapper.Map;
import mapper.Mover;
import BL.TerrainWalker;
import objects.Dot;

public class VacumWalker extends Thread {

	private Point b;
	private Dot dot;
	private TerrainWalker t;
	private Map m;
	private PathFinder p;
	public VacumWalker(Mover mover, Dot d)
	{
		this.m=mover.map;
		dot=d;
		b=new Point((int)d.x,(int)d.y);
		t=new TerrainWalker();
		p=new PathFinder(0,0);
	}
	
	public void run()
	{
		Point p=t.GetNextPoint(b);
		while(p!=null)
		{		int i=0;	
			while(dot.walk)
			try{
			Thread.sleep(50);
			i++;
			if(i==50)
				break;
			}
			catch(Exception e)
			{
				
			}
			p=t.GetNextPoint(p);
			try{
			makepaths(p.x,p.y,dot);
			System.out.println("walking to point "+p.x+" "+p.y);
			}
			catch(NullPointerException e)
			{
			makepaths(b.x,b.y,dot);
			}
			try{
			Thread.sleep(100);
			}
			catch(Exception e)
			{
				
			}
		}
		//while(true)
		//System.out.println("WUT");
		makepaths(b.x,b.y,dot);
		
	}
	public void makepaths(double x, double y, Dot d)
	{
			d.node.clear();
			d.node.addAll(p.setPoints((int)d.x,(int)d.y).makePathTo((int)x, (int)y, m,d));
	}


}
