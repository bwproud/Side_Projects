import java.util.Random;
import java.util.Scanner;

class Assn4Main {
	public static void main(String[] args){
		SPLT binary = new SPLT();
		Scanner s = new Scanner(System.in);
		if (args.length==0) {
			System.out.println("Interactive Mode: ");
		      String command = "";
		      do{
		    	  command=s.next();
		    	  switch(command){
		    	  case "new":
		    		  binary = new SPLT();
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
		    	  case "f":
		    		  MyRandom random = new MyRandom();
		    			for(int x =0; x<20; x++){
		    					binary.insert(random.nextString(1,1));
		    			}
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
		    		  binary = new SPLT();
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
		    	  case "f":
		    		  MyRandom random = new MyRandom();
		    			for(int x =0; x<20; x++){
		    					binary.insert(random.nextString(1,1));
		    			}
		    		  break;
		    	  case "q":
		    		  break;
		        }
		      }
		      System.out.println();     
		    }
		    
		  }
}


class SPLT{
	Node root;
	public int size;
	public int height;

	public SPLT(){
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

	public void splay(Node t){
	while(root.data!=t.data){
		if(t.parent.data==root.data){
			if(t.parent.rightChild.data==t.data){
				t.parent.rightChild=t.leftChild;
				t.leftChild.parent=t.parent;
				t.leftChild=t.parent;
				t.parent.parent=t;
				t.parent = null;
				root=t;
			}
			else if(t.parent.leftChild.data==t.data){
				t.parent.leftChild=t.rightChild;
				t.rightChild.parent=t.parent;
				t.rightChild=t.parent;
				t.parent.parent=t;
				t.parent = null;
				root=t;
			}
		}
		else if(t.parent.parent!=null){
			if(t.parent.parent.rightChild.data==t.parent.data&&t.parent.rightChild.data==t.data){
				Node l=t.parent.parent.parent;
				t.parent.rightChild=t.leftChild;
				t.leftChild.parent=t.parent;
				t.parent.parent.rightChild=t.parent.leftChild;
				t.parent.leftChild.parent=t.parent.parent;
				t.parent.leftChild = t.parent.parent;
			    t.leftChild=t.parent;
			    t.parent.parent.parent=t.parent;
			    t.leftChild.parent=t;
			    t.parent = l;
			    if(t.parent==null){
			    	root=t;
			    }
			    else if(t.parent.leftChild.data==t.leftChild.leftChild.data){
			    	t.parent.leftChild=t;
			    }
			    else if(t.parent.rightChild.data==t.leftChild.leftChild.data){
				    t.parent.rightChild=t;
				} 
			}
			else if(t.parent.parent.leftChild.data==t.parent.data&&t.parent.leftChild.data==t.data){
				Node l=t.parent.parent.parent;
				t.parent.leftChild=t.rightChild;
				t.rightChild.parent=t.parent;
				t.parent.parent.leftChild=t.parent.rightChild;
				t.parent.rightChild.parent=t.parent.parent;
				t.parent.rightChild = t.parent.parent;
			    t.rightChild=t.parent;
			    t.parent.parent.parent=t.parent;
			    t.rightChild.parent=t;
			    t.parent = l;
			    if(t.parent==null){
			    	root=t;
			    }
			    else if(t.parent.leftChild.data==t.rightChild.rightChild.data){
			    	t.parent.leftChild=t;
			    }
			    else if(t.parent.rightChild.data==t.rightChild.rightChild.data){
				    t.parent.rightChild=t;
				} 
			}
			else if(t.parent.parent.rightChild.data==t.parent.data&&t.parent.leftChild.data==t.data){
				Node l = t.parent.parent.parent;
				t.parent.parent.rightChild=t.leftChild;
				t.leftChild.parent=t.parent.parent;
				t.parent.leftChild=t.rightChild;
				t.rightChild.parent=t.parent;
				t.leftChild=t.parent.parent;
				t.rightChild=t.parent;
				t.leftChild.parent=t;
				t.rightChild.parent=t;
				t.parent=l;
				if(t.parent==null){
			    	root=t;
			    }
			    else if(t.parent.leftChild.data==t.leftChild.data){
			    	t.parent.leftChild=t;
			    }
			    else if(t.parent.rightChild.data==t.leftChild.data){
				    t.parent.rightChild=t;
				}
			}
			else if(t.parent.parent.leftChild.data==t.parent.data&&t.parent.rightChild.data==t.data){
				Node l = t.parent.parent.parent;
				t.parent.parent.leftChild=t.rightChild;
				t.rightChild.parent=t.parent.parent;
				t.parent.rightChild=t.leftChild;
				t.leftChild.parent=t.parent;
				t.rightChild=t.parent.parent;
				t.leftChild=t.parent;
				t.rightChild.parent=t;
				t.leftChild.parent=t;
				t.parent=l;
				if(t.parent==null){
			    	root=t;
			    }
			    else if(t.parent.leftChild.data==t.rightChild.data){
			    	t.parent.leftChild=t;
			    }
			    else if(t.parent.rightChild.data==t.rightChild.data){
				    t.parent.rightChild=t;
				}
			}
		}
	}
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
			splay(t);
			size++;
		}
		
		if(s.compareTo(t.data)==0){
			splay(t);
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
		findMax(root.leftChild);
		root.rightChild.rightChild.parent=root;
		root.rightChild = root.rightChild.rightChild;
		size--;
	}
	
	public String findMin(){
		return findMin(root);
	}
	
	private String findMin(Node t){
		if(t.data==null){
			return null;
		}
		else if(t.leftChild.data==null){
			splay(t);
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
			splay(t);
			return t.data;
		}
		return findMax(t.rightChild);
	}
	
	public boolean contains(String s){
		if(size==0){
			return false;
		}
		return contains(s, root);
	}
	
	private boolean contains(String s, Node t){
		if(t.data==null){
			splay(t.parent);
			return false;
		}
		
		if(s.compareTo(t.data)==0){
			splay(t);
			return true;
		}
		else if(s.compareTo(t.data)>0){
			return contains(s, t.rightChild);
		}
		else{
			return contains(s, t.leftChild);
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

class MyRandom {

	  private static Random rn = new Random();

	  protected MyRandom(){ }

	  public static int rand(int lo, int hi) {
	     int n = hi - lo + 1;
	     int i = rn.nextInt() % n;
	     if (i < 0) i = -i;
	     return lo + i;
	  }

	  public static String nextString(int lo, int hi) {
	     int n = rand(lo, hi);
	     byte b[] = new byte[n];
	     for (int i = 0; i < n; i++)
	     b[i] = (byte)rand('a', 'z');
	     return new String(b, 0);
	  }

	  public static String nextString() {
	     return nextString(5, 25);
	  }
	  
	}
