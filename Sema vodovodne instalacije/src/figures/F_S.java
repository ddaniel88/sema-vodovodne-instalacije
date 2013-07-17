package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;

public final class F_S extends Figure {
	public F_S() {
		super();
	}
	
	public F_S(float x, float y, float width, float height) {
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
		
		// |-
		graphics.drawLine((int) x, (int) y, (int) x, (int) (y + height));		// |
		graphics.drawLine((int) x, (int) (y + height / 2),
				(int) (x + width), (int) (y + height / 2));			//  -

		return true;
	}

}
