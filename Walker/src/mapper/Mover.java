package mapper;

import java.util.ArrayList;
import java.util.TimerTask;

import objects.Obj;
import mapper.Map;

public class Mover extends TimerTask {

	public int x=0,y=0,w=0,h=0;
	int xx=0,yy=0;
	public Map map=new Map();
	ArrayList<Obj> dots=new ArrayList<Obj>();
	
	public void run() {

		for(Obj o:map.objects)
			o.move(map);
		main.main.applet.repaint();
		
	}

	public void makepaths(double x, double y)
	{
		xx=(int) x; yy=(int) y;
		for(Obj o:map.objects)
			o.createPath((int)(x), (int)(y), map);
	}
	
	public void reset(){
		
		for(Obj o:map.objects)
			if(o.isdot)
				dots.add(o);
		for(Obj o:dots)
		{
			o.mx=o.my=0;
			o.node.clear();
			o.tx=o.ty=0;
			o.points.clear();
		}
		
		map=new Map(dots);
		dots.clear();
	}
	public void hardReset()
	{
		map=new Map();
	}
}
