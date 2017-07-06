package a6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

public class DiGraph implements DiGraphInterface {

	  private long vertices;
	  private long edges;
	  private HashMap<String, Node> nodeMap;
	  private HashMap<Pair, Edge> edgeMap;
	  private HashMap<Long, Node> idNumNode;
	  private HashMap<Long, Edge> idNumEdge;
	  private HashMap<String, String> labelToLabel;
	  
	  public DiGraph( ) { 
	  vertices = 0;
	  edges=0;
	  nodeMap=new HashMap<>();
	  edgeMap=new HashMap<>();
	  idNumNode=new HashMap<>();
	  idNumEdge=new HashMap<>();
	  labelToLabel=new HashMap<>();
	  }

	  public static class Node{
		  public Node(long idNum, String label, Edge edge, int inDeg){
			 this.idNum=idNum;
			 this.label=label;
			 this.edge=edge;
			 this.inDeg=inDeg;
			 }
			
		  	public long idNum;
		    public int inDeg;
		  	public Edge edge;
			public String label;
		}
	  
	  public static class Edge{
		  public Edge(long idNum, Node pNode, Node dNode, long weight, String eLabel){
			 this.idNum=idNum;
			 this.dNode=dNode;
			 this.pNode=pNode;
			 this.weight=weight;
			 this.elabel=eLabel;
			 }
			
			public long idNum;
			public Node dNode;
			public Node pNode;
			public long weight;
			public String elabel;
		}
	  
	  public static class Pair{
		  public Pair(Node p, Node d){
			 this.dNode=d;
			 this.pNode=p;
			 }
			
			public Node dNode;
			public Node pNode;
		}
	  
	@Override
	public boolean addNode(long idNum, String label) {
		if(contains(idNum, label)||idNum<0||label==null){
			return false;
		}
		nodeMap.put(label, new Node(idNum, label, null, 0));
		idNumNode.put(idNum, new Node(idNum, label, null, 0));
		vertices++;
		return true;
	}
	
	public boolean contains(long idNum, String label){
		if(idNumNode.containsKey(idNum)||nodeMap.containsKey(label)){
			return true;
		}
		return false;
	}

	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		if(idNum<0||nodeMap.get(sLabel)==null||nodeMap.get(dLabel)==null){
			return false;
		}
		if(check(idNum, sLabel, dLabel)){
				return false;
		}
		Edge e =new Edge(idNum, nodeMap.get(sLabel), nodeMap.get(dLabel), weight, eLabel);
		labelToLabel.put(sLabel, dLabel);
		idNumEdge.put(idNum, e);
		nodeMap.get(dLabel).inDeg++;
		edgeMap.put(new Pair(nodeMap.get(sLabel), nodeMap.get(dLabel)),e );
		edges++;
		return true;
	}
	
	
	private Boolean check(long idNum, String sLabel, String dLabel){
		if(idNumEdge.containsKey(idNum)){
			return true;
		}
		if(labelToLabel.containsKey(sLabel)){
			if(labelToLabel.get(sLabel).equals(dLabel)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean delNode(String label) {
		if(nodeMap.get(label)==null){
			return false;
		}
		
		Set s = edgeMap.entrySet();
		Iterator i = s.iterator();
		while(i.hasNext()){
		Map.Entry<Pair, Edge> m = (Entry<Pair, Edge>) i.next();
		if(m.getKey().dNode.label.equals(label)||m.getKey().pNode.label.equals(label)){
			delEdge(m.getValue().pNode.label, m.getValue().pNode.label);
		}
		}
		nodeMap.remove(label);
		vertices--;
		return true;
	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		Pair p = new Pair(nodeMap.get(sLabel), nodeMap.get(dLabel));
		Set s = edgeMap.entrySet();
		Iterator i = s.iterator();
		while(i.hasNext()){
		Map.Entry<Pair, Edge> m = (Entry<Pair, Edge>) i.next();
		if(m.getKey().pNode==p.pNode&&m.getKey().dNode==p.dNode){
			edgeMap.remove(m.getKey());
			nodeMap.get(dLabel).inDeg--;
			edges--;
			return true;
		}
		}
			return false;
	}

	@Override
	public void print() {
		Set s = nodeMap.entrySet();
		Iterator i = s.iterator();
		while(i.hasNext()){
		Map.Entry<String, Node> m = (Entry<String, Node>) i.next();
		System.out.println("("+m.getValue().idNum+") "+ m.getKey());
		printE(m.getValue());
		System.out.println();
		}
	}
	
	private void printE(Node n){
		if(n==null){
			return;
		}
		Set s = edgeMap.entrySet();
		Iterator i = s.iterator();
		while(i.hasNext()){
			Map.Entry<Pair, Edge> m = (Entry<Pair, Edge>) i.next();
			if(m.getKey().pNode==n){
				System.out.println("("+m.getValue().idNum+")--"+m.getValue().elabel+","+m.getValue().weight+"--> "+m.getValue().dNode.label);
			}
		}
	}

	@Override
	public String[] topoSort() {
		Queue<Node> q = new LinkedList<>();
		if(vertices==0){
			return new String[0];
		}
		String[] result = new String[(int)vertices];
		int i =0;
		Set s = nodeMap.entrySet();
		Iterator it = s.iterator();
		while(it.hasNext()){
		Map.Entry<String, Node> m = (Entry<String, Node>) it.next();
		if(m.getValue().inDeg==0){
			q.add(m.getValue());
		}
		}
		if(q.isEmpty()){
			return null;
		}
		while(!q.isEmpty()){
			Node a =q.remove();
			result[i]=a.label;
			i++;
			Set se = edgeMap.entrySet();
			Iterator ite = se.iterator();
			while(ite.hasNext()){
			Map.Entry<Pair, Edge> m = (Entry<Pair, Edge>) ite.next();
			if(m.getKey().pNode.label.equals(a.label)){
				m.getValue().dNode.inDeg--;
				if(m.getValue().dNode.inDeg==0){
					q.add(m.getValue().dNode);
				}
			}

			}
		}
		return result;
	}	 
	
	@Override
	public long numNodes() {
		return vertices;
	}

	@Override
	public long numEdges() {
		return edges;
	}
 
}