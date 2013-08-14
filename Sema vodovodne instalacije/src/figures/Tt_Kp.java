package figures;

import helper.DrawHelper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public final class Tt_Kp extends Figure {
	
	private Point p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12,p13, p14, p15, p16;
	private Point cp1, cp2;
	
	
	public Tt_Kp() {
		super();
	}
	
	public Tt_Kp(float x, float y, float width, float height) {
		super(x, y, width, height);
		
		float offset = height/6;
		
		this.p1 = new Point(x ,y);
		this.p2 = new Point(x + width,y);
		this.p3 = new Point(x + width,y + height);
		this.p4 = new Point(x,y + height);
		
		this.p5 = new Point(x,y + height/2+ offset);
		this.p6 = new Point(x, y + height/2 - offset);
		this.p7 = new Point(x, y + height/2);
		
		this.p8 = new Point(x + width, y+height/2);
		this.p9 = new Point(x + width, y + height/2 - offset);
		this.p10 = new Point(x + width, y + height/2 + offset);
		
		this.p11 = new Point(x + width/2, y );
		this.p12 = new Point(x + width/2-offset, y);
		this.p13 = new Point(x + width/2+offset, y);
		
		this.p14 = new Point(x + width/2, y+height);
		this.p15 = new Point(x + width/2-offset, y+height );
		this.p16 = new Point(x + width/2+offset, y+height );
		
		this.cp1 = new Point(x - 10, y + height/2);
		this.cp2 = new Point(x + width + 10, y + height/2);
	}

	@Override
	public boolean draw(float x, float y, float width, float height, Graphics g) {
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
//		float radius = height * 2 / 3;
//		float offset = height / 3;
//		
//		Rectangle2D rectangleForArc = new Rectangle();
//		// left arc
//		rectangleForArc.setRect(x - offset, y + offset, radius, radius);
//		graphics.draw(new Arc2D.Double(rectangleForArc, 270, 180, Arc2D.OPEN));
//		
//		// right arc
//		rectangleForArc.setRect(x + width - offset, y + offset, radius, radius);
//		graphics.draw(new Arc2D.Double(rectangleForArc, 90, 180, Arc2D.OPEN));
//		
//		// line between arcs
//		graphics.draw(new Line2D.Double(x + radius / 2, y + radius, x + width - radius / 2, y + radius));
//		
//		// vertical line on middle
//		graphics.draw(new Line2D.Double(x + width / 2, y, x + width / 2, y + offset + radius / 2));
//		
//		// horizontal line on middle
//		graphics.draw(new Line2D.Double(x + width / 2 - offset, y, x + width / 2 + offset, y));
//		
//		return true;
//	}
	
	@Override
	public boolean draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D graphics = (Graphics2D) g;
		
		
		Color currentColor = graphics.getColor();

	
		Line2D.Float l1 = new Line2D.Float(p5.getX(),p5.getY(),p6.getX(),p6.getY());
		Line2D.Float l2 = new Line2D.Float(p7.getX(),p7.getY(),p8.getX(),p8.getY());
		Line2D.Float l3 = new Line2D.Float(p11.getX(),p11.getY(),p14.getX(),p14.getY());
		Line2D.Float l4 = new Line2D.Float(p9.getX(),p9.getY(),p10.getX(),p10.getY());
		Line2D.Float l5 = new Line2D.Float(p15.getX(),p15.getY(),p16.getX(),p16.getY());
		Line2D.Float l6 = new Line2D.Float(p12.getX(),p12.getY(),p13.getX(),p13.getY());
		
		graphics.draw(l1);
		graphics.draw(l2);
		graphics.draw(l3);
		graphics.draw(l4);
		graphics.draw(l5);
		graphics.draw(l6);
		
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
		this.p14.movePointFor(dx, dy);
		this.p15.movePointFor(dx, dy);
		this.p16.movePointFor(dx, dy);
		
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
		Point2D.Float p14 = new Point2D.Float();
		Point2D.Float p15 = new Point2D.Float();
		Point2D.Float p16 = new Point2D.Float();
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
		rotateAffineTransform.transform(this.p14.getPoint2D(), p14);
		rotateAffineTransform.transform(this.p15.getPoint2D(), p15);
		rotateAffineTransform.transform(this.p16.getPoint2D(), p16);
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
		this.p13 = new Point(p13);
		this.p14= new Point(p14);
		this.p15 = new Point(p15);
		this.p16= new Point(p16);
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










