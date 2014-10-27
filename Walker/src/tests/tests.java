package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import objects.Obj;
import objects.Triangle;

import org.junit.Before;
import org.junit.Test;

public class tests {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void intersectingLines() {
		Point a=new Point(0,0);
		Point b=new Point(100,100);
		Point c=new Point(0,100);
		Point d=new Point(100,0);
		Obj o=new Triangle(a,b,c);
		assertEquals("",true, o.intersects(a,b,c,d));	
	}
	@Test
	public void intersectingLines2() {
		Point a=new Point(300,500);
		Point b=new Point(1000,1000);
		Point c=new Point(400,1000);
		Point d=new Point(600,500);
		Obj o=new Triangle(a,b,c);
		assertEquals("",true, o.intersects(a,b,c,d));	
	}
	@Test
	public void intersectingDeepLines() {
		Point a=new Point(0,0);
		Point b=new Point(100,100);
		Point c=new Point(0,100);
		Point d=new Point(10,0);
		Obj o=new Triangle(a,b,c);
		assertEquals("",true, o.intersects(a,b,c,d));	
	}
	@Test
	public void parallelNotTouchingLines() {
		Point a=new Point(0,0);
		Point b=new Point(100,100);
		Point c=new Point(0,100);
		Point d=new Point(100,200);
		Obj o=new Triangle(a,b,c);
		boolean can=o.intersects(a,b,c,d);
		assertEquals("",false, can);
	}
	@Test
	public void parallelTouchingLines() {
		Point a=new Point(0,0);
		Point b=new Point(100,100);
		Point c=new Point(31,100); // any
		Point d=new Point(100,100);
		Obj o=new Triangle(a,b,c);
		c=new Point(0,0); 
		boolean can=o.intersects(a,b,c,d);
		assertEquals("",false, can);
	}
	@Test
	public void notIntersectingLines() {
		Point a=new Point(0,0);
		Point b=new Point(100,100);
		Point c=new Point(150,150);
		Point d=new Point(100,200);
		Obj o=new Triangle(a,b,c);
		boolean can=o.intersects(a,b,c,d);
		assertEquals("",false, can);
	}
	@Test
	public void touchingLines1() {
		Point a=new Point(0,0);
		Point b=new Point(100,100);
		Point c=new Point(100,100);
		Point d=new Point(100,200);
		Obj o=new Triangle(a,b,c);
		boolean can=o.intersects(a,b,c,d);
		assertEquals("linie stykaj¹ siê prawy dolny - lewy górny róg",false, can);
	}
	@Test
	public void touchingLines2() {
		Point a=new Point(100,200);
		Point b=new Point(200,100);
		Point c=new Point(200,100);
		Point d=new Point(300,0);
		Obj o=new Triangle(a,b,c);
		boolean can=o.intersects(a,b,c,d);
		assertEquals("linie stykaj¹ siê lewy dolny - prawy górny róg",false, can);
	}
	@Test
	public void touchingTriangles() {
		Point a=new Point(100,100);
		Point b=new Point(200,200);
		Point c=new Point(200,100);
		Point d=new Point(300,0);
		Point e=new Point(400,0);
		Obj o=new Triangle(a,b,c);
		Triangle o2=new Triangle(c,d,e);
		boolean can=o.intersectsTriangle(o2);
		assertEquals("stykaj¹ce siê trójk¹ty",false, can);
	}
	@Test
	public void intersectingTriangles() {
		Point a=new Point(100,100);
		Point b=new Point(200,200);
		Point c=new Point(200,100);
		Point d=new Point(100,200);
		Point e=new Point(200,200);
		Obj o=new Triangle(a,b,c);
		Triangle o2=new Triangle(c,d,e);
		boolean can=o.intersectsTriangle(o2);
		assertEquals("nachodzace",true, can);
	}
	@Test
	public void triangleInsideTriangle() {
		Point a=new Point(100,100);
		Point b=new Point(200,200);
		Point c=new Point(200,100);
		Point d=new Point(110,110);
		Point e=new Point(200,200);
		Obj o=new Triangle(a,b,c);
		Triangle o2=new Triangle(c,d,e);
		boolean can=o.intersectsTriangle(o2);
		assertEquals("nachodzace",true, can);
	}
	@Test
	public void weirdlyIntersectingTriangle() {
		Point a=new Point(100,100);
		Point b=new Point(200,800);
		Point c=new Point(200,900);
		Point d=new Point(100,1500);

		Obj o=new Triangle(a,b,c);
		Triangle o2=new Triangle(c,d,b);
		boolean can=o.intersectsTriangle(o2);
		assertEquals("nachodzace",true, can);
	}
	
}
