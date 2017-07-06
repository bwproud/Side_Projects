package Stacks_Queues;

import java.util.Stack;

public class QueueFromStacks {
	private Stack<Integer> main;
	private Stack<Integer> toFlip;
	
	public QueueFromStacks(){
		main = new Stack<>();
		toFlip = new Stack<>();
	}
	
	public void enqueue(int n){
		while(!main.isEmpty()){
			toFlip.push(main.pop());
		}
		toFlip.push(n);
		while(!toFlip.isEmpty()){
			main.push(toFlip.pop());
		}
	}
	
	public int dequeue(){
		return main.pop();
	}
}
