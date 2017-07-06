package a6;

import java.util.Scanner;

public class AssnTopoSort {

	  public static void main (String[] args) {
		  DiGraph graph = new DiGraph();
			Scanner s = new Scanner(System.in);
		    System.out.println("Interactive Mode: ");
		    String command = "";
		    do{
		  	  command=s.next();
		  	  switch(command){
		  	  case "new":
		  		  graph = new DiGraph();
		  		  break;
		  	  case "in":
		  		  long idNum = s.nextLong();
		  		  String label = s.next();
		  		  graph.addNode(idNum, label);
		  		  break;
		  	case "ie":
		  		  long id = s.nextLong();
		  		  String source = s.next();
		  		  String dest = s.next();
		  		  long weight = s.nextLong();
		  		  String elab = s.next();
		  		  graph.addEdge(id, source, dest, weight, elab);
		  		  break;
		  	  case "d":
		  		  String slabel = s.next();
		  		  graph.delNode(slabel);
		  		  break;
		  	  case "n":
		  		  System.out.println("The number of nodes is " +graph.numNodes());
		  		  break;
		  	  case "e":
		  		  System.out.println("The number of edges is " +graph.numEdges());
		  		  break;
		  	  case "p":
		  		  graph.print();
		  		  break;
		  	  case "t":
		  		  String[] set = graph.topoSort();
		  		  for(int xe=0; xe<set.length; xe++){
		  			  System.out.print(set[xe]+" ");
		  		  }
		  		  break;	
		  	  case "q":
		  		  break;
		  	  }
		    }while(!command.equals("q"));
	  }
	}
