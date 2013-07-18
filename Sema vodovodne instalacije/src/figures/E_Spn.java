package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public final class E_Spn extends Figure {
	public E_Spn() {
		super();
	}

	public E_Spn(float x, float y, float width, float height) {
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
		
		// |-(
		graphics.drawLine((int) x, (int) y, (int) x, (int) (y + height));		// |
		graphics.drawLine((int) x, (int) (y + height / 2),
				(int) (x + width - height/2), (int) (y + height / 2));			//  -
		graphics.drawArc((int) (x + width - height/2), (int) y,
						 (int) (height), (int) height,
						 90, 180);												//   (

		return true;
	}
	
	@Override
	
	public boolean draw(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		
		// |-(
		graphics.drawLine((int) x, (int) y, (int) x, (int) (y + height));		// |
		graphics.drawLine((int) x, (int) (y + height / 2),
				(int) (x + width - height/2), (int) (y + height / 2));			//  -
		graphics.drawArc((int) (x + width - height/2), (int) y,
						 (int) (height), (int) height,
						 90, 180);												//   (

		
		return true;
	}
}
