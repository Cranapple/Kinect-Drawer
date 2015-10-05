package drawDemoGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * JPanel that can be drawn on.
 */
public class Canvas extends JPanel{

	private int prevX, prevY;  // The previous location of the mouse.
	private boolean dragging;  // This is set to true while the user is drawing.
	private int brushSize;
	private Color markerColor;
	
	private Graphics2D graphicsForDrawing;


	/**
	 * Creates new Canvas on which the user can draw, with the color set to black
	 */
	public Canvas() {
		dragging = false;
		setBackground(Color.WHITE);
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
	
	//FOR KINECT
	/**
	 * Continues to draw a curve once started
	 * @param x x coord of cursor
	 * @param y y coord of cursor
	 */
	public void dragged(int x, int y) {
		if (dragging == false)
			return;  // Nothing to do because the user isn't drawing.

		if (x < 3+brushSize/2)                          // Adjust the value of x,
			x = 3+brushSize/2;                           //   to make sure it's in
		if (x > getWidth() - 4-brushSize/2)       //   the drawing area.
			x = getWidth() - 4-brushSize/2;

		if (y < 3+brushSize/2)                          // Adjust the value of y,
			y = 3+brushSize/2;                           //   to make sure it's in
		if (y > getHeight() - 4-brushSize/2)       //   the drawing area.
			y = getHeight() - 4-brushSize/2;
		if(y<28 && x>getWidth()-103){
			x = prevX;
			y = prevY;
		}

		graphicsForDrawing.setStroke(new BasicStroke(brushSize));
		graphicsForDrawing.setColor(markerColor);
		graphicsForDrawing.drawLine(prevX, prevY, x, y);  // Draw the line.

		prevX = x;  // Get ready for the next line segment in the curve.
		prevY = y;

	}
	
	/**
	 * Starts drawing a curve.
	 * @param x x coord of selection
	 * @param y y coord of selection
	 */
	public void selected(int x, int y) 
	{
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
	
	/**
	 * Stops drawing a curve
	 */
	public void released() {
		if (dragging == false)
			return;  // Nothing to do because the user isn't drawing.
		dragging = false;
		graphicsForDrawing.dispose();
		graphicsForDrawing = null;		
	}
	

	private void setUpMarker() {
		graphicsForDrawing = (Graphics2D)getGraphics();
		graphicsForDrawing.setColor(markerColor);

	}


}
