package figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public final class Ventil_Ispusni extends Figure {
	private Point p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16;
	private Point cp1, cp2;
	
	public Ventil_Ispusni() {
		super();
	}
	
	public Ventil_Ispusni(float x, float y, float width, float height) {
		super(x, y, width, height);
		
		double height_2r = height / 2;
		double height_2 = height / 4;
		double width_4 = width / 4;
		double width_34 = 0.75 * width;
		
		this.p1 = new Point(x, y);
		this.p2 = new Point(x + width, y);
		this.p3 = new Point(x + width, y + height);
		this.p4 = new Point(x, y + height);
		
		this.p5 = new Point(x, y + height_2);
		this.p6 = new Point(x + width_4, y + height_2);
		this.p7 = new Point(x + width_34, y + height_2);
		this.p8 = new Point(x + width, y + height_2);
		this.p9 = new Point(x + width_4, y);
		this.p10 = new Point(x + width_34, y);
		this.p11 = new Point(x + width_34, y + height_2r);
		this.p12 = new Point(x + width_4, y + height_2r);
		
		this.p13 = new Point(x + width_34 - width / 8, y + 0.75 * height_2r);
		this.p14 = new Point(x + width_34 - width / 8, y + height);
		this.p15 = new Point(x + width_34 - width / 8 - width / 16, y + 0.75 * height_2r + height_2r);
		this.p16 = new Point(x + width_34 - width / 8 + width / 16, y + 0.75 * height_2r + height_2r);
		
		this.cp1 = new Point(x - 10, y + height_2);
		this.cp2 = new Point(x + width + 10, y + height_2);
	}

	@Override
	public boolean moveFigureFor(float x, float y, Point endCanvas) {
		this.p1.movePointFor(x, y);
		this.p2.movePointFor(x, y);
		this.p3.movePointFor(x, y);
		this.p4.movePointFor(x, y);
		this.p5.movePointFor(x, y);
		this.p6.movePointFor(x, y);
		this.p7.movePointFor(x, y);
		this.p8.movePointFor(x, y);
		this.p9.movePointFor(x, y);
		this.p10.movePointFor(x, y);
		this.p11.movePointFor(x, y);
		this.p12.movePointFor(x, y);
		this.p13.movePointFor(x, y);
		this.p14.movePointFor(x, y);
		this.p15.movePointFor(x, y);
		this.p16.movePointFor(x, y);
		
		GeneralPath path = new GeneralPath();
		path.append(new Line2D.Float(p1.getX(),p1.getY(),p2.getX(),p2.getY()), false);
		path.append(new Line2D.Float(p2.getX(),p2.getY(),p3.getX(),p3.getY()), false);
		path.append(new Line2D.Float(p3.getX(),p3.getY(),p4.getX(),p4.getY()), false);
		path.closePath();
		
		this.cp1.movePointFor(x, y);
		this.cp2.movePointFor(x, y);

		Rectangle2D bound = path.getBounds2D();
		this.x = (float)bound.getX();
		this.y = (float)bound.getY();
		this.height = (float)bound.getHeight();
		this.width = (float)bound.getWidth();

		return true;
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
		
		Line2D leftLine = new Line2D.Double(this.p5.getPoint2D_D(), this.p6.getPoint2D_D());
		Line2D rightLine = new Line2D.Double(this.p7.getPoint2D_D(), this.p8.getPoint2D_D());
		Line2D leftVerticalLine = new Line2D.Double(this.p9.getPoint2D_D(), this.p12.getPoint2D_D());
		Line2D rightVerticalLine = new Line2D.Double(this.p10.getPoint2D_D(), this.p11.getPoint2D_D());
		Line2D fromTopLeftLine = new Line2D.Double(this.p9.getPoint2D_D(), this.p11.getPoint2D_D());
		Line2D fromBottomLeftLine = new Line2D.Double(this.p12.getPoint2D_D(), this.p10.getPoint2D_D());
		Line2D ispustV = new Line2D.Double(this.p13.getPoint2D_D(), this.p14.getPoint2D_D());
		Line2D ispustHorizontal = new Line2D.Double(this.p15.getPoint2D_D(), this.p16.getPoint2D_D());
		
		graphics.draw(leftLine);
		graphics.draw(rightLine);
		graphics.draw(leftVerticalLine);
		graphics.draw(rightVerticalLine);
		graphics.draw(fromTopLeftLine);
		graphics.draw(fromBottomLeftLine);
		graphics.draw(ispustV);
		graphics.draw(ispustHorizontal);
        
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
		this.p11= new Point(p11);
		this.p12= new Point(p12);
		this.p13= new Point(p13);
		this.p14= new Point(p14);
		this.p15= new Point(p15);
		this.p16= new Point(p16);
		this.cp1 = new Point(cp1);
		this.cp2 = new Point(cp2);
		
		GeneralPath path = new GeneralPath();
		path.append(new Line2D.Float(this.p1.getX(), this.p1.getY(), this.p2.getX(), this.p2.getY()), false);
		path.append(new Line2D.Float(this.p2.getX(), this.p2.getY(), this.p3.getX(), this.p3.getY()), false);
		path.append(new Line2D.Float(this.p3.getX(), this.p3.getY(), this.p4.getX(), this.p4.getY()), false);
		path.closePath();
		
		Rectangle2D bound = path.getBounds2D();
		this.x = (float)bound.getX();
		this.y = (float)bound.getY();
		this.height = (float)bound.getHeight();
		this.width = (float)bound.getWidth();

		return true;
	}
}
