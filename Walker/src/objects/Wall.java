package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;

public class Wall  extends Obj {

	
	
	public Wall(int xx,int yy, int ww, int hh){
		x=xx;
		y=yy;
		w=ww;
		h=hh;
		id=++ids;
		color=Color.WHITE;
	}
	
	public void draw()
	{
		System.out.println(x+" "+y+" "+w+" "+h);
		
	}
	public void Draw(Graphics g){
		g.setColor(color);
		g.drawLine((int)x,(int)y,(int)x+(int)w,(int)y);
		g.drawLine((int)x,(int)y,(int)x,(int)y+(int)h);
		g.drawLine((int)x,(int)y+(int)h,(int)x+(int)w,(int)y+(int)h);
		g.drawLine((int)x+(int)w,(int)y,(int)x+(int)w,(int)y+(int)h);
		//g.setColor(color);
		//g.fillRect((int)x,(int) y,(int) w,(int)h);
	}
	public Rectangle makeRectangle() {
		Rectangle r = new Rectangle((int)x+1,(int)y+1,(int)w-2,(int)h-2);
		return r;
		
	}
	public Collection<Point> getPoints() {
		ArrayList<Point> points=new ArrayList<Point>();
		points.add(new Point((int)x,(int)y));
		points.add(new Point((int)x,(int)y+(int)h));
		points.add(new Point((int)x+(int)w,(int)y));
		points.add(new Point((int)x+(int)w,(int)y+(int)h));

		return points;
	}
}
