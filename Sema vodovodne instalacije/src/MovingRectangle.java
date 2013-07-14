import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import figures.E_Spn;
import figures.Figure;
import figures.Point;


public class MovingRectangle extends JFrame {

	public static TransformingCanvas canvas;
	private static Kvadrat kvadrat;
	private static Nesto linija;
	static Cursor curCursor;
	private static E_Spn espn, sspn;
	private static ArrayList<Figure> figure;
	private static Graphics2D ourGraphics;
	private static Figure tmpFigure;
	private static int bb = 0;
	
	
	
	//Klik na neki elemeent iz menija 
	public static class MenuActionListener implements ActionListener {
		  public void actionPerformed(ActionEvent e) {
			  
			  System.out.println("Selected: " + e.getActionCommand());
			  
			  String figura;
			  
		        switch (e.getActionCommand()) {
		            case "001":   System.out.println("aaa");
		                     break;
		            case "002":   System.out.println("bbb");
		                     break;
		            case "003":  System.out.println("ccc");
		                     break;
		            
		            default: figura = "Invalid month";
		                     break;
		        }
		    }
			  
		    

		  }
		
	
	//meni
	public static class SwingMenu implements ActionListener{
		 
		  public JMenuBar SwingMenu()
		  {
			  JMenuBar menubar = new JMenuBar();
			  JMenu filemenu = new JMenu("Figure");
			  filemenu.add(new JSeparator());
			  JMenu editmenu = new JMenu("Edit");
			  editmenu.add(new JSeparator());
			  JMenuItem fileItem1 = new JMenuItem("001");
			  fileItem1.addActionListener(new MenuActionListener());
			  JMenuItem fileItem2 = new JMenuItem("002");
			  fileItem2.addActionListener(new MenuActionListener());
			  JMenuItem fileItem3 = new JMenuItem("003");
			  
			  fileItem3.addActionListener(new MenuActionListener());
			  JMenuItem editItem1 = new JMenuItem("Rotate");
			  JMenuItem editItem2 = new JMenuItem("Translae");
			  JMenuItem editItem3 = new JMenuItem("Zoom");
			  filemenu.add(fileItem1);
			  filemenu.add(fileItem2);
			  filemenu.add(fileItem3);
			  
			  editmenu.add(editItem1);
			  editmenu.add(editItem2);
			  editmenu.add(editItem3);
			  menubar.add(filemenu);
			  menubar.add(editmenu);
			  
			  
			  
			  return menubar;
		  }

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			System.out.println("aaa");
			
		}
		
		}
	public static void main(String[] args) {

		JFrame frame = new JFrame();
		
		SwingMenu s = new SwingMenu();
		frame.setJMenuBar(s.SwingMenu());
		
		//Set default close operation for JFrame  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		canvas = new TransformingCanvas();
//		figure = new ArrayList<>();
		TranslateHandler translater = new TranslateHandler();
		canvas.addMouseListener(translater);
		canvas.addMouseMotionListener(translater);
		canvas.addMouseWheelListener(new ScaleHandler());
		
		
		frame.setLayout(new BorderLayout());
		frame.getContentPane().add(canvas, BorderLayout.CENTER);
		frame.setSize(500, 500);
		
		
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
	
		
	}

	public static class TransformingCanvas extends JComponent {
		private double translateX;
		private double translateY;
		private double scale;

		TransformingCanvas() {
			translateX = 0;
			translateY = 0;
			scale = 1;
			figure = new ArrayList<>();
	
			MovingAdapter ma = new MovingAdapter();
			addMouseMotionListener(ma);
			addMouseListener(ma);
			addMouseWheelListener(ma);

			kvadrat = new Kvadrat(70, 70, 20, 20);
			linija = new Nesto(100, 100, 200, 100);
			// zell = new ZEllipse(150, 70, 80, 80);
			
			/******** DRAW CUSTOM FIGURES ********/
			espn = new E_Spn(200, 200, 100, 50);
			espn.setDescription("Prva figura");
//			espn.draw(ourGraphics);
			figure.add(espn);
			
			sspn = new E_Spn(10, 10, 40, 10);
			sspn.setDescription("Mala figura");
//			sspn.draw(ourGraphics);
			
			figure.add(sspn);
			/******** END OF DRAW CUSTOM FIGURES ********/

			setOpaque(true);
			setDoubleBuffered(true);
		}

		public void doDrawing(Graphics g) {
			System.out.println("doDrawing " + bb++);
			
			canvas.repaint();
			AffineTransform tx = new AffineTransform();
			tx.translate(translateX, translateY);
			tx.scale(scale, scale);
			ourGraphics = (Graphics2D) g;
			ourGraphics.setColor(Color.WHITE);
			ourGraphics.fillRect(0, 0, getWidth(), getHeight());
			ourGraphics.setTransform(tx);
			ourGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			ourGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			ourGraphics.setColor(Color.BLACK);
			ourGraphics.drawString("Proba zoom-in i zoom-out", 100, 100);
			
			AffineTransform aa = new AffineTransform();
			aa.scale(kvadrat.kv_scale, kvadrat.kv_scale);
			Graphics2D kv = (Graphics2D) g;
			kv.setColor(new Color(0, 0, 200));
			kv.setTransform(aa);
			kv.fill(kvadrat);
			
			espn.draw(ourGraphics);
			sspn.draw(ourGraphics);

			// g2d.setColor(new Color(0, 200, 0));
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
			 * // new x and y are defined by current mouse location subtracted
			 * // by previously processed mouse location int newX = e.getX() -
			 * lastOffsetX; int newY = e.getY() - lastOffsetY;
			 * 
			 * // increment last offset to last processed by drag event.
			 * lastOffsetX += newX; lastOffsetY += newY;
			 * 
			 * // update the canvas locations canvas.translateX += newX;
			 * canvas.translateY += newY;
			 * 
			 * // schedule a repaint. canvas.repaint();
			 */
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseMoved(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}
	}

	public static class ScaleHandler implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {

				// make it a reasonable amount of zoom
				// .1 gives a nice slow transition
				canvas.scale -= (.1 * e.getWheelRotation());
				// don't cross negative threshold.
				// also, setting scale to 0 has bad effects
				canvas.scale = Math.max(0.00001, canvas.scale);

				
				canvas.repaint();
			}
		}
	}

	public static class Kvadrat extends Rectangle2D.Float {

		double kv_scale = 1;

		public Kvadrat(double d, double e, float width, float height) {
			setRect(d, e, width, height);
		}

		public boolean isHit(float x, float y) {
			
			if (getBounds2D().contains(x, y))
			{
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
	
	public static class Nesto extends Line2D.Float {
		private static final long serialVersionUID = 1L;
		
		public Nesto(float a, float b, float c, float d) {
			setLine(a, b, c, d);
		}
	}

	public static class MovingAdapter extends MouseAdapter {
		private int x;
		private int y;

		@Override
		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			int brojac = 0;
			
			for (Figure fig : figure) {
				if (fig.isHit(e)) {
					tmpFigure = fig;
//					System.out.println(fig.getDescription() + " klik × " + brojac);
					brojac++;
				}
			}
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
			
//			for (Figure fig : figure) {
//				if (fig.isHit(e)) {
////					System.out.println(fig.getStartPosition().getX() + " " + fig.getStartPosition().getY());
////					System.out.println(e.getX() + " " + e.getY());
////					System.out.println(dx + " " + dy);
//					
//					System.out.println("Pogoðeno " + fig.getDescription());
//					boolean move = fig.moveFigureFor(dx, dy);
//					E_Spn a = (E_Spn)fig;
//					((E_Spn) fig).draw(ourGraphics);
//					canvas.repaint();
//				}
//			}
			
			if (tmpFigure != null) {
				tmpFigure.moveFigureFor(dx, dy);
				((E_Spn) tmpFigure).draw(ourGraphics);
			}
			
			x += dx;
			y += dy;
			
			canvas.repaint();
		}

		public void mouseMoved(MouseEvent e) {
//			for (Figure fig : figure) {
//				if (fig.isHit(e)) {
//					System.out.print(fig.getDescription() + " ");
//					System.out.println(System.currentTimeMillis());
//				}
//			}
			
			
//			System.out.print("X: " + espn.getStartPosition().getX() + " " + e.getX() + "\t");
//			System.out.println("Y: " + espn.getStartPosition().getY() + " " + e.getY());
//			if (espn.isHit(e.getX(), e.getY())) {
//				System.out.println("Figura pogoðena");
//			}
		
			
			for (Figure fig : figure) {
				if (fig.isHit(e)) {
					curCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
//					System.out.println(fig.getStartPosition().getX());
				}
				else {
					if (kvadrat != null) {
						if (kvadrat.contains(e.getX(), e.getY())) {
							curCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
						} else {
							curCursor = Cursor.getDefaultCursor();
						}
					} 
				}
			}
			canvas.repaint();
		}

		public void mouseWheelMoved(MouseWheelEvent e) {
			for (Figure fig : figure) {
				if (fig.isHit(e)) {
					fig.scaleFigure(e.getWheelRotation() / 10, new Point(canvas.getWidth(), canvas.getHeight()));
				}
			}
			
//			System.out.println("mouseWheelMoved " + kvadrat.getX() + ", "
//					+ kvadrat.getY());
//			
////			kvadrat.setRect(kvadrat.x *= (1 - .1 * e.getWheelRotation()), kvadrat.y *= (1 - .1 * e.getWheelRotation()),
////					kvadrat.width *= (1 - .1 * e.getWheelRotation()), kvadrat.height *= (1 - .1 * e.getWheelRotation()));
//			
//			// minus ide zato što je obrnuto kolce na dole i kolce na gore :-)
//			// promenimo kvadrat pravougaonika i faktièki smo ga zumirali
//			double newX = kvadrat.getX() - kvadrat.getX() * e.getWheelRotation() / 10;
//			double newY = kvadrat.getY() - kvadrat.getY() * e.getWheelRotation() / 10;
//			double newWidth = kvadrat.getWidth() - kvadrat.getWidth() * e.getWheelRotation() / 10;
//			double newHeight = kvadrat.getHeight() - kvadrat.getHeight() * e.getWheelRotation() / 10;
//			
//			kvadrat.setRect(newX, newY, newWidth, newHeight);
//			
//			// make it a reasonable amount of zoom
//			// .1 gives a nice slow transition
////			kvadrat.kv_scale -= (.1 * e.getWheelRotation());
//			// don't cross negative threshold.
//			// also, setting scale to 0 has bad effects
////			kvadrat.kv_scale = Math.max(0.00001, kvadrat.kv_scale);
//
//			System.out.println("Trenutni zoom: " + kvadrat.kv_scale);
			canvas.repaint();
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			tmpFigure = null;
		}

	}
}