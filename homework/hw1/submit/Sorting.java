import java.util.Arrays;

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

	public static void merge(CompareInt[] arrC, CompareInt[] arrA, CompareInt[] arrB) {
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
		//TODO
		return null;
	}

}
