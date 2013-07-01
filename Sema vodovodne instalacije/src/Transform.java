import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Formatter;

/* This class shows examples of how to use the rotate, scale, and
   translate transformations in Java. The class displays a rectangle
   with two selection handles. One of the selection handles appears
   rotated and the other is scaled to twice its normal size in the
   x direction. The class also draws the x- and y-axes so that the
   user can see the coordinate space set up by the current transform.

   The user can interact with the application in one of four ways:

   1) Rotate the rectangle
   2) Translate the rectangle
   3) Scale (zoom) the rectangle
   4) Click somewhere in the main window and have the mouse coordinates be
      transformed relative to the upper left corner of the rectangle. The
      (X,Y) coordinates appear in the left controls window. For some reason,
      if you click in the window before a transformation has been applied,
      the affine transform object is messed up and the wrong result gets
      displayed. After you perform the first transformation, the mouse
      coordinates are correctly inverted.
*/

public class Transform extends JFrame {

    /* The current transformation amounts */
    double angle = 0.0;
    double scaleX = 1.0;
    double scaleY = 1.0;
    double translateX = 0.0;
    double translateY = 0.0;
    TransPanel mainCanvas;
    transformationControlsPanel controlsCanvas;

    class TransPanel extends JPanel {
        /* The object for the two handles */
	Rectangle2D handle = new Rectangle2D.Double(0, 0, 20, 20);

	/* The rectangle that gets transformed */
	Rectangle2D target = new Rectangle2D.Double(0, 0, 200, 100);

	/* The position where the mouse was last pressed */
	int mouseX = 0;
	int mouseY = 0;

	AffineTransform at;  // the current affine transform
	
