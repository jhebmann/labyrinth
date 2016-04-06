package graph;

import java.util.Arrays;

/**
 * 
 * @author Hebmann Julien, Rogeon Louis
 * a class that implements Graph as an adjacency matrix
 */

public class AdjacencyMatrix implements Graph{

	/**
	 * the adjacency matrix
	 */
	private int[][] matrix = {};

	/**
	 * boolean to know if the graph is a directed one
	 */
	private boolean isDirected = false;
	/**
	 * vertex array containing all vertices in the graph
	 */
	private Vertex[] vertices = {};

	/**
	 * constructor of the class
	 */
	public AdjacencyMatrix(){

	}

	/**
	 * constructor of the class with the option to create a directed graph
	 */
	public AdjacencyMatrix(boolean isDirected){
		this.isDirected = isDirected;
	}
	
	/**
	 * getter of vertices
	 * @return the vertices of the graph
	 */
	public Vertex[] getVertices() {
		return vertices;
	}

	/**
	 * setter of vertices
	 * @param vertices Vertex Array that will be added to the graph
	 */
	public void setVertices(Vertex[] vertices) {
		this.vertices = vertices;
	}

	/**
	 * method to add a Vertex to the graph
	 * @param v Vertex that will be added to the graph
	 */
	public void addVertex(Vertex v){
		if (place(v) == -1){
			vertices = addElement(vertices, v);
			setMatrix(addElement(getMatrix()));
		}
		else
			System.out.println("A Vertex with this Object already exists !");
	}

	/**
	 * method to add an Edge to the graph, will add the edge's vertices if they're not present in the graph
	 * @param e Edge that will be added to the graph
	 */
	public void addEdge(Edge e){
		if (place(e.extremitie1)==-1){
			System.out.println("The first Vertex didn't exist and was added to the graph");
			this.addVertex(e.extremitie1);
		}
		
		if (place(e.extremitie2)==-1){
			System.out.println("The second Vertex didn't exist and was added to the graph");
			this.addVertex(e.extremitie2);
		}
		
		getMatrix()[place(e.extremitie1)][place(e.extremitie2)] = 1;
		if (!isDirected)
			getMatrix()[place(e.extremitie2)][place(e.extremitie1)] = 1;
	}
	
	/**
	 * method to add an Edge to the graph if the vertices are already part of the graph
	 * @param v1 the first Vertex
	 * @param v2 the second Vertex
	 */
	public void addEdge(Vertex v1, Vertex v2){
		if (place(v1)==-1){
			System.out.println("The first Vertex didn't exist and was added to the graph");
			this.addVertex(v1);
		}
		
		if (place(v2)==-1){
			System.out.println("The second Vertex didn't exist and was added to the graph");
			this.addVertex(v2);
		}
		
		getMatrix()[place(v1)][place(v2)] = 1;
		if (!isDirected)
			getMatrix()[place(v2)][place(v1)] = 1;
	}

	/**
	 * method to know the position of a vertex in the vertex array, depending of his id
	 * @param v Vertex that will be searched
	 * @return the position of the Vertex if he's in the graph, or -1 if it isn't
	 */
	private int place(Vertex v) {
		for (int i=0 ; i<getVertices().length ; i++){
			if (v.getId().equals(getVertices()[i].getId()))
				return i;
		}
		return -1;
	}

	/**
	 * getter of the matrix
	 * @return int[][] the matrix of the graph
	 */
	public int[][] getMatrix() {
		return matrix;
	}

	/**
	 * setter of the matrix
	 * @param matrix int[][], the matrix that will be set
	 */
	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	/**
	 * method that counts the number of edges in the graph
	 * @return int the number of edges in the graph
	 */
	public int nbEdge() {
		int n=0;
		for (int i=0;i<getMatrix().length;i++)
			for(int j=0 ; j<getMatrix()[i].length;j++)
				n+=getMatrix()[i][j];
		if (isDirected)
			return n;
		else
			return n/2;
	}

	/**
	 * method that counts the number of vertices in the graph
	 * @return int the number of vertices in the graph
	 */
	public int nbVertex() {
		return (vertices.equals(null)) ? 0 : vertices.length;
	}

	/**
	 * method that check if an edge is in the graph
	 * @return boolean : true if the Edge exists in the graph, else false
	 */
	public boolean searchEdge(Vertex v1, Vertex v2) {
		if (place(v1)!=-1 && place(v2)!=-1){
			return ((getMatrix()[place(v1)][place(v2)]) == 1);
		}
		else
			System.out.println("At least 1 of the Vertices of this Edge are undefined");
		return false;
	}

	/**
	 * method to add an element to a Vertex Array (since we couldn't use Lists :C
	 * @param a the Vertex Array in which we'll add the Vertex
	 * @param e The Vertex that'll be added to the Vertex Array
	 * @return Vertex[] the old Vertex Array with the value that had to be added
	 */
	static Vertex[] addElement(Vertex[] a, Vertex e) {
		a  = (Vertex[]) Arrays.copyOf(a, a.length + 1);
		a[a.length - 1] = e;
		return a;
	}

	/**
	 * method to add an element to the matrix
	 * @param a the matrix in which we want to add an element
	 * @return int[][] the new matrix
	 */
	static int[][] addElement(int[][] a) {
		a  = Arrays.copyOf(a, a.length + 1);
		int[] temp = new int[a.length];
		a[a.length-1] = temp;
		for (int i=0 ; i<a.length ; i++){
			a[i] = Arrays.copyOf(a[i], a[i].length + 1);
			
		}
		for (int i=0 ; i< a.length ; i++){
			a[a.length - 1][i] = 0;
			a[i][a.length - 1] = 0;
		}
		return a;
	}

	/**
	 * method to print the matrix of the graph in ASCII
	 */
	public void printMatrix(){
		System.out.print("   ");
		for (int i = 0; i < getMatrix().length ; i++){
			System.out.print(" "+getVertices()[i].getId());
		}
		System.out.println();;
		for (int i = 0; i < getMatrix().length ; i++){
			System.out.print(getVertices()[i].getId());
			for (int j = 0; j < getMatrix().length ; j++){
				System.out.print("| "+getMatrix()[i][j]+" ");
			}
			System.out.println("|");
		}
	}
	
	/**
	 * a method that returns the neighbours of a given vertex
	 * @param v the Vertex we are studying
	 * @return Vertex[] the vertices that are neighbours of the given Vertex
	 */
	public Vertex[] neighbours(Vertex v){
		Vertex[] results1 = {};
		if (place(v) == -1)
			return results1;
		int nombre = 0;
		for (int i = 0; i < getMatrix()[place(v)].length ; i++){
			if (getMatrix()[place(v)][i] == 1)
				nombre++;
		}
		Vertex[] results2 = new Vertex[nombre];
		int j=0;
		for (int i = 0; i < getMatrix()[place(v)].length ; i++){
			if (getMatrix()[place(v)][i] == 1){
				results2[j] = vertices[i];
				j++;
			}
		}
		return results2;
	}
}