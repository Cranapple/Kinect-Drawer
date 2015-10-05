Kinect Interface
Lucas Tecot, Amir Mazor, Avi Gokhale
2014

----------------------------------------------------------------------------
DESCRIPTION:
This program will use the open source java kinect libraries to write a program that utilizes the kinect's motion detection abilities.
When the program starts, there will be four options for the user. There will be one Kinect Viewer App, which lets the user see a 3D representation of him/herself. The Image Avatar App will let the user act as an avatar, with the default being Barack Obama. There are two versions of the Draw Demo Game, with one working with the Kinet, and one working as a basic paint program with the mouse.
With the kinect Draw Demo Game, when selection is turned on, the movement of the user's hand is drawn on the screen.

----------------------------------------------------------------------------
INSTRUCTIONS:
While standing in front of a kinect plugged into the computer running the program, simply move your hand to move the cursor around the screen. The user will be able to draw on a canvas with his/her hand by two methods of selection. The first is speed selection, where if the hand is flicked, selection turns on, and flicking again would turn selection off. The other method is skeleton selection, where if the hand is pushed forward, selection turns on, and if the hand is moved back, selection turns off. For changing settings of the marker, changing the method of selection, and tilting the kinect will be done with a mouse.

----------------------------------------------------------------------------
FEATURE LIST:

Must Have:
A simple program that interfaces with the kinect using java successfully. This likely will be just a simple program to draw paintings with your hand.

Want to Have:
A more complicated program that can take a picture using the kinect and make puzzle pieces out of the picture.
The user then can drag puzzle pieces into the required places to connect the puzzle into the original picture.
A shooting game where the user would shoot enemies by flicking his hand in a pistol shape and then reload by moving his hand sideways (out of the screen).

Stretch Features:
A nice cursor that uses neat animations when pressed and moved, many options allowing you to customize your gesture to select items and change the cursor, 
an on and off button for easy switching between the kinect and the mouse, etc.
Also a shooting game that uses the kinect to be able to use hands to aim and fire at picture of enemies.

----------------------------------------------------------------------------
CLASS LIST:
SwagOverloadRunner - creates the game (main method)
Menu - puts all games together and lets user choose which he/she wants to play

DrawDemoGame - a simple "Paint" program that allows the user to paint using mouse and kinect
Options - a panel with options for modifying the "marker" used to draw
Canvas - panel on which the user draws
Kinect - class that works with data coming in from kinect and feeds to program to modify the canvas
KinectViewer - options for the kinect to change the angle (physically) and method of selection for user

DrawDemoGameNoKinect - version of the Draw Demo Game that works with the mouse
MouseCanvas - a canvas that works with a Mouse Listener instead of a Kinect
MouseOptions - same as options, except modified to work with a Mouse Listener

ImageAvatarApp - app that shows user an avatar reflecting user's skeleton movements as seen by the Kinect
CardboardAvatar - represents avatar that is shown in the app
Kinect - analyzes kinect data and gives it to app
ViewerPanel3D - 3D representation in app + screen seen by user

KinectViewerApp - app that shows 3D representation of user
Kinect- uses data from kinect and feeds it to app
ViewerPanel3D - 3D representation in app + screen seen by user (not very different from ViewerPanel3D in ImageAvatarApp)

----------------------------------------------------------------------------
RESPONSIBILITY LIST:

Lucas Tecot:
Lucas was generally responsible for Kinect side classes, including getting the kinect to work with the computer, working with generic libraries, and skeleton data

Amir Mazor:
Amir was generally responsible for making the Draw Demo Game, including drawing using the mouse and changing the marker.

Avi Gokhale:
Avi was generally responsible for SwagOverloadRunner and Menu, putting the options into a window and changing to different windows, as well as connecting the kinect to the canvas.

All members were pretty involved with the entire process, with a lot of group work on solving problems, especially with null skeletons with the kinect.

----------------------------------------------------------------------------
REQUIRED SOFTWARE TO RUN KINECT:

Kinect SDK:
http://www.microsoft.com/en-us/kinectforwindowsdev/Downloads.aspx

Kinect Drivers:
http://www.microsoft.com/en-us/download/details.aspx?id=36997