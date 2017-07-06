package a7;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

//this is the main class that displays the image adjuster
public class ImageAdjuster {
	public static void main(String[] args) throws IOException {
		
		//creates the frame and frame widget
		Frame2D f = A7Helper.readFromURL("http://ecx.images-amazon.com/images/I/51sBFJYnq6L._SX355_.jpg");
		ImageAdjusterWidget simple_widget = new ImageAdjusterWidget(f);
		
		//creates and calibrates the JFrame
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment Image Adjuster");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		//Creates the JPanel that holds the widge
		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(simple_widget, BorderLayout.CENTER);
		
		//makes the JFrame visible
		main_frame.setContentPane(top_panel);
		main_frame.pack();
		main_frame.setVisible(true);
	}
}