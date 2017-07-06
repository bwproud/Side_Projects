package a7;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//this is the widget that is housed in the main JPanel
public class FramePuzzleWidget extends JPanel implements MouseListener, KeyListener {

	//instance variables
	private FrameView frame_view;
	private List<tiles> tileArray;
	private Frame2D whiteFrame;
	
	
	/* Input: A Frame2D frame
	* Output: n/a
	* Error: n/a
	* Description: Constructor for FramePuzzleWidget 
	*/
	public FramePuzzleWidget(Frame2D f) {
		setFocusable(true);
		//creates a Arraylist of tiles
		tileArray = new ArrayList<tiles>();
		
		//calculates the width and height of the 5x5 arrays
		double width = f.getWidth()/5.0;
		double height =f.getHeight()/5.0;
		
		//sets the layout
		setLayout(new GridLayout(5,5));
		
		//keeps track of the count of the frameView
		int count =1;
		
		//goes through each frameView
		for(int x =0; x<=4; x++){
			for(int y =0; y<5; y++){
				
				//activates if its the not the last tile
				if(!(x==4&&y==4)){
					
					//creates a new indirect frame with these dimensions
					Frame2D indirect = new IndirectFrame2DImpl(
								     	f, 0+(y*(int)width), 0+(x*(int)height), (int)width, (int)height);
					frame_view = new FrameView(indirect.createObservable());
					
					//add the frameview and listeners to the arraylist
					tileArray.add(new tiles(frame_view, count));
					tileArray.get(count-1).getFrameView().addMouseListener(this);
					tileArray.get(count-1).getFrameView().addKeyListener(this);
					
					//adds the arraylist tiles to the widget
					
					
					add(tileArray.get(count-1).getFrameView());
				}
				
				//activates if its the last tile
				else{
					setFocusable(true);
					//creates a white tile
					ColorPixel white = new ColorPixel(1.0, 1.0, 1.0);
					Frame2D blank = new MutableFrame2D((int)width, (int)height);
					
					//adds the white tile to the FrameView
					frame_view = new FrameView(blank.createObservable());
					
					//adds the frameview and listeners to the arraylist
					tileArray.add(new tiles(frame_view, count));
					this.whiteFrame=frame_view.getFrame();
					tileArray.get(count-1).getFrameView().addMouseListener(this);
					tileArray.get(count-1).getFrameView().addKeyListener(this);
					
					//adds the whiteframe to the widget
					add(tileArray.get(count-1).getFrameView());
				}
				count++;
			}
		}
		setFocusable(true);
		//goes through each object in the arraylist and sets the whiteframe
		for(tiles t: tileArray){
			t.setWhiteFrame(whiteFrame);
		}
	}

	
	@Override
	/* Input: KeyEvent(a key was pressed)
	* Output: If an arrow key was pressed the white tile moves
	* Error: n/a
	* Description: Detects if an arrow key was pressed and moves the tile 
	*/
	public void keyPressed(KeyEvent e) {
		setFocusable(true);
		int keyCode = e.getKeyCode();
	    
		//activates a different case depending on the arrow pressed
		switch( keyCode ) { 
	        
			//moves the tile up if the up key was pressed
			case KeyEvent.VK_UP:
	            upMove();
	        	break;
	        	
	        //moves the tile down if the up key was pressed
	        case KeyEvent.VK_DOWN:
	        	downMove();
	            break;
	            
	        //moves the tile left if the up key was pressed
	        case KeyEvent.VK_LEFT:
	        	leftMove();
	            break;
	            
	        //moves the tile right if the up key was pressed
	        case KeyEvent.VK_RIGHT :
	        	rightMove();
	            break;
	     }
	}

	
	@Override
	/* Input: A MouseEvent
	* Output: Moves the white tile
	* Error: n/a
	* Description: Moves the white tile to the frameview object clicked
	  if it is in the same row or column as the white frame
	*/
	public void mousePressed(MouseEvent e) {
		
		//Creates a frameview equal to the frameview clicked
		FrameView h = (FrameView) e.getComponent();
		
		//goes through each tile in the array
		for(tiles t: tileArray){
			
			//activates if the curren tile is the tile clicked
			if(t.getFrameView()==h){
				
				//goes through each tile in the array to find the white tile
				for(tiles a: tileArray){
					if(a.getFrameView().getFrame()==a.getWhiteFrame()){
						
						//activates if the clicked tile is in the same row or frame as
						//the white frame
						if(t.getCoordinate().getX()==a.getCoordinate().getX()||
								t.getCoordinate().getY()==a.getCoordinate().getY()){
							
							//activates if the tiles are in the same row
							if(a.getCoordinate().getX()!=t.getCoordinate().getX()){
								
								//finds the difference between the tiles
								int difference = a.getCoordinate().getX()-t.getCoordinate().getX();
								
								//moves the tiles left a certain number of spaces
								if(difference>0){
									for(int x=0; x<difference; x++){
										leftMove();
									}
								}
								
								//moves the tile right a certain number of spaces
								else{
									for(int x=0; x<Math.abs(difference);x++){
										rightMove();
									}
								}
							}
							
							//activates if the tiles are in the same column
							else{
								
								//finds the difference between the tiles
								int difference = a.getCoordinate().getY()-t.getCoordinate().getY();
								
								///moves the tile up a certain number of spaces
								if(difference>0){
									for(int x=0; x<difference; x++){
										upMove();
									}
								}
								
								//moves the tile down a certain number of spaces
								else{
									for(int x=0; x<Math.abs(difference);x++){
										downMove();
									}
								}
							}
						}
					}
				}
			}
		}
		
	}

	
	/* Input: n/a
	* Output: The tile is moved up
	* Error: n/a
	* Description: Moves the tile up 
	*/
	public void upMove(){
		
		//goes through each tile in the tileArray
		for(tiles t: tileArray){
			
			//activates if the current tile is the white frame
			if(t.getFrameView().getFrame()== t.getWhiteFrame()){
				//activates if the tile isn't on an edge
				if(t.getOrder()>5){
        			
					//changes the tile above the white tile with the white tile
					ObservableFrame2D tempFrameHolder =tileArray.get(
														t.getOrder()-6).getFrameView().getFrame();
        			tileArray.get(t.getOrder()-6).getFrameView().setFrame(
        												t.getFrameView().getFrame());
        			tileArray.get(t.getOrder()-1).getFrameView().setFrame(tempFrameHolder);
        		}
        	}
        }
	}
	
	
	/* Input: n/a
	* Output: The tile is moved down
	* Error: n/a
	* Description: Moves the tile down 
	*/
	public void downMove(){
		
		//goes through each tile in the tileArray
		for(tiles t: tileArray){
        	
			//activates if the current tile is the white frame
			if(t.getFrameView().getFrame()==t.getWhiteFrame()){
        		
				//activates if the tile isn't on an edge
				if(t.getOrder()<=20){
        			
					//changes the tile below the white tile with the white tile
					ObservableFrame2D tempFrameHolder =tileArray.get(
														t.getOrder()+4).getFrameView().getFrame();
        			tileArray.get(t.getOrder()+4).getFrameView().setFrame(
        													t.getFrameView().getFrame());
        			tileArray.get(t.getOrder()-1).getFrameView().setFrame(tempFrameHolder);
        			break;
        		}
        	}
        }
	}

	
	/* Input: n/a
	* Output: The tile is moved left
	* Error: n/a
	* Description: Moves the tile left 
	*/
	public void leftMove(){
		
		//goes through each tile in the tileArray
		for(tiles t: tileArray){
        	
			//activates if the current tile is the white frame
			if(t.getFrameView().getFrame() == t.getWhiteFrame()){
        		
				//activates if the tile isn't on an edge
				if(t.getOrder()-1!=0&&t.getOrder()%5!=1){
        		
					//changes the tile to the left the white tile with the white tile
					ObservableFrame2D tempFrameHolder =tileArray.get(
														t.getOrder()-2).getFrameView().getFrame();
					tileArray.get(t.getOrder()-2).getFrameView().setFrame(
														(ObservableFrame2D) t.getWhiteFrame());
					tileArray.get(t.getOrder()-1).getFrameView().setFrame(tempFrameHolder);
        		}
        	}
		}
	}
	
	
	/* Input: n/a
	* Output: The tile is moved right
	* Error: n/a
	* Description: Moves the tile right 
	*/
	public void rightMove(){
		
		//goes through each tile in the tileArray
		for(tiles t: tileArray){
        	
			//activates if the current tile is the white frame
			if(t.getFrameView().getFrame()==t.getWhiteFrame()){
        		
				//activates if the tile isn't on an edge
				if(t.getOrder()%5!=0){
					
					//changes the tile to the right of the white tile with the white tile
					ObservableFrame2D tempFrameHolder =tileArray.get(
														t.getOrder()).getFrameView().getFrame();
					tileArray.get(t.getOrder()).getFrameView().setFrame(
														(ObservableFrame2D) t.getWhiteFrame());
					tileArray.get(t.getOrder()-1).getFrameView().setFrame(tempFrameHolder);
					break;
        		}
        	}
        }
	}

	
	//unused methods required for mouse listeners and key listeners
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
}




