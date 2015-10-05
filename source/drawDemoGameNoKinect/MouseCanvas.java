package drawDemoGameNoKinect;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

/**
 * JPanel that can be drawn on.
 */
public class MouseCanvas extends JPanel{


	/* The following variables are used when the user is sketching a
     curve while dragging a mouse. */

	private int prevX, prevY;  // The previous location of the mouse.
	private boolean dragging;      // This is set to true while the user is drawing.
	private int brushSize;
	private Color markerColor;
	
	private Graphics2D graphicsForDrawing;
	
	private CanvasListener cl;


	/**
	 * Creates new Canvas on which the user can draw. Sets the color set to black
	 */
	public MouseCanvas() {
		cl = new CanvasListener();
		dragging = false;
		setBackground(Color.WHITE);
		addMouseListener(cl);
		addMouseMotionListener(cl);
	}

	/**
	 * Draws a thin grey border around area where the user can draw
	 * @param g Graphics used to draw the border
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);  // Fill with background color (white).

		int width = getWidth();    // Width of the panel.
		int height = getHeight();  // Height of the panel.
		

		//Border
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, width-1, height-1);
		g.drawRect(1, 1, width-3, height-3);
		g.drawRect(2, 2, width-5, height-5);

	}
	
	/**
	 * Sets size of brush
	 * @param bs brush size
	 */
	public void setBrushSize(int bs) {
		brushSize = bs;
	}
	
	/**
	 * Sets color of "marker"
	 * @param c color in which to draw
	 */
	public void setMarkerColor(Color c) {
		markerColor = c;
	}
	
	private void setUpMarker() {
		graphicsForDrawing = (Graphics2D)getGraphics();
		graphicsForDrawing.setColor(markerColor);

	}
	
	private class CanvasListener implements MouseListener, MouseMotionListener{
		public void mouseDragged(MouseEvent evt) {
			if (dragging == false)
				return;  // Nothing to do because the user isn't drawing.

			int x = evt.getX();   // x-coordinate of mouse.
			int y = evt.getY();   // y-coordinate of mouse.

			if (x < 3+brushSize/2)                          // Adjust the value of x,
				x = 3+brushSize/2;                           //   to make sure it's in
			if (x > getWidth() - 4-brushSize/2)       //   the drawing area.
				x = getWidth() - 4-brushSize/2;

			if (y < 3+brushSize/2)                          // Adjust the value of y,
				y = 3+brushSize/2;                           //   to make sure it's in
			if (y > getHeight() - 4-brushSize/2)       //   the drawing area.
				y = getHeight() - 4-brushSize/2;
			

			graphicsForDrawing.setStroke(new BasicStroke(brushSize));
			graphicsForDrawing.drawLine(prevX, prevY, x, y);  // Draw the line.

			prevX = x;  // Get ready for the next line segment in the curve.
			prevY = y;

		}



		public void mousePressed(MouseEvent evt) {
			int x = evt.getX();  
			int y = evt.getY(); 

			if (dragging == true)  // Ignore mouse presses that occur
				return;            //    when user is already drawing a curve.
			//    (This can happen if the user presses
			//    two mouse buttons at the same time.)

			// Start drawing a curve from the point (x,y).
			prevX = x;
			prevY = y;
			dragging = true;
			setUpMarker();
		}
		
		public void mouseReleased(MouseEvent evt) {
			if (dragging == false)
				return;  // Nothing to do because the user isn't drawing.
			dragging = false;
			graphicsForDrawing.dispose();
			graphicsForDrawing = null;		
		}
		
		public void mouseMoved(MouseEvent arg0) {}
		public void mouseClicked(MouseEvent arg0) {}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}

	}
	



}
