package figures;

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
}
