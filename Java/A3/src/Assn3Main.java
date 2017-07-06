import java.util.Scanner;

class Assn3Main {
	public static void main(String[] args){
		BST binary = new BST();
		Scanner s = new Scanner(System.in);
		if (args.length==0) {
			System.out.println("Interactive Mode: ");
		      String command = "";
		      do{
		    	  command=s.next();
		    	  switch(command){
		    	  case "new":
		    		  binary = new BST();
		    		  break;
		    	  case "i":
		    		  String input = s.next();
		    		  binary.insert(input);
		    		  break;
		    	  case "r":
		    		  String r = s.next();
		    		  binary.remove(r);
		    		  break;
		    	  case "c":
		    		  String c = s.next();
		    		  System.out.println(binary.contains(c));
		    		  break;
		    	  case "g":
		    		  String g = s.next();
		    		  if(binary.get(g).data!=null){
		    		  System.out.println(binary.get(g).data);
		    		  }
		    		  break;
		    	  case "x":
		    		  System.out.println("The maximum value is "+ binary.findMax());
		    		  break;
		    	  case "n":
		    		  System.out.println("The mininum value is " +binary.findMin());
		    		  break;
		    	  case "v":
		    		  System.out.println("The value stored in the root is " +binary.val());
		    		  break;
		    	  case "e":
		    		  System.out.println(binary.empty());
		    		  break;
		    	  case "s":
		    		  System.out.println("The size is " +binary.size());
		    		  break;
		    	  case "h":
		    		  System.out.println("The height is " +binary.height());
		    		  break;
		    	  case "p":
		    		  binary.print();
		    		  break;
		    	  case "q":
		    		  break;
		    	  }
		      }while(!command.equals("q"));
		    }
		    else {  
		      String cmd;
		      System.out.println("Noninteractive Mode:\n");
		      int na = args.length; 
		      for (int i=0; i < na; i++) {
		        cmd = args[i];
		        switch (cmd) {
		    	  case "new":
		    		  binary = new BST();
		    		  break;
		    	  case "i":
		    		  String input = args[i+1];
		    		  binary.insert(input);
		    		  i++;
		    		  break;
		    	  case "r":
		    		  String r = args[i+1];
		    		  binary.remove(r);
		    		  i++;
		    		  break;
		    	  case "c":
		    		  String c = args[i+1];
		    		  System.out.println(binary.contains(c));
		    		  i++;
		    		  break;
		    	  case "g":
		    		  String g = args[i+1];
		    		  if(binary.get(g).data!=null){
			    		  System.out.println(binary.get(g).data);
			    	  }
		    		  i++;
		    		  break;
		    	  case "x":
		    		  System.out.println("The maximum value is "+binary.findMax());
		    		  break;
		    	  case "n":
		    		  System.out.println("The minimum value is "+ binary.findMin());
		    		  break;
		    	  case "v":
		    		  System.out.println("The value stored in the root is " +binary.val());
		    		  break;
		    	  case "e":
		    		  System.out.println(binary.empty());
		    		  break;
		    	  case "s":
		    		  System.out.println("The size is " +binary.size());
		    		  break;
		    	  case "h":
		    		  System.out.println("The height is " +binary.height());
		    		  break;
		    	  case "p":
		    		  binary.print();
		    		  break;
		    	  case "q":
		    		  break;
		        }
		      }
		      System.out.println();     
		    }
		  }
}


class BST{
	Node root;
	public int size;
	public int height;

	public BST(){
		root = new Node(null, null, null, null, 0);
		size=0;
	}
	
	public static class Node{
		public Node(String d, Node lc, Node rc, Node p, int h){
			leftChild = lc;
			rightChild = rc;
			data = d;
			parent = p;
			height = h;
		}
		
		public int height;
		public String data;
		public Node leftChild;
		public Node rightChild;
		public Node parent;
	}

	public void insert(String s){
		if(root.data==null){
			root.data = s;
			root.leftChild = new Node(null, null, null, root, 1);
			root.rightChild = new Node(null, null, null, root, 1);
			size++;
		}
		else{
			insert(s, root);
		}
	}
	
	private void insert(String s, Node t){
		if(t.data == null){
			t.data = s;
			t.leftChild = new Node(null, null, null, t, t.height+1);
			t.rightChild = new Node(null, null, null, t, t.height+1);
			size++;
		}
		
		if(s.compareTo(t.data)==0){
		}
		else if(s.compareTo(t.data)>0){
			insert(s, t.rightChild);
		}
		else{
			insert(s, t.leftChild);
		}
		
	}
	
