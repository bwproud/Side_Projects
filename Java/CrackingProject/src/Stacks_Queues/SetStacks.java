package Stacks_Queues;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SetStacks {
	private int capacity;
	private List<Stack<Integer>> stackOfStacks;
	private Stack<Integer> currentStack;
	private int head;
	
	public SetStacks(int capacity){
		stackOfStacks= new ArrayList<>();
		currentStack = new Stack<>();
		this.capacity= capacity;
		head=-1;
	}
	
	public void push(int n){
		if(currentStack.size()>=capacity){
			System.out.println("TOP OF STACK "+ (getStacks()+1)+" IS "+currentStack.peek());
			stackOfStacks.add(currentStack);
			currentStack = new Stack<>();
			head++;
		}
		currentStack.push(n);
	}
	
	public int pop(){
		if(currentStack.isEmpty()){
			if(stackOfStacks.isEmpty()){
				return -1;
			}
			currentStack=stackOfStacks.remove(head);
			head--;
		}
		return currentStack.pop();
	} 
	
	public int popAt(int index){
		if(stackOfStacks.get(index).isEmpty()){
			stackOfStacks.remove(index);
			head--;
			return -1;
		}
		
		return stackOfStacks.get(index).pop();
	} 
	
	public int getStacks(){
		return stackOfStacks.size();
	}
	
}
