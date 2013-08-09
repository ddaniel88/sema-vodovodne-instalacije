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

public final class E_Spn extends Figure {
	
	private Point p1, p2, p3, p4, p5, p6;
	
	private Point cp1, cp2;
	
	public E_Spn() {
		super();
	}

	public E_Spn(float x, float y, float width, float height) {
		super(x, y, width, height);
		this.p1 = new Point(x ,y);
		this.p2 = new Point(x + width,y);
		this.p3 = new Point(x + width,y + height);
		this.p4 = new Point(x,y + height);
		this.p5 = new Point(x,y + height/2);
		this.p6 = new Point(x + width - height/2, y + height/2);
		
		this.cp1 = new Point(x - 10, y + height/2);
		this.cp2 = new Point(x + width, y + height/2);
		
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
	
	@Override
	
//	public boolean draw(Graphics g) {
//		Graphics2D graphics = (Graphics2D) g;
//		
//		// |-(
//		graphics.drawLine((int) x, (int) y, (int) x, (int) (y + height));		// |
//		graphics.drawLine((int) x, (int) (y + height / 2),
//				(int) (x + width - height/2), (int) (y + height / 2));			//  -
//		graphics.drawArc((int) (x + width - height/2), (int) y,
//						 (int) (height), (int) height,
//						 90, 180);												//   (
//
//		
//		return true;
//	}

	public boolean draw(Graphics g) {
		
		Graphics2D graphics = (Graphics2D) g;
		
		Color currentColor = graphics.getColor();

		Line2D.Float l1 = new Line2D.Float(p1.getX(),p1.getY(),p4.getX(),p4.getY());
		Line2D.Float l2 = new Line2D.Float(p5.getX(),p5.getY(),p6.getX(),p6.getY());
		Arc2D arc = DrawHelper.makeArc(p2, p6, p3);
		
		graphics.draw(l1);
		graphics.draw(l2);
		graphics.draw(arc);
		
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
		Point2D.Float cp1 = new Point2D.Float();
		Point2D.Float cp2 = new Point2D.Float();
		
		rotateAffineTransform.transform(this.p1.getPoint2D(), p1);
		rotateAffineTransform.transform(this.p2.getPoint2D(), p2);
		rotateAffineTransform.transform(this.p3.getPoint2D(), p3);
		rotateAffineTransform.transform(this.p4.getPoint2D(), p4);
		rotateAffineTransform.transform(this.p5.getPoint2D(), p5);
		rotateAffineTransform.transform(this.p6.getPoint2D(), p6);
		rotateAffineTransform.transform(this.cp1.getPoint2D(), cp1);
		rotateAffineTransform.transform(this.cp2.getPoint2D(), cp2);
		
		this.p1 = new Point(p1);
		this.p2 = new Point(p2);
		this.p3 = new Point(p3);
		this.p4 = new Point(p4);
		this.p5 = new Point(p5);
		this.p6 = new Point(p6);
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
