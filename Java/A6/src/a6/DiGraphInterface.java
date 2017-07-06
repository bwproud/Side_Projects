package a6;

public interface DiGraphInterface {
  boolean addNode(long idNum, String label);
  boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel);
  boolean delNode(String label);
  boolean delEdge(String sLabel, String dLabel);
  long numNodes();
  long numEdges();
  void print();
  String[] topoSort();
}