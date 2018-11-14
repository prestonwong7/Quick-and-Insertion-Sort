import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class QuickInsertion {
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out.println("Enter the size of the array: ");
		Random rand = new Random();
		int arraySize = in.nextInt();
		int[] array = new int[arraySize];
		for (int i = 0; i < arraySize; i++) { // Initializing the array with random numbers
			int randomInt = rand.nextInt(14000) - 7000;
			array[i] = randomInt;
		}

		// Start Quick Sort
		long startQuickTime = System.nanoTime();
		quick_sort(array, 0, array.length - 1);
		long endQuickTime = System.nanoTime() - startQuickTime;
		System.out.println("One Time: Quick Sort Time: " + endQuickTime + " nanoseconds");
		// End
		
		//RESET
		for (int i = 0; i < arraySize; i++) {
			int randomInt = rand.nextInt(14000) - 7000;
			array[i] = randomInt;
		}

		// Insertion Sort
		long startInsertionTime = System.nanoTime();
		insertion_sort(array);
		long endInsertionTime = System.nanoTime() - startInsertionTime;
		System.out.println("One Time - Insertion Sort Time: " + endInsertionTime + " nanoseconds");
		// End
		
		System.out.println();
		System.out.println("N = 10000"); // N = array size
		arraySize = 10000;

		// Quick Sort Start Part 2
		int array2[] = new int[arraySize];
		long startQuickTime2 = 0;
		long endQuickTime2 = 0;
		for (int i = 0; i < 100; i++) {	
			for (int j = 0; j < arraySize; j++) {
				array2[j] = rand.nextInt(14000) - 7000;
			}
			startQuickTime2 = System.nanoTime();
			quick_sort(array2, 0, array.length - 1);
			endQuickTime2 += System.nanoTime() - startQuickTime2;
		}
		endQuickTime2 = endQuickTime2 / 100;
		System.out.println("AVERAGE for 100 Repititions Quick Sort Time: " + endQuickTime2 + " nanoseconds");
		// End Part 2
		
		//Insertion Part 2
		long startInsertionTime2 = 0;
		long endInsertionTime2 = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < arraySize; j++) {
				array2[j] = rand.nextInt(14000) - 7000;
			}
			startInsertionTime2 = System.nanoTime();
			insertion_sort(array2);
			endInsertionTime2 += System.nanoTime() - startInsertionTime2;
		}
		endInsertionTime2 = endInsertionTime2 / 100;

		System.out.println("AVERAGE for 100 Repititions Insertion Sort Time: " + endInsertionTime2 + " nanoseconds");

		double perInstruction = (endInsertionTime2 / Math.pow(10000, 2)) / 1000000000;
		double instructions = 1 / perInstruction;
		System.out.println("The amount of instruction for insertion sort in a second: " + instructions);
		// End part 2

	}

	public static void quick_sort(int[] a, int low, int high){

		int mid = (high + low)/2;
		int pivot = 0;
		
		// IF the middle is bigger than the highest, swap the middle with the highest
		if ((a[mid] >= a[low] && a[mid] <= a[high]) || (a[mid] <= a[low] && a[mid] >= a[high] )) {
			pivot = a[mid];
			int temp = a[mid];
			a[mid] = a[high];
			a[high] = temp;
			
		}
		
		// IF the left most value is bigger than the highest, swap the left with the highest
		else if ((a[low] >= a[mid] && a[low] <= a[high]) || (a[low] <= a[mid] && a[low] >= a[high])){
			pivot = a[low];
			int temp = a[high];
			a[high] = a[low];
			a[low] = temp;
		}
		
		//If the right most value is the biggest, set it as pivot
		else if ((a[high] >= a[mid] && a[high] <= a[low]) || (a[high] <= a[mid] &&
				a[high] >= a[low])){
			pivot = a[high];
		}
		
		//FIND LOW AND HIGH AGAIN
		int l = low; 
		int h = high;
		
		while(l <= h){
			while(a[l] < pivot){
				//Increment by one to increase position
				l++;
			}
			while(a[h] > pivot){
				//Decrement by one to decrease position
				h--;
			}
			if(l <= h){
				// Swap if reaches a stop
				swap(a, l, h);
				l++;
				h--;
			}
		}
		
		//RUN QUICKsort RECURSIVELY until it finishes
		if(low < h && (l - low != 0) && (l - low != 1)){
			quick_sort(a, low, h);
		}
		// Position
		if(l < high && (high - h != 1)){
			quick_sort(a, l, high);
		}

	}

	public static void insertion_sort(int[] a) {
		int position;
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j < a.length; j++) {
				position = a[i];
				//whenever finds a higher number, swap it
				if (a[j] < position) {
					int temp = a[j];
					a[j] = a[i];
					a[i] = temp;
				}
			}
	
		}
	}

	private static void swap(int[] a, int l, int h) {
		int temp = a[l];
		a[l] = a[h];
		a[h] = temp;
	}
	
}