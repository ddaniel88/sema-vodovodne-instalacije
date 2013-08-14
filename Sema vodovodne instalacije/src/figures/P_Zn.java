package figures;

import helper.DrawHelper;

import java.awt.BasicStroke;
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

public final class P_Zn extends Figure {
	
	private Point p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12;
	private Point  cp2;
	
	public P_Zn() {
		super();
	}
	
	public P_Zn(float x, float y, float width, float height) {
		super(x, y, width, height);
		
		this.p1 = new Point(x ,y);
		this.p2 = new Point(x + width,y);
		this.p3 = new Point(x + width,y + height);
		this.p4 = new Point(x,y + height);
		
		this.p5 = new Point(x, y + height/4);
		this.p6 = new Point(x, y + height*3/4);
		this.p7 = new Point(x + height/3, y + height/4);
		this.p8 = new Point(x + height/3,  y + height*3/4);
		
		this.p9 = new Point(x + height/4, y);
		this.p10 = new Point(x + height/4, y + height);
		this.p11 = new Point(x + height/2, y+height/2);
		
		this.p12 = new Point(x + width, y+height/2);
		
		this.cp2 = new Point(x + width + 10, y + height/2);
	}

	@Override
	public boolean draw(float x, float y, float width, float height, Graphics g) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		return draw(g);
	}

//	@Override
//	public boolean draw(Graphics g) {
//		Graphics2D graphics = (Graphics2D) g;
//		
//		double radius_2 = height / 2;
//		
//		double sin30 = 0.5;
//		double cos30 = Math.sqrt(3) / 2;
//		double offsetX = radius_2 * cos30;
//		double offsetY = radius_2 * sin30;
//		
//		Rectangle2D rectangleForArc = new Rectangle();
//		// left arc
//		rectangleForArc.setRect(x - radius_2, y, height, height);
//		graphics.draw(new Arc2D.Double(rectangleForArc, 290, 140, Arc2D.OPEN));
//		
//		graphics.draw(new Line2D.Double(x, y + radius_2 - offsetY, x + offsetX, y + radius_2 - offsetY));
//		graphics.draw(new Line2D.Double(x + offsetX, y + radius_2 - offsetY, x + offsetX, y + radius_2 + offsetY));
//		graphics.draw(new Line2D.Double(x, y + radius_2 + offsetY, x + offsetX, y + radius_2 + offsetY));
//		
//		graphics.draw(new Line2D.Double(x + radius_2, y + radius_2, x + width, y + radius_2));
//		
//		return true;
//	}
	
	@Override
	public boolean draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D graphics = (Graphics2D) g;
		
		float radius = height * 2 / 3;
		float offset = height / 3;
		
		Color currentColor = graphics.getColor();

		Arc2D arc1 = DrawHelper.makeArc(p9, p11, p10);
		Line2D.Float l1 = new Line2D.Float(p5.getX(),p5.getY(),p7.getX(),p7.getY());
		Line2D.Float l2 = new Line2D.Float(p5.getX(),p5.getY(),p6.getX(),p6.getY());
		Line2D.Float l3 = new Line2D.Float(p8.getX(),p8.getY(),p6.getX(),p6.getY());
		Line2D.Float l4 = new Line2D.Float(p11.getX(),p11.getY(),p12.getX(),p12.getY());
		
		//Line2D.Float l2 = new Line2D.Float(p7.getX(),p7.getY(),p8.getX(),p8.getY());
		//Arc2D arc2 = DrawHelper.makeArc(p8, p7, p9);
		
		graphics.draw(l1);
		graphics.draw(l2);
		graphics.draw(l3);
		
		
		BasicStroke old = (BasicStroke) graphics.getStroke();
		
		 float dash1[] = {5.0f};
	   BasicStroke dashed =
	        new BasicStroke(1.0f,
	                        BasicStroke.CAP_BUTT,
	                        BasicStroke.JOIN_MITER,
	                        10.0f, dash1, 0.0f);
		graphics.setStroke(dashed);
		
		graphics.draw(l4);
		graphics.draw(arc1);
		
		graphics.setStroke(old);
		
		Ellipse2D.Float ee2 = new Ellipse2D.Float(cp2.getX() - 5, cp2.getY() - 5, 10.0F, 10.0F);
		
		if (this.getSelected())
			graphics.setColor(Color.RED);
		else
			graphics.setColor(Color.GRAY);
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
		
		GeneralPath path = new GeneralPath();
		path.append(new Line2D.Float(p1.getX(),p1.getY(),p2.getX(),p2.getY()), false);
		path.append(new Line2D.Float(p2.getX(),p2.getY(),p3.getX(),p3.getY()), false);
		path.append(new Line2D.Float(p3.getX(),p3.getY(),p4.getX(),p4.getY()), false);
		path.closePath();
		
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
		this.p11= new Point(p11);
		this.p12= new Point(p12);
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
