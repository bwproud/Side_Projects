import java.util.Scanner;

public class Sorts {
	int LEN =1000;
	public static int numbers[];
	
	/**
	 * Sorts an array of integers by splitting an array into
	 * partitions and recursively sorting each partition
	 * @param low low bound of the partition
	 * @param high high bound of the partition
	 * @return A sorted Int Array
	 */
	int[] quickSort(int low, int high){
	    int p, pivot, x, y, temp;
	    if(low<high){
	    	pivot=low;
	    	x=low;
	    	y=high;
	    	while(x<y){
	    		while(numbers[x]<=numbers[pivot]&&x<high){
	    			x++;
	    		}
	    		while(numbers[y]>numbers[pivot]){
	    			y--;
	    		}
	    		if(x<y){
	    			temp=numbers[x];
	    			numbers[x]=numbers[y];
	    			numbers[y]=temp;
	    		}
	    	}
	    	temp=numbers[pivot];
	    	numbers[pivot]=numbers[y];
	    	numbers[y]=temp;
	    	quickSort(low, y-1);
	    	quickSort(y+1, high);
	    }
	    return numbers;
	}
	
    static int[] bubbleSort(int limit){
	for(int x=0; x<limit; x++){
		for(int y=x; y<limit; y++){
			if(numbers[x]>numbers[y]){
				int temp = numbers[y];
				numbers[y]=numbers[x];
				numbers[x]=temp;
			}
		}
	}
	return numbers;
	}
	
	static void setNumbers(int[] a){
		numbers= a;
	}
	
	static int[] selectionSort(int limit){
		int minimum=0;
		int counter=0;
		for(int x=0; x<limit; x++){
			minimum= numbers[x];
			counter=x;
			for(int y=x+1; y<limit; y++){
				if(numbers[y]<minimum){
					minimum=numbers[y];
					counter=y;
				}
			}
			int temp = numbers[x];
			numbers[x]=numbers[counter];
			numbers[counter]=temp;
		}
		return numbers;
	}
	
	/**
	 * sorts items
	 * @param limit number of items in list
	 * @return sorted list
	 */
	static int[] insertionSort(int limit){
		for(int x=0; x<limit; x++){
			if(x==0){}
			else if(numbers[x]>=numbers[x-1]){}
			else{
				while(numbers[x]<numbers[x-1]){
					int temp=numbers[x-1];
					numbers[x-1] = numbers[x];
					numbers[x]=temp;
					x--;
					if(x==0){break;}
				}
			}
		}
		return numbers;
	}
	
	static int[] mergeSort(int limit){
	return numbers;
	}
	
	static int[] heapSort(int limit){
	return numbers;
	}
	
	public static void main(String args[]){
	    int nums[];
	    int x;
	    int NUM=0;
	    Scanner s = new Scanner(System.in);
	    NUM=s.nextInt();
	    numbers=new int[NUM];
	    for (x=0; x<NUM; x++){
	        numbers[x]=s.nextInt();
	    }
	    
	    System.out.println("\nHere are the numbers in the order you entered:");
	    
	    for(x=0; x<NUM; x++){
	        System.out.println(numbers[x]);
	    }
	    
	    //quickSort(0, NUM-1);
	    bubbleSort(NUM);
	    //insertionSort(NUM);
	    //selectionSort(NUM);
	    
	    System.out.println("\nIn numerical order, the numbers are:");
	    for(x=0; x<NUM; x++){
	        System.out.println(numbers[x]);
	    }
	}
}
