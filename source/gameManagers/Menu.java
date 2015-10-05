package gameManagers;

import imageavatarapp.ImageAvatarApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import drawDemoGame.DrawDemoGame;
import drawDemoGameNoKinect.DrawDemoGameNoKinect;
import kinectViewerApp.KinectViewerApp;


/**
 * Creates a menu to select which application the user wants to use.
 */
public class Menu extends JFrame{
	
	private JButton startDDG, startKVA, startIAA, startDDGNK;
	private JPanel options;
	
	/**
	 * Creates Menu object with options to run each application
	 * @param title title of window
	 */
	public Menu (String title) {
		super(title);
		setBounds(300, 300, 700, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		OptionListener ol = new OptionListener();
		startDDG = new JButton("Draw Demo");
		startKVA = new JButton("Kinect Viewer App");
		startIAA = new JButton("Image Avatar App");
		startDDGNK = new JButton("Draw Demo No Kinect");
		startDDG.addActionListener(ol);
		startKVA.addActionListener(ol);
		startIAA.addActionListener(ol);
		startDDGNK.addActionListener(ol);
		
		options = new JPanel();
		options.add(startDDG);
		options.add(startKVA);
		options.add(startIAA);
		options.add(startDDGNK);
		add(options);
		
	}
	
	private class OptionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			JButton choice = (JButton)(e.getSource());
			
			if(choice == startDDG) {
				DrawDemoGame game = new DrawDemoGame();
				JFrame runner = new JFrame();
				runner.add(game);
				runner.setBounds(0, 0, 1000, 500);
				runner.setVisible(true);
				dispose();
			} else if (choice == startKVA) {
				dispose();
				KinectViewerApp.main(null);
			} else if (choice == startIAA) {
				dispose();
				ImageAvatarApp.main(null);
			} else if (choice == startDDGNK){
				DrawDemoGameNoKinect game = new DrawDemoGameNoKinect();
				JFrame runner = new JFrame();
				runner.add(game);
				runner.setBounds(0, 0, 1000, 500);
				runner.setVisible(true);
				dispose();
			}
		}

	}
	
	
}
