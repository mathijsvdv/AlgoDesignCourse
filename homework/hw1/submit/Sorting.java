
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
		//TODO
	}

	public static CompareInt[] merge(CompareInt[] arrA, CompareInt[] arrB) {
		int n = arrA.length;
		int m = arrB.length;
		CompareInt[] arrC = new CompareInt[n+m];
		
		int i, j, k;
		i = j = k = 0;
		
		while (i < n && j < m) {
			CompareInt a = arrA[i];
			CompareInt b = arrB[j];
			if (a < b) {
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

		return arrC;
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
