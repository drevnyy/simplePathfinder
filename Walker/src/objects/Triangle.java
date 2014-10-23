package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Triangle extends Obj {

	Point p1,p2,p3;
	
	public Triangle(Point xx,Point yy, Point ww){
		p1=xx;
		p2=yy;
		p3=ww;
		id=++ids;
		color=Color.blue;
	}
	
	public void draw()
	{
		System.out.println(x+" "+y+" "+w+" "+h);
		
	}
	public void Draw(Graphics g){
		g.setColor(Color.RED);
		g.drawLine(p1.x,p1.y,p2.x,p2.y);
		g.drawLine(p1.x,p1.y,p3.x,p3.y);
		g.drawLine(p3.x,p3.y,p2.x,p2.y);
		g.setColor(color);
		
		//g.fillPolygon(new int[]{p1.x,p2.x,p3.x}, new int[]{p1.y,p2.y,p3.y}, 3);
	}

	public boolean intersectsTriangle(Triangle o) {

		if(intersects(p1,p2,o.p1,o.p2) || intersects(p1,p2,o.p1,o.p3) || intersects(p1,p2,o.p2,o.p3))
			return true;
		if(intersects(p1,p3,o.p1,o.p2) || intersects(p1,p3,o.p1,o.p3) || intersects(p1,p3,o.p2,o.p3))
			return true;
		if(intersects(p2,p3,o.p1,o.p2) || intersects(p2,p3,o.p1,o.p3) || intersects(p2,p3,o.p2,o.p3))
			return true;
		//if(intersectsPoint(o.p1) || intersectsPoint(o.p2) || intersectsPoint(o.p3))
		//	return true;
		return false;
	}

	private boolean intersectsPoint(Point p) {
		if(strona(p1,p2,p)==strona(p2,p3,p) && 
				strona(p2,p3,p)==strona(p3,p1,p) )
		{
			if(p1.x!=p.x && p1.y!=p.y &&
			p2.x!=p.x && p2.y!=p.y &&
			p3.x!=p.x && p3.y!=p.y )
				return true;
		
		}
		return false;
	}
	/*protected int strona(Point start, Point end, Point p)
    {
        float det = (end.y - start.y) * (end.x - p.x) + (start.x - end.x) * (end.y - p.y);
        if (det < 0) return -1;
        if(det > 0) return 1;
        return 0;
    }*/
}
