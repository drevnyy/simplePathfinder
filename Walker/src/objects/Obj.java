package objects;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import mapper.Map;
import mapper.Node;


public class Obj {

	public boolean isdot=false;
	public double x,y,w,h;
	public int id;
	public static int ids;
	public double mx=0.;
	public double my=0.;
	public double tx;
	public double ty;
	public ArrayList<Node> node=new ArrayList<Node>();
	public ArrayList<Point> points=new ArrayList<Point>();
	public void draw(){}

	public void move() {

		
	}
	public void move(Map map){}

	
	public void createPath(int x2, int y2, Map map) {

		
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

    boolean intersects(Point start, Point end, Point p, Point p2)
    {
       
         if (strona(start, end, p) !=
            strona(start, end, p2) &&
            strona(p, p2, start) !=
            strona(p, p2, end))
            return true;

        return false;

        
    }

	public boolean intersects(Point start, Point end,int idd) {
		
		if(idd==id) return false;
		
		int offset=1;
		
		Rectangle r=new Rectangle ((int)x+offset,(int)y+offset,(int)w-2*offset,(int)h-2*offset);
		if(r.intersectsLine(start.x, start.y, end.x, end.y))
			return true;
		
		return false;
	}
	public boolean intersectsEnd(Point start, Point end,int idd) {
		
		return inside(start,end);
	}
	private boolean inside(Point start, Point end) {
		
		int offset=1;
		Rectangle r=new Rectangle ((int)x+offset,(int)y+offset,(int)w-2*offset,(int)h-2*offset);
		if(r.intersectsLine(start.x, start.y, end.x, end.y))
		{
			if(pointInside(start,r) || pointInside(end,r))
				return false;
			return true;
		}
		return false;

	}

	private boolean pointInside(Point start, Rectangle r) {
		if(start.x>r.x && start.x<(r.x+r.width) &&
			start.y>r.y && start.y<(r.y+r.height))
			return true;
		return false;
	}
}
