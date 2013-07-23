package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;

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
		Graphics2D graphics = (Graphics2D) g;

		super.x = x;
		super.y = y;
		super.width = width;
		super.height = height;
		
		// )>(
		graphics.drawArc((int) x, (int) y, (int) (height), (int) (height), 90, 180);		// (
		graphics.drawLine((int) x, (int) y,
				(int) (x + width - height/2), (int) (y + height / 2));			// >
		graphics.drawArc((int) (x + width - height/2), (int) y,
						 (int) (height), (int) height,
						 90, 180);												//  (

		return true;
	}
	
	@Override
	
	public boolean draw(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		
		// )>(
		graphics.drawArc((int) (x-height/2), (int) y, (int) (height), (int) (height), 90, -180);		// (
		graphics.drawLine((int) x, (int) y,
				(int) (x + width - height/2), (int) (y + height / 2));	
		graphics.drawLine((int) x, (int) (y+height),
				(int) (x + width - height/2), (int) (y + height / 2)); 			//  >
		graphics.drawArc((int) (x + width - height/2), (int) y,
						 (int) (height), (int) height,
						 90, 180);												//   (

		
		return true;
	}
}
