package figures;

import java.awt.geom.Point2D;

public final class Point {
	private float x;
	private float y;
	
	public Point() {
		x = 0;
		y = 0;
	}
	
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(double x, double y) {
		this.x = (float)x;
		this.y = (float)y;
	}
	
	public Point(Point2D.Float p) {
		this.x = p.x;
		this.y = p.y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void movePointFor(float dx, float dy) {
		this.x += dx;
		this.y += dy;
	}
	
	public void movePointTo(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Point2D.Float getPoint2D(){
		Point2D.Float toReturn = new Point2D.Float(x,y);
		
		return toReturn;
	}
	
	public Point2D.Double  getPoint2D_D() {
		Point2D.Double toReturn = new Point2D.Double(x,y);
		
		return toReturn;
	}
}
