package LinkedList;

public class test {
	
	private static class ListNode{
		private int val;
		private ListNode next;
		
		public ListNode(int data){
			this.val=data;
		}
	}
	
	public static void main(String[] args) {
		ListNode h1 = new ListNode(5);
		ListNode h2 = new ListNode(5);
//		h2.next.next.next=new ListNode(9);
//		h2.next.next.next.next=new ListNode(9);
//		h2.next.next.next.next.next=new ListNode(9);
//		h2.next.next.next.next.next.next=new ListNode(9);
//		h2.next.next.next.next.next.next.next=new ListNode(9);
//		h2.next.next.next.next.next.next.next.next=new ListNode(9);
//		h2.next.next.next.next.next.next.next.next.next=new ListNode(9);
		ListNode h3 = addTwoNumbers(h1, h2);
		System.out.println(h3.next.val);
	}
	
	public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
		int sum = 0;
		int n = 0;
		ListNode sol=null;
		ListNode current=null;
		while(head1!=null || head2!=null){
			if(head1!=null){
				sum+=head1.val;
				head1=head1.next;
			}
			if(head2!=null){
				sum+=head2.val;
				head2=head2.next;
			}
			System.out.println(sum);
			if(n==0){
				System.out.println(sum%10);
			    sol= new ListNode(sum%10);
			    current = sol;
			    sum/=10;
			    n++;
			    continue;
			}
			ListNode next = new ListNode(sum%10);
			System.out.println(sum%10);
			sum/=10;
			current.next = next;
			current=current.next;
			n++;
		}
		if(sum>0){
			ListNode next = new ListNode(sum%10);
			current.next = next;
		}
		return sol;
    }

}
