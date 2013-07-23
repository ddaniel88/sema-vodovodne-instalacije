import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JSeparator;
import enums.ScaleEnum;
import figures.E_Spn;
import figures.F_S;
import figures.Figure;
import figures.Mmr_Rn;
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
	
	
	// Klik na neki elemeent iz menija
	public static class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			String figura;

			switch (e.getActionCommand()) {
			case "001":
				type = "001";
				break;
			case "002":
				type ="002";
				break;
			case "003":
				type ="003";
				break;

			default:
				figura = "Invalid figure";
				break;
			}
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
			JMenuItem fileItem1 = new JMenuItem("001");
			fileItem1.addActionListener(new MenuActionListener());
			JMenuItem fileItem2 = new JMenuItem("002");
			fileItem2.addActionListener(new MenuActionListener());
			JMenuItem fileItem3 = new JMenuItem("003");
			fileItem3.addActionListener(new MenuActionListener());
			JMenuItem fileItem4 = new JMenuItem("004");
			fileItem4.addActionListener(new MenuActionListener());
			JMenuItem fileItem5 = new JMenuItem("005");
			fileItem5.addActionListener(new MenuActionListener());
			JMenuItem fileItem6 = new JMenuItem("006");
			fileItem6.addActionListener(new MenuActionListener());
			JMenuItem fileItem7 = new JMenuItem("007");
			fileItem7.addActionListener(new MenuActionListener());
			JMenuItem fileItem8 = new JMenuItem("008");
			fileItem8.addActionListener(new MenuActionListener());
			JMenuItem fileItem9 = new JMenuItem("009");
			fileItem9.addActionListener(new MenuActionListener());
			
			JMenuItem editItem1 = new JMenuItem("Zoom In");
			editItem1.addActionListener(new MenuActionListener());
			JMenuItem editItem2 = new JMenuItem("Zoom Out");
			editItem2.addActionListener(new MenuActionListener());
			
			filemenu.add(fileItem1);
			filemenu.add(fileItem2);
			filemenu.add(fileItem3);
			filemenu.add(fileItem4);
			filemenu.add(fileItem5);
			filemenu.add(fileItem6);
			filemenu.add(fileItem7);
			filemenu.add(fileItem8);
			filemenu.add(fileItem9);
			
			editmenu.add(editItem1);
			editmenu.add(editItem2);
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

		
		frame = new JFrame();

		SwingMenu s = new SwingMenu();
		frame.setJMenuBar(s.SwingMenu());

		// Set default close operation for JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		canvas = new TransformingCanvas();

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
			 if (e.getButton() == MouseEvent.BUTTON3) {
				 
				 switch (type) {
					case "001":
						espn = new E_Spn(e.getX(), e.getY(), 100, 50);
						espn.setDescription("Prva figura");
						figure.add(espn);
					//	JOptionPane.showMessageDialog(null, frame.get);
						break;
					case "002":
						sspn = new E_Spn(e.getX(), e.getY(), 40, 10);
						sspn.setDescription("Mala figura");
						figure.add(sspn);
						break;
					case "003":
						fs = new Mmr_Rn(e.getX(), e.getY(), 40, 20);
						fs.setDescription("Treca figura");
						figure.add(fs);
						break;

					default:
						break;
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

		public void mouseWheelMoved(MouseWheelEvent e) {

			AffineTransform saveXform = ourGraphics.getTransform();
			for (Figure fig : figure) {
				if (fig.isHit(e)) {
					if (e.getWheelRotation() < 0) {
						
						fig.scaleFigure(ScaleEnum.ZOOM_IN.getCode(),
										new Point(canvas.getWidth(),
												  canvas.getHeight())); 
						//fig.rotateFigure(Math.PI/8.0, ourGraphics);
					}
					else if (e.getWheelRotation() > 0) {
						fig.scaleFigure(ScaleEnum.ZOOM_OUT.getCode(),
										new Point(canvas.getWidth(),
												  canvas.getHeight()));
					}
					break;
				}
			}

			canvas.repaint();
			ourGraphics.setTransform(saveXform); 
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			tmpFigure = null;
		}

	}
	
	public static Point endCanvas() {
		return new Point(canvas.getWidth(), canvas.getHeight());
	}
}