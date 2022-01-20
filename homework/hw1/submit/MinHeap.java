import java.util.Arrays;

/**
 * A Heap implementation class
 * 
 * @param heap the array that holds the heap data
 * @param size the number of elements currently stored in the heap
 */
public class MinHeap {
	
	CompareInt[] heap;
	int size;

	/**
	 * Constructs a new heap with maximum capacity n
	 * Remember to index your heap at 1 instead of 0!
	 * @param n the maximum number of elements allowed in the heap
	 */
	public MinHeap(int n) {
		heap = new CompareInt[n+1];
		size = 0;
	}
	
	/**
	 * Get maximum size of the heap
	 * 
	 */
	public int getMaxSize() {
		return heap.length - 1;
	}

	/**
	 * Adds an element to the heap
	 * 
	 * @param val the value to be added to the heap
	 */
	public void add(CompareInt val) {
		if (size == getMaxSize()) {
			String msg = String.format("Heap is already at maximum capacity of %d elements", size);
			throw new IllegalArgumentException(msg);
		}

		heap[size+1] = val;
		size++;

		swim(size);
		return;
	}

	/**
	 * Extracts the smallest element from the heap
	 */
	public CompareInt extractMin() {
		CompareInt min = heap[1];
		heap[1] = heap[size];
		
		heap[size] = null;
		size--;

		sink(1);
		return min;
	}

	/**
	 * Swim an element up until the heap order property is restored
	 * 
	 * @param k index of the element that should swim up
	 */
	private void swim(int k) {
		while (k > 1 && heap[k/2].compareTo(heap[k]) > 0) {
			swap(k, k/2);
			k /= 2;
		}
		return;
	}

	/**
	 * Sink an element down until the heap order property is restored
	 * 
	 * @param k index of the element that should be sunk
	 */
	private void sink(int k) {
		while (2*k <= size) {
			int kSmallest = 2*k;
			if (kSmallest + 1 <= size && heap[kSmallest + 1].compareTo(heap[kSmallest]) < 0) {
				kSmallest = kSmallest + 1;
			}
			if (heap[k].compareTo(heap[kSmallest]) < 0) {
				break;
			}
			swap(k, kSmallest);
			k = kSmallest;
		}
		return;
	}

	/**
	 * Swap two elements with each other
	 * 
	 * @param i index of first element to swap
	 * @param j index of second element to swap
	 */	
	private void swap(int i, int j) {
		CompareInt iVal = heap[i];
		heap[i] = heap[j];
		heap[j] = iVal;
		return;
	}	
	
}
