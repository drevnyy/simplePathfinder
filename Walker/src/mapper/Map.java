package mapper;

import objects.Obj;
import objects.Terrain;
import objects.Wall;
import objects.Dot;

import java.util.ArrayList;

public class Map {

	//public ArrayList<Node> node=new ArrayList<Node>();
	public int sizex;
	public int sizey;
	public int size=40;
	public int terrainSizeInterval=4;
	public static ArrayList<Wall> objects;
	public static ArrayList<Wall> knownObjects;
	public static ArrayList <Dot> dots;
	public static ArrayList<Terrain> terrain;
	private int dotSize=30;
	public int offset=10;
	public Map()
	{
		terrain=new ArrayList<Terrain>();
		sizex=1000;
		sizey=1000;
		objects=new ArrayList<Wall>();
		dots=new ArrayList<Dot>();
		knownObjects=new ArrayList<Wall>();
		objects.add(new Wall(0,0,17*size,size,offset));
		objects.add(new Wall(17*size,0,size,17*size,offset));
		objects.add(new Wall(0,size,size,17*size,offset));
		objects.add(new Wall(size,17*size,17*size,size,offset));
		objects.add(new Wall(3*size,2*size,3*size,size,offset));
		objects.add(new Wall(2*size,2*size,size,9*size,offset));
		objects.add(new Wall(7*size,2*size,4*size,size,offset));
		objects.add(new Wall(10*size,3*size,size,6*size,offset)); 
		objects.add(new Wall(4*size,4*size,6*size,size,offset));
		objects.add(new Wall(4*size,5*size,size,4*size,offset));
		objects.add(new Wall(6*size,6*size,size,2*size,offset));
		objects.add(new Wall(6*size,9*size,8*size,size,offset));
		objects.add(new Wall(2*size,11*size,9*size,size,offset));
		objects.add(new Wall(11*size,11*size,size,3*size,offset));
		objects.add(new Wall(13*size,11*size,size,3*size,offset));
		objects.add(new Wall(13*size,7*size,3*size,size,offset));
		dots.add(new Dot(15*size,15*size,dotSize,offset));
		//dots.add(new Dot(12*size,2*size,dotSize,offset));
		//terrain.add(new Terrain(12*size,2*size,size/terrainSizeInterval,size/terrainSizeInterval,0,1));

		//terrain.add(new Terrain(16*size,16*size,size/terrainSizeInterval,size/terrainSizeInterval,0,1));
	}

	public Map(Dot dots)
	{
		terrain=new ArrayList<Terrain>();
		sizex=1000;
		sizey=1000;
		objects=new ArrayList<Wall>();
		knownObjects=new ArrayList<Wall>();
		objects.add(new Wall(0,0,17*size,size,offset));
		objects.add(new Wall(17*size,0,size,17*size,offset));
		objects.add(new Wall(0,size,size,17*size,offset));
		objects.add(new Wall(size,17*size,17*size,size,offset));
		objects.add(new Wall(3*size,2*size,3*size,size,offset));
		objects.add(new Wall(2*size,2*size,size,9*size,offset));
		objects.add(new Wall(7*size,2*size,4*size,size,offset));
		objects.add(new Wall(10*size,3*size,size,6*size,offset)); 
		objects.add(new Wall(4*size,4*size,6*size,size,offset));
		objects.add(new Wall(4*size,5*size,size,4*size,offset));
		objects.add(new Wall(6*size,6*size,size,2*size,offset));
		objects.add(new Wall(6*size,9*size,8*size,size,offset));
		objects.add(new Wall(2*size,11*size,9*size,size,offset));
		objects.add(new Wall(13*size,7*size,3*size,size,offset));
		objects.add(new Wall(13*size,8*size,size,3*size,offset));
		
	}
	public void draw()
	{
		for(Obj o:objects)
			o.draw();
	}
	

}
