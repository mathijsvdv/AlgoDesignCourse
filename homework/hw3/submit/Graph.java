
import java.util.*;

/**
 * A undirected graph class
 * 
 * Also includes functions for running dfs and bfs
 *
 */
public class Graph {
	
	private int n; //number of vertices
	private LinkedList<Integer>[] adj; //adjacency list
	
	/**
	 * Constructs a graph with n vertices (containing no edges)
	 * 
	 * @param n - the number of vertices in the graph
	 */
	@SuppressWarnings("unchecked")
	public Graph(int n) {
		this.n = n;
		adj = (LinkedList<Integer>[]) new LinkedList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new LinkedList<>();
		}
	}
	
	/**
	 * add an edge between vertices v and w
	 */
	public void addEdge(int v, int w) {
		adj[v].add(w); //add w to v's adjacency list
		adj[w].add(v);
	}
	
	/**
	 * outputs the neighbors of a vertex v
	 */
	public List<Integer> neighbors(int v) {
		return adj[v];
	}
	
	/**
	 * @return the number of vertices in the graph
	 */
	public int size() {
		return n;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj.getClass() != this.getClass()) {
			return false;
		}
		final Graph other = (Graph) obj;
		return this.size() == other.size() && Arrays.equals(this.adj, other.adj);
	}
	
	/**
	 * returns the number of shortest paths from s to t
	 * 
	 * ex. if the shortest path from s to t is of length 4, and there
	 * are two distinct paths from s to t of length 4, then you should return 2
	 * 
	 * @param s the source vertex
	 * @param t the destination vertex
	 * @return
	 */
	public int numShortestPaths(int s, int t) {
		int n = size();
		int[] numShortestPathsToVertex = new int[n];
		numShortestPathsToVertex[s] = 1;
		Integer[] levels = new Integer[n];
		levels[s] = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);

		while (q.size() > 0) {
			s = q.poll();

			// Stop if our path length already exceeds the shortest path
			Integer lengthShortestPath = levels[t];
			boolean foundShortestPath = lengthShortestPath != null;
			if (foundShortestPath && levels[s] >= lengthShortestPath) {
				break;  
			}

			for (Integer neighbor : neighbors(s)) {
				if (levels[neighbor] == null) {
					q.add(neighbor);
					levels[neighbor] = levels[s] + 1;
				}
				if (levels[neighbor] == levels[s] + 1) {
					numShortestPathsToVertex[neighbor] += numShortestPathsToVertex[s];
				}
			}
		}
		
		return numShortestPathsToVertex[t];
	}
}
