package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;

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
	public Color color;
	public void draw(){}

	public void move() {

		
	}
	public void move(Map map){}

	
	public void createPath(int x2, int y2, Map map) {

		
	}
	
	protected int strona(Point start, Point end, Point p)
    {
        float dot = (end.y - start.y) * (end.x - p.x) + (start.x - end.x) * (end.y - p.y);
        //if (dot == 0) 
        //	{
        //	return 0;
        //	}
        if (dot <= 0) return -1;
        return 1;
    }

    public boolean intersects(Point start, Point end, Point p, Point p2)
    {
    	if(canIntersect(start,end,p,p2))
         if (strona(start, end, p) !=
            strona(start, end, p2) &&
            strona(p, p2, start) !=
            strona(p, p2, end))
            return true;

        return false;  
    }


	public boolean canIntersect(Point start, Point end, Point p, Point p2) {
		int rx=(start.x>end.x? end.x : start.x);
		int ry=(start.y>end.y? end.y : start.y);
		int rx2=(start.x<end.x? end.x : start.x);
		int ry2=(start.y<end.y? end.y : start.y);
		int rrx=(p.x>p2.x? p2.x : p.x);
		int rry=(p.y>p2.y? p2.y : p.y);
		int rrx2=(p.x<p2.x? p2.x : p.x);
		int rry2=(p.y<p2.y? p2.y : p.y);
		return new Rectangle(rx,ry,rx2-rx,ry2-ry).intersects(rrx,rry,rrx2-rrx,rry2-rry);
		/*
		if((p.x>rx && p.x<rx2) && (p2.y>ry && p2.y<ry2) )
			return true;
		if((p.y>ry && p.y<ry2) && (p2.x>rx && p2.x<rx2))
			return true;
		return false;*/
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

	public void Draw(Graphics g) {
		
		
	}

	public Rectangle makeRectangle() {
		return null;
		
	}

	public Collection<? extends Point> getPoints() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean intersectsTriangle(Triangle nt) {
		// goto triangle >> intersects triangle
		return true;
	}

	public boolean intersects(Point p1, Point p2, Obj o) {
		
		//if(o.id==id) return false;
		
		int offset=1;
		Rectangle r=new Rectangle ((int)o.x+offset,(int)o.y+offset,(int)o.w-2*offset,(int)o.h-2*offset);
		if(r.intersectsLine(p1.x, p1.y, p2.x, p2.y))
				return true;
		return false;
	}
}


