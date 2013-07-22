package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;

public final class Mmk_Ln extends Figure {
	public Mmk_Ln() {
		super();
	}

	public Mmk_Ln(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	@Override
	public boolean draw(float x,
					    float y,
					    float width,
					    float height,
					    Graphics g)
	{
		Graphics2D graphics = (Graphics2D) g;

		super.x = x;
		super.y = y;
		super.width = width;
		super.height = height;
		
		// )^(
				graphics.drawArc((int) (x - height / 2), (int) y,
								 (int) height, (int) height,
								 315, 90);												// )

//				graphics.drawArc((int) x, (int) (y + height / 4),
//								 (int) width, (int) height, 58, 64);						// ^
				
//				graphics.drawArc((int) (x + width - height / 2), (int) y,
//								 (int) (height), (int) height,
//								 90, 180-45);												// (

		return true;
	}
	
	@Override
	public boolean draw(Graphics g) {
		//////// NIŠTA NE RADI KAKO TREBA!!!!!!!!!!!
		
		
		Graphics2D graphics = (Graphics2D) g;
		
		double offset = height - Math.cos(45 * Math.PI / 180) * height;
		
		graphics.drawLine((int)x, (int)y, (int)x, (int)(y + height));
//		graphics.drawLine((int)(x + offset), (int)y, (int)(x + offset), (int)(y + height));
		graphics.drawLine((int)(x - offset), (int)y, (int)(x - offset), (int)(y + height));
		
		// )^(
		graphics.drawArc((int) ((double)x - offset), (int) y,
						 (int) height, (int) height,
						 315, 180);												// )

//		graphics.drawArc((int) (x), (int) (y),
//						 (int) (height * 2), (int) (height * 2), 53, 74);						// ^
		
		graphics.drawArc((int) ((double)x + (double)width - offset), (int) y,
						 (int) (height), (int) height,
						 45, 180);												// (

		return true;
	}
}