
import java.io.*;
import java.util.*;

public class Maze {
	
	Graph g; //We will store the maze internally as a graph
	int startVertex; //one of the vertices in the graph will be the start of the maze
	int endVertex; //another will be the end of the maze
	int n; // Number of points that the maze is wide/long
	
	/**
	 * We will store an nxn maze in a textfile, and read it in.
	 * 
	 * A maze is simply represented as a textfile with 4 numbers: 0, 1, 2, 3
	 * 
	 * 0s represent walls- this is not a valid part of the maze
	 * 1s represent valid parts of the maze (i.e. blocks you can move to)
	 * 2 represents the starting point of the maze
	 * 3 represents the end point of the maze.
	 * 
	 * Our constructor will create the 2d array of integers from the file for you
	 * 
	 */
	public Maze(String filename) throws IOException{
		
		//create the 2d grid from the maze textfile
		int[][] grid = createGrid(filename);

		//identify start and end vertices, then add any edges
		n = grid.length;
		g = new Graph(n*n);
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				Point p = new Point(row, col);
				int v = getVertex(p);
				if (grid[row][col] == 2) {
					startVertex = v;
				}
				if (grid[row][col] == 3) {
					endVertex = v;
				}
				addRightAndDownEdges(grid, p, v);
			}
		}
	}
	
	/**
	 * 
	 * Add any edges that face to the right and down, if applicable
	 * 
	 * By doing this for every vertex we add all valid edges.
	 * 
	 * @param grid - grid to check for valid edges 
	 * @param p - point corresponding to the vertex
	 * @param v - vertex for which adding right and down edges will be considered
	 * 
	 */	
	private void addRightAndDownEdges(int[][] grid, Point p, int v) {
		if (grid[p.row][p.col] == 0) {
			return;
		}
		if (p.row < n && grid[p.row+1][p.col] > 0) {
			g.addEdge(v, v+n);
		}
		if (p.col < n && grid[p.row][p.col+1] > 0) {
			g.addEdge(v, v+1);
		}
	}

	/**
	 * 
	 * This algorithm should solve the maze output a list of "moves", beginning at the start vertex,
	 * that can be taken to arrive at the end vertex.  You should solve the maze on the graph,
	 * using some sort of graph traversal.
	 * 
	 * More information on figuring out what "move" to output at each step can be found in the writeup!
	 * 
	 * @param g - the graph to traverse
	 * @param startVertex - the starting vertex
	 * @param endVertex - we will stop the traversal and output the list of moves when we hit this vertex
	 * 
	 */
	public List<Move> solveMaze() {
		//TODO
		return null;
	}
	
	
	/**
	 * Move is an enum type- when navigating a maze, you can either move
	 * UP, DOWN, LEFT, or RIGHT
	 */
	public enum Move {
		UP, DOWN, LEFT, RIGHT
	}

	public class Point {
		int row; // Row index of the point in the maze
		int col; // Column index of the point in the maze

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public Point getPoint(int v) {
		int row = v / n;
		int col = v % n;
		return new Point(row, col);
	}

	public int getVertex(Point p) {
		return p.row*n + p.col;
	}

	public static Move determineMove(Point p, Point q) {
		if (p.row == q.row) {
			if (p.col < q.col) {
				return Move.RIGHT;
			} else if (p.col > q.col) {
				return Move.LEFT;
			}
		} else if (p.col == q.col) {
			if (p.row < q.row) {
				return Move.DOWN;
			} else if (p.row > q.row) {
				return Move.UP;
			}
		}
		throw new IllegalArgumentException(
			String.format("Unable to determine move direction from points %s to %s", p, q)
		);
	}
	
	public Move determineMove(int u, int v) {
		Point p = getPoint(u);
		Point q = getPoint(v);
		return determineMove(p, q);
	}

	/**
	 * Helper function for creating a 2d grid to represent the maze, given a file name
	 * 
	 * @param filename - the name of the file to be read from, containing the maze data
	 */
	public static int[][] createGrid(String filename) throws IOException {
		//create the 2d array from the maze textfile
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line = br.readLine();
		int n = line.length(); //the grid will always be an nxn square
		int[][] grid = new int[n][n];
		int row = 0;
		while (line != null) {
			int col = 0;
			for (char c : line.toCharArray()) {
				int val = Character.getNumericValue(c);
				grid[row][col] = val;
				col++;
			}
			line = br.readLine();
			row++;
		}
		br.close();
		return grid;
	}
	
}
