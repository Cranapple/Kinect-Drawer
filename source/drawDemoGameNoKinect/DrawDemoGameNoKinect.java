package drawDemoGameNoKinect;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * This class represents a Drawing game, that can be added to a Menu
 */
public class DrawDemoGameNoKinect extends JPanel{
	private MouseOptions options;
	private MouseCanvas canvas;
	
	/**
	 * Creates a new DrawDemoGameNoKinect
	 * Also sets the layout and adds Canvas and Options to the game.
	 */
	public DrawDemoGameNoKinect(){
		super();
		canvas = new MouseCanvas();
		options = new MouseOptions(canvas);
		setLayout(new BorderLayout());
		add(canvas, BorderLayout.CENTER);
		add(options, BorderLayout.NORTH);
	}
	
	/**
	 * Returns title of game
	 * @return title of game
	 */
	public String getTitle(){
		return "Draw Demo Game";
	}
	
}
