package drawDemoGame;
import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * This class represents a Drawing game, that can be added to a Menu
 */
public class DrawDemoGame extends JPanel{
	private Options options;
	private Canvas canvas;
	private KinectViewer kdubs;
	
	/**
	 * Creates a new DrawDemoGame
	 * Also sets the layout and adds Canvas, Options and a KinectViewer to the game.
	 */
	public DrawDemoGame(){
		super();
		canvas = new Canvas();
		options = new Options(canvas);
		Kinect kinect = new Kinect(canvas);
		kdubs = new KinectViewer(kinect);
		setLayout(new BorderLayout());
		add(canvas, BorderLayout.CENTER);
		add(options, BorderLayout.NORTH);
		add(kdubs, BorderLayout.SOUTH);
	}
	
	/**
	 * Returns title of game
	 * @return title of game
	 */
	public String getTitle(){
		return "Draw Demo Game";
	}
	
	/**
	 * Stops the kinect
	 */
	public void stopKinect()
	{
		kdubs.stopKinect();
	}
	
}
