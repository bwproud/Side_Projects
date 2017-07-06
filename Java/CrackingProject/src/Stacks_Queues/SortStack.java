package Stacks_Queues;

import java.util.Stack;

public class SortStack {
	Stack<Integer> main;
	Stack<Integer> toSort;
	
	public SortStack(){
		main = new Stack<>();
		toSort = new Stack<>();
	}
	
	public void push(int n){
		if(main.isEmpty()||main.peek()>=n){
			main.push(n);
			return;
		}
		
		while(!main.isEmpty()&&main.peek()<n){
			toSort.push(main.pop());
		}
		
		main.push(n);
		while(!toSort.isEmpty()){
			main.push(toSort.pop());
		}
	}
	
	public int pop(){
		return main.pop();
	}
}
