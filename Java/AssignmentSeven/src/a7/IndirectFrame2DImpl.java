package a7;

public class IndirectFrame2DImpl extends AnyFrame2D implements IndirectFrame2D {

	private Frame2D source;
	private int x_offset;
	private int y_offset;
	private int width;
	private int height;
	
	public IndirectFrame2DImpl(Frame2D source, int xoff, int yoff, int width, int height) {
		if (source == null) {
			throw new IllegalArgumentException("Source of indirect frame is null");
		}
		
		
		if (xoff < 0 || xoff >= source.getWidth() ||
				yoff < 0 || yoff >= source.getHeight() ||
				width < 1 ||
				xoff+width > source.getWidth() ||
				height < 1 ||
				yoff+height > source.getHeight()) {
			throw new IllegalFrame2DGeometryException();
		}
		
		x_offset = xoff;
		y_offset = yoff;
		this.source = source;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if ((x < 0) || (x >= width) ||  (y < 0) || (y >= height)) {
			throw new IllegalArgumentException("Coordinates out of range.");
		}
		
		return source.getPixel(x+x_offset, y+y_offset);
	}

	@Override
	public Frame2D setPixel(int x, int y, Pixel p) {
		if ((x < 0) || (x >= width) ||  (y < 0) || (y >= height)) {
			throw new IllegalArgumentException("Coordinates out of range.");
		}
		
		if (p == null) {
			throw new IllegalArgumentException("Pixel is null");
		}
		
		return new IndirectFrame2DImpl(source.setPixel(x+x_offset, y+y_offset, p), x_offset, y_offset, width, height);
	}

	@Override
	public Frame2D getSource() {
		return source;
	}

	@Override
	public int getXOffset() {
		return x_offset;
	}

	@Override
	public int getYOffset() {
		return y_offset;
	}
	
	//lightens Frame
		public Frame2D lighten(double factor) {
			for (int x=0; x< getWidth(); x++) {
				for (int y=0; y<getHeight(); y++) {
					source.setPixel(x, y, source.getPixel(x, y).lighten(factor));
				}
			}
			return this;
		}

		//darkens Frame
		public Frame2D darken(double factor) {
			for (int x=0; x< getWidth(); x++) {
				for (int y=0; y<getHeight(); y++) {
					source.setPixel(x, y, source.getPixel(x, y).darken(factor));
				}
			}
			return this;
		}

		@Override
		public Frame2D brightness(double factor) {
			return null;
		}

		@Override
		public Frame2D saturation(double factor) {
			return null;
		}
}
