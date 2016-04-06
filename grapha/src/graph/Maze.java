package graph;

public class Maze extends AdjacencyMatrix{
	private int height;
	private int width;
	
	public Maze(int h, int w){
		super();
		setHeight(h);
		setWidth(w);

		for (int i=0 ; i<h ; i++){
			for (int j = 0 ; j<w ; j++){
				addVertex(new Vertex(new Cell(i,j,'W')));
				if (i == 0 && j == 0){
					getVertices()[0].setId(new Cell(i,j,'D'));
				}
				else if (i == h-1 && j == w-1){
					getVertices()[h*w-1].setId(new Cell(i,j,'A'));
				}
			}
		}
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	public String toString(){
		String result = "";

		for (int i=0 ; i<getHeight() ; i++){
			for (int j = 0 ; j<getWidth() ; j++){
				if (((Cell)getVertices()[i*getWidth()+j].getId()).getType() == 'W')
					result+= 'O';
				else if (((Cell)getVertices()[i*getWidth()+j].getId()).getType() == 'E')
					result+= '.';
				else
					result += ((Cell)getVertices()[i*getWidth()+j].getId()).getType();
			}
			result+="\n";
		}
		
		return result;
	}
	
	public void setChemin(int x, int y){
		((Cell)getVertices()[x*getWidth()+y].getId()).setType('E');
		Cell ourCell = (Cell)getVertices()[x*getWidth()+y].getId();
		Cell[] neighb = neighbours(ourCell);
		
		for (Vertex v : getVertices()){
			for (Cell temp : neighb){
				if (((Cell)v.getId()).equals(temp) && temp.getType() != 'W'){
					addEdge(v, getVertices()[x*getWidth()+y]);
				}
			}
		}
	}
	
	public Cell[] neighbours(Cell c){
		int nombre = 4;
		if ((c.getCoordX() == 0 && c.getCoordY() == 0)||(c.getCoordX() == getHeight()-1 && c.getCoordY() == getWidth()-1)||(c.getCoordX() == 0 && c.getCoordY() == getWidth()-1)||(c.getCoordX() == getHeight()-1 && c.getCoordY() == 0)){
			nombre = 2;
		}
		else if (c.getCoordX() == 0 || c.getCoordY() == 0 || c.getCoordX() == getHeight()-1 || c.getCoordY() == getWidth()-1){
			nombre = 3;
		}
		Cell[] results = new Cell[nombre];
		int actual = 0;
		//pas en haut
		if (c.getCoordX() != 0){
			results[actual] = ((Cell)getVertices()[(c.getCoordX()-1)*getWidth()+c.getCoordY()].getId());
			actual++;
		}
		//pas a droite
		if (c.getCoordY() != getWidth()-1){
			results[actual] = ((Cell)getVertices()[c.getCoordX()*getWidth()+c.getCoordY()+1].getId());
			actual++;
		}
		//pas en bas
		if (c.getCoordX() != getHeight()-1){
			results[actual] = ((Cell)getVertices()[(c.getCoordX()+1)*getWidth()+c.getCoordY()].getId());
			actual++;
		}
		//pas a gauche
		if (c.getCoordY() != 0){
			results[actual] = ((Cell)getVertices()[c.getCoordX()*getWidth()+c.getCoordY()-1].getId());
			actual++;
		}
		return results;
	}
}
