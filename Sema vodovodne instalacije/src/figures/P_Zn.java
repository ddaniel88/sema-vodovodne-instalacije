package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public final class P_Zn extends Figure {
	public P_Zn() {
		super();
	}
	
	public P_Zn(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	@Override
	public boolean draw(float x, float y, float width, float height, Graphics g) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		return draw(g);
	}

	@Override
	public boolean draw(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		
		double radius_2 = height / 2;
		
		double sin30 = 0.5;
		double cos30 = Math.sqrt(3) / 2;
		double offsetX = radius_2 * cos30;
		double offsetY = radius_2 * sin30;
		
		Rectangle2D rectangleForArc = new Rectangle();
		// left arc
		rectangleForArc.setRect(x - radius_2, y, height, height);
		graphics.draw(new Arc2D.Double(rectangleForArc, 290, 140, Arc2D.OPEN));
		
		graphics.draw(new Line2D.Double(x, y + radius_2 - offsetY, x + offsetX, y + radius_2 - offsetY));
		graphics.draw(new Line2D.Double(x + offsetX, y + radius_2 - offsetY, x + offsetX, y + radius_2 + offsetY));
		graphics.draw(new Line2D.Double(x, y + radius_2 + offsetY, x + offsetX, y + radius_2 + offsetY));
		
		graphics.draw(new Line2D.Double(x + radius_2, y + radius_2, x + width, y + radius_2));
		
		return true;
	}
}
