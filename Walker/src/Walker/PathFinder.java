package Walker;

import java.awt.Point;
import java.util.ArrayList;

import mapper.Merger;
import mapper.Map;
import mapper.Node;
import objects.Dot;
import objects.Obj;
import objects.Wall;

public class PathFinder {
	int x,y;
	int mcounter=0;
	public PathFinder(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
		public ArrayList<Node> makePathTo(int targetX,int targetY, Map m, Dot o)
	{
			System.out.println("starting o make path to : "+targetX+","+targetY);
			ArrayList<Node> nodes=new ArrayList<Node>();
		o.walk=false;
		nodes.add(new Node(x,y,0,0));
		ArrayList<Node> path=createPath(targetX, targetY, m, o);
		int i=0;
		for(Node n:path)
		{
			createDir(nodes.get(i).x,nodes.get(i).y,n.x,n.y,o);
			nodes.add(n.setDirs(o.mx,o.my));
			i++;
		}
		
		o.curNode=0;
		o.tx=x;
		o.ty=y;
		mcounter++;
		o.walk=true;
		Merger.mergeRects(m);
		if(mcounter==10)
		{
			mcounter=0;
			Merger.mergeTerrain(m);
		}
		return nodes;
	}
	
	private ArrayList<Node> createPath(int x2, int y2,Map map, Dot vacum) {
		
		System.out.println("path making started");
		ArrayList<Node> node = new ArrayList<Node>();
		ArrayList<Point> points=new ArrayList<Point>();
		node.add(new Node(x,y,0,0));
		points.add(new Point((int)x,(int)y));
		for(Obj o: Map.knownObjects)
			points.addAll(o.getPoints());
		points.add(new Point(x2,y2));
		
		int [][] array=new int[points.size()][points.size()];
		fixVacumPosition(vacum,map,x2,y2);
		if(see(points.get(0),points.get(points.size()-1),map))
		{
			System.out.println("no pathfinder needed here");
			node.add(new Node((int)x2,(int)y2,0,0));
			return node;
		}
		System.out.println("creating matrix");
		for(int j=0; j<points.size(); j++)
		{
			if(see(points.get(0),points.get(j),map)){
				array[0][j]=distance(points.get(0),points.get(j));
			}
			array[j][0]=array[0][j];
			
		}
		
		int size=points.size();
		
		for(int i=1; i<points.size(); i++)
			for(int j=i; j<points.size(); j++)
			{
				if(see(points.get(i),points.get(j),map)){
					array[i][j]=distance(points.get(i),points.get(j));
				}
				array[j][i]=array[i][j];
				
			}
		System.out.println("matrix made succesfully");
		return getPath(array,points,x2,y2);
	}
	private void fixVacumPosition(Dot vacum, Map map, int x2, int y2) {
		for(Wall w : Map.knownObjects)
		{
			if(w.pointInside(new Point((int)vacum.x,(int)vacum.y), w.makeRectangle(map.offset+1)))
			{
				System.out.println("fixing Dot Position");
				if(vacum.x>w.x+w.w/2)
					vacum.x=w.x+w.w+w.offset*2;
				else
					vacum.x=w.x-w.offset*2;
				if(vacum.y>w.y+w.h/2)
					vacum.y=w.y+w.h+w.offset*2;
				else
					vacum.y=w.y-w.offset*2;
			}			

		}
		
	}
	private int distance(Point start, Point end) {
		
		int x=(int) (end.getX()-start.getX());
		int  y=(int) (end.getY()-start.getY());
		
		return (int)Math.sqrt(x*x+y*y);
	}
	ArrayList<Node> getPath( int[][] x, ArrayList<Point> p, int xx, int yy)
	{
		System.out.println("making path from matrix");
		int size=p.size();		
		ArrayList<Integer> queue=new ArrayList<Integer>();
		queue.add(0);
		ArrayList<Node> path=new ArrayList<Node>();
		ArrayList<Integer>[] parentq=new ArrayList[size];
		int [] parents=new int[size];
		for(int i=0; i<size; i++)
			parentq[i]=new ArrayList<Integer>();
		while(queue.size()>0)
		{
			int that=queue.get(0);
			for(int i=1; i<size; i++)
				if(x[that][i]>0 && i!=that)
				{
					if(parentq[i].size()==0)
					{
						parentq[i].addAll(parentq[that]);
						parentq[i].add(that);
						parentq[i].add(i);
						parents[i]=parents[that]+x[that][i];
						queue.add(i);
					}
					if(parents[i]>(parents[that]+x[that][i]) && parentq[that].contains(0))
					{
						parentq[i].clear();
						parentq[i].addAll(parentq[that]);
						parentq[i].add(that);
						parentq[i].add(i);
						parents[i]=parents[that]+x[that][i];
						queue.add(i);
					}
			}
			queue.remove(0);
		}
		
		if(parentq[size-1].size()==0)
			{
			System.out.println("can't find path");
				return path;
			}
		size=size-1;
		path.addAll(turnPathAround(parentq[size],p));
		System.out.println("dlugosc sciezki: "+path.size());
			return path;
		}
	ArrayList<Node> turnPathAround(ArrayList<Integer> shit,ArrayList<Point> p)
	{
		ArrayList<Node>path=new ArrayList<Node>();
		for(int i=0; i<shit.size(); i++)
		{
			path.add(new Node(p.get(shit.get(i)).x,p.get(shit.get(i)).y,0,0));
		}
		return path;
	}
	public PathFinder setPoints(int x2, int y2) {
		x=x2;
		y=y2;
		return this;
	}	
	private void createDir(double x3,double y3,double d, double e, Dot o) {

		double mx=(0.99*x3+0.01*d)-x3;
		double my=(0.99*y3+0.01*e)-y3;
		double t=mx;
		mx=abs(mx)/(abs(mx)+(abs(my)))*o.speed;
		my=abs(my)/(abs(t)+(abs(my)))*o.speed;
		if(x3>d)
		{
			if(mx>0)
				mx=-mx;
		}
		else
			if(mx<0)
				mx=-mx;
		if(y3>e)
		{
			if(my>0)
				my=-my;
		}
		else
			if(my<0)
				my=-my;
		o.mx=mx;
		o.my=my;
	}
	private double abs(double a)
	{
		return (a>0)? a:-a;
	}

	private boolean see(Point start, Point end, Map map) {
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
