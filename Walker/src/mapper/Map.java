package mapper;

import objects.Obj;
import objects.Triangle;
import objects.Wall;
import objects.Dot;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

public class Map {

	public int sizex;
	public int sizey;
	public int size=30;
	public ArrayList<Obj> objects;
	
	public Map()
	{
		
		sizex=1000;
		sizey=1000;
		objects=new ArrayList<Obj>();
		objects.add(new Wall(2*size,size,3*size,size));
		objects.add(new Wall(size,size,size,9*size));
		objects.add(new Wall(6*size,size,4*size,size));
		objects.add(new Wall(9*size,2*size,size,6*size)); 
		objects.add(new Wall(3*size,3*size,5*size,size));
		objects.add(new Wall(3*size,4*size,size,4*size));
		objects.add(new Wall(5*size,5*size,size,2*size));
		objects.add(new Wall(5*size,8*size,8*size,size));
		objects.add(new Wall(size,10*size,9*size,size));
		objects.add(new Wall(10*size,10*size,size,3*size));
		objects.addAll(generateTriangles(objects));
		objects.add(new Dot(0,0));
		objects.add(new Dot(0,10*size));
		objects.add(new Dot(11*size,5*size));
	}
	private Collection<Obj> generateTriangles(ArrayList<Obj> objects2) {
		ArrayList<Obj> list=new ArrayList<Obj>();
		
		ArrayList<Point> availablePoints=new ArrayList<Point>();
		for(Obj o : objects2)
		{		
			availablePoints.addAll(o.getPoints());
		}		
		for(Point p : availablePoints)
		{
			for(Point pi : availablePoints)
			{
				if(p.x!=pi.x || p.y!=pi.y)
						if(!intersects(p,pi,objects2))
						{
							System.out.println(p.toString()+" "+pi.toString());
							Triangle nt=null;
							for(Point ppp : availablePoints)
							{
								if((p.x!=ppp.x || p.y!=ppp.y) && (pi.x!=ppp.x || pi.y!=ppp.y) )
									if(!intersects(p,ppp,objects2) 
											&& !intersects(pi,ppp,objects2)	
											&& strona(p,pi,ppp)!=0)
									{
										nt=new Triangle(pi, p,ppp);
										if(!intersectsTriangle(nt,list))
										{
											list.add(nt);
											break;
										}
									}
							}
						}
					
					
			}
		}
		return list;
	}

		private int strona(Point start, Point end, Point p)
	    {
	        float dot = (end.y - start.y) * (end.x - p.x) + (start.x - end.x) * (end.y - p.y);
	        if (dot == 0) 
	        	{
	        	return 0;
	        	}
	        if (dot < 0) return -1;
	        return 1;
	    }
	
	private boolean intersectsTriangle(Triangle nt, ArrayList<Obj> list) {
		for(Obj o:list)
			if(o!=nt && o.intersectsTriangle(nt))
				return true;
		return false;
	}
	private boolean intersects(Point p1, Point p2, ArrayList<Obj> objects2) {
		for(Obj o: objects2)
		{
			if(o.intersects(p1, p2, o))
				return true;
		}
		return false;
	}
	public Map(ArrayList<Obj> dots)
	{
		
		sizex=1000;
		sizey=1000;
		objects=new ArrayList<Obj>();
		objects.add(new Wall(2*size,size,3*size,size));
		objects.add(new Wall(size,size,size,9*size));
		objects.add(new Wall(6*size,size,4*size,size));
		objects.add(new Wall(9*size,2*size,size,6*size)); 
		objects.add(new Wall(3*size,3*size,5*size,size));
		objects.add(new Wall(3*size,4*size,size,4*size));
		objects.add(new Wall(5*size,6*size,size,2*size));
		objects.add(new Wall(5*size,8*size,8*size,size));
		objects.add(new Wall(size,10*size,9*size,size));
		objects.add(new Wall(10*size,10*size,size,3*size));
		objects.addAll(generateTriangles(objects));
		for(Obj o:dots)
			objects.add(o);
	}
	public void draw()
	{
		for(Obj o:objects)
			o.draw();
	}
	

}
