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

public final class Mmk_Ln extends Figure {
	
	private Point p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13;
	private Point cp1, cp2;
	
	public Mmk_Ln() {
		super();
	}

	public Mmk_Ln(float x, float y, float width, float height) {
		super(x, y, width, height);
		
		this.p1 = new Point(x ,y);
		this.p2 = new Point(x + width,y);
		this.p3 = new Point(x + width,y + height);
		this.p4 = new Point(x,y + height);
		
		
		//ostale taèke
		
		float radius = height;
		float radius_2 = height / 2;

		double sin_cos45 = Math.sqrt(2) / 2;
		double offset = sin_cos45 * radius_2;
		
		double p = x + radius_2 - offset / 2;
		double q = y + radius_2 + offset / 2;
		
		Point startPoint, middlePoint, endPoint;
		double y_intersect;
		double x_intersect;
		
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
		
		//levi polukrug
		
		this.p5 = new Point(x_intersect, y + height);
		this.p6 = new Point(p, y + offset / 2);
		this.p7 = new Point(x, y_intersect);
		
		this.cp1 = new Point(x+10, q+10);
		
		//desni polukrug
		p = p + width - radius;
		
		this.p8 = new Point(x + width, y_intersect);
		this.p9= new Point(p, y + offset);
		this.p10 = new Point(x + width - (x_intersect - x), y + height);
		
		
		//srednji polukrug
		this.p11 = new Point(x + width - radius_2 - offset / 2, y - offset / 2 + radius_2);

		// middle point is on top center
		this.p12 = new Point(x + width / 2, y);

		// end point is on left arc
		this.p13 = new Point(x + offset * 2, y - offset / 2 + radius_2);
		
		
		this.cp2 = new Point(x+width-10, q+10);
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
		Color currentColor = graphics.getColor();

		Arc2D arc1 = DrawHelper.makeArc(p5, p6, p7);
		Arc2D arc2 = DrawHelper.makeArc(p8, p9, p10);
		Arc2D arc3 = DrawHelper.makeArc(p11, p12, p13);
		
		graphics.draw(arc1);
		graphics.draw(arc2);
		graphics.draw(arc3);
		
		Ellipse2D.Float ee1 = new Ellipse2D.Float(cp1.getX() - 5, cp1.getY() - 5, 10.0F, 10.0F);
		Ellipse2D.Float ee2 = new Ellipse2D.Float(cp2.getX() - 5, cp2.getY() - 5, 10.0F, 10.0F);
		
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
		this.p11.movePointFor(dx, dy);
		this.p12.movePointFor(dx, dy);
		this.p13.movePointFor(dx, dy);
		
		GeneralPath path = new GeneralPath();
		path.append(new Line2D.Float(p1.getX(),p1.getY(),p2.getX(),p2.getY()), false);
		path.append(new Line2D.Float(p2.getX(),p2.getY(),p3.getX(),p3.getY()), false);
		path.append(new Line2D.Float(p3.getX(),p3.getY(),p4.getX(),p4.getY()), false);
		path.closePath();
		
		this.cp1.movePointFor(dx, dy);
		this.cp2.movePointFor(dx, dy);

		Rectangle2D bound = path.getBounds2D();
		this.x = (float)bound.getX();
		this.y = (float)bound.getY();
		this.height = (float)bound.getHeight();
		this.width = (float)bound.getWidth();

		return true;
	}
	
	@Override
	public boolean rotateFigure(double angle, Graphics2D g) {

		AffineTransform rotateAffineTransform = AffineTransform.getRotateInstance(angle, this.getCentralPosition().getX(), this.getCentralPosition().getY());
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
		Point2D.Float p11 = new Point2D.Float();
		Point2D.Float p12 = new Point2D.Float();
		Point2D.Float p13 = new Point2D.Float();
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
		rotateAffineTransform.transform(this.p11.getPoint2D(), p11);
		rotateAffineTransform.transform(this.p12.getPoint2D(), p12);
		rotateAffineTransform.transform(this.p13.getPoint2D(), p13);
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
		this.p10= new Point(p10);
		this.p11 = new Point(p11);
		this.p12= new Point(p12);
		this.p13= new Point(p13);
		this.cp1 = new Point(cp1);
		this.cp2 = new Point(cp2);
		
		GeneralPath path = new GeneralPath();
		path.append(new Line2D.Float(this.p1.getX(),this.p1.getY(),this.p2.getX(),this.p2.getY()), false);
		path.append(new Line2D.Float(this.p2.getX(),this.p2.getY(),this.p3.getX(),this.p3.getY()), false);
		path.append(new Line2D.Float(this.p3.getX(),this.p3.getY(),this.p4.getX(),this.p4.getY()), false);
		path.closePath();
		
		Rectangle2D bound = path.getBounds2D();
		this.x = (float)bound.getX();
		this.y = (float)bound.getY();
		this.height = (float)bound.getHeight();
		this.width = (float)bound.getWidth();

		return true;
	}

}