
import java.util.*;

public class GreedyDynamicAlgorithms {

	/**
	 * Goal: find the smallest number of red intervals to select, such that
	 * every blue interval overlaps with at least one of the selected red intervals.
	 * Output this number
	 * 
	 * @param red - the list of red intervals
	 * @param blue - the list blue intervals
	 * @return
	 */
	public static int optimalIntervals(ArrayList<Interval> red, ArrayList<Interval> blue) {
		Interval.sortByFinishTime(red);
		Interval.sortByFinishTime(blue);
		return sortedOptimalIntervals(red, blue, 0, 0);
	}

	private static int sortedOptimalIntervals(ArrayList<Interval> red, ArrayList<Interval> blue, 
											  int redFromIndex, int blueFromIndex) {
		int nBlue = blue.size();
		if (blueFromIndex == nBlue) {
			return 0;
		}
		Interval currentBlue = blue.get(blueFromIndex);
		int nRed = red.size();

		while (redFromIndex < nRed && !(red.get(redFromIndex).overlaps(currentBlue))) {
			redFromIndex++;
		}
		if (redFromIndex == nRed) {
			return -1; // Went through entire list of red intervals, couldn't find overlapping one
		}

		while (redFromIndex < nRed && red.get(redFromIndex).overlaps(currentBlue)) {
			redFromIndex++;
		}

		Interval optimalRed = red.get(redFromIndex - 1);
		while (blueFromIndex < nBlue && optimalRed.overlaps(blue.get(blueFromIndex))) {
			blueFromIndex++;
		}

		int restOptimalIntervals = sortedOptimalIntervals(red, blue, redFromIndex, blueFromIndex);

		if (restOptimalIntervals == -1) {
			return -1;
		} else {
			return 1 + restOptimalIntervals;
		}
	}
	
	/**
	 * Goal: find any path of lowest cost from the top-left of the grid (grid[0][0])
	 * to the bottom right of the grid (grid[m-1][n-1]).  Output this sequence of directions
	 * 
	 * @param grid - the 2d grid containing the cost of each location in the grid.
	 * @return
	 */
	public static List<Direction> optimalGridPath(int[][] grid) {
		//TODO
		return null;
	}
	
	/**
	 * A simple Direction enum
	 * directions can be either DOWN or RIGHT
	 * You will output a list of these in the grid-path problem
	 */
	public static enum Direction {
		DOWN, RIGHT
	}

	/**
	 * A private Interval class to help with the interval question
	 */
	public static class Interval {
		
		int start;
		int finish;
		
		public Interval(int start, int finish) {
			this.start = start;
			this.finish = finish;
		}

		public boolean overlaps(Interval o) {
			return this.start <= o.finish && o.start <= this.finish;
		}

		@Override
		public String toString() {
			return String.format("[%d, %d]", start, finish);
		}
		
		/**
		 * sorts a list of intervals by start time, you are free to use this on the first question
		 */
		public static void sortByStartTime(ArrayList<Interval> l) {
			Collections.sort(l, new Comparator<Interval>() {
				public int compare(Interval o1, Interval o2) {
					Interval i1 = (Interval) o1;
					Interval i2 = (Interval) o2;
					return i1.start - i2.start;
				}
			});
		}
		
		/**
		 * sorts a list of intervals by finish time, you are free to use this on the first question
		 */
		public static void sortByFinishTime(ArrayList<Interval> l) {
			Collections.sort(l, new Comparator<Interval>() {
				public int compare(Interval o1, Interval o2) {
					Interval i1 = (Interval) o1;
					Interval i2 = (Interval) o2;
					return i1.finish - i2.finish;
				}
			});
		}
	}
	
}
