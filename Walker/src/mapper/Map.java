package mapper;

import objects.Obj;
import objects.Wall;
import objects.Dot;


import java.util.ArrayList;

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
		objects.add(new Wall(size,size,4*size,size));
		objects.add(new Wall(size,size,size,10*size));
		objects.add(new Wall(6*size,size,4*size,size));
		objects.add(new Wall(9*size,size,size,8*size)); 
		objects.add(new Wall(3*size,3*size,5*size,size));
		objects.add(new Wall(3*size,3*size,size,6*size));
		objects.add(new Wall(5*size,6*size,size,3*size));
		objects.add(new Wall(5*size,8*size,8*size,size));
		objects.add(new Wall(size,10*size,10*size,size));
		objects.add(new Wall(10*size,10*size,size,3*size));
		objects.add(new Dot(0,0));
		objects.add(new Dot(0,10*size));
		objects.add(new Dot(11*size,5*size));
		for(int i=0; i<7; i++)
			objects.add(new Dot((int)(Math.random()*10*size),(int)(Math.random()*10*size)));

	}
	public Map(ArrayList<Obj> dots)
	{
		
		sizex=1000;
		sizey=1000;
		objects=new ArrayList<Obj>();
		objects.add(new Wall(size,size,4*size,size));
		objects.add(new Wall(size,size,size,10*size));
		objects.add(new Wall(6*size,size,4*size,size));
		objects.add(new Wall(9*size,size,size,8*size)); 
		objects.add(new Wall(3*size,3*size,5*size,size));
		objects.add(new Wall(3*size,3*size,size,6*size));
		objects.add(new Wall(5*size,6*size,size,3*size));
		objects.add(new Wall(5*size,8*size,8*size,size));
		objects.add(new Wall(size,10*size,10*size,size));
		objects.add(new Wall(10*size,10*size,size,3*size));
		
		for(Obj o:dots)
			objects.add(o);
	}
	public void draw()
	{
		for(Obj o:objects)
			o.draw();
	}
	

}
