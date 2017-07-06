package a7;

public interface Pixel {
	double getRed();
	double getGreen();
	double getBlue();
	double getIntensity();
	Pixel lighten(double a);
	Pixel darken(double a);
}
