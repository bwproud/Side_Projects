package a7;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

//this is the main class that displays the frame puzzle
public class FramePuzzle {
	public static void main(String[] args) throws IOException {
		
		//creates the frame and frame widget
		Frame2D f = A7Helper.readFromURL("https://d1wtzzt4oxg683.cloudfront.net/images/covers/148/75648.jpg");
		FramePuzzleWidget simple_widget = new FramePuzzleWidget(f);
		
		//creates and calibrates the JFrame22
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 7 Frame Puzzle");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Creates the JPanel that holds the widget
		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(simple_widget, BorderLayout.CENTER);
		
		//makes the JFrame visible
		main_frame.setContentPane(top_panel);
		main_frame.pack();
		main_frame.setVisible(true);
	}
}