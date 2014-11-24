package mapper;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import mapper.Map;
import objects.Terrain;
import objects.Wall;

public class Merger {
	private static ArrayList<Rectangle> merge (ArrayList<Rectangle> merged)
	{
	int size=0;
	boolean combine=false;
	int id=0;
	Rectangle rect,Current;
	while (id < merged.size())
	{
	combine=false;
		Current=merged.get(id);
		for(int i=0; i<merged.size();i++)
		{
			if(i==id && i<merged.size()-1)i++;
			rect=merged.get(i);
			
			if(Current!=rect && Current.y==rect.y && Current.height==rect.height && Current.x<=rect.x && Current.x+Current.width>=rect.x-5)
			{

				if(rect.x+rect.width<=Current.x+Current.width)
					size=Current.width;
				else
					size=rect.x+rect.width-Current.x;
					
					Current.setBounds(Current.x, Current.y,size,Current.height);
					merged.remove(rect);
					rect=Current;
					combine=true;
					if(i>0)i--;
			}
			else if(Current!=rect && Current.x==rect.x && Current.width==rect.width && Current.y<=rect.y && Current.y+Current.height>=rect.y-5)
			{
				
				if(rect.y+rect.height<=Current.y+Current.height)
					size=Current.height;
				else
					size=rect.y+rect.height-Current.y;
					
					
					Current.setBounds(Current.x, Current.y,rect.width,size);
					merged.remove(rect);
					rect=Current;
					combine=true;
					if(i>0)i--;
				
				}
		}
		if(combine && id>0)id--;
		if(!combine){ id++;}
	}
	
	return merged;
	}
	public static void mergeRects(Map m)
	{
		synchronized(Map.knownObjects){
		Map.knownObjects=toWall(merge(toRects(Map.knownObjects)),m.size/m.terrainSizeInterval);
		}
	}
	public static void mergeTerrain(Map m)
	{	ArrayList<Terrain> greens=new ArrayList<Terrain>();
	ArrayList<Terrain> yellows=new ArrayList<Terrain>();
	for(Terrain o:Map.terrain)
		if(o.IsGreen())
			greens.add(o);
		else
			yellows.add(o);
	synchronized(Map.terrain){
		Map.terrain=toTerrain(merge(toRectsT(greens)));
		Map.terrain.addAll(yellows);
	}
	}
	private static ArrayList<Wall> toWall(ArrayList<Rectangle> merge,int offset) {
		ArrayList<Wall> walls=new ArrayList<Wall>();
		for(Rectangle o:merge)
		{
			walls.add(new Wall(o.x,o.y,o.width,o.height,offset,Color.red));
		}
		return walls;
}

	private static ArrayList<Rectangle> toRects(ArrayList<Wall> knownObjects) {
	ArrayList<Rectangle> rects=new ArrayList<Rectangle>();
	for(Wall o:knownObjects)
	{
		rects.add(o.makeRectangle());
	}
	return rects;
}
	private static ArrayList<Terrain> toTerrain(ArrayList<Rectangle> merge) {
		ArrayList<Terrain> terrain=new ArrayList<Terrain>();
		for(Rectangle o:merge)
		{
			
			terrain.add(new Terrain(o.x,o.y,o.width,o.height,0,Terrain.green));
		}
		return terrain;
}
	private static ArrayList<Rectangle> toRectsT(ArrayList<Terrain> knownObjects) {
	ArrayList<Rectangle> rects=new ArrayList<Rectangle>();
	for(Terrain o:knownObjects)
	{
		if(o.IsGreen())
		{
			rects.add(o.makeRectangle());
		}

	}
	return rects;
}
}
