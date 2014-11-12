package BL;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import mapper.Map;
import objects.Dot;
import objects.Terrain;
import objects.Wall;

public class TerrainMerger {
	public void UpdateTerrain(Map m, Dot dot) {
		int terrainSize=m.size/m.terrainSizeInterval;
		for(int i=(((int)dot.x-3*dot.seeSight/2)-((int)dot.x-dot.seeSight/2)%m.size); i<=((int)dot.x+3*dot.seeSight/2); i+=terrainSize)
			for(int j=(((int)dot.y-3*dot.seeSight/2)-((int)dot.y-dot.seeSight/2)%m.size); j<=((int)dot.y+3*dot.seeSight/2); j+=terrainSize)
			{
				if(distance(i+(int)terrainSize/2,j+(int)terrainSize/2,dot.x,dot.y)<=dot.h/2 
						&& !pointInTerainGreenIt(new Point(i+terrainSize/2,j+terrainSize/2), m) 
						&& !canYellow(new Point(i+terrainSize/2,j+terrainSize/2), m,dot.offset))
				{
					synchronized(Map.terrain){
					Map.terrain.add(new Terrain(i, j, (int)terrainSize, (int)terrainSize, 0, 2));
					}
				}
				else
					if(distance(i+(int)terrainSize/2,j+(int)terrainSize/2,dot.x,dot.y)<=dot.seeSight/2-5)
						if(!pointInTerain(new Point(i+(int)terrainSize/2,j+(int)terrainSize/2), m) 
						&& !canYellow(new Point(i+(int)terrainSize/2,j+(int)terrainSize/2),m,dot.offset))
				{
					synchronized(Map.terrain){
						Map.terrain.add(new Terrain(i, j, (int)terrainSize, (int)terrainSize, 0, 1));
					}
							
				}
			}
		
	 // dodawanie przeszkod
	for(int i=(((int)dot.x-3*dot.seeSight/2)-((int)dot.x-dot.seeSight/2)%m.size); i<=((int)dot.x+3*dot.seeSight/2); i+=terrainSize)
		for(int j=(((int)dot.y-3*dot.seeSight/2)-((int)dot.y-dot.seeSight/2)%m.size); j<=((int)dot.y+3*dot.seeSight/2); j+=terrainSize)
		{
				if(distance(i+(int)terrainSize/2,j+(int)terrainSize/2,dot.x,dot.y)<=dot.seeSight/2+terrainSize-1
					&& pointInside(new Point(i+1,j+1),m)
					&& !knownObjectsContains(m,new Rectangle(i,j,(int)terrainSize,(int)terrainSize)))
					synchronized(Map.knownObjects){
							Map.knownObjects.add(new Wall(i,j,(int)terrainSize,(int)terrainSize,(int)dot.offset,Color.red));
					}
			}

	}
	private int distance(int i, int j, double x, double y) {
		return (int)Math.sqrt((i-x)*(i-x)+(j-y)*(j-y));
	}

	private boolean knownObjectsContains(Map m, Rectangle wall) {
		for(Wall w : Map.knownObjects)
			if(w.intersects(wall))
		return true;
		return false;
	}
	private boolean pointInside(Point point,Map m) {
		for(Wall o: Map.objects)
		{
			if(o.pointInside(point, o.makeRectangle(1)))
				return true;
		}
		return false;
	}
	private boolean canYellow(Point point, Map m, int offset) {
		synchronized (Map.knownObjects) {
			for(Wall o: Map.knownObjects)
			{
				if(o.pointInside(point, o.makeRectangle(m.offset-1)))
					return true;
			}
		}
		return false;
	}
	private boolean pointInTerain(Point point, Map m) {
		synchronized(Map.terrain){
			for(Terrain o: Map.terrain)
			{
				if(o.pointInside(point, o.makeRectangle()))
					return true;
			}
		}
		return false;
	}
	private boolean pointInTerainGreenIt(Point point, Map m) {
		synchronized(Map.terrain){
			for(Terrain o: Map.terrain)
			{
				if(o.pointInside(point, o.makeRectangle())){
					o.GreenIt();
					return true;
				}
			}
		}
		return false;
	}

}
