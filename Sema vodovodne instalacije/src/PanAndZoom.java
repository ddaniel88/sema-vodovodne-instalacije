/* Adapted from code posted by R.J. Lorimer in an articleentitled "Java2D: Have Fun With Affine
     Transform". The original post and code can be found 
     at http://www.javalobby.org/java/forums/t19387.html.
*/

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
 
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.BorderFactory;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
 
public class PanAndZoom {
 
    PanAndZoomCanvas canvas;
    AffineTransform at;   // the current pan and zoom transform
    Point2D XFormedPoint; // storage for a transformed mouse point
 
    public static void main(String[] args) {
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		    new PanAndZoom();
		}
	    });
    }

    public PanAndZoom() {
	JFrame frame = new JFrame();
	canvas = new PanAndZoomCanvas();
	PanningHandler panner = new PanningHandler();
	canvas.addMouseListener(panner);
	canvas.addMouseMotionListener(panner);
	canvas.setBorder(BorderFactory.createLineBorder(Color.black));

	// code for handling zooming
	JSlider zoomSlider = new JSlider(JSlider.HORIZONTAL, 0, 300, 100);
	zoomSlider.setMajorTickSpacing(25);
	zoomSlider.setMinorTickSpacing(5);
	zoomSlider.setPaintTicks(true);
	zoomSlider.setPaintLabels(true);
	zoomSlider.addChangeListener(new ScaleHandler());

	// Add the components to the canvas
	frame.getContentPane().add(zoomSlider, BorderLayout.NORTH);
	frame.getContentPane().add(canvas, BorderLayout.CENTER);
	frame.pack();
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.setVisible(true);
    }
 
    class PanAndZoomCanvas extends JComponent {
	double translateX;
	double translateY;
	double scale;
 
	PanAndZoomCanvas() {
	    translateX = 0;
	    translateY = 0;
	    scale = 1;
	}
 
	public void paintComponent(Graphics g) {
	    Graphics2D ourGraphics = (Graphics2D) g;
	    // save the original transform so that we can restore
	    // it later
	    AffineTransform saveTransform = ourGraphics.getTransform();

	    // blank the screen. If we do not call super.paintComponent, then
	    // we need to blank it ourselves
	    ourGraphics.setColor(Color.WHITE);
	    ourGraphics.fillRect(0, 0, getWidth(), getHeight());
			
	    // We need to add new transforms to the existing
	    // transform, rather than creating a new transform from scratch.
	    // If we create a transform from scratch, we will
	    // will start from the upper left of a JFrame, 
	    // rather than from the upper left of our component
	    at = new AffineTransform(saveTransform);

	    // The zooming transformation. Notice that it will be performed
	    // after the panning transformation, zooming the panned scene,
	    // rather than the original scene
	    at.translate(getWidth()/2, getHeight()/2);
	    at.scale(scale, scale);
	    at.translate(-getWidth()/2, -getHeight()/2);

	    // The panning transformation
	    at.translate(translateX, translateY);

	    ourGraphics.setTransform(at);

	    // draw the objects
	    ourGraphics.setColor(Color.BLACK);
	    ourGraphics.drawRect(50, 50, 50, 50);
	    ourGraphics.fillOval(100, 100, 100, 100);
	    ourGraphics.drawString("Test Affine Transform", 50, 30);

	    // make sure you restore the original transform or else the drawing
	    // of borders and other components might be messed up
	    ourGraphics.setTransform(saveTransform);
	}
	public Dimension getPreferredSize() {
	    return new Dimension(500, 500);
	}
    }
    
    class PanningHandler implements MouseListener,
				    MouseMotionListener {
	double referenceX;
	double referenceY;
	// saves the initial transform at the beginning of the pan interaction
	AffineTransform initialTransform;

	// capture the starting point 
	public void mousePressed(MouseEvent e) {

	    // first transform the mouse point to the pan and zoom
	    // coordinates
	    try {
		XFormedPoint = at.inverseTransform(e.getPoint(), null);
	    }
	    catch (NoninvertibleTransformException te) {
		System.out.println(te);
	    }

	    // save the transformed starting point and the initial
	    // transform
	    referenceX = XFormedPoint.getX();
	    referenceY = XFormedPoint.getY();
	    initialTransform = at;
	}
	
	public void mouseDragged(MouseEvent e) {
	    
	    // first transform the mouse point to the pan and zoom
	    // coordinates. We must take care to transform by the
	    // initial tranform, not the updated transform, so that
	    // both the initial reference point and all subsequent
	    // reference points are measured against the same origin.
	    try {
		XFormedPoint = initialTransform.inverseTransform(e.getPoint(), null);
	    }
	    catch (NoninvertibleTransformException te) {
		System.out.println(te);
	    }

	    // the size of the pan translations 
	    // are defined by the current mouse location subtracted
	    // from the reference location
	    double deltaX = XFormedPoint.getX() - referenceX;
	    double deltaY = XFormedPoint.getY() - referenceY;

	    // make the reference point be the new mouse point. 
	    referenceX = XFormedPoint.getX();
	    referenceY = XFormedPoint.getY();
	    
	    canvas.translateX += deltaX;
	    canvas.translateY += deltaY;
 
	    // schedule a repaint.
	    canvas.repaint();
	}
 
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	}
 
    class ScaleHandler implements ChangeListener {
	public void stateChanged(ChangeEvent e) {
	    JSlider slider = (JSlider)e.getSource();
	    int zoomPercent = slider.getValue();
	    // make sure zoom never gets to actual 0, or else the objects will
	    // disappear and the matrix will be non-invertible.
	    canvas.scale = Math.max(0.00001, zoomPercent / 100.0);
	    canvas.repaint();
	}
    }
 }
