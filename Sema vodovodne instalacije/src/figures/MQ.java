package figures;

import helper.DrawHelper;

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

public final class MQ extends Figure {
	
	private Point p1, p2, p3, p4, p5, p6, p7;
	private Point cp1, cp2;
	
	public MQ() {
		super();
	}

	public MQ(float x, float y, float width, float height) {
		super(x, y, width, height);
		
		double sin_cos45 = Math.sqrt(2) / 2;
		double radiusM = width / 3;
		double radiusM_2 = radiusM / 2;
		double radiusV_2 = height;
		double dxy = radiusV_2 * sin_cos45 - radiusV_2 * sin_cos45 / (radiusM + radiusM_2);
		
		this.p1 = new Point(x ,y);
		this.p2 = new Point(x + width,y);
		this.p3 = new Point(x + width,y + height);
		this.p4 = new Point(x,y + height);
		
		this.p5 = new Point(x + radiusM, y);
		this.p6 = new Point(x + radiusM_2, y + radiusM_2);
		this.p7 = new Point(x + width - dxy, y + dxy);
		
//		this.p5 = new Point(x, y)
		
//		this.p5 = new Point(x +  2* radius,y);
//		this.p6 = new Point(x + radius, y + radius);
//		this.p7  = new Point( x + width- radius2*sin_cos45,  y  +  radius2 * sin_cos45);
//		this.p8 = new Point(x + width - radius, y + height);
//		
//		this.p9 = new Point(x ,y);
		
		this.cp1 = new Point(x + radiusM_2 , y - 10);
		this.cp2 = new Point(x + width + 10, y + height);
	}

	@Override
	public boolean draw(float x,
					    float y,
					    float width,
					    float height,
					    Graphics g)
	{
		super.x = x;
		super.y = y;
		super.width = width;
		super.height = height;
		
		return draw(g);
	}
	
//	@Override
//	public boolean draw(Graphics g) {
//		Graphics2D graphics = (Graphics2D) g;
//		
//		// )=(
//		graphics.drawArc((int) (x - height / 2), (int) y,
//						 (int) height, (int) height,
//						 -90, 180);												// )
//		graphics.drawLine((int) (x + height / 2),
//						  (int) (y + height / 2 - height / 8),
//						  (int) (x + width - height / 2),
//						  (int) (int) (y + height / 2 - height / 8));			// -
//		graphics.drawLine((int) (x + height / 2),
//						  (int) (y + height / 2 + height / 8),
//						  (int) (x + width - height / 2),
//						  (int) (y + height / 2 + height / 8));					// -
//		graphics.drawArc((int) (x + width - height/2), (int) y,
//						 (int) (height), (int) height,
//						 90, 180);												// (
//
//		return true;
//	}

	public boolean draw(Graphics g) {
		
		Graphics2D graphics = (Graphics2D) g;
		
		Color currentColor = graphics.getColor();

		Arc2D arc1 = DrawHelper.makeArc(p1, p6, p5);
		Arc2D arc2 = DrawHelper.makeArc(p6, p7, p3);
		
		graphics.draw(arc1);
		graphics.draw(arc2);
		
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
		Point2D.Float cp1 = new Point2D.Float();
		Point2D.Float cp2 = new Point2D.Float();
		
		rotateAffineTransform.transform(this.p1.getPoint2D(), p1);
		rotateAffineTransform.transform(this.p2.getPoint2D(), p2);
		rotateAffineTransform.transform(this.p3.getPoint2D(), p3);
		rotateAffineTransform.transform(this.p4.getPoint2D(), p4);
		rotateAffineTransform.transform(this.p5.getPoint2D(), p5);
		rotateAffineTransform.transform(this.p6.getPoint2D(), p6);
		rotateAffineTransform.transform(this.p7.getPoint2D(), p7);
		rotateAffineTransform.transform(this.cp1.getPoint2D(), cp1);
		rotateAffineTransform.transform(this.cp2.getPoint2D(), cp2);
		
		this.p1 = new Point(p1);
		this.p2 = new Point(p2);
		this.p3 = new Point(p3);
		this.p4 = new Point(p4);
		this.p5 = new Point(p5);
		this.p6 = new Point(p6);
		this.p7 = new Point(p7);
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
