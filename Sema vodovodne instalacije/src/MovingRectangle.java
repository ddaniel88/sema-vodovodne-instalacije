import java.awt.Adjustable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import enums.FigureTypes;
import enums.ScaleEnum;
import figures.E_Spn;
import figures.F_S;
import figures.Figure;
import figures.Mma_Onp;
import figures.Mmb_On;
import figures.Mmk_Ln;
import figures.Mmr_Rn;
import figures.P_Zn;
import figures.Point;

public class MovingRectangle extends JFrame {
	public static TransformingCanvas canvas;
	static Cursor curCursor;
	private static Figure espn, sspn, fs;
	private static ArrayList<Figure> figure;
	private static Graphics2D ourGraphics;
	private static Figure tmpFigure;
	private static String type="";
	private static JFrame frame;
	private static Dimension area;
	
	// Klik na neki elemeent iz menija
	public static class MenuActionListener implements ActionListener {
		
		private void ZoomIn()
		{
			AffineTransform saveXform = ourGraphics.getTransform();
			for (Figure fig : figure) {
				
				fig.scaleFigure(ScaleEnum.ZOOM_IN.getCode(),
								new Point(canvas.getWidth(),
										  canvas.getHeight())); 
			}
			canvas.repaint();
			ourGraphics.setTransform(saveXform); 
		}
		
		private void ZoomOut()
		{
			AffineTransform saveXform = ourGraphics.getTransform();
			for (Figure fig : figure) {
				
				fig.scaleFigure(ScaleEnum.ZOOM_OUT.getCode(),
								new Point(canvas.getWidth(),
										  canvas.getHeight())); 
			}
			canvas.repaint();
			ourGraphics.setTransform(saveXform); 
		}
		
		public void actionPerformed(ActionEvent e) {

			type = e.getActionCommand();
			if(type == "Zoom In")ZoomIn();
			if(type == "Zoom Out")ZoomOut();
			/*switch (type) {
			case "Zoom In":
					ZoomIn();
				break;
			case "Zoom Out":
					ZoomOut();
				break;
//			case "002":
//				type ="002";
//				break;
//			case "003":
//				type ="003";
//				break;
//			case "004":
//				type ="004";
//				break;
//			case "005":
//				type = "005";
//				break;
//			case "006":
//				type ="006";
//				break;
//			case "007":
//				type ="007";
//				break;
//			case "008":
//				type ="008";
//				break;
//			case "009":
//				type ="009";
//				break;
//
//			default:
//				break;
			}*/
		}
	}

	// meni
	public static class SwingMenu implements ActionListener {

		public JMenuBar SwingMenu() {
			JMenuBar menubar = new JMenuBar();
			JMenu filemenu = new JMenu("Figure");
			filemenu.add(new JSeparator());
			JMenu editmenu = new JMenu("Edit");
			editmenu.add(new JSeparator());
			
			// adding figures to menu
			for (FigureTypes figure : FigureTypes.values()) {
				JMenuItem fileItem = new JMenuItem(figure.getCode());
				fileItem.addActionListener(new MenuActionListener());
				filemenu.add(fileItem);
			}
			
			JMenuItem editItem1 = new JMenuItem("Zoom In");
			editItem1.addActionListener(new MenuActionListener());
			JMenuItem editItem2 = new JMenuItem("Zoom Out");
			editItem2.addActionListener(new MenuActionListener());
			
			editmenu.add(editItem1);
			editmenu.add(editItem2);
			menubar.add(filemenu);
			menubar.add(editmenu);

			return menubar;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public static void main(String[] args) {

		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setVisible(true); 
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
		SwingMenu s = new SwingMenu();
		frame.setJMenuBar(s.SwingMenu());
		
		canvas = new TransformingCanvas(); 
        JScrollPane scrollPane = new JScrollPane(canvas);  
        scrollPane.setPreferredSize(new Dimension(400,400));
        scrollPane.setOpaque(true);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);  
        
       // frame.setSize(400,400);  
        
        frame.pack();
        
       
	}
	
	

	public static class TransformingCanvas extends JPanel {
		private double translateX;
		private double translateY;
		private double scale;
		int maxWidth;  
		int totalHeight;  
		
