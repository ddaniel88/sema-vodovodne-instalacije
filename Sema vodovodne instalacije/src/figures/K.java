package figures;

import helper.DrawHelper;
import helper.IntersectionPoints;
import helper.QuadraticEquationResult;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public final class K extends Figure {

	private Point p1, p2, p3, p4, p5, p6, p7, p8, p9, p10;
	private Point cp1, cp2;

	public K() {
		super();
	}

	public K(float x, float y, float width, float height) {
		super(x, y, width, height);

		double radius = 1.5 * height; // 2 * (3/4)
		double radius_2 = radius / 2;
		
		this.p1 = new Point(x, y);
		this.p2 = new Point(x + width, y);
		this.p3 = new Point(x + width, y + height);
		this.p4 = new Point(x, y + height);
		
		// levi polukrug
		double pl = x + 0.25 * height;
		double ql = y + 0.25 * height;
		
		double left_intersect; // J ta�ka
		QuadraticEquationResult verticalIntersect =
				IntersectionPoints.getVerticalResultFromPQR(pl, ql, radius_2, x);
		if (verticalIntersect.getX1() >= y && verticalIntersect.getX1() <= y + height) {
			left_intersect = verticalIntersect.getX1();
		}
		else {
			left_intersect = verticalIntersect.getX2();
		}
		
		this.p5 = new Point(x, left_intersect); // J ta�ka
		this.p6 = new Point(x + 0.25 * height, y + height); // F ta�ka
		
		double up_left_intersect;
		QuadraticEquationResult horizontalIntersect =
				IntersectionPoints.getHorizontalResultFromPQR(pl, ql, radius_2, y + height);
		if (horizontalIntersect.getX1() >= x && horizontalIntersect.getX1() <= x + width) {
			up_left_intersect = horizontalIntersect.getX1();
		}
		else {
			up_left_intersect = horizontalIntersect.getX2();
		}
		
		this.p7 = new Point(up_left_intersect, y); // I ta�ka
		// levi polukrug - kraj
		
		// desni polukrug
		double radiusR = width * 4;
		double radiusR_2 = width * 2;
		
		// centar desnog luka
		double pr = x + width;
		double qr = y + height - 2 * width;
		
		//presek dve kru�nice...
		double a = pl;
		double b = ql;
		double c = pr;
		double d = qr;
		
		// rastojanje izme�u centra kru�nica (lukova)
		double D = Math.sqrt((c - a) * (c - a) + (d - b) * (d - b));
		double delta = 0.25 *
						  Math.sqrt((D + radius_2 + radiusR_2) *
									(D + radius_2 - radiusR_2) *
									(D - radius_2 + radiusR_2) *
									(-D + radius_2 + radiusR_2));
		
		double x1 = 0.5 * (a + c) + ((c - a) * ((radius_2 * radius_2 - radiusR_2 * radiusR_2)) / (2 * D * D)) +
					(2 * (b - d) * delta) / (D * D);
		double x2 = 0.5 * (a + c) + ((c - a) * ((radius_2 * radius_2 - radiusR_2 * radiusR_2)) / (2 * D * D)) -
				(2 * (b - d) * delta) / (D * D);
		
		double y1 = 0.5 * (b + d) + ((d - b) * ((radius_2 * radius_2 - radiusR_2 * radiusR_2)) / (2 * D * D)) -
				(2 * (a - c) * delta) / (D * D);
		double y2 = 0.5 * (b + d) + ((d - b) * ((radius_2 * radius_2 - radiusR_2 * radiusR_2)) / (2 * D * D)) +
				(2 * (a - c) * delta) / (D * D);
		
//		double tmp1 = (Math.pow(radius_2, 2) - Math.pow(radiusR_2, 2)) / (2 * Math.pow(D, 2));
//		double tmp2 = (2 * delta) / Math.pow(D, 2);
//		double tmpx = (a + c) / 2 + (c - a) * tmp1;
//		double tmpy = (b + d) / 2 + (d - b) * tmp2;
//		double x1 = tmpx + (b - d) / tmp2;
//		double x2 = tmpx - (b - d) / tmp2;
//		double y1 = tmpy - (a - c) / tmp2;
//		double y2 = tmpy + (a - c) / tmp2;
		
		// ta�ka preseka 2 kru�nice - T ta�ka
		if (x1 >= x && x1 <= x + width && y1 >= y && y1 <= y + height) {
			this.p8 = new Point(x1, y1);
		}
		else {
			this.p8 = new Point(x2, y2);
		}
		
//		// drugi na�in
//		double de2 = (pr - pl) * (pr - pl) + (qr - ql) * (qr - ql); 
//		double K_ = 0.25 * Math.sqrt(((radius_2 + radiusR_2) * (radius_2 + radiusR_2) - de2) *
//				(de2 - (radius_2 - radiusR_2) * (radius_2 - radiusR_2)));
//		
//		x1 = 0.5 * (pr + pl) + 0.5 * (pr - pl) * (radius_2 * radius_2 - radiusR_2 * radiusR_2) / de2 + 2 * (qr - ql) * K_ / de2;
//		x2 = 0.5 * (pr + pl) + 0.5 * (pr - pl) * (radius_2 * radius_2 - radiusR_2 * radiusR_2) / de2 - 2 * (qr - ql) * K_ / de2;
//		
//		y1 = 0.5 * (qr + ql) + 0.5 * (qr - ql) * (radius_2 * radius_2 - radiusR_2 * radiusR_2) / de2 - 2 * (pr - pl) * K_ / de2;
//		y2 = 0.5 * (qr + ql) + 0.5 * (qr - ql) * (radius_2 * radius_2 - radiusR_2 * radiusR_2) / de2 + 2 * (pr - pl) * K_ / de2;
//		
//		if (x1 >= x && x1 <= x + width && y1 >= y && y1 <= y + height) {
//			this.p8 = new Point(x1, y1);
//		}
//		else {
//			this.p8 = new Point(x2, y2);
//		}
		
		double middle_intersect;
		QuadraticEquationResult rightVerticalIntersect =
				IntersectionPoints.getVerticalResultFromPQR(pr, qr, radiusR_2, x);
		if (rightVerticalIntersect.getX1() >= y && rightVerticalIntersect.getX1() <= y + height) {
			middle_intersect = rightVerticalIntersect.getX1();
		}
		else {
			middle_intersect = rightVerticalIntersect.getX2();
		}
		
		this.p9 = new Point(x + width / 2, y + height - (middle_intersect - y));
		this.p10 = new Point(x + width, y + height);
		
		// desni polukrug - kraj

		this.cp1 = new Point(x - 10, y + height * 2 / 3);
		this.cp2 = new Point(x + width + 10, y + height * 2 / 3);
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
		// TODO Auto-generated method stub
		Graphics2D graphics = (Graphics2D) g;

		Color currentColor = graphics.getColor();

		Arc2D arc1 = DrawHelper.makeArc(p5, p6, p7);
		Arc2D arc2 = DrawHelper.makeArc(p8, p9, p10);
		
		graphics.draw(arc1);
		graphics.draw(arc2);

		Ellipse2D.Float ee1 = new Ellipse2D.Float(cp1.getX() - 5,
				cp1.getY() - 5, 10.0F, 10.0F);
		Ellipse2D.Float ee2 = new Ellipse2D.Float(cp2.getX() - 5,
				cp2.getY() - 5, 10.0F, 10.0F);

		if (this.getSelected())
			graphics.setColor(Color.RED);
		else
			graphics.setColor(Color.GRAY);
		graphics.fill(ee1);
		graphics.fill(ee2);

		graphics.setColor(currentColor);

		return true;
	}

	@Override
	public boolean moveFigureFor(float dx, float dy, Point endCanvas) {

		this.p1.movePointFor(dx, dy);
		this.p2.movePointFor(dx, dy);
		this.p3.movePointFor(dx, dy);
		this.p4.movePointFor(dx, dy);
		this.p5.movePointFor(dx, dy);
		this.p6.movePointFor(dx, dy);
		this.p7.movePointFor(dx, dy);
		this.p8.movePointFor(dx, dy);
		this.p9.movePointFor(dx, dy);
		this.p10.movePointFor(dx, dy);

		GeneralPath path = new GeneralPath();
		path.append(
				new Line2D.Float(p1.getX(), p1.getY(), p2.getX(), p2.getY()),
				false);
		path.append(
				new Line2D.Float(p2.getX(), p2.getY(), p3.getX(), p3.getY()),
				false);
		path.append(
				new Line2D.Float(p3.getX(), p3.getY(), p4.getX(), p4.getY()),
				false);
		path.closePath();

		this.cp1.movePointFor(dx, dy);
		this.cp2.movePointFor(dx, dy);

		Rectangle2D bound = path.getBounds2D();
		this.x = (float) bound.getX();
		this.y = (float) bound.getY();
		this.height = (float) bound.getHeight();
		this.width = (float) bound.getWidth();

		return true;
	}

	@Override
	public boolean rotateFigure(double angle, Graphics2D g) {

		AffineTransform rotateAffineTransform = AffineTransform
				.getRotateInstance(angle, this.getCentralPosition().getX(),
						this.getCentralPosition().getY());
		Point2D.Float p1 = new Point2D.Float();
		Point2D.Float p2 = new Point2D.Float();
		Point2D.Float p3 = new Point2D.Float();
		Point2D.Float p4 = new Point2D.Float();
		Point2D.Float p5 = new Point2D.Float();
		Point2D.Float p6 = new Point2D.Float();
		Point2D.Float p7 = new Point2D.Float();
		Point2D.Float p8 = new Point2D.Float();
		Point2D.Float p9 = new Point2D.Float();
		Point2D.Float p10 = new Point2D.Float();
		Point2D.Float cp1 = new Point2D.Float();
		Point2D.Float cp2 = new Point2D.Float();

		rotateAffineTransform.transform(this.p1.getPoint2D(), p1);
		rotateAffineTransform.transform(this.p2.getPoint2D(), p2);
		rotateAffineTransform.transform(this.p3.getPoint2D(), p3);
		rotateAffineTransform.transform(this.p4.getPoint2D(), p4);
		rotateAffineTransform.transform(this.p5.getPoint2D(), p5);
		rotateAffineTransform.transform(this.p6.getPoint2D(), p6);
		rotateAffineTransform.transform(this.p7.getPoint2D(), p7);
		rotateAffineTransform.transform(this.p8.getPoint2D(), p8);
		rotateAffineTransform.transform(this.p9.getPoint2D(), p9);
		rotateAffineTransform.transform(this.p10.getPoint2D(), p10);
		rotateAffineTransform.transform(this.cp1.getPoint2D(), cp1);
		rotateAffineTransform.transform(this.cp2.getPoint2D(), cp2);

		this.p1 = new Point(p1);
		this.p2 = new Point(p2);
		this.p3 = new Point(p3);
		this.p4 = new Point(p4);
		this.p5 = new Point(p5);
		this.p6 = new Point(p6);
		this.p7 = new Point(p7);
		this.p8 = new Point(p8);
		this.p9 = new Point(p9);
		this.p10 = new Point(p10);
		this.cp1 = new Point(cp1);
		this.cp2 = new Point(cp2);

		GeneralPath path = new GeneralPath();
		path.append(
				new Line2D.Float(this.p1.getX(), this.p1.getY(),
						this.p2.getX(), this.p2.getY()), false);
		path.append(
				new Line2D.Float(this.p2.getX(), this.p2.getY(),
						this.p3.getX(), this.p3.getY()), false);
		path.append(
				new Line2D.Float(this.p3.getX(), this.p3.getY(),
						this.p4.getX(), this.p4.getY()), false);
		path.closePath();

		Rectangle2D bound = path.getBounds2D();
		this.x = (float) bound.getX();
		this.y = (float) bound.getY();
		this.height = (float) bound.getHeight();
		this.width = (float) bound.getWidth();

		return true;
	}

}