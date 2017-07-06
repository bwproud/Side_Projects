package a7;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//this is the widget that is housed in the main JPanel

public class PixelInspectorWidget extends JPanel implements MouseListener {

	//instance variables
	private FrameView frame_view;
	private JPanel pixelInspector;
	private JLabel xCoordinate;
	private JLabel yCoordinate;
	private JLabel r;
	private JLabel g;
	private JLabel b;
	private JLabel i;
	
	
	/* Input: A Frame2D frame
	* Output: n/a
	* Error: n/a
	* Description: Constructor for PixelInspectorWidget 
	*/
	public PixelInspectorWidget(Frame2D f) {
		
		setLayout(new BorderLayout());
		
		frame_view = new FrameView(f.createObservable());
		frame_view.addMouseListener(this);
		add(frame_view, BorderLayout.EAST);
		
		pixelInspector = newPixelInspector();
	}
	
	
	/* Input: n/a
	* Output: A pixelInspector JPanel is added to the widget
	* Error: n/a
	* Description: Constructs the pixelInspector that holds
	  information about mouse clicks
	*/	
	public JPanel newPixelInspector(){
		
		//creates the pixelInspector JPanel
		JPanel pixelInspector = new JPanel();
		pixelInspector.setLayout(new GridLayout(0,1));
		
		//initializes the instance variables
		this.xCoordinate = new JLabel("X: \n");
		this.yCoordinate = new JLabel("Y: " );
		this.r = new JLabel("Red: ");
		this.g = new JLabel("Green: ");
		this.b = new JLabel("Blue: ");
		this.i = new JLabel("Brightness:            ");
		
		//adds the labels to the PixelInspector
		pixelInspector.add(xCoordinate);
		pixelInspector.add(yCoordinate);
		pixelInspector.add(r);
		pixelInspector.add(g);
		pixelInspector.add(b);
		pixelInspector.add(i);
		
		//adds PixelInspector to the widget
		this.add(pixelInspector, BorderLayout.WEST);
		return pixelInspector;
	}

	
	/* Input: A mouse event(a mouse click)
	* Output: Changes the pixelInspector to hold the information about
	  where the mouse clicked on the Frame2D frame
	* Error: n/a
	* Description: displays the information on the screen about where
	  the mouse clicked
	*/	
	public void mousePressed(MouseEvent e) {
		
		//gets the information about the xCoordinate and initializes the instance variable 
		double xCoor = e.getX();
		xCoordinate.setText("X: " + xCoor+"\n");
		
		//gets the information about the yCoordinate and initializes the instance variable
		double yCoor=e.getY();
		yCoordinate.setText("Y: " + yCoor);
		
		//gets the information about the red intensity and initializes the instance variable
		double red = frame_view.getFrame().getPixel(
								e.getX(), e.getY()).getRed();
		red=(double)Math.round(red * 100d) / 100d;
		r.setText("Red: "+ red);
		
		//gets the information about the green intensity and initializes the instance variable
		double green= frame_view.getFrame().getPixel(
								e.getX(), e.getY()).getGreen();
		green=(double)Math.round(green * 100d) / 100d;
		g.setText("Green: "+ green);
		
		//gets the information about the blue concentration and initializes the instance variable
		double  blue= frame_view.getFrame().getPixel(
								e.getX(), e.getY()).getBlue();
		blue=(double)Math.round(blue * 100d) / 100d;
		b.setText("Blue: "+ blue);
		
		//gets the information about the intensity and initializes the instance variable
		double intensity= frame_view.getFrame().getPixel(
								e.getX(), e.getY()).getIntensity();
		intensity=(double)Math.round(intensity * 100d) / 100d;
		i.setText("Brightness: "+ intensity);
	}


	//unused methods required for MouseListeners
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
