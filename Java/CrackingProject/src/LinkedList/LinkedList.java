package LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LinkedList {
	private static class LinkNode{
		private int data;
		private LinkNode next;
		
		public LinkNode(int data){
			this.data=data;
		}
	}
	
	public static void main(String args[]){
		int test = 0;
		if(test==1||test==0){
			System.out.println("Duplicate Removal:");
			LinkNode head = buildLinkList(10);
			System.out.print("before duplicate removal: ");
			printLink(head);
			System.out.print("after duplicate removal:  ");
			deleteDuplicates(head);
			printLink(head);
			System.out.println();
		}
		if(test==2||test==0){
			int k= 5;
			System.out.println("K = "+k);
			System.out.println("Kth from last version 1:");
			LinkNode head = buildLinkList(10);
			printLink(head);
			LinkNode node = findKElementV1(head, k);
			System.out.println(node.data);
			
			System.out.println("Kth from last version 2:");
			printLink(head);
			node = findKElementV2(head, k);
			System.out.println(node.data+"\n");
		}
		if(test==3||test==0){
			System.out.println("Middle Deletion:");
			LinkNode head = buildLinkList(10);
			LinkNode remove = findKElementV1(head, 5);
			System.out.print("before middle removal of "+ remove.data+": ");
			printLink(head);
			System.out.print("after middle removal of "+ remove.data+":  ");
			deleteMiddleLink(remove);
			printLink(head);
			System.out.println();
		}
		if(test==4||test==0){
			System.out.println("Linked List Partition:");
			LinkNode head = buildLinkList(10);
			System.out.print("before partition of 5: ");
			printLink(head);
			System.out.print("after partition of 5:  ");
			head=partitionLinkedList(head, 5);
			printLink(head);
			System.out.println();
		}
		if(test==5||test==0){
			System.out.println("Sum List from back:");
			LinkNode head1 = buildLinkList(3);
			LinkNode head2 = buildLinkList(3);
			printLink(head1);
			printLink(head2);
			System.out.println("Sum = "+sumLinkedListV1(head1, head2)+"\n");
			System.out.println("Sum List from front:");
			head1 = buildLinkList(3);
			head2 = buildLinkList(3);
			printLink(head1);
			printLink(head2);
			System.out.println("Sum = "+sumLinkedListV2(head1, head2)+"\n");
		}
		if(test==6||test==0){
			System.out.println("Is Palindrome:");
			LinkNode head = buildLinkList(10);
			printLink(head);
			System.out.println(isPalindrome(head));
			LinkNode palindromeHead = buildPalindromeList(10);
			printLink(palindromeHead);
			System.out.print(isPalindrome(palindromeHead)+"\n\n");
		}
		if(test==7||test==0){
			System.out.println("List Intersect:");
			LinkNode head1 = buildLinkList(5);
			LinkNode head2 = buildLinkList(5);
			LinkNode shared = new LinkNode(69);
			shared.next=head1.next;
			head1.next=shared;
			head2.next=shared;
			System.out.print("list 1: ");
			printLink(head1);
			System.out.print("list 2: ");
			printLink(head2);
			LinkNode intersect = listIntersectV1(head1, head2);
			System.out.print("shared: ");
			printLink(intersect);
			System.out.println();
		}
		if(test==8||test==0){
			System.out.println("Loop Detection:");
			LinkNode head = buildCircularLinkList(10);
			printCircularLink(head, 10);
			LinkNode loop = loopDetection(head);
			printCircularLink(loop, 10);
			System.out.println();
		}
	}
	
	/**
	 * builds a linked list
	 */
	public static LinkNode buildLinkList(int nodes){
		LinkNode head = new LinkNode((int)(Math.random()*10));
		LinkNode current=head;
		for(int i =1; i<nodes; i++){
			LinkNode newNode = new LinkNode((int)(Math.random()*10));
			current.next=newNode;
			current=current.next;
		}
		return head;
	}
	
	/**
	 * builds a circular linked list
	 */
	public static LinkNode buildCircularLinkList(int nodes){
		LinkNode head = new LinkNode((int)(Math.random()*10));
		LinkNode current=head;
		LinkNode circular = new LinkNode(69);
		current.next=circular;
		current=current.next;
		for(int i =1; i<nodes-1; i++){
			LinkNode newNode = new LinkNode((int)(Math.random()*10));
			current.next=newNode;
			current=current.next;
		}
		current.next=circular;
		return head;
	}
	
	/**
	 * builds a palindrome list for testing purposes
	 */
	public static LinkNode buildPalindromeList(int nodes){
		LinkNode head = new LinkNode(0);
		LinkNode current=head;
		for(int i =1; i<nodes/2; i++){
			LinkNode newNode = new LinkNode(i);
			current.next=newNode;
			current=current.next;
		}
		LinkNode newNode = new LinkNode(nodes/2+1);
		current.next=newNode;
		current=current.next;
		for(int i =nodes/2-1; i>=0; i--){
			newNode = new LinkNode(i);
			current.next=newNode;
			current=current.next;
		}
		return head;
	}
	
	
	
	/**
	 * prints a linked list
	 */
	public static void printLink(LinkNode head){
		if(head.next==null){
			System.out.println("["+head.data+"]");
			return;
		}
		System.out.print("[ ");
		while(head!=null && head.next!=null){
			System.out.print(head.data+", ");
			head=head.next;
		}
		System.out.print(head.data+"]\n");	
	}
	
	/**
	 * prints a linked list
	 */
	public static void printCircularLink(LinkNode head, int length){
		if(head==null){
			System.out.println("[]");
			return;
		}
		
		if(head.next==null){
			System.out.println("["+head.data+"]");
			return;
		}
		System.out.print("[ ");
		int i = 0;
		while(head!=null && head.next!=null && i<length){
			System.out.print(head.data+", ");
			head=head.next;
			i++;
		}
		System.out.print("...]\n");	
	}
	
	/**
	 * 2.1
	 * deletes duplicates from a linked list using runner/chaser pattern
	 */
	public static LinkNode deleteDuplicates(LinkNode head){
		if(head==null){
			return null;
		}
		
		Map<Integer, Boolean> map = new HashMap<>();
		LinkNode runner = head.next;
		LinkNode chaser = head;
		map.put(head.data, true);
		
		while(runner!=null){
			boolean inList = map.getOrDefault(runner.data, false);
			if(inList){
				chaser.next=runner.next;
			}else{
				map.put(runner.data, true);
				chaser=chaser.next;
			}
			runner=runner.next;
		}
		return head;
	}
	
	/**
	 * 2.2
	 * returns the kth to the last element using runner/chaser pattern
	 */
	public static LinkNode findKElementV1(LinkNode head, int k){
		LinkNode chaser = head;
		int count=0;
		while(head!=null){
			if(count>k){
				chaser=chaser.next;
			}
			count++;
			head=head.next;
		}
		return chaser;
	}
	
	/**
	 * 2.2
	 * returns the kth to the last element by finding the legnth of the list
	 */
	public static LinkNode findKElementV2(LinkNode head, int k){
		int length = findLength(head);
		int index = length-k;
		while(head!=null){
			index--;
			if(index==0){
				return head;
			}
			head=head.next;
		}
		return null;
	}
	
	/**
	 * finds the length of a linked list
	 */
	public static int findLength(LinkNode head){
		int length=0;
		while(head!=null){
			length++;
			head=head.next;
		}
		return length;
	}
	
	/**
	 * 2.3
	 * delete the middle of a linked list
	 */
	public static void deleteMiddleLink(LinkNode middleNode){
		middleNode.data=middleNode.next.data;
		middleNode.next=middleNode.next.next;
	}
	
	/**
	 * 2.4
	 * partitions a linked list
	 */
	public static LinkNode partitionLinkedList(LinkNode head, int val){
		LinkNode lHead=new LinkNode(0);
		LinkNode lTail=null;
		LinkNode gHead=new LinkNode(0);
		LinkNode gTail=gHead;
		int lcount=0;
		int gcount=0;
		
		while(head!=null){
			if(head.data<val){
				if(lcount==0){
					lHead.data=head.data;
					lHead.next=new LinkNode(0);
					lTail=lHead.next;
					lcount++;
				}else if(lcount==1){
					lTail.data=head.data;
					lcount++;
				}else{
					lTail.next=new LinkNode(head.data);
					lTail=lTail.next;	
				}
			}else{
				if(gcount==0){
					gHead.data=head.data;
					gHead.next=new LinkNode(0);
					gTail=gHead.next;
					gcount++;
				}else if(gcount==1){
					gTail.data=head.data;
					gcount++;
				}else{
					gTail.next=new LinkNode(head.data);
					gTail=gTail.next;	
				}
			}
			head=head.next;
		}
		lTail.next=gHead;
		return lHead;
	}
	
	/**
	 * 2.5
	 * returns sum of linked list from back
	 */
	public static int sumLinkedListV1(LinkNode head1, LinkNode head2){
		int sum = 0;
		int n = 0;
		while(head1!=null || head2!=null){
			if(head1!=null){
				sum+=head1.data*Math.pow(10, n);
				head1=head1.next;
			}
			if(head2!=null){
				sum+=head2.data*Math.pow(10, n);
				head2=head2.next;
			}
			n++;
		}
		return sum;
	}
	
	/**
	 * 2.5
	 * returns sum of linked list from front
	 */
	public static int sumLinkedListV2(LinkNode head1, LinkNode head2){
		int sum = 0;
		int length1 = findLength(head1);
		int length2 = findLength(head2);
		int max = Math.max(length1, length2)-1;
		while(max>=0){
			if(length1>=max){
				sum+=head1.data*Math.pow(10, max);
				head1=head1.next;
			}
			if(length2>=max){
				sum+=head2.data*Math.pow(10, max);
				head2=head2.next;
			}
			max--;
		}
		return sum;
	}
	
	/**
	 * 2.6
	 * checks to see if a linked list a palindrome
	 */
	public static boolean isPalindrome(LinkNode head){
		Stack<LinkNode> stack = new Stack<>();
		int length = findLength(head);
		int count=1;
		while(head!=null){
			if(count<(length/2)+1){
				stack.push(head);
			}else if(count==(length/2)+1&&length%2==1){
			}else{
				if(head.data!=stack.pop().data){
					return false;
				}
			}
			count++;
			head=head.next;
		}
		return true;
	}
	
	/**
	 * 2.7
	 * checks to see if 2 linked lists intersect
	 */
	public static LinkNode listIntersectV1(LinkNode head1, LinkNode head2){
		Map<LinkNode, Boolean> map = new HashMap<>();
		while(head1!=null){
			map.put(head1, true);
			head1=head1.next;
		}
		while(head2!=null){
			if(map.getOrDefault(head2, false)){
				return head2;
			}
			head2=head2.next;
		}
		return null;
	}
	
	/**
	 * 2.7
	 * checks to see if 2 linked lists intersect using hashmap
	 */
	public static LinkNode listIntersectV2(LinkNode head1, LinkNode head2){
		Map<LinkNode, Boolean> map = new HashMap<>();
		while(head1!=null){
			map.put(head1, true);
			head1=head1.next;
		}
		while(head2!=null){
			if(map.getOrDefault(head2, false)){
				return head2;
			}
			head2=head2.next;
		}
		return null;
	}
	
	/**
	 * 2.8
	 * loop detection
	 */
	public static LinkNode loopDetection(LinkNode head){
		Map<LinkNode, Boolean> map = new HashMap<>();
		while(head!=null){
			if(map.getOrDefault(head, false)){
				return head;
			}
			map.put(head, true);
			head=head.next;
		}
		return null;
	}
}
