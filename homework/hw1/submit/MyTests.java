
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class contains some test cases to help you write your code
 * 
 * You are encouraged to write larger test cases!
 * Note that when you submit, our tests also check that you've implementing the three
 * functions correctly (they make an appropriate number of comparisons)
 */
public class MyTests {

	/**
	 * Helper function for converting an array of ints to an array of our ComapreInts
	 */
	private static CompareInt[] convert(int[] arr) {
		CompareInt[] newArr = new CompareInt[arr.length];
		for (int i = 0; i < newArr.length; i++) {
			newArr[i] = new CompareInt(arr[i]);
		}
		return newArr;
	}
	
	@Test
	public void KthSmallestTestSampleHeapTest() {
		CompareInt[] arr = convert(new int[]{4, 1, 3});
		assertEquals(3, KthSmallest.heapImpl(2, arr));
	}
	
	@Test
	public void KthSmallestTestSampleMergeTest() {
		CompareInt[] arr = convert(new int[]{4, 1, 3});
		assertEquals(3, KthSmallest.mergeSortImpl(2, arr));
	}
	
	@Test
	public void KthSmallestTestSampleQuickSelectTest() {
		CompareInt[] arr = convert(new int[]{4, 1, 3});
		assertEquals(3, KthSmallest.quickSelectImpl(2, arr));
	}

	@Test
	public void KthSmallestTrivialQuickSelectTest() {
		CompareInt[] arr = convert(new int[]{1});
		assertEquals(1, KthSmallest.quickSelectImpl(1, arr));
	}

	@Test
	public void KthSmallestTwoElementQuickSelectTest() {
		CompareInt[] arr = convert(new int[]{1, 3});
		assertEquals(3, KthSmallest.quickSelectImpl(2, arr));
	}

	@Test
	public void KthSmallestTwoElementUnsortedQuickSelectTest() {
		CompareInt[] arr = convert(new int[]{3, 1});
		assertEquals(3, KthSmallest.quickSelectImpl(2, arr));
	}

	@Test
	public void KthSmallestUntrivialQuickSelectTest() {
		CompareInt[] arr = convert(new int[]{3, 5, 6, 2, 1, 9, 10});
		assertEquals(5, KthSmallest.quickSelectImpl(4, arr));
	}

}
