package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;

public final class Mmr_Rn extends Figure {
	public Mmr_Rn() {
		super();
	}

	public Mmr_Rn(float x, float y, float width, float height) {
		super(x, y, width, height);
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
	
	public boolean draw(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		
		float radius_2 = height / 2;
		double sin_cos45 = Math.sqrt(2) / 2;
		double offset = sin_cos45 * radius_2;
		
		// )>(
		graphics.draw(new Arc2D.Double(x-height/2, y, height, height, 90, -180, Arc2D.OPEN));		// (
		
		graphics.draw(new Line2D.Double(x + offset * 1.08, y + offset / 2,
										x + width - height / 2, y + height / 2));	
		
		graphics.draw(new Line2D.Double(x + offset * 1.08, y + height - offset / 2,
				x + width - height / 2, y + height / 2)); 											//  >
		
		graphics.draw(new Arc2D.Double(x + width - height / 2, y,
						 			   height, height, 90, 180, Arc2D.OPEN));						//   (
		
		return true;
	}
}
