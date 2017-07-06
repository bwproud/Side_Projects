package a7;

public class MutableFrame2D extends AnyFrame2D {

	Pixel[][] pixels;
	
	
	public MutableFrame2D(int width, int height) {
		this(width, height, new ColorPixel(0,0,0));
	}
	
	public MutableFrame2D(int width, int height, Pixel init_color) {
		if (width < 1 || height < 1) {
			throw new IllegalFrame2DGeometryException();
		}
		if (init_color == null) {
			throw new IllegalArgumentException("Initial pixel is null");
		}
		
		pixels = new Pixel[width][height];
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				pixels[x][y] = init_color;
			}
		}
	}
	
	public MutableFrame2D(Pixel[][] pixels) {
		if ((pixels.length < 1) || (pixels[0].length < 1)) {
			throw new IllegalFrame2DGeometryException();
		}
		int width = pixels.length;
		int height = pixels[0].length;
		
		
		this.pixels = new Pixel[width][height];
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				if (pixels[x][y] == null) {
					throw new IllegalArgumentException("Pixels contains null");
				} else {
					this.pixels[x][y] = pixels[x][y];
				}
			}
		}
	}
	
	@Override
	public int getWidth() {
		return pixels.length;
	}

	@Override
	public int getHeight() {
		return pixels[0].length;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("Coordinates out of range");
		}
		return pixels[x][y];
	}
	
	@Override
	public Frame2D setPixel(int x, int y, Pixel p) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("Coordinates out of range");
		}
		if (p == null) {
			throw new IllegalArgumentException("Pixel value is null");
		}
		pixels[x][y] = p;
		return this;
	}
	
	public Frame2D lighten(double factor) {
		for (int x=0; x< getWidth(); x++) {
			for (int y=0; y<getHeight(); y++) {
				pixels[x][y] = pixels[x][y].lighten(factor);
			}
		}
		return this;
	}

	public Frame2D darken(double factor) {
		for (int x=0; x< getWidth(); x++) {
			for (int y=0; y<getHeight(); y++) {
				pixels[x][y] = pixels[x][y].darken(factor);
			}
		}
		return this;
	}
	
	/* Input: n/a
	* Output: returns an exact copy of the frame
	* Error: n/a
	* Description: returns an exact copy of the frame
	  that calls the method
	*/
	public Frame2D copy(){
		  
		//creates a new frame
		  Frame2D copy = new MutableFrame2D(getWidth(), getHeight());
		  
		  //copies over each frame individually into the new frame
		  for(int x = 0; x< getWidth(); x++){
			  for(int y = 0; y<getHeight(); y++){
				  copy.setPixel(x, y, ((ColorPixel)getPixel(x, y)).copy());
			  }
		  }
		  
		  //returns the new frame
		  return copy;
	  }
	
	
	/* Input: A double numbered between -100 and 100
	* Output: Returns a changed frame
	* Error: n/a
	* Description: returns a frame that is either lightened
	  or darkened appropriately
	*/
	public Frame2D brightness(double factor){
		
		//creates a new frame
		Frame2D changedFrame= this;
		
		//activates if the factor is greater than or equal to 0
		if(factor>=0){
			
			//lightens the factor
			changedFrame=lighten(factor);
		}
		
		//activates if the factor is less than 0
		else if(factor<0){
			
			//darkens the frame by the factor
			changedFrame=darken(Math.abs(factor));	
		}
		
		//returns the changed frame
		return changedFrame;
	}
	
	
	/* Input: A double numbered between -100 and 100
	* Output: Returns a changed frame
	* Error: n/a
	* Description: returns a frame that is either saturated
	  or desaturated appropriately 
	*/
	public Frame2D saturation(double factor){
		
		//creates a new frame
		Frame2D changedFrame= this;
		
		//activates if the factor is less than 0
		if(factor<0){
			
			//for loop that goes through each Pixel in the frame
			for(int x=0; x<getWidth(); x++){
				for(int y=0; y<getHeight(); y++){
					
					//changes each Pixel component by a factor
					double newR = getPixel(x, y).getRed() * (1.0 + (factor / 100.0) ) 
									- (getPixel(x, y).getIntensity() * factor / 100.0);
					double newG = getPixel(x, y).getGreen() * (1.0 + (factor / 100.0) )
									- (getPixel(x, y).getIntensity() * factor / 100.0);
					double newB = getPixel(x, y).getBlue() * (1.0 + (factor / 100.0) ) 
									- (getPixel(x, y).getIntensity() * factor / 100.0);
					
					//puts the altered pixel in the new frame
					changedFrame.setPixel(x, y, new ColorPixel(newR, newG, newB));
				}
			}
		}
		
		//activates if the factor is greater than 0
		else if(factor>0){
			
			//goes through each loop in the frame
			for(int x=0; x<getWidth(); x++){
				for(int y=0; y<getHeight(); y++){
					
					//declares and initializes variables
					double l =0;
					double oldR=getPixel(x, y).getRed();
					double oldG=getPixel(x, y).getGreen();
					double oldB=getPixel(x, y).getBlue();
					
					//finds out which is the dominant component
					if(oldR>=oldG && oldR>=oldB){
						l=oldR;
					}
					else if(oldG>=oldR && oldG>=oldB){
						l= oldG;
					}
					else if(oldB>=oldR && oldB>=oldG){
						l= oldB;
					}
					
					//calculates the factor each component is changed by
					double gain = (l + ((1.0 - l) * (factor / 100.0))) / l;

					//changes each pixel component
					double newR = oldR*gain;
					double newG = oldG*gain;
					double newB = oldB*gain;
					
					//puts the changed pixel in the frame
					changedFrame.setPixel(x, y, new ColorPixel(newR, newG, newB));
				}
			}
		}
		
		//returns the changed frame
		return changedFrame;
	}
}
