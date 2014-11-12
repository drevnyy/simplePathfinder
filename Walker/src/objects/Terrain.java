package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Terrain extends Wall{
 public int id;
 public static int green=3;
 public static int yellow=1;
	public Terrain(int xx, int yy, int ww, int hh, int of, int id) {
		super(xx, yy, ww, hh, of);
		this.id=id;
		ofcolor=Color.gray;
	}
	public void Draw(Graphics g){
		if(id!=0){
			
		g.setColor(Color.black);
		switch (id)
		{
		case 1:
			g.setColor(Color.GRAY);
			g.fillRect((int)x+1, (int)y+1, (int)w-2, (int)h-2);
			break;
		case 3:
			g.setColor(Color.green);
			g.fillRect((int)x+1, (int)y+1, (int)w-2, (int)h-2);
			break;
		
		case 2:
			g.setColor(Color.orange);
			g.fillRect((int)x+1, (int)y+1, (int)w-2, (int)h-2);
			break;
		}
		
		}
	}
	public void GreenIt()
	{
		id=3;
	}
	public boolean IsGreen()
	{
		return id>2;
	}
	public Rectangle makeRectangle() {
		Rectangle r = new Rectangle((int)x,(int)y,(int)w,(int)h);
		return r;
		
	}
	public Point GetCenter()
	{
		return new Point((int)(x+h/2),(int)(y+h/2));
	}
	public int distance(Terrain t2) {
		return (int)Math.sqrt((t2.x-x)*(t2.x-x)+(t2.y-y)*(t2.y-y));
	}
	public int distance(Point t2) {
		return (int)Math.sqrt((t2.x-x)*(t2.x-x)+(t2.y-y)*(t2.y-y));
	}
	public void Mark()
	{
		id=2;
	}
}
