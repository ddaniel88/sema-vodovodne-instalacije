package org.arad.graphicseditor;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import figures.A;
import figures.B;
import figures.E_Spn;
import figures.F_S;
import figures.Ff_Sp;
import figures.Ffk_Lp;
import figures.Ffr_Rp;
import figures.Figure;
import figures.J;
import figures.K;
import figures.MQ;
import figures.Mma_Onp;
import figures.Mmb_On;
import figures.Mmk_Ln;
import figures.Mmq_Ln;
import figures.Mmr_Rn;
import figures.N_Ls;
import figures.O_Zc;
import figures.P_Zn;
import figures.Point;
import figures.Q_Lp;
import figures.T_Op;
import figures.Tt_Kp;
import figures.U;
import figures.U_Sn;
import figures.X_Zp;

public class MainCanvas extends JPanel {

	private static final long serialVersionUID = 1L;

	private Frame mainFrame = null;

	private double scale = 1.0;
	private double rotate = 0.0;
	
	// user space
	private double translateX = 0.0;
	private double translateY = 0.0;
	private double userSpaceWidth = 5000.0;
	private double userSpacesHeight = 3000.0;

	private Figure tmpFigure;
	private Figure lastSelectedFigure;
	private List<Figure> figures;
	
	private Graphics2D lastGraphics2D = null;

	private Cursor curCursor = null;

	//mouseDragged
	private float xCursorPosition = 0;
	private float yCursorPosition = 0;
	
	private JPopupMenu mainContextMenu = null;
	private JMenuItem jmenuItemE_SPN = null;
	private JMenuItem jmenuItemF_S = null;
	private JMenuItem jmenuItemMMA_ONP = null;
	private JMenuItem jmenuItemMMB_ON = null;
	private JMenuItem jmenuItemMMK_LN = null;
	private JMenuItem jmenuItemMMQ_LN = null;
	private JMenuItem jmenuItemMMR_RN = null;
	private JMenuItem jmenuItemP_ZN = null;
	private JMenuItem jmenuItemU_SN = null;
	private JMenuItem jmenuItemO_ZC = null;
	private JMenuItem jmenuItemQ_LP = null;
	private JMenuItem jmenuItemN_LS = null;
	private JMenuItem jmenuItemFFK_LP = null;
	private JMenuItem jmenuItemT_OP = null;
	private JMenuItem jmenuItemX_ZP = null;
	private JMenuItem jmenuItemTT_KP = null;
	private JMenuItem jmenuItemFFR_RP = null;
	private JMenuItem jmenuItemFF_SP = null;
	private JMenuItem jmenuItemA = null;
	private JMenuItem jmenuItemB = null;
	private JMenuItem jmenuItemU = null;
	private JMenuItem jmenuItemMQ = null;
	private JMenuItem jmenuItemJ= null;
	private JMenuItem jmenuItemK= null;
	
	private JPopupMenu figureContextMenu = null;
	private JMenuItem jmenuItemRotatePlus30 = null;
	private JMenuItem jmenuItemRotatePlus45 = null;
	private JMenuItem jmenuItemRotatePlus60 = null;
	private JMenuItem jmenuItemRotatePlus90 = null;
	private JMenuItem jmenuItemRotateMinus30 = null;
	private JMenuItem jmenuItemRotateMinus45 = null;
	private JMenuItem jmenuItemRotateMinus60 = null;
	private JMenuItem jmenuItemRotateMinus90 = null;
	private JMenuItem jmenuItemDelete = null;

