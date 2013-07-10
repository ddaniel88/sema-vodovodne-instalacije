package figures;

import java.awt.event.MouseEvent;

public interface IFigure {
	/**
	 * Get figure's central position
	 * @return central position of figure
	 */
	Point getCentralPosition();
	
	/**
	 * Get figure's start point position
	 * @return start position of figure
	 */
	Point getStartPosition();
	
	/**
	 * Move figure to new start point position
	 * @param x - new X start position
	 * @param y - new Y start position
	 * @param endCanvas - end point of canvas
	 * @return true if all figure is visible, otherwise false
	 */
	boolean moveFigure(float x, float y, Point endCanvas);
	
	/**
	 * Move figure to new start point position
	 * @param p - new start point of figure
	 * @param endCanvas - end point of canvas
	 * @return true if all figure is visible, otherwise false
	 */
	boolean moveFigure(Point p, Point endCanvas);
	
	boolean moveFigureFor(float x, float y);
	
	/**
	 * Scale figure, start point of figure is fix
	 * @param mX - multiply for width
	 * @param mY - multiply for height
	 * @param endCanvas - end point of canvas
	 * @return
	 */
	boolean scaleFigure(float mX, float mY, Point endCanvas);
	
	/**
	 * Scale figure, start point of figure is fix
	 * @param multiple - multiply for width and height
	 * @param endCanvas - end point of canvas
	 * @return
	 */
	boolean scaleFigure(float multiple, Point endCanvas);
	
	/**
	 * Resize figure to new width and height
	 * @param newWidth - new figure's width
	 * @param newHeight - new figure's height
	 * @param endCanvas - end point of canvas
	 * @return - true if all figure is visible and size is greater than 0, otherwise false
	 */
	boolean resizeFigure(float newWidth, float newHeight, Point endCanvas);
	
	/**
	 * Check if mouse is over figure
	 * @param cursor - position of mouse cursor
	 * @return true if mouse cursor is over figure
	 */
	boolean isHit(Point cursor);
	
	/**
	 * Check if mouse is over figure
	 * @param cursorX - X position of mouse cursor
	 * @param cursorY - Y position of mouse cursor
	 * @return true if mouse cursor is over figure
	 */
	boolean isHit(float cursorX, float cursorY);
	
	boolean isHit(MouseEvent e);
	
	String getDescription();
	
	void setDescription(String description);
}








