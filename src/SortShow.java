/**
 *
 * @author Ouda
 * and Group 24
 */

//importing the libraries that will be needed in this program

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Random;

//The class that has all the sorts in it
public class SortShow extends JPanel { 

	
		// An array to hold the lines_lengths to be sorted
		public int[] lines_lengths;
		//The amount of lines needed
		public final int total_number_of_lines = 256;
		 // An array to holds the scrambled lines_lengths
		public int[] scramble_lines;
		//A temp Array that is used later for sorts
		public int[] tempArray;
		
		//the default constructor for the SortShow class
		public SortShow(){
			//assigning the size for the lines_lengths below
			lines_lengths = new int[total_number_of_lines];
			for(int i = 0; i < total_number_of_lines; i++) 
				lines_lengths[i] =  i+5;
			
		}
		

		//A method that scrambles the lines
		public void scramble_the_lines(){
			//A random generator
			Random num = new Random(); 
			//Randomly switching the lines
			for(int i = 0; i < total_number_of_lines; i++){
				//getting a random number using the nextInt method (a number between 0 to i + 1)
				int j = num.nextInt(i + 1); 
				//swapping The element at i and j 
				swap(i, j);
			}
			//assigning the size for the scramble_lines below
			scramble_lines = new int[total_number_of_lines];
			//copying the now scrambled lines_lengths array into the scramble_lines array 
			//to store for reuse for other sort methods
			//so that all sort methods will use the same scrambled lines for fair comparison 
			for (int i = 0; i < total_number_of_lines; i++)
			{
				scramble_lines[i] = lines_lengths[i];
			}
			//Drawing the now scrambled lines_lengths
			paintComponent(this.getGraphics());
		}
		
		//Swapping method that swaps two elements in the lines_lengths array
		public void swap(int i, int j){
			//storing the i element in lines_lengths in temp
			int temp = lines_lengths[i];
			//giving i element in lines_lengths the value of j element in lines_lengths
			lines_lengths[i] = lines_lengths[j];
			//giving j element in lines_lengths the value of temp
			lines_lengths[j] = temp;
		}
		
		//The selectionSort method
		public void SelectionSort(){
			//getting the date and time when the selection sort starts
			Calendar start = Calendar.getInstance();
			//Using the selection sort to lines_lengths sort the array

			int minIndex = 0;

			for(int i = 0; i <= lines_lengths.length - 1; i++)
			{
				minIndex = i;

				for(int j = i + 1; j <= lines_lengths.length -1; j++)
				{
					if(lines_lengths[j] < lines_lengths[minIndex])
						minIndex = j;
				}

				if(minIndex != i) {
					swap(i, minIndex);
					//redrawing the lines_lengths
					paintComponent(this.getGraphics());
					//causing a delay for 10ms
					delay(10);
				}
			}

			//getting the date and time when the selection sort ends
			Calendar end = Calendar.getInstance();
			//getting the time it took for the selection sort to execute 
			//subtracting the end time with the start time
	        SortGUI.selectionTime = end.getTime().getTime() - start.getTime().getTime();
		}
		
		//this method gets the smallest element in the array of lines_lengths
		public int getIndexOfSmallest(int first, int last){
			int index = 0;
			int indexOfSmallestItem = 0;

			for(int i = first; i <= last; i++ )
			{
				if(lines_lengths[i] == 0)
					return index;
				else if(lines_lengths[i] < lines_lengths[indexOfSmallestItem])
					indexOfSmallestItem = index;

				index ++;
			}

			return indexOfSmallestItem;
		}
		
	///////////////////////////////////////////////////////////////////////////////////
		
		//recursive merge sort method
		public void R_MergeSort(){
			//getting the date and time when the recursive merge sort starts
			Calendar start = Calendar.getInstance();
			//assigning the size for the tempArray below

			//You need to complete this part.

			int[]  temp_array = lines_lengths;

			int first = 0;
			int last = total_number_of_lines -1;

			R_MergeSort(first, last);

			Calendar end = Calendar.getInstance();
			//getting the time it took for the iterative merge sort to execute
			//subtracting the end time with the start time
	        SortGUI.rmergeTime = end.getTime().getTime() - start.getTime().getTime();
		}
		
