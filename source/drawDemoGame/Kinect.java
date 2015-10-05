package drawDemoGame;
import java.util.ArrayList;

import edu.ufl.digitalworlds.j4k.J4KSDK; 
import edu.ufl.digitalworlds.j4k.DepthMap; 
import edu.ufl.digitalworlds.j4k.Skeleton; 
import edu.ufl.digitalworlds.j4k.VideoFrame; 

/**
 * Class that communicates with the kinect and causes its canvas to draw on itself according to the data gathered.
 */
public class Kinect extends J4KSDK
{
	
	private boolean speedSelecting;
	private int rightHandX;
	private int rightHandY;
	private int rightHandZ;
	private boolean selecting;
	private boolean previouslySelecting;
	private Canvas canvas;
	private long previousTime;
	private int previousZ;
	
	/**
	 * Constructs new Kinect object
	 * @param c canvas to interact with
	 */
	public Kinect(Canvas c) 
	{ 
		super(); 
	    canvas = c;
	    selecting = false;
		previouslySelecting = false;
		previousTime = System.currentTimeMillis();
		rightHandX = 0;
		rightHandY = 0;
		rightHandZ = 0;
		previousZ = 0;
		speedSelecting = true;
	}
	
	@Override 
	/**
	 * Gives depth data from kinect. Called whenever a new depth frame is available
	 * @param depth info on depth of parts on skeleton
	 * @param U info for skeleton
	 * @param V info for skeleton
	 */
	public void onDepthFrameEvent(short[] depth, int[] U, int V[]) {
		
	}
	
    @Override 
    /**
     * Gives skeleton data from the kinect. Called whenever a new skeleton frame is available
     * Causes the canvas to draw on itself according to the received skeletons
     * @param data data about skeleton
     * @param flags flags on skeleton
     */
    public void onSkeletonFrameEvent(float[] data, boolean[] flags) 
    {
    	Skeleton[] skeletons = new Skeleton[Kinect.NUI_SKELETON_COUNT];
		for(int i=0;i<Kinect.NUI_SKELETON_COUNT;i++)
			skeletons[i]=Skeleton.getSkeleton(i, data, flags);
	    
		ArrayList<Skeleton> potentials = new ArrayList<Skeleton>();
		Skeleton target = null;
		Skeleton placeHolder = null;
		for(int i = 0; i < skeletons.length; i++)
		{
			if(skeletons[i] != null)
			{
				placeHolder = skeletons[i];
				if(skeletons[i].getTimesDrawn()<=10 && skeletons[i].isTracked())
				{
					placeHolder.increaseTimesDrawn();
					potentials.add(placeHolder);
				}
			}
		}
		
		int z = 1000000000;
		for(int i = 0; i < potentials.size(); i++)
		{
			Skeleton s = potentials.get(i);
			if((int)(1000 * s.get3DJointZ(Skeleton.HAND_RIGHT)) < z)
			{
				z = (int)(1000 * s.get3DJointZ(Skeleton.HAND_RIGHT));
				target = s;
			}
		}
		
		if(target != null)
		{
			rightHandX = (int)(canvas.getWidth() * target.get3DJointX(Skeleton.HAND_RIGHT) + canvas.getWidth()/2);
		    rightHandY = (int)(-canvas.getHeight() * target.get3DJointY(Skeleton.HAND_RIGHT) + canvas.getHeight()/2);
		    rightHandZ = (int)(1000 * target.get3DJointZ(Skeleton.HAND_RIGHT));
		    
		    if(speedSelecting == true)
		    {
			    if(System.currentTimeMillis() - previousTime > 50)
				{
					previousTime = System.currentTimeMillis();
					if(selecting == false)
					{
						if(rightHandZ - previousZ <= -100)
							selecting = true;
					}
					else
					{
						if(rightHandZ - previousZ >= 100)
							selecting = false;
					}
					previousZ = rightHandZ;
					
				}
		    }
		    else
		    {
				if((rightHandZ - (int)(1000 * target.get3DJointZ(Skeleton.HEAD)) <= -300))
					selecting = true;
				else
					selecting = false;
		    }
		    
			if(previouslySelecting == false && selecting == true)
			    canvas.selected(rightHandX, rightHandY);
			else if(previouslySelecting == true && selecting == true)
				canvas.dragged(rightHandX, rightHandY);
			else
				canvas.released();
		}
		else 
		{
			selecting = false;
		}
		previouslySelecting = selecting;
	}
	  
    @Override
    /**
     * Gives video data from the kinect. Called whenever a new video frame is available
     * @param data data about the video frame
     */
	public void onVideoFrameEvent(byte[] data) {
		
	}
    
    public int getRightHandX()
    {
    	return rightHandX;
    }
    
    public int getRightHandY()
    {
    	return rightHandY;
    }
    
    public boolean isSelecting()
    {
    	return selecting;
    }
    
    public boolean wasSelecting()
    {
    	return previouslySelecting;
    }
    
    /**
     * Activates speed selecting
     */
    public void activateSpeedSelecting()
    {
    	speedSelecting = true;
    }
    
    /**
     * Activates skeleton selecting
     */
    public void activateSkeletonSelecting()
    {
    	speedSelecting = false;
    }
}
