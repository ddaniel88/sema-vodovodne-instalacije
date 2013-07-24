package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;

public final class U_Sn extends Figure {
	public U_Sn() {
		super();
	}

	public U_Sn(float x, float y, float width, float height) {
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
		
		// )=(
		graphics.drawArc((int) (x - height / 2), (int) y,
						 (int) height, (int) height,
						 -90, 180);												// )
		graphics.drawLine((int) (x + height / 2),
						  (int) (y + height / 2 - height / 8),
						  (int) (x + width - height / 2),
						  (int) (int) (y + height / 2 - height / 8));			// -
		graphics.drawLine((int) (x + height / 2),
						  (int) (y + height / 2 + height / 8),
						  (int) (x + width - height / 2),
						  (int) (y + height / 2 + height / 8));					// -
		graphics.drawArc((int) (x + width - height/2), (int) y,
						 (int) (height), (int) height,
						 90, 180);												// (

		return true;
	}
}
