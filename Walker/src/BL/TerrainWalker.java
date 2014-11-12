package BL;

import java.awt.Point;

import mapper.Map;
import objects.Terrain;

public class TerrainWalker {
	public Point GetNextPoint(Point myPos) {
		if(Map.terrain.size()>0){			
		Terrain t2=Map.terrain.get(0);
		int oldDist=100000000;
		for(Terrain t : Map.terrain)
				if(!t.IsGreen())
				{
					int dst=t.distance(myPos);
					if(dst>0 && dst<oldDist)
					{
						t2=t;
						oldDist=dst;
					}
				}
		if(!t2.IsGreen())
		{
			t2.Mark();
			return t2.GetCenter();
		}
		}
		return null;
	}
}
