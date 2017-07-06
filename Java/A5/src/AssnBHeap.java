import java.util.Random;
import java.util.Scanner;

public class AssnBHeap {

  public static void main (String[] args) {
    MinBinHeap heap = new MinBinHeap();
	Scanner s = new Scanner(System.in);
    System.out.println("Interactive Mode: ");
    String command = "";
    do{
  	  command=s.next();
  	  switch(command){
  	  case "new":
  		  heap = new MinBinHeap();
  		  break;
  	  case "i":
  		  String input = s.next();
  		  int priority = s.nextInt();
  		  heap.insert(new EntryPair(input, priority));
  		  break;
  	  case "d":
  		  heap.delMin();
  		  break;
  	  case "m":
  		  System.out.println("The mininum value is " +heap.getMin().priority);
  		  break;
  	  case "s":
  		  System.out.println("The size is " +heap.size());
  		  break;
  	  case "p":
  		  heap.print();
  		  break;
  	  case "o":
  		  heap.sort();
  		  break;
  	  case "b":
  		  	MyRandom ran = new MyRandom();
  		  	EntryPair[] ar = new EntryPair[20];
  		  	for(int x =0; x<20; x++){
				ar[x]=ran.nextEP();
			}
  		  	heap.build(ar);
  		  	break;
  	  case "f":
  		  MyRandom random = new MyRandom();
  			for(int x =0; x<20; x++){
  				heap.insert(random.nextEP());
  			}
  		  break;	
  	  case "q":
  		  break;
  	  }
    }while(!command.equals("q"));
  }

}