package graph;

/**
 * 
 * @author Hebmann Julien, Rogeon Louis
 * a class to test graph implementation
 */

public class TestGraphImpl {

	public static void main(String[] args) {
		AdjacencyMatrix m = new AdjacencyMatrix();
		Vertex v1 = new Vertex("ve1",m);
		Vertex v2 = new Vertex("ve2",m);
		Vertex v3 = new Vertex("ve3",m);
		Vertex v4 = new Vertex("ve4",m);
		Vertex v5 = new Vertex("ve5",m);
		Vertex v6 = new Vertex("ve6",m);
		Edge e1 = new UndirectedEdge(v1, v2);
		Edge e2 = new UndirectedEdge(v2, v3);
		
		m.addEdge(e1);
		m.addEdge(e2);
		m.addVertex(v4);
		m.addEdge(v3,v5);
		m.addVertex(v6);
		System.out.println();
		
		System.out.print("The vertices of the graph are : ");
		for (int i = 0; i< m.getVertices().length ; i++){
			System.out.print(m.getVertices()[i].getId()+" ");
		}
		System.out.println("\n");
		
		System.out.print("Is there an edge between v1 and v2 ? : ");
		System.out.println(m.searchEdge(v1, v2));
		System.out.print("Is there an edge between v1 and v5 ? : ");
		System.out.println(m.searchEdge(v1, v5));
		System.out.println();
		
		System.out.print("Neighbours of v2 : ");
		for (int i = 0; i< m.neighbours(v2).length ; i++){
			System.out.print(m.neighbours(v2)[i].getId()+" ");
		}
		System.out.println("\n");
		
		m.printMatrix();
	}
}
