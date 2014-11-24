package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;

public class Wall  extends Obj {

	
	
	public Wall(int xx,int yy, int ww, int hh, int of){
		x=xx;
		y=yy;
		w=ww;
		h=hh;
		offset=of;
		color=Color.BLACK;
		ofcolor=Color.black;
	}
	public Wall(int xx,int yy, int ww, int hh, int of, Color c){
		x=xx;
		y=yy;
		w=ww;
		h=hh;
		offset=of;
		color=c;
	}

	public void Draw(Graphics g){
		g.setColor(color);
		g.fillRect((int)x+1, (int)y+1, (int)w-2, (int)h-2);
		g.drawLine((int)(x-offset), (int)(y-offset), (int)(x-offset), (int)(y+offset+h));
		g.drawLine((int)(x+w+offset), (int)(y-offset), (int)(x+w+offset), (int)(y+offset+h));

		g.drawLine((int)(x-offset), (int)(y-offset), (int)(x+w+offset), (int)(y-offset));
		g.drawLine((int)(x-offset), (int)(y+h+offset), (int)(x+w+offset), (int)(y+offset+h));
	}
	public Rectangle makeRectangle() {
		Rectangle r = new Rectangle((int)x,(int)y,(int)w,(int)h);
		return r;
		
	}
	public Rectangle makeRectangle(int offset) {
		Rectangle r = new Rectangle((int)x-offset,(int)y-offset,(int)w+2*offset,(int)h+2*offset);
		return r;
		
	}
	public Collection<Point> getPoints() {
		ArrayList<Point> points=new ArrayList<Point>();
		
		points.add(new Point((int)x-offset,(int)y-offset));
		points.add(new Point((int)x-offset,(int)y+(int)h+offset));
		points.add(new Point((int)x+(int)w+offset,(int)y-offset));
		points.add(new Point((int)x+(int)w+offset,(int)y+(int)h+offset));

		return points;
	}
	public boolean intersectsEnd(Point start, Point end) {
		return inside(start,end);
	}
	private boolean inside(Point start, Point end) {
		
		int ofset=-offset+1;
		Rectangle r=new Rectangle ((int)x+ofset,(int)y+ofset,(int)w-2*ofset,(int)h-2*ofset);
		if(r.intersectsLine(start.x, start.y, end.x, end.y))
		{
			if(pointInside(start,r) /*|| pointInside(end,r)*/)
				return false;
			return true;
		}
		return false;

	}	

	public boolean intersects(Point p1, Point p2) {
		
		//if(o.id==id) return false;
		
		int ofset=-offset+1;
		Rectangle r=new Rectangle ((int)x+ofset,(int)y+ofset,(int)w-2*ofset,(int)h-2*ofset);
		if(r.intersectsLine(p1.x, p1.y, p2.x, p2.y))
				return true;
		return false;
	}
	public boolean intersects(Rectangle wall) {
		if(new Rectangle((int)x,(int)y,(int)w,(int)h).intersects(wall))
			return true;
		return false;
	}
	public boolean pointInside(Point start, Rectangle r) {
		if(start.x>r.x && start.x<(r.x+r.width) &&
				start.y>r.y && start.y<(r.y+r.height))
				return true;
			return false;
		}
	public boolean pointInside(Point start) {
		Rectangle r=new Rectangle();
		r.x=(int)x;
		r.y=(int)y;
		r.width=(int)w;
		r.height=(int)h;
		
		if(start.x>r.x && start.x<(r.x+r.width) &&
				start.y>r.y && start.y<(r.y+r.height))
				return true;
			return false;
		}
}
