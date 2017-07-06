package Stacks_Queues;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class MinStack {
	private List<Integer> main;
	private List<Integer> min;
	private int head;
	
	public MinStack(){
		main = new ArrayList<>();
		min  = new ArrayList<>();
		head=-1;
	}
	
	public void push(int n){
		int mi = min();
		if(n<=mi){
			min.add(n);
		}else{
			min.add(mi);
		}
		
		main.add(n);
		head++;
	}
	
	public int pop(){
		if(head<0){
			return -1;
		}
		min.remove(head);
		int retVal = main.remove(head);
		head--;
		return retVal;
	}
	
	public int min(){
		return (head>=0) ? min.get(head) : Integer.MAX_VALUE;
	}
	
}
