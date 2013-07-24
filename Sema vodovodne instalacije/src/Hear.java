import java.awt.*;  
import java.awt.event.*;  
   
public class Hear extends Frame {  
    public static void main(String args[]) {  
        new Hear();  
    }  
   
    public Hear() {  
        super("Java 2D Example01");  
        DrawingPanel drawingPanel = new DrawingPanel();  
        ScrollPane scrollPane = new ScrollPane();  
        scrollPane.add(drawingPanel);  
        add(scrollPane);  
        setSize(400,300);  
        setVisible(true);  
        addWindowListener(new WindowAdapter()  
        {public void windowClosing(WindowEvent e)  
            {dispose(); System.exit(0);}});  
    }  
}  
   
class DrawingPanel extends Panel {  
    int maxWidth;  
    int totalHeight;  
    final int PAD = 25;  
   
    public DrawingPanel() {  
        maxWidth    = 75 + 300 + 2*PAD;  
        totalHeight = 75 + 200 + 2*PAD;  
    }  
   
    public void paint(Graphics g) {  
        super.paint(g);  
        g.setColor(Color.red);  
        g.drawRect(50,50,200,200);  
   
        Graphics2D g2d = (Graphics2D)g;  
        g2d.setColor(Color.blue);  
        g2d.drawRect(75,75,300,200);  
    }  
   
    public Dimension getPreferredSize() {  
        return new Dimension(maxWidth, totalHeight);  
    }  
}