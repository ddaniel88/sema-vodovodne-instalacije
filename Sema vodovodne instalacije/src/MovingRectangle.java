import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class MovingRectangle extends JFrame {
	
	public static TransformingCanvas canvas;
	private static Kvadrat kvadrat;
	static Cursor curCursor;

/*	public MovingRectangle() {
		initUI();
	}

	private void initUI() {
		setTitle("Java 2D proba");
		//add(new Surface());
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
*/
	public static void main(String[] args) {
		
		
		JFrame frame = new JFrame();
		canvas = new TransformingCanvas();
		TranslateHandler translater = new TranslateHandler();
		canvas.addMouseListener(translater);
		canvas.addMouseMotionListener(translater);
		canvas.addMouseWheelListener(new ScaleHandler());
		frame.setLayout(new BorderLayout());
		frame.getContentPane().add(canvas, BorderLayout.CENTER);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				//MovingRectangle ms = new MovingRectangle();
				//ms.setVisible(true);
				
			}
		}); 
	
}
	
	
	public static class TransformingCanvas extends JComponent {
		private double translateX;
		private double translateY;
		private double scale;

		TransformingCanvas() {
			translateX = 0;
			translateY = 0;
			scale = 1;
			
			MovingAdapter ma = new MovingAdapter();
			addMouseMotionListener(ma);
			addMouseListener(ma);
			// addMouseWheelListener(new ScaleHandler());

			kvadrat = new Kvadrat(20, 20, 20, 20);
			// zell = new ZEllipse(150, 70, 80, 80);

			setOpaque(true);
			setDoubleBuffered(true);
		}
		
		

		public void doDrawing(Graphics g) {

			AffineTransform tx = new AffineTransform();
			tx.translate(translateX, translateY);
			tx.scale(scale, scale);
			Graphics2D ourGraphics = (Graphics2D) g;
			ourGraphics.setColor(Color.WHITE);
			ourGraphics.fillRect(0, 0, getWidth(), getHeight());
			ourGraphics.setTransform(tx);
			ourGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			ourGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);			
			ourGraphics.setColor(Color.BLACK);
			ourGraphics.drawRect(50, 50, 50, 50);
			ourGraphics.fillOval(100, 100, 100, 100);
			ourGraphics.drawString("Proba zoom-in i zoom-out", 50, 30);
			
			ourGraphics.setColor(new Color(0, 0, 200));
			ourGraphics.fill(kvadrat);
			//g2d.setColor(new Color(0, 200, 0));
			// g2d.fill(zell);
			
			if (curCursor != null)
		        setCursor(curCursor);
			// super.paint(g);
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			doDrawing(g);
		}
	}


	public static class TranslateHandler implements MouseListener,
		MouseMotionListener {
		
			private int lastOffsetX;
			private int lastOffsetY;
			
		public void mousePressed(MouseEvent e) {
			// capture starting point
			lastOffsetX = e.getX();
			lastOffsetY = e.getY();
			}
			
			
			public void mouseDragged(MouseEvent e) {
		/*	
			// new x and y are defined by current mouse location subtracted
			// by previously processed mouse location
			int newX = e.getX() - lastOffsetX;
			int newY = e.getY() - lastOffsetY;
			
			// increment last offset to last processed by drag event.
			lastOffsetX += newX;
			lastOffsetY += newY;
			
			// update the canvas locations
			canvas.translateX += newX;
			canvas.translateY += newY;
			
			// schedule a repaint.
			canvas.repaint();
			*/
			}
			
			
			
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseMoved(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
	}


	public static class ScaleHandler implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			if(e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
				
				// make it a reasonable amount of zoom
				// .1 gives a nice slow transition
				canvas.scale -= (.1 * e.getWheelRotation());
				// don't cross negative threshold.
				// also, setting scale to 0 has bad effects
				canvas.scale = Math.max(0.00001, canvas.scale); 
					
				//za kvadrat skaliranje, pomeranje x,y sta vec
				
				
				
				canvas.repaint();
			}
		}
	}
	
	
	public static class Kvadrat extends Rectangle2D.Float {
		public Kvadrat(float x, float y, float width, float height) {
			setRect(x, y, width, height);
		}

		public boolean isHit(float x, float y) {
			if (getBounds2D().contains(x, y)) {
				return true;
			} else {
				return false;
			}
		}

		public void addX(float x) {
			this.x += x;
		}

		public void addY(float y) {
			this.y += y;
		}

		public void addWidth(float w) {
			this.width += w;
		}

		public void addHeight(float h) {
			this.height += h;
		}
	}
	
	public static class MovingAdapter extends MouseAdapter {
		private int x;
		private int y;

		@Override
		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			int dx = e.getX() - x;
			int dy = e.getY() - y;

			if (kvadrat.isHit(x, y)) {
				kvadrat.addX(dx);
				kvadrat.addY(dy);
				canvas.repaint();
			}
			/*
			 * else if (zell.isHit(x, y)) { zell.addX(dx); zell.addY(dy);
			 * repaint(); }
			 */

			x += dx;
			y += dy;
		}
		
		  public void mouseMoved(MouseEvent e) {
		        if (kvadrat != null) { 
		          if (kvadrat.contains(e.getX(), e.getY())) {
		            curCursor = Cursor
		                .getPredefinedCursor(Cursor.HAND_CURSOR);
		          } else {
		            curCursor = Cursor.getDefaultCursor();
		          }
		        }
		       canvas.repaint();
		      }
		  
		  
		  
	}
}