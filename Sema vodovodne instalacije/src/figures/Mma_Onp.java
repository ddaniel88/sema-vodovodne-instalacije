package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

public final class Mma_Onp extends Figure {
	public Mma_Onp() {
		super();
	}
	
	public Mma_Onp(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	@Override
	public boolean draw(float x, float y, float width, float height, Graphics g) {
		Graphics2D graphics = (Graphics2D) g;

		super.x = x;
		super.y = y;
		super.width = width;
		super.height = height;
		
		// |-
		graphics.drawLine((int) x, (int) y, (int) x, (int) (y + height));		// |
		graphics.drawLine((int) x, (int) (y + height / 2),
				(int) (x + width), (int) (y + height / 2));			//  -

		return true;
	}

	@Override
	public boolean draw(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		
		float radius = height * 2 / 3;
		float offset = height / 3;
		
		Rectangle2D r2d = new Rectangle();
		Rectangle2D or = new Rectangle();
		r2d.setRect(x + width - radius, y + offset, radius * 2, radius * 2);
		or.setRect(x - radius, y + offset, radius * 2, radius * 2);
		
		// )-(
		graphics.draw(new Arc2D.Double(or, 270, 180, Arc2D.OPEN));
		graphics.draw(new Arc2D.Double(r2d, 90, 180, Arc2D.OPEN));
				
				
//		graphics.drawArc((int) (x - radius - offset), (int) (y + offset),
//						 (int) (height - offset), (int) (height - offset),
//						 -90, 180);												// )
//		graphics.drawLine((int) (x + radius - offset),
//						  (int) (y + radius + offset),
//						  (int) (x + width - radius + offset),
//						  (int) (int) (y + radius + offset));						// -
//		graphics.drawArc((int) (x + width - radius + offset), (int) (y + offset),
//						 (int) (height - offset), (int) (height - offset),
//						 90, 180);												// (

		return true;
	}

}