	TransPanel() {	    
	    addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
	 	    // record the mouse point 
		    mouseX = e.getX(); mouseY = e.getY();

		    // transform the mouse point to the rectangle's coordinate space
		    // and save the transformed point. You can do a sanity 
		    // check on the transformed point's value by comparing it with
		    // the upper left corner of the rectangle. Do the transformed
		    // values appear to lie at the right point relative to the 
		    // upper left corner of the rectangle? They should.
		    Point2D mousePoint = new Point2D.Double(mouseX, mouseY);
		    try {
			Point2D XFormedPoint = at.inverseTransform(mousePoint, null);
			controlsCanvas.setMousePoint(XFormedPoint);
		    }
		    catch (NoninvertibleTransformException te) {
			System.out.println(te);
		    }
		}
  	    });
	    setBorder(BorderFactory.createEtchedBorder());
	}

	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    Graphics2D g2 = (Graphics2D)g;
	    // get a copy of the current transform so we can restore it later
	    AffineTransform saveTransform = g2.getTransform();

	    // this is the copy we will modify
	    at = g2.getTransform();
	    Color saveColor = g2.getColor();

	    Dimension d = getSize();
	    int width = d.width;
	    int height = d.height;
	    Line2D xAxis = new Line2D.Double(0, 0, width, 0);
	    Line2D yAxis = new Line2D.Double(0, 0, 0, height);

	    // java applies transforms in a LIFO order, so the first
	    // transform you see here is actually the last one applied

	    // adjust the rectangle's coordinate system by the current
	    // transformation amounts
	    at.translate(translateX, translateY);

	    // adjust the rectangle's coordinate system so that it starts
	    // inside the insets for this canvas and so that the upper
	    // left handle is fully displayed
	    Insets insets = getInsets();
	    at.translate(insets.left + handle.getWidth(), 
			 insets.top + handle.getHeight());

	    // notice that we rotate about the target's center, rather than
	    // about the origin of the coordinate system. Notice that we
	    // have to rotate about the scaled center, not the original
	    // viewing coordinates center.
	    at.rotate(angle, target.getWidth()/2*scaleX, target.getHeight()/2*scaleY);

	    // the first transformation we do is a scaling transformation.
	    // we do the scaling transformation first so that parallel
	    // lines and perpendicular lines are maintained. If we switch
	    // the order of the rotate and scale, the rectangle will be
	    // turned into a diamond
	    at.scale(scaleX, scaleY);


	    g2.setTransform(at);

	    // draw the coordinate axes and the rectangle
	    g2.setColor(Color.blue);
 	    g2.draw(xAxis);
	    g2.draw(yAxis);
	    g2.setColor(Color.black);
	    g2.draw(target);

	    // translate the first handle to the upper left corner and then
	    // rotate it by 45 degrees
	    g2.translate(-handle.getWidth()/2, 
			 -handle.getHeight()/2);
	    g2.rotate(Math.PI/4, handle.getWidth()/2, handle.getHeight()/2);
	    g2.fill(handle);

	    // get back the rectangle's coordinate system so that the second
	    // handle can be positioned. We first move the handle so that
	    // its upper left corner lies at the lower right corner of the
	    // rectangle. Then we scale the handle to its correct size and
	    // center it about the lower right corner of the rectangle. It
	    // might seem that the two translations can be combined but that
	    // is not possible. Suppose the two translations are combined.
	    // The resulting translation must be:
	    //  (target.wd - handle.wd / 2), (target.ht - handle.ht / 2)
	    // This translation must either precede the scaling or succeed
	    // it. Let's examine both cases:
	    // 
	    // 1) The translation precedes the scaling: The translation
	    //    set's the handles coordinate space to start at the same
	    //    point where the handle's upper, left corner should
	    //    ultimately reside. When the scaling is applied, it
	    //    stretches the coordinate space starting at this upper,
	    //    left corner. The result is that the upper, left corner
	    //    of the handle is in the correct position but the lower,
	    //    right corner gets stretched too far (1/4 of the handle lies
	    //    to the left of the rectangle's lower right corner and 3/4
	    //    of the handle lies to the right of the rectangle's lower
	    //    right corner.
	    // 
     	    // 2) The translation succeeds the scaling: The scaling is now
	    //    applied to the rectangle's entire coordinate space, which
	    //    means that the translation will get elongated to twice
	    //    the length you want it to. The handle will now show up
	    //    well to the right of the lower, right corner of the
	    //    rectangle. The problem is that the appropriate scaling gets
	    //    applied to the handle.getWidth part of the translation but
	    //    the wrong scaling gets applied to the target.getWidth part
	    //    of the translation. By doing the translation for 
	    //    target.getWidth before the coordinate space gets elongated,
	    //    the handle gets placed properly.
	    g2.setTransform(at);
	    g2.translate(target.getWidth(), target.getHeight());
	    g2.scale(2.0, 1.0);
	    g2.translate(-handle.getWidth()/2, -handle.getHeight()/2);
	    g2.fill(handle);
	    g2.setTransform(saveTransform);
	    g2.setColor(saveColor);
	}

        public Dimension getPreferredSize() {
	    return new Dimension(400, 400);
	}
    }

    public Transform() {
	mainCanvas = new TransPanel();
	controlsCanvas = new transformationControlsPanel();
	getContentPane().add(mainCanvas, BorderLayout.CENTER);
	getContentPane().add(controlsCanvas, BorderLayout.WEST);
    }

    public static void main(String argv[]) {
	Transform t = new Transform();
	t.pack();
	t.setVisible(true);
    }

    class transformationControlsPanel extends JPanel {
	// variables for formatting a mouse point
	StringBuilder formatString = new StringBuilder();
	Formatter ptFormatter = new Formatter(formatString) ; 
	JLabel mouseX = new JLabel("X: 0");
	JLabel mouseY = new JLabel("Y: 0");
	JPanel mousePanel = new JPanel();

        public transformationControlsPanel() {
	    JSlider rotateSlider = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
	    JSlider xTranslateSlider = new JSlider(JSlider.HORIZONTAL, 0, 300, 0);
	    JSlider yTranslateSlider = new JSlider(JSlider.HORIZONTAL, 0, 300, 0);
	    JSlider xScaleSlider = new JSlider(JSlider.HORIZONTAL, 0, 200, 100);
	    JSlider yScaleSlider = new JSlider(JSlider.HORIZONTAL, 0, 200, 100);
	    JLabel rotateLabel = new JLabel("rotation angle: ");
	    JLabel xTranslateLabel = new JLabel("x translation: ");
	    JLabel yTranslateLabel = new JLabel("y translation: ");
	    JLabel xScaleLabel = new JLabel("x zoom percentage: ");
	    JLabel yScaleLabel = new JLabel("y zoom percentage: ");

	    // add action listeners
	    rotateSlider.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent ce) {
			JSlider slider = (JSlider)ce.getSource();
			angle = Math.toRadians(slider.getValue());
			mainCanvas.repaint();
		    }
		});
	    xTranslateSlider.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent ce) {
			JSlider slider = (JSlider)ce.getSource();
			translateX = slider.getValue();
			mainCanvas.repaint();
		    }
		});

	    yTranslateSlider.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent ce) {
			JSlider slider = (JSlider)ce.getSource();
			translateY = slider.getValue();
			mainCanvas.repaint();
		    }
		});

	    xScaleSlider.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent ce) {
			JSlider slider = (JSlider)ce.getSource();
			scaleX = Math.max(0.00001, slider.getValue() / 100.0);
			mainCanvas.repaint();
		    }
		});

	    yScaleSlider.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent ce) {
			JSlider slider = (JSlider)ce.getSource();
			scaleY = Math.max(0.00001, slider.getValue() / 100.0);
			mainCanvas.repaint();
		    }
		});

	    rotateSlider.setMinorTickSpacing(9);
	    rotateSlider.setMajorTickSpacing(45);
	    xTranslateSlider.setMinorTickSpacing(10);
	    xTranslateSlider.setMajorTickSpacing(50);
	    yTranslateSlider.setMinorTickSpacing(10);
	    yTranslateSlider.setMajorTickSpacing(50);
	    xScaleSlider.setMinorTickSpacing(5);
	    xScaleSlider.setMajorTickSpacing(25);
	    yScaleSlider.setMinorTickSpacing(5);
	    yScaleSlider.setMajorTickSpacing(25);

	    // enable painting of tick marks in addSliderRows
	    GridBagLayout layoutMgr = new GridBagLayout();
	    setLayout(new BorderLayout());
	    JPanel controlsPanel = new JPanel(layoutMgr);
	    JLabel[] labels = { rotateLabel, xTranslateLabel, yTranslateLabel,
			       xScaleLabel, yScaleLabel };
	    JSlider [] sliders = { rotateSlider, xTranslateSlider,
				   yTranslateSlider,
				   xScaleSlider, yScaleSlider };
	    addSliderRows(labels, sliders, layoutMgr, controlsPanel);
	    mousePanel.add(mouseX);
	    mousePanel.add(mouseY);
	    this.add(controlsPanel, BorderLayout.CENTER);
	    this.add(mousePanel, BorderLayout.SOUTH);
	    setBorder(BorderFactory.createEtchedBorder());
	}

	/* lay out the labels and sliders so that the labels are 
	   right aligned with one another and so that the sliders
	   get all the additional space allocated to them */
	void addSliderRows(JLabel[] labels,
			   JSlider[] sliders,
			   GridBagLayout gridbag,
			   Container container) {
	    GridBagConstraints c = new GridBagConstraints();
	    c.anchor = GridBagConstraints.EAST;
	    int numLabels = labels.length;

	    for (int i = 0; i < numLabels; i++) {
		c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
		c.fill = GridBagConstraints.NONE;      //reset to default
		c.weightx = 0.0;                       //reset to default
		container.add(labels[i], c);

		c.gridwidth = GridBagConstraints.REMAINDER;     //end row
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		container.add(sliders[i], c);
		sliders[i].setPaintTicks(true);
		sliders[i].setPaintLabels(true);
	    }
	}

	    public void setMousePoint(Point2D pt) {
		formatString.setLength(0); // clear the buffer
		ptFormatter.format("%6.0f", pt.getX());
		mouseX.setText("X: " + formatString.toString());
		formatString.setLength(0); // clear the buffer
		ptFormatter.format("%6.0f", pt.getY());
		mouseY.setText("Y: " + formatString.toString());
		mousePanel.revalidate();
		repaint();
	    }


	public Dimension getPreferredSize() {
	    return new Dimension(400, 400);
	}
    }
}
