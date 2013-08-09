package figures;

import helper.DrawHelper;
import helper.IntersectionPoints;
import helper.QuadraticEquationResult;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public final class Mmk_Ln extends Figure {
	public Mmk_Ln() {
		super();
	}

	public Mmk_Ln(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	@Override
	public boolean draw(float x, float y, float width, float height, Graphics g) {
		super.x = x;
		super.y = y;
		super.width = width;
		super.height = height;

		return draw(g);
	}

	@Override
	public boolean draw(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;

		float radius = height;
		float radius_2 = height / 2;

		double sin_cos45 = Math.sqrt(2) / 2;
		double offset = sin_cos45 * radius_2;
		
		double p = x + radius_2 - offset / 2;
		double q = y + radius_2 + offset / 2;
		
		Point startPoint, middlePoint, endPoint;
		double y_intersect;
		double x_intersect;
		
		graphics.draw(new Rectangle2D.Double(x, y, width, height));
		
		// for LEFT ARC
		// Y coordinate of intersect circle and line
		QuadraticEquationResult verticalIntersect =
				IntersectionPoints.getVerticalResultFromPQR(p, q, radius_2, x);
		if (verticalIntersect.getX1() >= y && verticalIntersect.getX1() <= y + height) {
			y_intersect = verticalIntersect.getX1();
		}
		else {
			y_intersect = verticalIntersect.getX2();
		}
		
		QuadraticEquationResult horizontalIntersect =
				IntersectionPoints.getHorizontalResultFromPQR(p, q, radius_2, y + height);
		if (horizontalIntersect.getX1() >= x && horizontalIntersect.getX1() <= x + width) {
			x_intersect = horizontalIntersect.getX1();
		}
		else {
			x_intersect = horizontalIntersect.getX2();
		}
		
		startPoint = new Point(x_intersect, y + height);
		middlePoint = new Point(p, y + offset / 2);
		endPoint = new Point(x, y_intersect);
		graphics.draw(DrawHelper.makeArc(startPoint, middlePoint, endPoint));
		
		// for RIGHT ARC
		p = p + width - radius;
		
		startPoint = new Point(x + width, y_intersect);
		middlePoint = new Point(p, y + offset);
		endPoint = new Point(x + width - (x_intersect - x), y + height);
		graphics.draw(DrawHelper.makeArc(startPoint, middlePoint, endPoint));

		
		// for MIDDLE ARC
		// start point is on right arc
		startPoint = new Point(x + width - radius_2 - offset / 2,
									 y - offset / 2 + radius_2);
		
		// middle point is on top center
		middlePoint = new Point(x + width / 2, y);
		
		// end point is on left arc
		endPoint = new Point(x + offset * 2,
								   y - offset / 2 + radius_2);
		
		graphics.draw(DrawHelper.makeArc(startPoint, middlePoint, endPoint));
		
		return true;
	}
}