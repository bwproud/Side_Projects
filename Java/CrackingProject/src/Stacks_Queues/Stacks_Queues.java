package Stacks_Queues;

import java.util.LinkedList;
import java.util.Queue;

public class Stacks_Queues {
	public static void main(String args[]){
		int test = 0;
		if(test==1||test==0){
			System.out.println("Duplicate Removal:");
		}
		if(test==2||test==0){
			System.out.println("MinStack Test:");
			System.out.println("Pushing elements to stack . . .");
			MinStack stack = new MinStack();
			for(int i = 0; i<10; i++){
				int add = (int)(Math.random()*100);
				System.out.println("Pushed to stack: "+add);
				stack.push(add);
				System.out.println("Current min element: "+stack.min());
			}
			System.out.println("\nPopping elements from stack . . .");
			for(int i = 0; i<10; i++){
				System.out.println("Popped from stack: "+stack.pop());
				System.out.println("Current min element: "+stack.min());
			}
			System.out.println();
		}
		if(test==3||test==0){
			System.out.println("StackOfStacks Test:");
			System.out.println("Pushing elements to stack . . .");
			SetStacks stack = new SetStacks(3);
			for(int i = 0; i<10; i++){
				int add = (int)(Math.random()*100);
				System.out.println("Pushed to stack: "+add);
				stack.push(add);
				System.out.println("stack of stacks size: "+stack.getStacks());
			}
			System.out.println("\nPopping elements from top of stacks . . .");
			for(int i = 0; i<stack.getStacks();i++){
				System.out.println("POPPED FROM STACK "+(i+1)+" is "+ stack.popAt(i));
			}
			System.out.println("\nPopping elements from rest of stacks . . .");
			for(int i = 0; i<10; i++){
				System.out.println("Popped from stack: "+stack.pop());
			}
			System.out.println();
		}
		if(test==4||test==0){
			System.out.println("QueueFromStacks Test:");
			System.out.println("enqueueing elements . . .");
			QueueFromStacks queue1 = new QueueFromStacks();
			Queue<Integer> queue2 = new LinkedList<>();
			for(int i = 0; i<10; i++){
				int add = (int)(Math.random()*100);
				System.out.println("Enqueued: "+add);
				queue1.enqueue(add);
				queue2.add(add);
			}
			System.out.println("\nDequeueing elements . . .");
			for(int i = 0; i<10; i++){
				System.out.println("Dequeued from QueueFromStacks: "+queue1.dequeue());
				System.out.println("Dequeued from LinkedListQueue: "+queue2.poll());
			}
			System.out.println();
		}
		if(test==5||test==0){
			System.out.println("SortStack Test:");
			System.out.println("Pushing elements to stack . . .");
			SortStack stack = new SortStack();
			for(int i = 0; i<10; i++){
				int add = (int)(Math.random()*100);
				System.out.println("Pushed to stack: "+add);
				stack.push(add);
			}
			System.out.println("\nPopping elements from stack . . .");
			for(int i = 0; i<10; i++){
				System.out.println("Popped from stack: "+stack.pop());
			}
			System.out.println();
		}
		if(test==6||test==0){
			
		}
		if(test==7||test==0){
			
		}
		if(test==8||test==0){
	
		}
	}
}
