package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Collection;


public class Obj {

	public double x,y,w,h;
	public Color color;
	public Color ofcolor;
	public int offset=0;
	public void draw(){

	}
	public void Draw(Graphics g) {
		g.fillOval(100, 100, 100, 100);
		
	}
	public Collection<? extends Point> getPoints() {
		return null;
	}





}


