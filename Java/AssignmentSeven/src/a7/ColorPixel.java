package a7;

public class ColorPixel implements Pixel {

	private double red;
	private double green;
	private double blue;
	
	public ColorPixel(double r, double g, double b) {
		if (r < 0.0 || r > 1.0 || g < 0.0 || g > 1.0 || b < 0.0 || b > 1.0) {
			throw new IllegalArgumentException("One or more color components out of range");
		}
		
		red = r;
		green = g;
		blue = b;
	}
	
	@Override
	public double getRed() {
		return red;
	}

	@Override
	public double getGreen() {
		return green;
	}

	@Override
	public double getBlue() {
		return blue;
	}

	@Override
	public double getIntensity() {
		return (0.2126 * getRed() + 0.7152 * getGreen() + 0.0722 * getBlue());
	}
	
	
	//lightens Pixel
	public Pixel lighten(double factor) {
		return new ColorPixel(lighten_component(getRed(), factor),
					     lighten_component(getGreen(), factor),
					     lighten_component(getBlue(), factor));
	}

	
	//darkens pixel
	public Pixel darken(double factor) {
		return new ColorPixel(darken_component(getRed(), factor),
					     darken_component(getGreen(), factor),
					     darken_component(getBlue(), factor));
	}

	
	//returns a lighter pixel component
	private static double lighten_component(double current, double factor) {
		return (current * (1.0 - factor) + factor);
	}

	
	//returns a darker pixel component
	private static double darken_component(double current, double factor) {
		return (current * (1.0 - factor));
	}

	public ColorPixel copy(){
		ColorPixel c = new ColorPixel(getRed(), getGreen(), getBlue());
		return c;
	}
}
