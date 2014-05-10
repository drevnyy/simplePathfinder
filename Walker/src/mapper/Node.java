package mapper;

public class Node {

	public int x,y;
	public double dirx,diry;
	
	public Node(double xx, double yy, double diirx, double diiry)
	{
		x=(int) xx;
		y=(int) yy;
		dirx=diirx;
		diry=diiry;
	}

	public Node setDirs(double mx, double my) {
		dirx=mx;
		diry=my;
		return this;
	}
}
