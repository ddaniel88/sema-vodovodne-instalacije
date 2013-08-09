package org.javalobby.tnt.java2d;

import java.awt.*;  
import java.awt.geom.*;  
import javax.swing.*;  
   
public class RectRotation extends JPanel {  
    protected void paintComponent(Graphics g) {  
        super.paintComponent(g);  
        Graphics2D g2 = (Graphics2D)g;  
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  
                            RenderingHints.VALUE_ANTIALIAS_ON);  
        double w = getWidth();  
        double h = getHeight();  
        Rectangle2D.Double rect = new Rectangle2D.Double(w/16, h*7/64, w/4, h/4);  
        g2.setPaint(Color.blue);  
        g2.draw(rect);  
      
        System.out.println("rect.x rect.y " + rect.x + " " + rect.y); 
        // Rotate rect 30/45/60 degrees cw about its se corner.  
        // Use transformed shape.  
        AffineTransform at = AffineTransform.getRotateInstance(Math.PI/6,  
                                 rect.x+rect.width, rect.y+rect.height);  
        g2.setPaint(Color.red);  
        Shape sh = at.createTransformedShape(rect);
        g2.draw(sh);  
        System.out.println("rect.x rect.y " + sh.getBounds().y + " " + sh.getBounds().y); 
   
        // Transform the graphics context.  
        rect = new Rectangle2D.Double(w*9/32, h*27/64, w/4, h/4);  
        g2.setPaint(Color.blue);  
        g2.draw(rect);  
        AffineTransform orig = g2.getTransform();  
        at.setToRotation(Math.PI/4, rect.x+rect.width, rect.y+rect.height);  
        g2.transform(at);  
        g2.setPaint(Color.red);  
        g2.draw(rect);  
        g2.setTransform(orig);  
   
        // Use a copy for the transform;  
        rect = new Rectangle2D.Double(w*31/64, h*47/64, w/4, h/4);  
        g2.setPaint(Color.blue);  
        g2.draw(rect);  
        at.setToRotation(Math.PI/3, rect.x+rect.width, rect.y+rect.height);  
        Graphics2D copy = (Graphics2D)g.create();  
        copy.transform(at);  
        copy.setPaint(Color.red);  
        copy.draw(rect);  
        copy.dispose();  
    }  
   
    public static void main(String[] args) {  
        RectRotation panel = new RectRotation();  
        panel.setPreferredSize(new Dimension(400,400));  
        JOptionPane.showMessageDialog(null, panel, "", -1);  
    }  
}  