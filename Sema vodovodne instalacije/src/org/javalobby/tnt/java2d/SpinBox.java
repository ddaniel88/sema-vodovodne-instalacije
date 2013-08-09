package org.javalobby.tnt.java2d;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SpinBox {

	public static void main(String[] args) {
		new SpinBox();
	}

	public SpinBox() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
               /* try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }*/

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new SpinPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

	public class SpinPane extends JPanel {

		private Rectangle box = new Rectangle(0, 0, 100, 100);
		private float angle = 0;

		public SpinPane() {
			Timer timer = new Timer(1000 / 60, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					angle += 1;
					repaint();
				}
			});
			timer.setRepeats(true);
			timer.start();
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(200, 200);
		}

		@Override
		protected void paintComponent(Graphics g) {

			super.paintComponent(g);

			int width = getWidth() - 1;
			int height = getHeight() - 1;

			int x = (width - box.width) / 2;
			int y = (height - box.height) / 2;

			Graphics2D g2d = (Graphics2D) g.create();
			AffineTransform at = new AffineTransform();
			at.rotate(Math.toRadians(angle), box.x + (box.width / 2), box.y
					+ (box.height / 2));
			PathIterator pi = box.getPathIterator(at);
			Path2D path = new Path2D.Float();
			path.append(pi, true);
			g2d.translate(x, y);
			g2d.draw(path);
			g2d.dispose();

		}

	}

}
