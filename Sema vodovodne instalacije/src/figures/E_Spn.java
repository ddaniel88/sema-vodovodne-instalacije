package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;

public final class E_Spn extends Figure {
	public E_Spn() {
		super();
	}

	public E_Spn(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	public static boolean draw(float x,
							   float y,
							   float width,
							   float height,
							   Graphics g)
	{
		Graphics2D graphics = (Graphics2D) g;

		// |-(
		graphics.drawLine((int) x, (int) y, (int) x, (int) (y + height));		// |
		graphics.drawLine((int) x, (int) (y + height / 2),
				(int) (x + width - height), (int) (y + height / 2));		//  -
		graphics.drawArc((int) (x + width - height), (int) y, (int) (height), (int) height,
				90, 180);														//   (

		return true;
	}
}
