package figures;

import helper.DrawHelper;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;
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

		Rectangle2D rectangleForArc = new Rectangle();
		// left arc
		rectangleForArc.setRect(x - radius_2 + offset, y + offset / 2, radius, radius);
		graphics.draw(new Arc2D.Double(rectangleForArc, 320, 174, Arc2D.OPEN));

		// right arc
		rectangleForArc.setRect(x + width - radius + offset / 2, y + offset / 2, radius, radius);
		graphics.draw(new Arc2D.Double(rectangleForArc, 50, 170, Arc2D.OPEN));

		// start point is on right arc
		Point startPoint = new Point(x + width - radius_2 - offset / 2,
									 y - offset / 2 + radius_2);
		
		// middle point is on top center
		Point middlePoint = new Point(x + width / 2, y);
		
		// end point is on left arc
		Point endPoint = new Point(x + offset * 2,
								   y - offset / 2 + radius_2);
		
		graphics.draw(DrawHelper.makeArc(startPoint, middlePoint, endPoint));
		
		return true;
	}
}