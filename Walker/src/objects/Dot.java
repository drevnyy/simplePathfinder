package objects;

import mapper.Node;
import mapper.Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Dot extends Obj {

	int offset=3;
	double speed=2.2;
	int curNode=1000;
	
	boolean walk=false;
	
	
	public Dot(int xx,int yy){
		x=xx;
		y=yy;
		w=h=15;
		isdot=true;
		id=++ids;
		color=Color.RED;
	}
	
	public void data()
	{
		System.out.println("i am at "+(int)x+" "+(int)y);
		System.out.println("going to "+(int)tx+" "+(int)ty);

	}

	public void move(Map map)
{
		if(curNode<node.size())
		{
			if(!about(x,tx) || !about(y,ty))
			{
				x=(x+=mx);
				y=(y+=my);
				
			}
			else
			{
				x= tx;
				y= ty;
				curNode++;
				
				if(curNode<node.size())
				{
					mx=node.get(curNode).dirx;
					my=node.get(curNode).diry;
					tx=node.get(curNode).x;
					ty=node.get(curNode).y;
				}
				//data();
			}
			
		}
	}
	
	private boolean about(double x2, double xx) 
	{
		if((x2-xx)>-speed && (x2-xx)<speed)return true;
		return false;
	}
	
	private double abs(double a)
	{
		return (a>0)? a:-a;
	}
	private void createDir(double x3,double y3,double d, double e) {

		mx=(0.99*x3+0.01*d)-x3;
		my=(0.99*y3+0.01*e)-y3;
		double t=mx;
		mx=abs(mx)/(abs(mx)+(abs(my)))*speed;
		my=abs(my)/(abs(t)+(abs(my)))*speed;
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
	}
	
	public void createPath(int x2, int y2,Map map) {
		walk=false;
		node.clear();
		points.clear();
		node.add(new Node(x,y,0,0));
		points.add(new Point((int)x,(int)y));
		getPoints(map);
		points.add(new Point(x2,y2));
		
		int [][] array=new int[points.size()][points.size()];
		
		for(int j=0; j<points.size(); j++)
		{
			if(seeEnd(points.get(0),points.get(j),map)){
				array[0][j]=distance(points.get(0),points.get(j));
			}
			array[j][0]=array[0][j];
			
		}
		
		int size=points.size();
		
		for(int i=1; i<points.size()-1; i++)
			for(int j=i; j<points.size(); j++)
			{
				if(see2D(points.get(i),points.get(j),map)){
					array[i][j]=distance(points.get(i),points.get(j));
				}
				array[j][i]=array[i][j];
				
			}
		
		for(int j=size-1; j<points.size(); j++)
		{
			if(see2D(points.get(size-1),points.get(j),map)){
				array[size-1][j]=distance(points.get(size-1),points.get(j));
			}
			array[j][size-1]=array[size-1][j];
			
		}
		
		ArrayList<Node> path=getPath(array,points,x2,y2);
		
		int i=0;
		
		for(Node n:path)
		{
			createDir(node.get(i).x,node.get(i).y,n.x,n.y);
			node.add(n.setDirs(mx,my));
			i++;
			//System.out.println(i+" "+n.x+" "+n.y);
		}
		
		
		
		
		curNode=0;
		tx=x;
		ty=y;
		walk=true;
	}

	private boolean see2D(Point start, Point end, Map map) {
		for(Obj o:map.objects)
		{
			if (o.intersects(start, end, id))
					return false;
		}
		return true;
	}
	private boolean seeEnd(Point start, Point end, Map map) {
		for(Obj o:map.objects)
		{
			if (o.intersectsEnd(start, end, id))
					return false;
		}
		return true;
	}

	private int distance(Point start, Point end) {
		
		int x=(int) (end.getX()-start.getX());
		int  y=(int) (end.getY()-start.getY());
		
		return (int)Math.sqrt(x*x+y*y);
	}

	private void getPoints(Map map) {
		
		for(Obj o:map.objects)
		{
			if(id!=o.id)
			{
				points.add(new Point((int)o.x-offset,(int)o.y-offset));
				points.add(new Point((int)(o.x+o.w)+offset,(int)o.y-offset));
				points.add(new Point((int)o.x-offset,(int)(o.y+o.h)+offset));
				points.add(new Point((int)(o.x+o.w)+offset,(int)(o.y+o.h)+offset));
			}
		}
		
	}

@SuppressWarnings("unchecked")
ArrayList<Node> getPath( int[][] x, ArrayList<Point> p, int xx, int yy)
{
	int size=p.size();
	/*for( Point o: p)
	{
		System.out.println(p.indexOf(o) +"  "+ o.x+"  " +o.y);
	}*/
	
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
	//System.out.println(parentq[size-1].size());
	if(parentq[size-1].size()==0)return path;
	size=size-1;
	path.addAll(givePath(parentq[size],p));
		return path;
	}

	ArrayList<Node> givePath(ArrayList<Integer> shit,ArrayList<Point> p)
	{
		ArrayList<Node>path=new ArrayList<Node>();
		for(int i=0; i<shit.size(); i++)
		{
			//System.out.println(shit.get(i)+ "  "+ p.get(shit.get(i)).x+"  " +p.get(shit.get(i)).y);
			path.add(new Node(p.get(shit.get(i)).x,p.get(shit.get(i)).y,0,0));
		}
		return path;
	}	
	public void Draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)x-4,(int) y-4, (int)w, (int)h);
		
	}
}

/*        private PointF getIntersectionPoint(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4)
        {
            PointF point = new PointF(-10,-10);
            float d = det(x1, y1, x2, y2, x3, y3, x4, y4);
            if (d != 0.0F)
            {
                float m = ((x3 - x1) * (y3 - y4) - (x3 - x4) * (y3 - y1)) / d;
                point.X = (1 - m) * x1 + m * x2;
                point.Y = (1 - m) * y1 + m * y2;

                return point;
            }

            return point;
        }
        
        
        
        
        */
