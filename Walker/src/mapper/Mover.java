package mapper;

import java.util.ArrayList;
import java.util.TimerTask;

import BL.TerrainMerger;
import objects.Dot;
import objects.Obj;
import Walker.ObjectMover;
import Walker.PathFinder;
import mapper.Map;

public class Mover extends TimerTask {

	//public boolean move=false;
	public int x=0,y=0,w=0,h=0;
	int xx=0,yy=0;
	public Map map=new Map();
	ArrayList<Obj> dots=new ArrayList<Obj>();
	PathFinder p=new PathFinder(0,0);
	ObjectMover m=new ObjectMover();
	public void run() {
		
		for(Dot o:Map.dots)
			if(m!=null)
			{
				o.walk=m.move(map,o);

				new TerrainMerger().UpdateTerrain(map,o);
			}
		main.main.applet.repaint();
		
	}


	public void reset()
	{
		for(Dot o:Map.dots)
		{
			o.tx=o.ty=0;
		}
	}
	public void hardReset()
	{
		map=new Map();
	}
}
