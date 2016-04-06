package graph;

public class Cell {
	private int coordX;
	private int coordY;
	private char type;
	
	public Cell(int x, int y, char t){
		setCoordX(x);
		setCoordY(y);
		t = Character.toUpperCase(t);
		setType(t);
		if (t != 'D' && t != 'A' && t != 'E' && t != 'W'){
			setType('E');
		}
	}
	
	public int getCoordX() {
		return coordX;
	}
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
	public int getCoordY() {
		return coordY;
	}
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	
	public boolean equals(Cell c){
		return (this.getCoordX() == c.getCoordX() && this.getCoordY() == c.getCoordY() && this.getType() == c.getType());
	}
	
	public String toString(){
		return this.getCoordX()+","+this.getCoordY();
	}
}
