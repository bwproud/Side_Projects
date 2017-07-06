package a7;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//this is the widget that is housed in the main JPanel
public class ImageAdjusterWidget extends JPanel implements ChangeListener{

	//instance variables
	private FrameView frame_view;
	private JPanel bottom;
	private Frame2D frame;
	private Frame2D frameCopy;
	private JSlider bSlider;
	private JSlider sSlider;
	
	
	/* Input: A Frame2D frame
	* Output: na/
	* Error: n/a
	* Description: Constructor for ImageAdjusterWidget 
	*/
	public ImageAdjusterWidget(Frame2D f) {
		
		setLayout(new BorderLayout());
		
		//creates a new frameview and adds it to the widget
		this.frame = ((MutableFrame2D)f).copy();
		this.frameCopy = ((MutableFrame2D)frame).copy();
		frame_view = new FrameView(f.createObservable());
		add(frame_view, BorderLayout.CENTER);
		
		//panel for the sliders
		bottom = new JPanel();
		bottom.setLayout(new GridLayout(0,1));
		add(bottom, BorderLayout.SOUTH);
		
		//adds the sliders
		bSlider();
		sSlider();
	}

	
	/* Input: n/a
	* Output: Adds a slider to the widget
	* Error: n/a
	* Description: Adds the saturation slider to the widget
	*/
	public void sSlider(){
		
		//creates a new slider
		sSlider = new JSlider(-100, 100, 0);
		
		//chooses slider settings
		sSlider.addChangeListener(this);
		sSlider.setPaintTicks(true);
		sSlider.setSnapToTicks(false);
		sSlider.setPaintLabels(true);
		sSlider.setMajorTickSpacing(25);
		
		//adds the slider to the JPanel
		bottom.add(new JLabel("Saturation: "));
		bottom.add(sSlider);
	}
	
	
	/* Input: n/a
	* Output: Adds a slider to the widget
	* Error: n/a
	* Description: Adds the brightness slider to the widget
	*/
	public void bSlider(){
		
		//creates a new slider
		bSlider = new JSlider(-100, 100, 0);
		
		//chooses slider settings
		bSlider.addChangeListener(this);
		bSlider.setPaintTicks(true);
		bSlider.setSnapToTicks(false);
		bSlider.setPaintLabels(true);
		bSlider.setMajorTickSpacing(25);
		
		//adds the slider to the JPanel
		bottom.add(new JLabel("Brightness: "));
		bottom.add(bSlider);
	}
	
	
	/* Input: A ChangeEvent(A change in one of the sliders)
	* Output: reflects the change in the frameView
	* Error: n/a
	* Description: reflects any changes in the state of the sliders
	*/
	public void stateChanged(ChangeEvent e) {
		
		//applies any change in saturation or brightness
		frameCopy.saturation(sSlider.getValue()/1.0).brightness(
													bSlider.getValue()/100.0);
		ObservableFrame2D a = new ObservableFrame2DImpl(
										frameCopy.createObservable());
		
		//reflects the change in the FrameView
		frame_view.setFrame(a);
		
		//resets the variable frameCopy
		frameCopy=((MutableFrame2D)frame).copy();
	}
}
