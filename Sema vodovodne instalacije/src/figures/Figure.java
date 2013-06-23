package figures;

public abstract class Figure implements IFigure {
	protected int x, y, width, height;
	
	public Figure() {
		
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
	public boolean moveFigure(int x, int y, Point endCanvas) {
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
	public boolean scaleFigure(int mX, int mY, Point endCanvas) {
		width *= mX;
		height *= mY;
		return x >= 0 &&
				y >= 0 &&
				x + width <= endCanvas.getX() &&
				y + height <= endCanvas.getY();
	}

	@Override
	public boolean scaleFigure(int multiple, Point endCanvas) {
		width *= multiple;
		height *= multiple;
		return x >= 0 &&
				y >= 0 &&
				x + width <= endCanvas.getX() &&
				y + height <= endCanvas.getY();
	}

	@Override
	public boolean resizeFigure(int newWidth, int newHeight, Point endCanvas) {
		this.width = newWidth;
		this.height = newHeight;
		return x >= 0 &&
				y >= 0 &&
				x + width <= endCanvas.getX() &&
				y + height <= endCanvas.getY();
	}
	
}