		TransformingCanvas() {
			super(new BorderLayout());
			area = new Dimension(0,0);
			
			translateX = 0;
			translateY = 0;
			scale = 1;
			figure = new ArrayList<Figure>();

			MovingAdapter ma = new MovingAdapter();
			addMouseMotionListener(ma);
			addMouseListener(ma);
			addMouseWheelListener(ma);


			setOpaque(true);
			setDoubleBuffered(true);
		}

		public void doDrawing(Graphics g) {
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
			ourGraphics.setColor(Color.BLUE);

		
			drawAllFigures(ourGraphics);

			if (curCursor != null) {
				setCursor(curCursor);
			}
		}

		public void drawAllFigures(Graphics2D ourGraphics) {
			for (Figure fig : figure) {
				fig.draw(ourGraphics);
			}
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			doDrawing(g);
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

	

	public static class MovingAdapter extends MouseAdapter {
		private int x;
		private int y;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (type.isEmpty()) {
				return;
			}
			
			if (e.getButton() == MouseEvent.BUTTON3) {
				try {
					int width = 150;
					int height= 50;
					
					Class<?> figureObject = Class.forName(type);
					Figure fig = (Figure) figureObject.newInstance();
					fig.setPosition(e.getX(), e.getY(), width, height);
					figure.add(fig);
					
					int x = e.getX() - width/2;
		            int y = e.getY() - height/2;
		            if (x < 0) x = 0;
		            if (y < 0) y = 0;
		            Rectangle rect = new Rectangle(x, y, width, height);
		           
		            canvas.scrollRectToVisible(rect);
		            
		            int this_width = (x + width + 2);
		            if (this_width > area.width) {
		                area.width = this_width; 
		            }
		            int this_height = (y + height + 2);
		            if (this_height > area.height) {
		                area.height = this_height; 
		            }
		            
		            canvas.setPreferredSize(area);
		            canvas.getWidth();
		            canvas.getHeight();
		            //Let the scroll pane know to update itself
		            //and its scrollbars.
		            canvas.revalidate();
		            canvas.repaint();
			            
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			 
			for (Figure fig : figure) {
				if (fig.isHit(e)) {
					tmpFigure = fig;
					if (e.getClickCount() == 2) {
			            //JOptionPane.showMessageDialog(null, "Double clicked!");
						String desription = JOptionPane.showInputDialog(null, "Enter description", "Description", JOptionPane.INFORMATION_MESSAGE);
						tmpFigure.setDescription(desription);
			        }
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			int dx = e.getX() - x;
			int dy = e.getY() - y;
		

			if (tmpFigure != null) {
				tmpFigure.moveFigureFor(dx, dy, endCanvas());
				tmpFigure.draw(ourGraphics);
			}

			x += dx;
			y += dy;

			canvas.repaint();
		}

		public void mouseMoved(MouseEvent e) {
			for (Figure fig : figure) {
				if (fig.isHit(e)) {
					curCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
					break;
				} else {
					curCursor = Cursor.getDefaultCursor();
				}
			}
			canvas.repaint();
		}

//		public void mouseWheelMoved(MouseWheelEvent e) {
//
//			AffineTransform saveXform = ourGraphics.getTransform();
//			for (Figure fig : figure) {
//				//if (fig.isHit(e)) {
//					if (e.getWheelRotation() < 0) {
//						
//						fig.scaleFigure(ScaleEnum.ZOOM_IN.getCode(),
//										new Point(canvas.getWidth(),
//												  canvas.getHeight())); 
//						//fig.rotateFigure(Math.PI/8.0, ourGraphics);
//					}
//					else if (e.getWheelRotation() > 0) {
//						fig.scaleFigure(ScaleEnum.ZOOM_OUT.getCode(),
//										new Point(canvas.getWidth(),
//												  canvas.getHeight()));
//					}
//					//break;
//				//}
//			}

//			canvas.repaint();
//			ourGraphics.setTransform(saveXform); 
//		}

		@Override
		public void mouseReleased(MouseEvent e) {
			tmpFigure = null;
		}

	}
	
	public static Point endCanvas() {
		return new Point(canvas.getWidth(), canvas.getHeight());
	}
}