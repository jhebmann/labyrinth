package graph;

public class Game {

	public static void main(String[] args) {
		Maze ma = new Maze(3, 3);
		ma.setChemin(0, 1);
		ma.setChemin(0, 2);
		ma.setChemin(1, 0);
		ma.setChemin(1, 2);
		ma.setChemin(2, 0);
		ma.setChemin(2, 1);
		System.out.println(ma);
		System.out.println(ma.nbEdge());
	}
}
