import static org.junit.Assert.*;

import org.junit.Test;

public class SortsTest {

	@Test
	public final void testQuickSort() {
		Sorts s = new Sorts();
		int[] test = {7,6,5,5,4,3,2,1};
		s.setNumbers(test);
		int[] expected = {1,2,3,4,5,5,6,7};
		test = s.quickSort(0, 7);
		for(int i=0; i<test.length; i++){
			assertEquals(expected[i], test[i]);
		}
	}

	@Test
	public final void testBubbleSort() {
		Sorts s = new Sorts();
		int[] test = {7,6,5,5,4,3,2,1};
		s.setNumbers(test);
		int[] expected = {1,2,3,4,5,5,6,7};
		test = s.bubbleSort(8);
		for(int i=0; i<test.length; i++){
			assertEquals(expected[i], test[i]);
		}
	}

	@Test
	public final void testSelectionSort() {
		Sorts s = new Sorts();
		int[] test = {7,6,5,5,4,3,2,1};
		s.setNumbers(test);
		int[] expected = {1,2,3,4,5,5,6,7};
		test = s.selectionSort(8);
		for(int i=0; i<test.length; i++){
			assertEquals(expected[i], test[i]);
		}
	}

	@Test
	public final void testInsertionSort() {
		Sorts s = new Sorts();
		int[] test = {7,6,5,5,4,3,2,1};
		s.setNumbers(test);
		int[] expected = {1,2,3,4,5,5,6,7};
		test = s.insertionSort(8);
		for(int i=0; i<test.length; i++){
			assertEquals(expected[i], test[i]);
		}
	}

	@Test
	public final void testMergeSort() {
		Sorts s = new Sorts();
		int[] test = {7,6,5,5,4,3,2,1};
		s.setNumbers(test);
		int[] expected = {1,2,3,4,5,5,6,7};
		test = s.mergeSort(8);
		for(int i=0; i<test.length; i++){
			assertEquals(expected[i], test[i]);
		}
	}

	@Test
	public final void testHeapSort() {
		Sorts s = new Sorts();
		int[] test = {7,6,5,5,4,3,2,1};
		s.setNumbers(test);
		int[] expected = {1,2,3,4,5,5,6,7};
		test = s.heapSort(8);
		for(int i=0; i<test.length; i++){
			assertEquals(expected[i], test[i]);
		}
	}

}
