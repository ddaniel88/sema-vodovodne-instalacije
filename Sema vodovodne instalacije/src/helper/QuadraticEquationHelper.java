package helper;

public class QuadraticEquationHelper {
	private double a, b, c;
	
	public Boolean setConstants(double a, double b, double c) {
		if (a == 0) {
			return false;
		}
		
		this.a = a;
		this.b = b;
		this.c = c;
		
		return true;
	}
	
	public static QuadraticEquationResult getResult(double a, double b, double c) {
		double x1 = (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
		double x2 = (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
		
		return new QuadraticEquationResult(x1, x2);
	}
}