	public MainCanvas(Frame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void initialize() {

		this.figures = new ArrayList<Figure>();

		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.println("mouseDragged");
				float dx = (float)(e.getX()/scale) - xCursorPosition;
				float dy = (float)(e.getY()/scale) - yCursorPosition;

				if (tmpFigure != null) {
					 tmpFigure.moveFigureFor(dx, dy, endCanvas());
					 //tmpFigure.draw(ourGraphics);
				}

				xCursorPosition += dx;
				yCursorPosition += dy;

				repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
//				System.out.println("mouseMoved");
				float x = (float)(e.getX()/scale);
				float y = (float)(e.getY()/scale);
				for (Figure fig : figures) {
					if (fig.isHit( x, y)) {
						curCursor = Cursor
								.getPredefinedCursor(Cursor.HAND_CURSOR);
						break;
					} else {
						curCursor = Cursor.getDefaultCursor();
					}
				}
				repaint();
			}

		});

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				//System.out.println("mouseClicked");
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				//System.out.println("mouseEntered");
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				//System.out.println("mouseExited");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//System.out.println("mousePressed");
				xCursorPosition = (float)(e.getX()/scale);
				yCursorPosition = (float)(e.getY()/scale);

				for (Figure fig : figures) {
					if (fig.isHit( xCursorPosition, yCursorPosition)) {
						tmpFigure = fig;
						tmpFigure.setSelected(true);
						lastSelectedFigure = fig;
						break;
					}
				}

				if (tmpFigure == null)
					maybeShowPopup(e);
				else
					maybeShowFigurePopup(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("mouseReleased");
				if (tmpFigure == null)
					maybeShowPopup(e);
				else
					maybeShowFigurePopup(e);

				if (tmpFigure !=null){
					tmpFigure.setSelected(false);
					tmpFigure = null;
				}
			}

			private void maybeShowPopup(MouseEvent e) {

				if (e.isPopupTrigger()) {
					getMainContextMenu().show(e.getComponent(), e.getX(),
							e.getY());
				}
			}
			
			private void maybeShowFigurePopup(MouseEvent e) {

				if (e.isPopupTrigger()) {
					getFigureContextMenu().show(e.getComponent(), e.getX(),
							e.getY());
				}
			}
		});
	}

	public void zoomIn() {
		this.scale += 0.5;
		repaint();
	}

	public void zoomOut() {
		this.scale -= 0.5;
		repaint();
	}

	public void rotatePlus() {
		this.rotate += Math.PI / 2;
		repaint();
	}

	public void rotateMinus() {
		this.rotate -= Math.PI / 2;
		repaint();
	}

	private void doDrawing(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		Color currentColor = g2d.getColor();
		
		AffineTransform affineTransform = new AffineTransform();

		affineTransform.translate(translateX, translateY);
		affineTransform.scale(this.scale, this.scale);
		affineTransform.rotate(this.rotate);

		g2d.setColor(Color.WHITE);
		Rectangle2D.Double rect = new Rectangle2D.Double(0, 0, this.userSpaceWidth, this.userSpacesHeight);
		g2d.fill(rect);

		g2d.setTransform(affineTransform);
		
		g2d.setColor( Color.LIGHT_GRAY);
		
		for(double i=0.0; i< this.userSpaceWidth; i+=100*scale){
			Line2D.Double ln = new Line2D.Double( i, 0.0, i, this.userSpacesHeight);
			g2d.draw(ln);
		}

		for(double i=0.0; i< this.userSpacesHeight; i+=100*scale){
			Line2D.Double ln = new Line2D.Double( 0.0, i, this.userSpaceWidth, i);
			g2d.draw(ln);
		}
		
		g2d.setColor(Color.blue);

		for (Figure figure : this.figures) {
			figure.draw(g2d);
		}

		if (curCursor != null) {
			setCursor(curCursor);
		}
		
		this.lastGraphics2D = g2d;
		
		g2d.setColor(currentColor);
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		doDrawing(g);
	}

	public Frame getMainFrame() {
		return mainFrame;
	}

	public JPopupMenu getMainContextMenu() {
		if (mainContextMenu == null) {
			mainContextMenu = new JPopupMenu();
			mainContextMenu.add(getJmenuItemE_SPN());
			mainContextMenu.add(getJmenuItemF_S());
			mainContextMenu.add(getJmenuItemMMA_ONP());
			mainContextMenu.add(getJmenuItemMMB_ON());
			mainContextMenu.add(getJmenuItemMMK_LN());
			mainContextMenu.add(getJmenuItemMMQ_LN());
			mainContextMenu.add(getJmenuItemMMR_RN());
			mainContextMenu.add(getJmenuItemP_ZN());
			mainContextMenu.add(getJmenuItemU_SN());
			mainContextMenu.add(getJmenuItemO_ZC());
			mainContextMenu.add(getJmenuItemQ_LP());
			mainContextMenu.add(getJmenuItemN_LS());
			mainContextMenu.add(getJmenuItemFFK_LP());
			mainContextMenu.add(getJmenuItemT_OP());
			mainContextMenu.add(getJmenuItemX_ZP());
			mainContextMenu.add(getJmenuItemTT_KP());
			mainContextMenu.add(getJmenuItemFFR_RP());
			mainContextMenu.add(getJmenuItemFF_SP());
			mainContextMenu.add(getJmenuItemA());
			mainContextMenu.add(getJmenuItemB());
			mainContextMenu.add(getJmenuItemU());
			mainContextMenu.add(getJmenuItemMQ());
			mainContextMenu.add(getJmenuItemJ());
			mainContextMenu.add(getJmenuItemK());
		}
		return mainContextMenu;
	}

	public JMenuItem getJmenuItemE_SPN() {
		if (jmenuItemE_SPN == null) {
			jmenuItemE_SPN = new JMenuItem("E_SPN");
			jmenuItemE_SPN.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height = 50;

					Figure figure = new E_Spn(xCursorPosition, yCursorPosition,
							width, height);
					figures.add(figure);
					repaint();

				}
			});
		}
		return jmenuItemE_SPN;
	}

	public JMenuItem getJmenuItemF_S() {
		if (jmenuItemF_S == null) {
			jmenuItemF_S = new JMenuItem("F_S");
			jmenuItemF_S.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height = 50;

					Figure figure = new F_S(xCursorPosition, yCursorPosition,
							width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemF_S;
	}

	public JMenuItem getJmenuItemMMA_ONP() {
		if (jmenuItemMMA_ONP == null) {
			jmenuItemMMA_ONP = new JMenuItem("MMA_ONP");
			jmenuItemMMA_ONP.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height = 50;

					Figure figure = new Mma_Onp(xCursorPosition,
							yCursorPosition, width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemMMA_ONP;
	}

	public JMenuItem getJmenuItemMMB_ON() {
		if (jmenuItemMMB_ON == null) {
			jmenuItemMMB_ON = new JMenuItem("MMB_ON");
			jmenuItemMMB_ON.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height = 50;

					Figure figure = new Mmb_On(xCursorPosition,
							yCursorPosition, width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemMMB_ON;
	}

	public JMenuItem getJmenuItemMMK_LN() {
		if (jmenuItemMMK_LN == null) {
			jmenuItemMMK_LN = new JMenuItem("MMK_LN");
			jmenuItemMMK_LN.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height = 50;

					Figure figure = new Mmk_Ln(xCursorPosition,
							yCursorPosition, width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemMMK_LN;
	}

	public JMenuItem getJmenuItemMMQ_LN() {
		if (jmenuItemMMQ_LN == null) {
			jmenuItemMMQ_LN = new JMenuItem("MMQ_LN");
			jmenuItemMMQ_LN.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height = 50;

					Figure figure = new Mmq_Ln(xCursorPosition,
							yCursorPosition, width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemMMQ_LN;
	}

	public JMenuItem getJmenuItemMMR_RN() {
		if (jmenuItemMMR_RN == null) {
			jmenuItemMMR_RN = new JMenuItem("MMR_RN");
			jmenuItemMMR_RN.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height = 50;

					Figure figure = new Mmr_Rn(xCursorPosition,
							yCursorPosition, width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemMMR_RN;
	}

	public JMenuItem getJmenuItemP_ZN() {
		if (jmenuItemP_ZN == null) {
			jmenuItemP_ZN = new JMenuItem("P_ZN");
			jmenuItemP_ZN.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height = 50;

					Figure figure = new P_Zn(xCursorPosition, yCursorPosition,
							width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemP_ZN;
	}

	public JMenuItem getJmenuItemU_SN() {
		if (jmenuItemU_SN == null) {
			jmenuItemU_SN = new JMenuItem("U_SN");
			jmenuItemU_SN.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height = 50;

					Figure figure = new U_Sn(xCursorPosition, yCursorPosition,
							width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemU_SN;
	}
	
	public JMenuItem getJmenuItemO_ZC() {
		if (jmenuItemO_ZC == null) {
			jmenuItemO_ZC = new JMenuItem("O_ZC");
			jmenuItemO_ZC.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height = 50;

					Figure figure = new O_Zc(xCursorPosition, yCursorPosition,
							width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemO_ZC;
	}
	
	public JMenuItem getJmenuItemQ_LP() {
		if (jmenuItemQ_LP == null) {
			jmenuItemQ_LP = new JMenuItem("Q_LP");
			jmenuItemQ_LP.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height = 50;

					Figure figure = new Q_Lp(xCursorPosition, yCursorPosition,
							width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemQ_LP;
	}
	
	public JMenuItem getJmenuItemN_LS() {
		if (jmenuItemN_LS == null) {
			jmenuItemN_LS= new JMenuItem("N_LS");
			jmenuItemN_LS.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height = 50;

					Figure figure = new N_Ls(xCursorPosition, yCursorPosition,
							width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemN_LS;
	}
	
	public JMenuItem getJmenuItemFFK_LP() {
		if (jmenuItemFFK_LP == null) {
			jmenuItemFFK_LP= new JMenuItem("FFK_LP");
			jmenuItemFFK_LP.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height = 50;

					Figure figure = new Ffk_Lp(xCursorPosition, yCursorPosition,
							width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemFFK_LP;
	}
	
	public JMenuItem getJmenuItemT_OP() {
		if (jmenuItemT_OP == null) {
			jmenuItemT_OP= new JMenuItem("T_OP");
			jmenuItemT_OP.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height = 50;

					Figure figure = new T_Op(xCursorPosition, yCursorPosition,
							width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemT_OP;
	}
	
	public JMenuItem getJmenuItemX_ZP() {
		if (jmenuItemX_ZP == null) {
			jmenuItemX_ZP= new JMenuItem("X_ZP");
			jmenuItemX_ZP.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height = 50;

					Figure figure = new X_Zp(xCursorPosition, yCursorPosition,
							width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemX_ZP;
	}
	
	public JMenuItem getJmenuItemTT_KP() {
		if (jmenuItemTT_KP == null) {
			jmenuItemTT_KP= new JMenuItem("TT_KP");
			jmenuItemTT_KP.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height =50;

					Figure figure = new Tt_Kp(xCursorPosition, yCursorPosition,
							width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemTT_KP;
	}
	
	public JMenuItem getJmenuItemFFR_RP() {
		if (jmenuItemFFR_RP == null) {
			jmenuItemFFR_RP= new JMenuItem("FFR_RP");
			jmenuItemFFR_RP.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height =50;

					Figure figure = new Ffr_Rp(xCursorPosition, yCursorPosition,
							width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemFFR_RP;
	}
	
	public JMenuItem getJmenuItemFF_SP() {
		if (jmenuItemFF_SP == null) {
			jmenuItemFF_SP= new JMenuItem("FF_SP");
			jmenuItemFF_SP.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height =50;

					Figure figure = new Ff_Sp(xCursorPosition, yCursorPosition,
							width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemFF_SP;
	}
	
	public JMenuItem getJmenuItemA() {
		if (jmenuItemA== null) {
			jmenuItemA= new JMenuItem("A");
			jmenuItemA.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height =50;

					Figure figure = new A(xCursorPosition, yCursorPosition,
							width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemA;
	}
	
	public JMenuItem getJmenuItemB() {
		if (jmenuItemB== null) {
			jmenuItemB= new JMenuItem("B");
			jmenuItemB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height =50;

					Figure figure = new B(xCursorPosition, yCursorPosition,
							width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemB;
	}
	
	public JMenuItem getJmenuItemU() {
		if (jmenuItemU== null) {
			jmenuItemU= new JMenuItem("U");
			jmenuItemU.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height =50;

					Figure figure = new U(xCursorPosition, yCursorPosition,
							width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemU;
	}
	
	public JMenuItem getJmenuItemMQ() {
		if (jmenuItemMQ== null) {
			jmenuItemMQ= new JMenuItem("MQ");
			jmenuItemMQ.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height =50;

					Figure figure = new MQ(xCursorPosition, yCursorPosition,
							width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemMQ;
	}
	
	public JMenuItem getJmenuItemJ() {
		if (jmenuItemJ== null) {
			jmenuItemJ= new JMenuItem("J");
			jmenuItemJ.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height =50;

					Figure figure = new J(xCursorPosition, yCursorPosition,
							width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemJ;
	}
	
	public JMenuItem getJmenuItemK() {
		if (jmenuItemK== null) {
			jmenuItemK= new JMenuItem("K");
			jmenuItemK.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int width = 150;
					int height =50;

					Figure figure = new K(xCursorPosition, yCursorPosition,
							width, height);
					figures.add(figure);
					repaint();
				}
			});
		}
		return jmenuItemK;
	}

	public JPopupMenu getFigureContextMenu() {
		if (figureContextMenu == null){
			figureContextMenu = new JPopupMenu();
			figureContextMenu.add(getJmenuItemRotatePlus30());
			figureContextMenu.add(getJmenuItemRotatePlus45());
			figureContextMenu.add(getJmenuItemRotatePlus60());
			figureContextMenu.add(getJmenuItemRotatePlus90());
			figureContextMenu.add(getJmenuItemRotateMinus30());
			figureContextMenu.add(getJmenuItemRotateMinus45());
			figureContextMenu.add(getJmenuItemRotateMinus60());
			figureContextMenu.add(getJmenuItemRotateMinus90());
			figureContextMenu.add(getJmenuItemDelete());
		}
		return figureContextMenu;
	}

	public JMenuItem getJmenuItemRotatePlus30() {
		if (jmenuItemRotatePlus30 == null){
			jmenuItemRotatePlus30 = new JMenuItem("Rotacija +30");
			jmenuItemRotatePlus30.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (lastSelectedFigure!=null && lastGraphics2D!=null) {
						lastSelectedFigure.rotateFigure(-Math.PI/6, lastGraphics2D);
					}
					repaint();
				}
			});
		}
		return jmenuItemRotatePlus30;
	}

	public JMenuItem getJmenuItemRotatePlus45() {
		if (jmenuItemRotatePlus45 == null){
			jmenuItemRotatePlus45 = new JMenuItem("Rotacija +45");
			jmenuItemRotatePlus45.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (lastSelectedFigure!=null && lastGraphics2D!=null) {
						lastSelectedFigure.rotateFigure(-Math.PI/4, lastGraphics2D);
					}
					repaint();
				}
			});
		}
		return jmenuItemRotatePlus45;
	}

	public JMenuItem getJmenuItemRotatePlus60() {
		if (jmenuItemRotatePlus60 == null){
			jmenuItemRotatePlus60 = new JMenuItem("Rotacija +60");
			jmenuItemRotatePlus60.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (lastSelectedFigure!=null && lastGraphics2D!=null) {
						lastSelectedFigure.rotateFigure(-Math.PI/3, lastGraphics2D);
					}
					repaint();
				}
			});
		}
		return jmenuItemRotatePlus60;
	}

	public JMenuItem getJmenuItemRotatePlus90() {
		if (jmenuItemRotatePlus90 == null){
			jmenuItemRotatePlus90 = new JMenuItem("Rotacija +90");
			jmenuItemRotatePlus90.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (lastSelectedFigure!=null && lastGraphics2D!=null) {
						lastSelectedFigure.rotateFigure(-Math.PI/2, lastGraphics2D);
					}
					repaint();
				}
			});
		}
		return jmenuItemRotatePlus90;
	}

	public JMenuItem getJmenuItemRotateMinus30() {
		if (jmenuItemRotateMinus30 == null){
			jmenuItemRotateMinus30 = new JMenuItem("Rotacija -30");
			jmenuItemRotateMinus30.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (lastSelectedFigure!=null && lastGraphics2D!=null) {
						lastSelectedFigure.rotateFigure(Math.PI/6, lastGraphics2D);
					}
					repaint();
				}
			});
		}
		return jmenuItemRotateMinus30;
	}

	public JMenuItem getJmenuItemRotateMinus45() {
		if (jmenuItemRotateMinus45 == null){
			jmenuItemRotateMinus45 = new JMenuItem("Rotacija -45");
			jmenuItemRotateMinus45.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (lastSelectedFigure!=null && lastGraphics2D!=null) {
						lastSelectedFigure.rotateFigure(Math.PI/4, lastGraphics2D);
					}
					repaint();
				}
			});
		}
		return jmenuItemRotateMinus45;
	}

	public JMenuItem getJmenuItemRotateMinus60() {
		if (jmenuItemRotateMinus60 == null){
			jmenuItemRotateMinus60 = new JMenuItem("Rotacija -60");
			jmenuItemRotateMinus60.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (lastSelectedFigure!=null && lastGraphics2D!=null) {
						lastSelectedFigure.rotateFigure(Math.PI/3, lastGraphics2D);
					}
					repaint();
				}
			});
		}
		return jmenuItemRotateMinus60;
	}

	public JMenuItem getJmenuItemRotateMinus90() {
		if (jmenuItemRotateMinus90 == null){
			jmenuItemRotateMinus90 = new JMenuItem("Rotacija -90");
			jmenuItemRotateMinus90.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (lastSelectedFigure!=null && lastGraphics2D!=null) {
						lastSelectedFigure.rotateFigure(Math.PI/2, lastGraphics2D);
					}
					repaint();
				}
			});
		}
		return jmenuItemRotateMinus90;
	}
	
	public Point endCanvas() {
		return new Point(this.getWidth(), this.getHeight());
	}

	public JMenuItem getJmenuItemDelete() {
		if (jmenuItemDelete == null){
			jmenuItemDelete = new JMenuItem("Brisanje");
			jmenuItemDelete.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (lastSelectedFigure!=null) figures.remove(lastSelectedFigure);
					repaint();
				}
			});
		}
		return jmenuItemDelete;
	}

	public double getUserSpaceWidth() {
		return userSpaceWidth;
	}

	public double getUserSpacesHeight() {
		return userSpacesHeight;
	}
	

}