		//recursive merge sort method
		public void R_MergeSort(int first, int last){
			if(first < last){

				//You need to complete this part.

				// Divide
				int mid = (first + last) / 2;

				// Recur
				R_MergeSort(first, mid);
				R_MergeSort(mid+1, last);

				// Conquer
				R_Merge(first, mid, last);

				paintComponent(this.getGraphics());

				//Causing a delay for 10ms
				delay(10);
			}
		}

		
		//recursive merge sort method
		public void R_Merge(int first, int mid, int last){

			//You need to complete this part.


			//You need to complete this part
			// Merges the adjacent subarrays a[first...mid] and a[mid+1...last].
			int beginHalf1 = first;
			int endHalf1 = mid;
			int beginHalf2 = mid + 1;
			int endHalf2 = last;
			int[] tempArray = new int[lines_lengths.length];
			int index = first;

			// While both subarrays are not empty, compare an element in one subarray with
			// an element in the other; then copy the smaller item into the temporary array
			// index = 0 next available location in tempArray
			while ((beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2)) {
				if (lines_lengths[beginHalf1] < lines_lengths[beginHalf2]) {
					tempArray[index] = lines_lengths[beginHalf1];
					beginHalf1++;
				} else {
					//lines_lengths[index] = lines_lengths[beginHalf2];
					tempArray[index] = lines_lengths[beginHalf2];
					//swap(index, beginHalf1);
					beginHalf2++;
				}
				index++;
			}

			// Copy the remaining elements from the first half, if any
			while (beginHalf1 <= endHalf1) {
				tempArray[index] = lines_lengths[beginHalf1];
				beginHalf1++;
				index++;
			}

			// Copy the remaining elements from the second half, if any
			while (beginHalf2 <= endHalf2) {
				tempArray[index] = lines_lengths[beginHalf2];
				beginHalf2++;
				index++;
			}

			// Copy the merged elements from tempArray back to lines_lengths
			for (index = first; index <= last; index++) {
				lines_lengths[index] = tempArray[index];
				paintComponent(this.getGraphics());
			}
		}


	///////////////////////////////////////////////////////////////////////////////////

	// bubble sort method //
	public void BubbleSort() {
		Calendar start = Calendar.getInstance();
		// nested for loops to sort lines
		for (int i = 0; i < total_number_of_lines - 1; i++) {
			for (int j = 0; j < total_number_of_lines - 1; j++) {
				if (lines_lengths[i] < lines_lengths [j]) {
					swap(i, j);
					//redrawing the lines_lengths
					paintComponent(this.getGraphics());
					//Causing a delay for 1ms, 10ms delay (like other functions) causes prolonged run time, still illustrative with only 1ms
					delay(1);
				}
			}
		}
		//getting the date and time when the bubble sort ends
		Calendar end = Calendar.getInstance();
		//getting the time it took for the bubble sort to execute
		//subtracting the end time with the start time
		SortGUI.bubbleTime = end.getTime().getTime() - start.getTime().getTime();

	}

	//////////////////////////////////////////////////////////////////////////////////

	// insertion sort method
	public void InsertionSort(){
		Calendar start = Calendar.getInstance();
		for(int i = 1; i <= lines_lengths.length -1; i ++)
		{
			var firstUnsortedElement = lines_lengths[i];
			InsertInOrder(firstUnsortedElement, i -1);
		}
		//getting the date and time when the insertion sort ends
		Calendar end = Calendar.getInstance();
		//getting the time it took for the insertion sort to execute
		//subtracting the end time with the start time
		SortGUI.insertionTime = end.getTime().getTime() - start.getTime().getTime();
	}

