import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class MyTests {
	
	/*TODO: Add your own test cases here!
	 * We've provided a sample test case for each problem below
	 * You can use these as building blocks to write your own test cases
	 */
	
	@Test
	public void HuffmanSampleTest() {
		String input = "abc";
		Huffman h = new Huffman(input);
		String encoding = h.encode();
		assertEquals(input, h.decode(encoding));
		assertEquals("huffman abc compression", Huffman.compressionRatio(input), 0.20833, 0.01);
	}
	
	@Test
	public void IntervalSampleTest() {
		GreedyDynamicAlgorithms.Interval red = new GreedyDynamicAlgorithms.Interval(1, 3);
		GreedyDynamicAlgorithms.Interval blue = new GreedyDynamicAlgorithms.Interval(0, 4);
		ArrayList<GreedyDynamicAlgorithms.Interval> reds = new ArrayList<>();
		ArrayList<GreedyDynamicAlgorithms.Interval> blues = new ArrayList<>();
		reds.add(red);
		blues.add(blue);
		int expectedOptimal = 1;
		int actualOptimal = GreedyDynamicAlgorithms.optimalIntervals(reds, blues);
		assertEquals("interval 1 red 1 blue", expectedOptimal, actualOptimal);
	}

	@Test
	public void RedBeforeBlueNoOverlapTest() {
		GreedyDynamicAlgorithms.Interval red = new GreedyDynamicAlgorithms.Interval(0, 2);
		GreedyDynamicAlgorithms.Interval blue = new GreedyDynamicAlgorithms.Interval(3, 4);
		assertEquals(false, red.overlaps(blue));
	}

	@Test
	public void RedAfterBlueNoOverlapTest() {
		GreedyDynamicAlgorithms.Interval red = new GreedyDynamicAlgorithms.Interval(5, 6);
		GreedyDynamicAlgorithms.Interval blue = new GreedyDynamicAlgorithms.Interval(3, 4);
		assertEquals(false, red.overlaps(blue));
	}

	@Test
	public void RedBeforeBlueOverlapTest() {
		GreedyDynamicAlgorithms.Interval red = new GreedyDynamicAlgorithms.Interval(0, 3);
		GreedyDynamicAlgorithms.Interval blue = new GreedyDynamicAlgorithms.Interval(3, 4);
		assertEquals(true, red.overlaps(blue));
	}

	@Test
	public void RedAfterBlueOverlapTest() {
		GreedyDynamicAlgorithms.Interval red = new GreedyDynamicAlgorithms.Interval(4, 5);
		GreedyDynamicAlgorithms.Interval blue = new GreedyDynamicAlgorithms.Interval(3, 4);
		assertEquals(true, red.overlaps(blue));
	}

	@Test
	public void RedAroundBlueOverlapTest() {
		GreedyDynamicAlgorithms.Interval red = new GreedyDynamicAlgorithms.Interval(2, 5);
		GreedyDynamicAlgorithms.Interval blue = new GreedyDynamicAlgorithms.Interval(3, 4);
		assertEquals(true, red.overlaps(blue));
	}

	@Test
	public void RedWithinBlueOverlapTest() {
		GreedyDynamicAlgorithms.Interval red = new GreedyDynamicAlgorithms.Interval(3, 4);
		GreedyDynamicAlgorithms.Interval blue = new GreedyDynamicAlgorithms.Interval(2, 5);
		assertEquals(true, red.overlaps(blue));
	}

	@Test
	public void OptimalGridTest() {
		int[][] grid = {
			{5, 1, 1}, 
			{2, 4, 7}, 
			{2, 4, 5},
			{5, 6, 3} 
	  	};
		int[][] optGrid = {
			{21, 17, 16},
			{16, 16, 15},
			{14, 12, 8},
			{14, 9, 3}
		};
		assertArrayEquals(optGrid, GreedyDynamicAlgorithms.optimalGrid(grid));
	}

	@Test
	public void Optimal2GridPathTest() {
		int[][] optGrid = {
			{21, 17, 16},
			{16, 16, 15},
			{14, 12, 8},
			{14, 9, 3}
		};

		List<GreedyDynamicAlgorithms.Direction> expected = new LinkedList<GreedyDynamicAlgorithms.Direction>();
		expected.add(GreedyDynamicAlgorithms.Direction.DOWN);
		expected.add(GreedyDynamicAlgorithms.Direction.DOWN);
		expected.add(GreedyDynamicAlgorithms.Direction.RIGHT);
		expected.add(GreedyDynamicAlgorithms.Direction.RIGHT);
		expected.add(GreedyDynamicAlgorithms.Direction.DOWN);

		assertEquals(expected, GreedyDynamicAlgorithms.optimal2GridPath(optGrid));
	}
}
