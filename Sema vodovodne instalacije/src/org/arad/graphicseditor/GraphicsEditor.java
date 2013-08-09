package org.arad.graphicseditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class GraphicsEditor extends JFrame {

	private static final long serialVersionUID = 1L;

	private JMenuBar menubar = null;
	private JMenu jmenuEdit = null;
	private JMenuItem jmenuItemZoomIn = null;
	private JMenuItem jmenuItemZoomOut = null;
	private JMenuItem jmenuItemRotatePlus = null;
	private JMenuItem jmenuItemRotateMinus = null;

	private MainCanvas mainCanvas = null;

	public GraphicsEditor() {

		super("Grafički editor");
		this.setLayout(new BorderLayout());
		this.setSize(600, 600);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.mainCanvas = new MainCanvas(this);
		this.mainCanvas.initialize();

		JScrollPane scrollPane = new JScrollPane(mainCanvas);
		scrollPane.setPreferredSize(new Dimension(800, 800));
		scrollPane.setOpaque(true);
		scrollPane.setViewportBorder(BorderFactory
				.createLineBorder(Color.black));

		this.setContentPane(scrollPane);

		this.setJMenuBar(getMenubar());

		this.setLocationRelativeTo(null);

	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				GraphicsEditor graphicsEditor = new GraphicsEditor();
				graphicsEditor.pack();
				graphicsEditor.setVisible(true);
			}
		});

	}

	public JMenuBar getMenubar() {
		if (menubar == null) {
			this.menubar = new JMenuBar();
			this.menubar.add(this.getJmenuEdit());
		}
		return menubar;
	}

	public JMenuItem getJmenuItemZoomIn() {
		if (jmenuItemZoomIn == null) {
			this.jmenuItemZoomIn = new JMenuItem("Zoom in");
			this.jmenuItemZoomIn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					mainCanvas.zoomIn();
				}
			});
		}
		return jmenuItemZoomIn;
	}

	public JMenuItem getJmenuItemZoomOut() {
		if (jmenuItemZoomOut == null) {
			this.jmenuItemZoomOut = new JMenuItem("Zooom out");
			this.jmenuItemZoomOut.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					mainCanvas.zoomOut();
				}
			});

		}
		return jmenuItemZoomOut;
	}

	public JMenuItem getJmenuItemRotatePlus() {
		if (jmenuItemRotatePlus == null) {
			this.jmenuItemRotatePlus = new JMenuItem("Rotate +");
			this.jmenuItemRotatePlus.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					mainCanvas.rotatePlus();
				}
			});
		}
		return jmenuItemRotatePlus;
	}

	public JMenuItem getJmenuItemRotateMinus() {
		if (jmenuItemRotateMinus == null) {
			this.jmenuItemRotateMinus = new JMenuItem("Rotate -");
			this.jmenuItemRotateMinus.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					mainCanvas.rotateMinus();
				}
			});
		}
		return jmenuItemRotateMinus;
	}

	public JMenu getJmenuEdit() {
		if (jmenuEdit == null) {
			this.jmenuEdit = new JMenu("Edit");
			this.jmenuEdit.add(getJmenuItemZoomIn());
			this.jmenuEdit.add(getJmenuItemZoomOut());
			this.jmenuEdit.add(getJmenuItemRotatePlus());
			this.jmenuEdit.add(getJmenuItemRotateMinus());

		}
		return jmenuEdit;
	}

}
