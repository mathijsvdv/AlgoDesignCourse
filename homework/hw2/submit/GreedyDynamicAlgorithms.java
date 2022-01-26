
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

	private static int sortedOptimalIntervals(ArrayList<Interval> red, 
											  ArrayList<Interval> blue, 
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
			// Went through entire list of red intervals, couldn't find overlapping one
			return -1; 
		}

		while (redFromIndex < nRed && red.get(redFromIndex).overlaps(currentBlue)) {
			redFromIndex++;
		}

		Interval optimalRed = red.get(redFromIndex - 1);
		while (blueFromIndex < nBlue && optimalRed.overlaps(blue.get(blueFromIndex))) {
			blueFromIndex++;
		}

		int restOptimalIntervals = sortedOptimalIntervals(
			red, blue, redFromIndex, blueFromIndex
		);

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
		int[][] optGrid = optimalGrid(grid);
		return optimal2GridPath(optGrid);
	}

	/**
	 * Find any path of lowest cost from the top-left of the grid (optimalGrid[0][0])
	 * to the bottom right of the grid (optimalGrid[m-1][n-1]). The optimalGrid already
	 * contains the cost of the optimal path from each location in the grid.
	 * 
	 * A greedy algorithm can be used here since the cost of the optimal path from each
	 * location is already known.
	 * 
	 * @param grid - the 2d grid containing the cost the optimal path of each location in the grid.
	 * @return
	 */
	public static List<Direction> optimal2GridPath(int[][] optimalGrid) {
		LinkedList<Direction> path = new LinkedList<Direction>();

		int m = optimalGrid.length;
		int n = m == 0 ? 0 : optimalGrid[0].length;
		int i, j;
		i = j = 0;

		while (i < m - 1 || j < n - 1) {
			if (i == m - 1) {
				path.add(Direction.RIGHT);
				j++;
			} else if (j == n - 1) {
				path.add(Direction.DOWN);
				i++;
			} else if (optimalGrid[i][j+1] < optimalGrid[i+1][j]) {
				path.add(Direction.RIGHT);
				j++;
			} else {
				path.add(Direction.DOWN);
				i++;
			}
		}

		return path;
	}

	/**
	 * Goal: find lowest cost from the top-left of the grid (grid[0][0])
	 * to the bottom right of the grid (grid[m-1][n-1]).  Output this optimal cost at every point.
	 * 
	 * @param grid - the 2d grid containing the cost of each location in the grid.
	 * @return
	 */
	public static int[][] optimalGrid(int[][] grid) {
		
		int m = grid.length;
		int n = m == 0 ? 0 : grid[0].length;
		int[][] optGrid = new int[m][n];
		
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				optGrid[i][j] = grid[i][j];
				if (i == m - 1 && j == n - 1) {
					continue;
				}

				if (i == m - 1) {
					optGrid[i][j] += optGrid[i][j+1];
				} else if (j == n - 1) {
					optGrid[i][j] += optGrid[i+1][j];
				} else if (optGrid[i][j+1] < optGrid[i+1][j]) {
					optGrid[i][j] += optGrid[i][j+1];
				} else {
					optGrid[i][j] += optGrid[i+1][j];
				}
			}
		}

		return optGrid;
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
