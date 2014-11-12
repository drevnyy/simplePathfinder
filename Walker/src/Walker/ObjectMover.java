package Walker;

import java.awt.Point;

import mapper.Map;
import objects.Dot;
import objects.Wall;

public class ObjectMover {
int xcounter=0;

public ObjectMover()
{
}
	public boolean move(Map map, Dot o)
	{
		if(o.curNode<o.node.size())
		{
			if(!about(o.x,o.tx,o.speed) || !about(o.y,o.ty,o.speed))
			{

				xcounter++;
				if(o.x!=0 && o.tx!=0)
				{
				if(!see(new Point((int)o.tx,(int)o.ty), new Point((int)o.x,(int)o.y),map))
				{
					o.curNode=10000;
					System.out.println("i got interrupted, leaving loop");
				}
				xcounter=0;
				}
				o.x+=o.mx;
				o.y+=o.my;
			}
			else
			{
				o.x= o.tx;
				o.y= o.ty;
				o.curNode++;
				
				if(o.curNode<o.node.size() )
				{
					o.mx=o.node.get(o.curNode).dirx;
					o.my=o.node.get(o.curNode).diry;
					o.tx=o.node.get(o.curNode).x;
					o.ty=o.node.get(o.curNode).y;
				}
			}
			return true;
		}
		else
			return false;
	}
	private boolean about(double x2, double xx,double speed) 
	{
		if((x2-xx)>-speed && (x2-xx)<speed)return true;
		return false;
	}	private boolean see(Point start, Point end, Map map) {
		synchronized(Map.knownObjects){
			for(Wall o:Map.knownObjects)
			{
				synchronized(o){
					if (o.intersects(start, end))
						return false;
				}
			}
		}
		return true;
	}
}