	private void InsertInOrder(int element, int end)
	{
		int index = end;
		while(index >= 0 && element < lines_lengths[index])
		{
			lines_lengths[index + 1] = lines_lengths[index];
			index --;
		}

		lines_lengths[index + 1] = element;
		//redrawing the lines_lengths
		paintComponent(this.getGraphics());
		//Causing a delay for 10ms
		delay(10);
	}
	///////////////////////////////////////////////////////////////////////////////////////////

	// shell sort method
	public void ShellSort(){
		Calendar start = Calendar.getInstance();
		int i = (lines_lengths.length - 1) / 2;
		for(; i > 0; i = i / 2)
		{
			for(int j = 0; j < i; j ++)
			{
				IncrementalInsertionSort(j, i);
			}
		}
		//getting the date and time when the shell sort ends
		Calendar end = Calendar.getInstance();
		//getting the time it took for the shell sort to execute
		//subtracting the end time with the start time
		SortGUI.shellTime = end.getTime().getTime() - start.getTime().getTime();
	}

	private void IncrementalInsertionSort(int begin, int space)
	{
		for(int i = begin + space; i <= lines_lengths.length - 1; i += space)
		{
			int j;
			int unsorted = lines_lengths[i];

			for(j = i - space; j >= begin && unsorted - lines_lengths[j] < 0; j -= space) {
				lines_lengths[j + space] = lines_lengths[j];
			}
			lines_lengths[j + space] = unsorted;
			//redrawing the lines_lengths
			paintComponent(this.getGraphics());
			//Causing a delay for 10ms
			delay(10);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////

	// recursive quick sort method
	public void RquickSort(int low, int high) {
		//getting the date and time when the recursive quick sort starts
		Calendar start = Calendar.getInstance();
		if (low < high) {
			int partitionIndex = partition(low, high);
			RquickSort(low, partitionIndex - 1);
			RquickSort(partitionIndex + 1, high);
		}
		//getting the date and time when the recursive quick sort ends
		Calendar end = Calendar.getInstance();
		//getting the time it took for the recursive sort to execute
		//subtracting the end time with the start time
		SortGUI.rquickTime = end.getTime().getTime() - start.getTime().getTime();
	}

	public int partition(int low, int high) {
		int pivot = lines_lengths[high];
		int i = (low - 1);
		for (int j = low; j <= high; ++j) {
			if (lines_lengths[j] < pivot) {
				swap(++i, j);
				//redrawing the lines_lengths
				paintComponent(this.getGraphics());
				//Causing a delay for 10ms
				//delay(10);
			}
		}
		//redrawing the lines_lengths
		paintComponent(this.getGraphics());
		//Causing a delay for 10ms
		delay(10);
		swap(i + 1, high);
		return (++i);
	}


	//////////////////////////////////////////////////////////////////////////////////////////
		
		//iterative merge sort method
		public void I_MergeSort()
		{
		//getting the date and time when the iterative merge sort starts
		Calendar start = Calendar.getInstance();
		//assigning the size for the tempArray below
		tempArray = new int[total_number_of_lines]; 
		//saving the value of total_number_of_lines
		int beginLeftovers = total_number_of_lines;

		
		for (int segmentLength = 1; segmentLength <= total_number_of_lines/2; segmentLength = 2*segmentLength)
		{
			beginLeftovers = I_MergeSegmentPairs(total_number_of_lines, segmentLength);
			int endSegment = beginLeftovers + segmentLength - 1;
			if (endSegment < total_number_of_lines - 1) 
			{
			I_Merge(beginLeftovers, endSegment, total_number_of_lines - 1);
			}
		} 

		// merge the sorted leftovers with the rest of the sorted array
		if (beginLeftovers < total_number_of_lines) {
			I_Merge(0, beginLeftovers-1, total_number_of_lines - 1);
		}
		//getting the date and time when the iterative merge sort ends
		Calendar end = Calendar.getInstance();
		//getting the time it took for the iterative merge sort to execute 
		//subtracting the end time with the start time
	    SortGUI.imergeTime = end.getTime().getTime() - start.getTime().getTime();
	} 

	// Merges segments pairs (certain length) within an array 
	public int I_MergeSegmentPairs(int l, int segmentLength)
	{
		//The length of the two merged segments 

		//You suppose  to complete this part (Given).
		int mergedPairLength = 2 * segmentLength;
		int numberOfPairs = l / mergedPairLength;

		int beginSegment1 = 0;
		for (int count = 1; count <= numberOfPairs; count++)
		{
			int endSegment1 = beginSegment1 + segmentLength - 1;

			int beginSegment2 = endSegment1 + 1;
			int endSegment2 = beginSegment2 + segmentLength - 1;
			I_Merge(beginSegment1, endSegment1, endSegment2);

			beginSegment1 = endSegment2 + 1;
			//redrawing the lines_lengths
			paintComponent(this.getGraphics());
			//Causing a delay for 10ms
			delay(10);
		}
		// Returns index of last merged pair
		return beginSegment1;
		//return 1;//modify this line
	}

	public void I_Merge(int first, int mid, int last)
	{
		//You suppose  to complete this part (Given).
		// Two adjacent sub-arrays
		int beginHalf1 = first;
		int endHalf1 = mid;
		int beginHalf2 = mid + 1;
		int endHalf2 = last;

		// While both sub-arrays are not empty, copy the
		// smaller item into the temporary array
		int index = beginHalf1; // Next available location in tempArray
		for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++)
		{
			// Invariant: tempArray[beginHalf1..index-1] is in order
			if (lines_lengths[beginHalf1] < lines_lengths[beginHalf2])
			{
				tempArray[index] = lines_lengths[beginHalf1];
				beginHalf1++;
			}
			else
			{
				tempArray[index] = lines_lengths[beginHalf2];
				beginHalf2++;
			}
		}

		//redrawing the lines_lengths
		paintComponent(this.getGraphics());

		// Finish off the nonempty sub-array

		// Finish off the first sub-array, if necessary
		for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
			// Invariant: tempArray[beginHalf1..index-1] is in order
			tempArray[index] = lines_lengths[beginHalf1];

		// Finish off the second sub-array, if necessary
		for (; beginHalf2 <= endHalf2; beginHalf2++, index++)
			// Invariant: tempa[beginHalf1..index-1] is in order
			tempArray[index] = lines_lengths[beginHalf2];

		// Copy the result back into the original array
		for (index = first; index <= last; index++)
			lines_lengths[index] = tempArray[index];
	}

	//////////////////////////////////////////////////////////////////////	
		
		//This method resets the window to the scrambled lines display
		public void reset(){
			if(scramble_lines != null)
			{
				//copying the old scrambled lines into lines_lengths
				for (int i = 0; i < total_number_of_lines; i++)
				{
					lines_lengths[i] = scramble_lines[i] ;
				}
			//Drawing the now scrambled lines_lengths
			paintComponent(this.getGraphics());
			}
		}
		
	
		//This method colours the lines and prints the lines
		public void paintComponent(Graphics g){
 			super.paintComponent(g);
			//A loop to assign a colour to each line
			for(int i = 0; i < total_number_of_lines; i++){
				//using eight colours for the lines
				if(i % 8 == 0){
					g.setColor(Color.green);
				} else if(i % 8 == 1){
					g.setColor(Color.blue);
				} else if(i % 8 == 2){
					g.setColor(Color.yellow);
				} else if(i%8 == 3){
					g.setColor(Color.red);
				} else if(i%8 == 4){
					g.setColor(Color.black);
				} else if(i%8 == 5){
					g.setColor(Color.orange);
				} else if(i%8 == 6){
					g.setColor(Color.magenta);
				} else
					g.setColor(Color.gray);
				
				//Drawing the lines using the x and y-components 
				g.drawLine(4*i + 25, 300, 4*i + 25, 300 - lines_lengths[i]);
			}
		}
		
		//A delay method that pauses the execution for the milliseconds time given as a parameter
		public void delay(int time){
			try{
	        	Thread.sleep(time);
	        } catch(InterruptedException ie){
	        	Thread.currentThread().interrupt();
	        }
		}
		
	}

