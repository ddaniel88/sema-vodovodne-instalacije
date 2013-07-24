package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public final class Mmb_On extends Figure {
	public Mmb_On() {
		super();
	}
	
	public Mmb_On(float x, float y, float width, float height) {
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
		
		float radius = height * 2 / 3;
		float offset = height / 3;
		
		Rectangle2D rectangleForArc = new Rectangle();
		// left arc
		rectangleForArc.setRect(x - offset, y + offset, radius, radius);
		graphics.draw(new Arc2D.Double(rectangleForArc, 270, 180, Arc2D.OPEN));
		
		// right arc
		rectangleForArc.setRect(x + width - offset, y + offset, radius, radius);
		graphics.draw(new Arc2D.Double(rectangleForArc, 90, 180, Arc2D.OPEN));
		
		// line between arcs
		graphics.draw(new Line2D.Double(x + radius / 2, y + radius, x + width - radius / 2, y + radius));
		
		// vertical line on middle
		graphics.draw(new Line2D.Double(x + width / 2, y + offset / 2, x + width / 2, y + offset + radius / 2));
		
		radius /= 2;
		offset /= 2;
		
		// arc on middle
		graphics.draw(new Arc2D.Double(x + width / 2 - offset, y - offset, radius, radius, 180, 180, Arc2D.OPEN));
		
		return true;
	}
	
	
}
