package figures;

import helper.DrawHelper;

import java.awt.Graphics;
import java.awt.Graphics2D;

public final class Mmq_Ln extends Figure {
	public Mmq_Ln() {
		super();
	}

	public Mmq_Ln(float x, float y, float width, float height) {
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
		
		double radius_2 = height / 2;
		double radius_4 = radius_2 / 2;
		double yMiddle = radius_2 - Math.sqrt(radius_2 * radius_2 - radius_4 * radius_4);
		
		// left arc
		Point startPoint = new Point(x + radius_4, y + height);
		Point middlePoint = new Point(x + radius_4, y + yMiddle);
		Point endPoint = new Point(x, y);
		graphics.draw(DrawHelper.makeArc(startPoint, middlePoint, endPoint));
		
		// right arc
		startPoint = new Point(x + width, y);
		middlePoint = new Point(x + width - radius_4, y + yMiddle);
		endPoint = new Point(x + width - radius_4, y + height);
		graphics.draw(DrawHelper.makeArc(startPoint, middlePoint, endPoint));
		
		// middle arc
		startPoint = new Point(x + width - radius_2, y + radius_2);
		middlePoint = new Point(x + width / 2, y + radius_4);
		endPoint = new Point(x + radius_2, y + radius_2);
		graphics.draw(DrawHelper.makeArc(startPoint, middlePoint, endPoint));

		return true;
	}
}