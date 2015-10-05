package drawDemoGameNoKinect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Creates a new Options panel that can be added to a DrawDemoGame
 */
public class MouseOptions extends JPanel {
	
	private Color currentColor;
	
	private ButtonGroup r;
	private JRadioButton blackButton, redButton,blueButton, greenButton,magentaButton,yellowButton;
	private JSlider slide;
	private JButton clearButton;
	
	private ButtonListener bl;
	private SliderListener sl;
	private MouseCanvas myCanvas;
	
	/**
	 * Creates a new Options panel
	 */
	public MouseOptions(MouseCanvas c) {
		myCanvas = c;
		currentColor = Color.BLACK;
		bl = new ButtonListener();
		sl = new SliderListener();
		currentColor = Color.BLACK;
		setLayout(new BorderLayout());
		r = new ButtonGroup();
		blackButton = new JRadioButton("Black", true);
		redButton = new JRadioButton("Red");
		blueButton = new JRadioButton("Blue");
		greenButton = new JRadioButton("Green");
		magentaButton = new JRadioButton("Magenta");
		yellowButton = new JRadioButton("Yellow");
		
		r.add(blackButton);
		r.add(redButton);
		r.add(blueButton);
		r.add(greenButton);
		r.add(magentaButton);
		r.add(yellowButton);
		
		blackButton.addActionListener(bl);
		redButton.addActionListener(bl);
		blueButton.addActionListener(bl);
		greenButton.addActionListener(bl);
		magentaButton.addActionListener(bl);
		yellowButton.addActionListener(bl);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1,6));
		buttons.add(blackButton);
		buttons.add(redButton);
		buttons.add(blueButton);
		buttons.add(greenButton);
		buttons.add(magentaButton);
		buttons.add(yellowButton);
		add(buttons, BorderLayout.NORTH);
		
		slide = new JSlider(1,10);
		slide.setMajorTickSpacing(1);
		slide.setPaintTicks(true);
		slide.setPaintLabels(true);
		slide.addChangeListener(sl);
		slide.setValue(1);
		
		add(slide,BorderLayout.CENTER);
		
		JPanel toolBoxButtons = new JPanel();
		toolBoxButtons.setLayout(new GridLayout());
		
		clearButton = new JButton("Clear");
		clearButton.addActionListener(bl);
		
		toolBoxButtons.add(clearButton);
		add(toolBoxButtons, BorderLayout.SOUTH);
		
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			Object x = evt.getSource();
			if(x == blackButton){
				currentColor = Color.BLACK;
			} else if (x == redButton){
				currentColor = Color.RED;
			} else if (x == blueButton){
				currentColor = Color.BLUE;
			} else if (x == greenButton){
				currentColor = Color.GREEN;
			} else if (x == magentaButton){
				currentColor = Color.MAGENTA;
			} else if (x == yellowButton){
				currentColor = Color.YELLOW;
			} else if (x == clearButton) {
				myCanvas.repaint();
			} 
			myCanvas.setMarkerColor(currentColor);
			repaint();
		}
		
	}
	
	private class SliderListener implements ChangeListener{
		public void stateChanged(ChangeEvent evt) {
			int xVal = slide.getValue();
			myCanvas.setBrushSize(xVal*2-1);
		}		
	}

}
