package figures;

public abstract class Figure implements IFigure {
	protected float x, y, width, height;
	
	public Figure() {
		
	}
	
	public Figure(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public Point getCentralPosition() {
		return new Point(x + width / 2, y + height / 2);
	}

	@Override
	public Point getStartPosition() {
		return new Point(x, y);
	}

	@Override
	public boolean moveFigure(float x, float y, Point endCanvas) {
		return x >= 0 &&
				y >= 0 &&
				x + width <= endCanvas.getX() &&
				y + height <= endCanvas.getY();
	}

	@Override
	public boolean moveFigure(Point p, Point endCanvas) {
		return p.getX() >= 0 &&
				p.getY() >= 0 &&
				p.getX() + width <= endCanvas.getX() &&
				p.getY() + height <= endCanvas.getY();
	}

	@Override
	public boolean scaleFigure(float mX, float mY, Point endCanvas) {
		width *= mX;
		height *= mY;
		return x >= 0 &&
				y >= 0 &&
				x + width <= endCanvas.getX() &&
				y + height <= endCanvas.getY();
	}

	@Override
	public boolean scaleFigure(float multiple, Point endCanvas) {
		width *= multiple;
		height *= multiple;
		return x >= 0 &&
				y >= 0 &&
				x + width <= endCanvas.getX() &&
				y + height <= endCanvas.getY();
	}

	@Override
	public boolean resizeFigure(float newWidth, float newHeight, Point endCanvas) {
		this.width = newWidth;
		this.height = newHeight;
		return x >= 0 &&
				y >= 0 &&
				x + width <= endCanvas.getX() &&
				y + height <= endCanvas.getY();
	}
	
}
