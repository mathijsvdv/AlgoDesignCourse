
import java.util.*;

public class Huffman {
	
	private String input;
	private Node huffmanTree; //the huffman tree
	private Map<Character, String> mapping; //maps characters to binary strings
	
	/**
	 * The Huffman constructor
	 * 
	 */
	public Huffman(String input) {
		
		this.input = input;
		mapping = new HashMap<>();
		
		//first, we create a map from the letters in our string to their frequencies
		Map<Character, Integer> freqMap = getFreqs(input);
		
		//we'll be using a priority queue to store each node with its frequency,
		//as we need to continually find and merge the nodes with smallest frequency
		PriorityQueue<Node> pNodes = getPriorityNodes(freqMap);

		while (pNodes.size() > 1) {
			Node left = pNodes.poll();
			Node right = pNodes.poll();
			Node mergedNode = new Node(left.freq + right.freq, left, right);
			pNodes.add(mergedNode);
		}

		huffmanTree = pNodes.poll();
		huffmanTree.updateMapping(mapping);

		/*
		 * TODO:
		 * 1) add all nodes to the priority queue
		 * 2) continually merge the two lowest-frequency nodes until only one tree remains in the queue
		 * 3) Use this tree to create a mapping from characters (the leaves)
		 *    to their binary strings (the path along the tree to that leaf)
		 *    
		 * Remember to store the final tree as a global variable, as you will need it
		 * to decode your encrypted string
		 */
	}
	
	/**
	 * Use the global mapping to convert your input string into a binary string
	 */
	public String encode() {
		List<String> codes = new ArrayList<String>(input.length());
		char[] chars = input.toCharArray();
		for (char c : chars) {
			codes.add(mapping.get(c));
		}

		return String.join("", codes);
	}
	
	/**
	 * Use the huffmanTree to decrypt the encoding back into the original input
	 * 
	 * You should convert each prefix-free group of binary numbers in the
	 * encoding to a character
	 * 
	 * @param encoding - the encoded string that needs to be decrypted
	 * @return the original string (should be the same as "input")
	 */
	public String decode(String encoding) {
		StringBuilder builder = new StringBuilder(encoding.length());
		
		Node node = huffmanTree;
		for (char bit : encoding.toCharArray()) {
			if (bit == '0') {
				node = node.left;
			} else if (bit == '1') {
				node = node.right;
			} else {
				throw new IllegalArgumentException("Encoding should only contain 0s and 1s");
			}
			if (node.isLeaf()) {
				builder.append(node.letter);
				node = huffmanTree;
			}
		}

		return builder.toString();
	}
	
	/**
	 * This function tells us how well the compression algorithm worked
	 * 
	 * note that a char is represented internal using 8 bits
	 * 
	 * ex. if the string "aabc" maps to "0 0 10 11", we would have
	 * a compression ratio of (6) / (8 * 4) = 0.1875
	 */
	public static double compressionRatio(String input) {
		Huffman h = new Huffman(input);
		String encoding = h.encode();
		int encodingLength = encoding.length();
		int originalLength = 8 * input.length();
		return encodingLength / (double) originalLength;
	}
	
	/**
	 * We've given you this function, which helps you create
	 * a frequency map from the input string
	 */
	private Map<Character, Integer> getFreqs(String input) {
		Map<Character, Integer> freqMap = new HashMap<>();
		for (char c : input.toCharArray()) {
			if (freqMap.containsKey(c)) {
				freqMap.put(c, freqMap.get(c) + 1);
			} else {
				freqMap.put(c, 1);
			}
		}
		return freqMap;
	}

	private PriorityQueue<Node> getPriorityNodes(Map<Character, Integer> freqMap) {
		PriorityQueue<Node> pNodes = new PriorityQueue<>();
		for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
			Node node = new Node(entry.getKey(), entry.getValue());
			pNodes.add(node);
		}

		return pNodes;
	}

	/**
	 * An inner Node class to build your huffman tree
	 * 
	 * Each node has:
	 * a frequency - the sum of the frequencies of all the node's leaves
	 * a letter - the character that this node represents (only for leaves)
	 * left and right children
	 */
	private class Node implements Comparable<Node> {
		private Character letter; //the letter of this node (only for leaves)
		private int freq; //frequency of this node
		private Node left; //add a 0 to you string
		private Node right; //add a 1 to your string
		
		public Node(Character letter, int freq, Node left, Node right) {
			this.letter = letter;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}

		public Node(Character letter, int freq, Node left) {
			this(letter, freq, left, null);
		}

		public Node(Character letter, int freq) {
			this(letter, freq, null);
		}

		public Node(int freq, Node left, Node right) {
			this(null, freq, left, right);
		}

		public Node(int freq, Node left) {
			this(freq, left, null);
		}

		public Node(int freq) {
			this(freq, null);
		}
		
		public boolean isLeaf() {
			return left == null && right == null;
		}

		/**
		* Update the character-to-encoding mapping by filling the node and its children
		* 
		* @param mapping - character-to-encoding mapping
		* @param codeBuilder - StringBuilder for building the encoding given the current node
		* @return the original character
		*/			
		public void updateMapping(Map<Character, String> mapping, StringBuilder codeBuilder) {
			if (isLeaf()) {
				mapping.put(letter, codeBuilder.toString());
			} else {
				codeBuilder.append('0');
				left.updateMapping(mapping, codeBuilder);
				codeBuilder.setLength(codeBuilder.length() - 1);
				
				codeBuilder.append('1');
				right.updateMapping(mapping, codeBuilder);
				codeBuilder.setLength(codeBuilder.length() - 1);
			}
		}

		public void updateMapping(Map<Character, String> mapping) {
			updateMapping(mapping, new StringBuilder());
		}
		
		@Override
		public int compareTo(Node o) {
			return this.freq - o.freq;
		}
	}

}
