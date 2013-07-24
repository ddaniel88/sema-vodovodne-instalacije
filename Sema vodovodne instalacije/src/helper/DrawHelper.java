package helper;

import java.awt.geom.Arc2D;

import figures.Point;

public final class DrawHelper {

	/**
	 * Draw arc across 3 points
	 * @param s - start point
	 * @param mid - middle point
	 * @param e - end point
	 * @return Arc2D
	 */
	public static Arc2D makeArc(Point s, Point mid, Point e) {
		Point c = getCircleCenter(s, mid, e);
		double radius = Math.sqrt(Math.pow(c.getX() - s.getX(), 2)
				+ Math.pow(c.getY() - s.getY(), 2));

		double startAngle = makeAnglePositive(Math.toDegrees(-Math.atan2(
				s.getY() - c.getY(), s.getX() - c.getX())));
		double midAngle = makeAnglePositive(Math.toDegrees(-Math.atan2(
				mid.getY() - c.getY(), mid.getX() - c.getX())));
		double endAngle = makeAnglePositive(Math.toDegrees(-Math.atan2(e.getY()
				- c.getY(), e.getX() - c.getX())));

		// Now compute the phase-adjusted angles beginning from startAngle,
		// moving positive and negative.
		double midDecreasing = getNearestAnglePhase(startAngle, midAngle, -1);
		double midIncreasing = getNearestAnglePhase(startAngle, midAngle, 1);
		double endDecreasing = getNearestAnglePhase(midDecreasing, endAngle, -1);
		double endIncreasing = getNearestAnglePhase(midIncreasing, endAngle, 1);

		// Each path from start -> mid -> end is technically, but one will wrap
		// around the entire
		// circle, which isn't what we want. Pick the one that with the smaller
		// angular change.
		double extent = 0;
		if (Math.abs(endDecreasing - startAngle) < Math.abs(endIncreasing
				- startAngle)) {
			extent = endDecreasing - startAngle;
		} else {
			extent = endIncreasing - startAngle;
		}

		return new Arc2D.Double(c.getX() - radius, c.getY() - radius,
				radius * 2, radius * 2, startAngle, extent, Arc2D.OPEN);
	}

	private static Point getCircleCenter(Point a, Point b, Point c) {
		double ax = a.getX();
		double ay = a.getY();
		double bx = b.getX();
		double by = b.getY();
		double cx = c.getX();
		double cy = c.getY();

		double A = bx - ax;
		double B = by - ay;
		double C = cx - ax;
		double D = cy - ay;

		double E = A * (ax + bx) + B * (ay + by);
		double F = C * (ax + cx) + D * (ay + cy);

		double G = 2 * (A * (cy - by) - B * (cx - bx));
		if (G == 0.0)
			return null; // a, b, c must be collinear

		double px = (D * E - B * F) / G;
		double py = (A * F - C * E) / G;
		return new Point((float) px, (float) py);
	}

	private static double makeAnglePositive(double angleDegrees) {
		double ret = angleDegrees;
		if (angleDegrees < 0) {
			ret = 360 + angleDegrees;
		}
		return ret;
	}

	private static double getNearestAnglePhase(double limitDegrees,
			double sourceDegrees, int dir) {
		double value = sourceDegrees;
		if (dir > 0) {
			while (value < limitDegrees) {
				value += 360.0;
			}
		} else if (dir < 0) {
			while (value > limitDegrees) {
				value -= 360.0;
			}
		}
		return value;
	}
}