	public void remove(String s){
		if(contains(s)){
			if(root.data==null){
			}
			else{
				remove(s, root);
			}
		}
		else{
			System.out.println("Not in tree");
		}
	}
	
	private void remove(String s, Node t){
		if(size==1&&root.data.equals(s)){
			root.data=null;
			size--;
			return;
		}
		
		if(s.compareTo(t.data)==0){
			if(t.leftChild.data!=null&&t.rightChild.data!=null){
				t.data = get(findMin(t.rightChild)).data;
				remove(get(findMin(t.rightChild)).data, t.rightChild);
			}
			else if(t.leftChild.data==null&&t.rightChild.data==null){
				t.data = null;
				size--;
			}
			else{
				if(t.leftChild.data==null&&t.rightChild.data!=null){
					t.rightChild.parent=t.parent;
					t.rightChild.height=t.height;
					if(t.parent.leftChild.data==t.data){
						t.parent.leftChild=t.rightChild;
						t=t.rightChild;
						size--;
					}
					else if(t.parent.rightChild.data==t.data){
						t.parent.rightChild=t.rightChild;
						t=t.rightChild;
						size--;
					}
				}
				else if(t.leftChild.data!=null&&t.rightChild.data==null){
					t.leftChild.parent=t.parent;
					t.leftChild.height=t.height;
					if(t.parent.leftChild.data==t.data){
						t.parent.leftChild=t.leftChild;
						t=t.leftChild;
						size--;
					}
					else if(t.parent.rightChild.data==t.data){
						t.parent.rightChild=t.leftChild;
						t=t.leftChild;
						size--;
					}
				}
			}
		}
		
		else if(s.compareTo(t.data)>0){
			remove(s, t.rightChild);
		}
		else{
			remove(s, t.leftChild);
		}
	}
	
	public String findMin(){
		return findMin(root);
	}
	
	private String findMin(Node t){
		if(t.data==null){
			return null;
		}
		else if(t.leftChild.data==null){
			return t.data;
		}
		return findMin(t.leftChild);
	}
	
	public String findMax(){
		return findMax(root);
	}
	
	private String findMax(Node t){
		if(t.data==null){
			return null;
		}
		else if(t.rightChild.data==null){
			return t.data;
		}
		return findMax(t.rightChild);
	}
	
	public boolean contains(String s){
	return contains(s, root);
	}
	
	private boolean contains(String s, Node t){
		if(t.data==null){
			return false;
		}
		
		if(s.compareTo(t.data)==0){
			return true;
		}
		else if(s.compareTo(t.data)>0){
			return contains(s, t.rightChild);
		}
		else{
			return contains(s, t.leftChild);
		}
	}
	
	public Node get(String s){
		if(contains(s)){
			return get(s, root);
		}
		else{
			System.out.println("No such node");
			return new Node(null, null, null, null, 0);
		}
	}
	
	private Node get(String s, Node t){		
		if(s.compareTo(t.data)==0){
			return t;
		}
		else if(s.compareTo(t.data)>0){
			return get(s, t.rightChild);
		}
		else{
			return get(s, t.leftChild);
		}
	}
	
	public String val(){
		return root.data;
	}
	
	public boolean empty(){
		return (size==0);
	}
	
	public int size(){
		return size;
	}
	
	public int height(){
		return height(root);
	}
	
	private void updateHeight(Node t, int height){
		if(t.data==null){
		}
		else{
			    t.height=height;
			    updateHeight(t.leftChild, height+1);
			    updateHeight(t.rightChild, height+1);
			}
		}
	
	private int height(Node t)
	{
		if(t.data==root.data){
			this.height=0;
			updateHeight(root, 0);
		}
		
		if(t.data!=null){
			height(t.rightChild);
			if(t.height>height){
				height=t.height;
			}
			height(t.leftChild);
		}
	   return height;
	}
	
	public void print(){
		print(root);
	}
	
	private void print(Node t){
		updateHeight(root, 0);
		if(t.data!=null){
			print(t.rightChild);
			for(int x=0; x<t.height; x++){
				System.out.print("   ");
			}
			System.out.println(t.data);
			print(t.leftChild);
		}
	}
}


