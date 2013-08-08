package helper;

public class IntersectionPoints {
	
	/**
	 * Calculate and return vertical intersection points
	 * @param p - p of circle
	 * @param q - q of circle
	 * @param r - circle radius
	 * @param x - x coordinate of line (usualy it's figure start or end X coordinate)
	 * @return two intersection points
	 */
	public static QuadraticEquationResult getVerticalResultFromPQR(double p, double q, double r, double x) {
		double a, b, c;
		a = 1;
		b = - 2 * q;
		c = - r * r + q * q + Math.pow(x - p, 2);
		
		return QuadraticEquationHelper.getResult(a, b, c);
	}
	
	/**
	 * Calculate and return horizontal intersection points
	 * @param p - p of circle
	 * @param q - q of circle
	 * @param r - circle radius
	 * @param y - y coordinate of line (usualy it's figure end Y coordinate)
	 * @return two intersection points
	 */
	public static QuadraticEquationResult getHorizontalResultFromPQR(double p, double q, double r, double y) {
		double a, b, c;
		a = 1;
		b = - 2 * p;
		c = - r * r + p * p + Math.pow(y - q, 2);
		
		return QuadraticEquationHelper.getResult(a, b, c);
	}
}
