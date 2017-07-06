package a8;

public interface RegisteredROIObserver extends ROIObserver {

	Region getROI();
	ROIObserver getObserver();

}
