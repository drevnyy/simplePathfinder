package objects;

public class Wall  extends Obj {

	
	
	public Wall(int xx,int yy, int ww, int hh){
		x=xx;
		y=yy;
		w=ww;
		h=hh;
		id=++ids;
	}
	
	public void draw()
	{
		System.out.println(x+" "+y+" "+w+" "+h);
		
	}
	
	
}
