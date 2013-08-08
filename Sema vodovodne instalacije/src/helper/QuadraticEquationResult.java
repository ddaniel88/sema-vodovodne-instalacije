package helper;

public class QuadraticEquationResult {
	private double x1, x2;
	
	public QuadraticEquationResult() {
		
	}
	
	public QuadraticEquationResult(double x1, double x2) {
		this.x1 = x1;
		this.x2 = x2;
	}
	
	public void setX1(double x1) {
		this.x1 = x1;
	}
	
	public double getX1() {
		return x1;
	}
	
	public void setX2(double x2) {
		this.x2 = x2;
	}
	
	public double getX2() {
		return x2;
	}
	
	public void setResult(double x1, double x2) {
		this.x1 = x1;
		this.x2 = x2;
	}
}
