package drawDemoGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.ufl.digitalworlds.gui.DWApp;


@SuppressWarnings("serial")
/**
 * Sets up KinectViewer, a panel with which to interact with a Kinect
 */
public class KinectViewer extends JPanel
{
	
	private Kinect myKinect;
	private JSlider elevation_angle;
	private JButton turn_off;
	private JRadioButton speedSelectingButton, skeletonSelectingButton;
	private ButtonGroup r;
	
	private KinectListener kl;
	
	/**
	 * constructs KinectViewer
	 * @param kinect Kinect to connect with
	 */
	public KinectViewer(Kinect kinect)
	{
		myKinect = kinect;
		kl = new KinectListener();
		
		if(myKinect.start(true,Kinect.NUI_IMAGE_RESOLUTION_320x240,Kinect.NUI_IMAGE_RESOLUTION_640x480)==0)
		{
			DWApp.showErrorDialog("ERROR", "<html><center><br>ERROR: The Kinect device could not be initialized.<br><br>1. Check if the Microsoft's Kinect SDK was succesfully installed on this computer.<br> 2. Check if the Kinect is plugged into a power outlet.<br>3. Check if the Kinect is connected to a USB port of this computer.</center>");
			System.exit(0); 
		}
		
		myKinect.computeUV(true);
		myKinect.setNearMode(false);
		myKinect.startSkeletonTracking(true);
			
		elevation_angle=new JSlider();
		elevation_angle.setMinimum(-27);
		elevation_angle.setMaximum(27);
		elevation_angle.setValue((int)myKinect.getElevationAngle());
		elevation_angle.setToolTipText("Elevation Angle ("+elevation_angle.getValue()+" degrees)");
		elevation_angle.addChangeListener(kl);
		
		turn_off=new JButton("Turn off");
		turn_off.addActionListener(kl);
		
		JPanel buttons = new JPanel();
		r = new ButtonGroup();
		speedSelectingButton = new JRadioButton("Speed Selection", true);
		skeletonSelectingButton = new JRadioButton("Skeleton Selection");
		r.add(speedSelectingButton);
		r.add(skeletonSelectingButton);
		skeletonSelectingButton.addActionListener(kl);
		speedSelectingButton.addActionListener(kl);
		buttons.add(speedSelectingButton);
		buttons.add(skeletonSelectingButton);
		
			
		add(elevation_angle);
		add(turn_off);
		add(buttons);
	}
	
	private void resetKinect()
	{
		if(turn_off.getText().compareTo("Turn on")==0) return;
		
		myKinect.stop();
		myKinect.start(true,Kinect.NUI_IMAGE_RESOLUTION_640x480,Kinect.NUI_IMAGE_RESOLUTION_320x240);
		myKinect.computeUV(true);
		myKinect.startSkeletonTracking(true);
		myKinect.setNearMode(false);
	}
	
	/**
	 * Stops the kinect
	 */
	public void stopKinect()
	{
		myKinect.stop();
	}
	
	private class KinectListener implements ActionListener, ChangeListener {
		
		public void stateChanged(ChangeEvent e) {
			if(e.getSource()==elevation_angle)
			{
				if(!elevation_angle.getValueIsAdjusting())
				{
					myKinect.setElevationAngle(elevation_angle.getValue());
					elevation_angle.setToolTipText("Elevation Angle ("+elevation_angle.getValue()+" degrees)");
				}
			}
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==turn_off)
			{
				if(turn_off.getText().compareTo("Turn off")==0)
				{
					myKinect.stop();
					turn_off.setText("Turn on");
				}
				else
				{
					turn_off.setText("Turn off");
					resetKinect();
				}
			}
			else if(e.getSource() == speedSelectingButton)
			{
				myKinect.activateSpeedSelecting();
			}
			else if(e.getSource() == skeletonSelectingButton)
			{
				myKinect.activateSkeletonSelecting();
			}
		}


		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
