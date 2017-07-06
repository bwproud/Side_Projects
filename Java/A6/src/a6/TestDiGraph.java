package a6;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;

public class TestDiGraph {
	
	@Test
	public void testEmptyGraph() {
		DiGraphInterface empty = new DiGraph();
		assertEquals(0, empty.numEdges());
		assertEquals(0, empty.numNodes());
		assertArrayEquals(new String[] {}, empty.topoSort());
	}
	

	@Test
	public void testSingletonGraph() {
		DiGraphInterface singleton = new DiGraph();
		assertTrue(singleton.addNode(1, "1"));
		
		assertEquals(0, singleton.numEdges());
		assertEquals(1, singleton.numNodes());
		assertArrayEquals(new String[] {"1"}, singleton.topoSort());
	}

	@Test
	public void testInvalidNodeAdd()
	{
		DiGraphInterface singleton = new DiGraph();
		assertTrue(singleton.addNode(1, "1"));
		
		assertFalse(singleton.addNode(1, "different"));	// Clashing ID
		assertFalse(singleton.addNode(2, "1"));	// Clashing label
		assertFalse(singleton.addNode(-1, "different")); // Invalid ID
		assertFalse(singleton.addNode(2, null)); // Invalid label
		
		assertEquals(0, singleton.numEdges());
		assertEquals(1, singleton.numNodes());
		assertArrayEquals(new String[] {"1"}, singleton.topoSort());
	}
	

	@Test
	public void testInvalidNodeDel()
	{
		DiGraphInterface singleton = new DiGraph();
		assertTrue(singleton.addNode(1, "1"));
		
		assertFalse(singleton.delNode("2")); // Nonexistent node
		
		assertEquals(0, singleton.numEdges());
		assertEquals(1, singleton.numNodes());
		assertArrayEquals(new String[] {"1"}, singleton.topoSort());
	}
	
	
	@Test
	public void testRemovedNodeGraph() {
		DiGraphInterface singleton = new DiGraph();
		assertTrue(singleton.addNode(1, "1"));
		assertTrue(singleton.delNode("1"));
		
		assertEquals(0, singleton.numEdges());
		assertEquals(0, singleton.numNodes());
		assertArrayEquals(new String[] {}, singleton.topoSort());
	}

	@Test
	public void testSingletonEdgeGraph() {
		DiGraphInterface edged = new DiGraph();
		
		assertTrue(edged.addNode(1, "1"));
		assertTrue(edged.addNode(2, "2"));
		assertTrue(edged.addEdge(1, "1", "2", 1, null));
		
		assertEquals(1, edged.numEdges());
		assertEquals(2, edged.numNodes());
		assertArrayEquals(new String[] {"1", "2"}, edged.topoSort());
	}

	@Test
	public void testInvalidEdgesAdd()
	{
		DiGraphInterface edged = new DiGraph();
		assertTrue(edged.addNode(1, "1"));
		assertTrue(edged.addNode(2, "2"));
		assertTrue(edged.addEdge(1, "1", "2", 1, null));
	
		// Invalid IDs
		assertFalse(edged.addEdge(1, "2", "1", 1, null));
		assertFalse(edged.addEdge(-1, "2", "1", 1, null));
		
		// Nonexistent 1st node and 2nd node
		assertFalse(edged.addEdge(2, "nonexistent", "1", 1, null));
		assertFalse(edged.addEdge(2, "2", "nonexistent", 1, null));
		
		// Duplicate edges
		assertFalse(edged.addEdge(2, "1", "2", 1, null));
		
		assertEquals(1, edged.numEdges());
		assertEquals(2, edged.numNodes());
		assertArrayEquals(new String[] {"1", "2"}, edged.topoSort());
	}
	@Test
	public void testInvalidEdgeDel()
	{
		DiGraphInterface edged = new DiGraph();
		assertTrue(edged.addNode(1, "1"));
		assertTrue(edged.addNode(2, "2"));
		assertTrue(edged.addEdge(1, "1", "2", 1, null));
	
		// Nonexistent 1st and 2nd edges
		assertFalse(edged.delEdge("nonexistent", "2"));
		assertFalse(edged.delEdge("1", "nonexistent"));
		
		assertEquals(1, edged.numEdges());
		assertEquals(2, edged.numNodes());
		assertArrayEquals(new String[] {"1", "2"}, edged.topoSort());
	}

	

	@Test
	public void testCyclicGraph() {
		DiGraphInterface edged = new DiGraph();
		assertTrue(edged.addNode(1, "1"));
		assertTrue(edged.addNode(2, "2"));
		assertTrue(edged.addEdge(1, "1", "2", 1, null));
		assertTrue(edged.addEdge(2, "2", "1", 1, null));
		
		assertEquals(2, edged.numEdges());
		assertEquals(2, edged.numNodes());
		assertArrayEquals(null, edged.topoSort());
	}

	@Test
	public void testRemovedEdgeGraph() {
		DiGraphInterface edged = new DiGraph();
		
		assertTrue(edged.addNode(1, "1"));
		assertTrue(edged.addNode(2, "2"));
		assertTrue(edged.addEdge(1, "1", "2", 1, null));
		
		assertTrue(edged.addEdge(2, "2", "1", 1, null));
		assertTrue(edged.delEdge("2", "1"));
		
		assertEquals(1, edged.numEdges());
		assertEquals(2, edged.numNodes());
		assertArrayEquals(new String[] {"1", "2"}, edged.topoSort());
	}

	@Test
	public void testTopologicalSort() {
		DiGraphInterface sortable = new DiGraph();
		sortable.addNode(1, "1");
		sortable.addNode(2, "2");
		sortable.addNode(3, "3");
		sortable.addNode(4, "4");
		
		sortable.addEdge(1, "3", "2", 1, null);
		sortable.addEdge(2, "3", "1", 1, null);
		sortable.addEdge(3, "2", "4", 1, null);
		
		String[] sorted = sortable.topoSort();
		String[] possible1 = new String[] { "3", "2", "4", "1"};
		String[] possible2 = new String[] { "3", "2", "1", "4"};
		String[] possible3 = new String[] { "3", "1", "2", "4"};
		
		if (!Arrays.equals(sorted, possible1) &&
			    !Arrays.equals(sorted, possible2) &&
			    !Arrays.equals(sorted, possible3)) {
			String sorted_str = String.join(" ", sorted);
			String possible1_str = String.join(" ", possible1);
			String possible2_str = String.join(" ", possible2);
			String possible3_str = String.join(" ", possible3);
			
			fail("Topological sort result was '" + sorted_str + "', expected " +
					"'" + possible1_str + "' or '" + possible2_str + "' or '" + 
					possible3_str + "'");
		}
	}
}
