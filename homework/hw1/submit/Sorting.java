import java.util.Arrays;
import java.util.Random;

public class Sorting {
	
	/**
	 * Implement the mergesort function, which should sort the array of
	 * integers in place
	 * 
	 * You will probably want to use helper functions here, as described in the lecture recordings
	 * (ex. merge(), a helper mergesort function)
	 * @param arr
	 */
	public static void mergeSort(CompareInt[] arr) {
		int n = arr.length;
		if (n <= 1) {
			return;
		}

		int mid = n / 2;
		CompareInt[] arrA = Arrays.copyOfRange(arr, 0, mid);
		CompareInt[] arrB = Arrays.copyOfRange(arr, mid, n);
		
		mergeSort(arrA);
		mergeSort(arrB);
		merge(arr, arrA, arrB);

		return;
	}

	/**
	 * merge Merge sorted arrays arrA and arrB, saving the sorted result in arrC
	 * 
	 * @param arrC Target array to store the result in
	 * @param arrA First array to merge
	 * @param arrB Second array to merge
	 */
	private static void merge(CompareInt[] arrC, CompareInt[] arrA, CompareInt[] arrB) {
		int n = arrA.length;
		int m = arrB.length;
		
		int i, j, k;
		i = j = k = 0;
		
		while (i < n && j < m) {
			CompareInt a = arrA[i];
			CompareInt b = arrB[j];
			if (a.compareTo(b) < 0) {
				arrC[k] = a;
				i++;
			} else {
				arrC[k] = b;
				j++;
			}
			k++;
		}

		while (i < n) {
			arrC[k++] = arrA[i++];
		}

		while (j < m) {
			arrC[k++] = arrB[j++];
		}

		return;
	}
	
	/**
	 * Implement the quickSelect
	 * 
	 * Again, you will probably want to use helper functions here
	 * (ex. partition(), a helper quickselect function)
	 */
	public static CompareInt quickSelect(int k, CompareInt[] arr) {
		Random rand = new Random();
		return quickSelect(k, arr, 0, arr.length, rand);
	}

	/**
	 * quickSelect on a range within the array, given a random seed
	 * 
	 * @param k k'th smallest element to select
	 * @param arr Array to search in
	 * @param lo Lower bound of indices within the array to search, inclusive
	 * @param hi Upper bound of indices within the array to search, exclusive
	 * @param rand Random seed for selecting the pivot
	 */
	private static CompareInt quickSelect(int k, CompareInt[] arr, int lo, int hi, Random rand) {
		// The slice of the array is closed from the left and open from the right: [lo, hi)
		if (hi == lo + 1) {
			return arr[lo];
		}

		int pivotLoc = selectPivotLoc(arr, lo, hi, rand);
		pivotLoc = partition(arr, lo, hi, pivotLoc);

		if (pivotLoc == k - 1) {
			return arr[pivotLoc];
		} else if (pivotLoc < k - 1) {
			return quickSelect(k, arr, pivotLoc + 1, hi, rand);
		} else {
			return quickSelect(k, arr, lo, pivotLoc, rand);
		}
	}

	/**
	 * selectPivotLoc Select location of the pivot within the array
	 * @param arr Array to search in
	 * @param lo Lower bound of indices within the array to search, inclusive
	 * @param hi Upper bound of indices within the array to search, exclusive
	 * @param rand Random seed for selecting the pivot
	 */
	private static int selectPivotLoc(CompareInt[] arr, int lo, int hi, Random rand) {
		int pivotLoc = rand.nextInt(hi - lo) + lo;
		return pivotLoc;
	}

	/**
	 * partition Partition array into elements below and above the pivot
	 * @param arr Array to search in
	 * @param lo Lower bound of indices within the array to search, inclusive
	 * @param hi Upper bound of indices within the array to search, exclusive
	 * @param pivotLoc Index of the pivot
	 */
	private static int partition(CompareInt[] arr, int lo, int hi, int pivotLoc) {
		int n = hi - lo;
		CompareInt[] partitioned = new CompareInt[n - 1]; // contains elements below and above pivot

		int nBelow, nAbove;
		nBelow = nAbove = 0;
		
		CompareInt pivot = arr[pivotLoc];
		for (int i = lo; i < hi; i++) {
			if (i == pivotLoc) {
				continue;
			}
			CompareInt a = arr[i];
			// Fill start of partitioned array if below pivot, and end when above it
			if (a.compareTo(pivot) < 0) {
				partitioned[nBelow++] = a;
			} else {
				partitioned[n - 2 - nAbove++] = a;
			}
		}
		
		int i = lo;
		for (int iBelow = 0; iBelow < nBelow; iBelow++) {
			arr[i++] = partitioned[iBelow];
		}
		pivotLoc = i;
		arr[i++] = pivot;
		for (int iAbove = 0; iAbove < nAbove; iAbove++) {
			// Keep original order, up to partitioning, so take values starting from the end
			arr[i++] = partitioned[n - 2 - iAbove];
		}

		return pivotLoc;
	}
}