//decorated class of Frameview Objects
class tiles{
	
	//instance variables
	private FrameView tiles;
	private int order;
	private Frame2D whiteFrame;
	
	
	/* Input: A FrameView and an integer value
	* Output: n/a
	* Error: n/a
	* Description: Constructor for tiles 
	*/
	public tiles(FrameView f, int order){
		tiles = f;
		this.order = order;
	}
	
	
	/* Input: n/a
	* Output: A FrameView
	* Error: n/a
	* Description: A getter which returns the FrameView 
	*/
	public FrameView getFrameView(){
		return tiles;
	}
	
	
	/* Input: n/a
	* Output: A Coordinate
	* Error: n/a
	* Description: A getter which returns the Coordinate of the object 
	*/
	public Coordinate getCoordinate(){
		int row=0;
		int column =0;
		if(order%5!=0){
			row = (order/5)+1;
		}
		else{
			row = order/5;
		}
		if(row==1){
			column = order;
		}
		else{
			column = order-((row-1)*5);
		}
		return new Coordinate(column, row);
	}
	
	
	/* Input: n/a
	* Output: A Frame2D
	* Error: n/a
	* Description: A getter which returns the WhiteFrame 
	*/
	public Frame2D getWhiteFrame(){
		return whiteFrame;
	}
	
	
	/* Input: A Frame2D frame
	* Output: n/a
	* Error: n/a
	* Description: A setter which sets the whiteframe of the object 
	*/
	public void setWhiteFrame(Frame2D f){
		whiteFrame = f;
	}
	
	
	/* Input: n/a
	* Output: An integer value
	* Error: n/a
	* Description: A getter which returns the order of the object 
	*/
	public int getOrder(){
		return order;
	}
	
	
}
