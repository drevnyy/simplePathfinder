package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

import mapper.Map;
import mapper.Node;

public class Dot extends Obj {
	public boolean walk=false;
	public int seeSight=-1;
	public double tx;
	public double ty;
	public double mx=0.0;
	public double my=0.0;
	public int curNode=1000;
	public double speed=0;//2.2

	public ArrayList<Node> node=new ArrayList<Node>();
	boolean makeDir;
	public Dot(int xx,int yy,int size, int offset){
		this.offset=offset;
		seeSight=3*size;
		x=xx;
		y=yy;
		w=h=size;
		color=Color.RED;
		speed=3.5;//2.2
	}


	

	public void Draw(Graphics g) {
		g.setColor(Color.white);
		g.drawOval((int)x-(int)seeSight/2, (int) y-(int)seeSight/2, (int)seeSight, (int)seeSight);
		if(mx==mx && my==my){
		g.drawLine((int)x,(int)y,(int)(x+mx*20),(int)(y+my*20));
		g.drawLine((int)x+1,(int)y+1,(int)(x+mx*20)+1,(int)(y+my*20)+1);
		g.drawLine((int)x-1,(int)y-1,(int)(x+mx*20)-1,(int)(y+my*20)-1);
		}
		g.fillOval((int)x-(int)h/2,(int) y-(int)h/2, (int)w, (int)h);
		g.setColor(color);
		g.drawOval((int)x-(int)h/2,(int) y-(int)h/2, (int)w, (int)h);
		g.drawOval((int)x-(int)h/2+1,1+(int)y-(int)h/2, (int)w-2, (int)h-2);
			}
	public void DrawPath(Graphics g) {

		g.setColor(Color.orange);
		int n=node.size();
		if(n>2)
	for(int i=0; i<node.size()-1; i++)
		g.drawLine((int)node.get(i).x,(int)node.get(i).y,(int)(node.get(i+1).x),(int)(node.get(i+1).y));
		else if(n==2)
			g.drawLine((int)node.get(0).x,(int)node.get(0).y,(int)(node.get(1).x),(int)(node.get(1).y));

		
	}
	public Collection<Point> getPoints() {
		ArrayList<Point> points=new ArrayList<Point>();
		
		points.add(new Point((int)x-offset*2,(int)y-offset*2));
		points.add(new Point((int)x-offset*2,(int)y+(int)h+offset*2));
		points.add(new Point((int)x+(int)w+offset*2,(int)y-offset*2));
		points.add(new Point((int)x+(int)w+offset*2,(int)y+(int)h+offset*2));

		return points;
	}
}
