import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
	
	public List<Square> squares = new ArrayList<Square>();
	public Square[][] grid = new Square[4][4];
	
	public Board() {
		this.addSquare();
	}
	
	public boolean gameOver() {
		if (squares.size() == 16) {
			return true;
		}
		return false;
	}
	
	public void addSquare() {
		squares.add(generateSquare());
		updateGrid();
	}
	
	public void moveSquares (Direction d) {
		for (Square s : squares) {
			s.move(d);
		}
	}
	
	public void setNeighbors(Square s) {
		for(Square q : this.squares) {
			if (isAdjacent(q, s)) {
				if(q.pos[0] == s.pos[0] + 1) {
					s.right = q;
				}
				else if (q.pos[0] == s.pos[0] - 1) {
					s.left = q;
				}
				else if (q.pos[1] == s.pos[1] + 1) {
					s.down = q;
				}
				else {
					s.up = q;
				}				
			}
		}
	}
	
	public boolean isAdjacent(Square s1, Square s2) {
		if ((s1.pos[0] == s2.pos[0] + 1 || s1.pos[0] == s2.pos[0] - 1) && s1.pos[1] == s2.pos[1]) {
			return true;
		}
		else if ((s1.pos[1] == s2.pos[1] + 1 || s1.pos[1] == s2.pos[1] - 1) && s1.pos[0] == s2.pos[0]) {
			return true;
		}
		return false;
	}
	
	public Square generateSquare() {
		Random rand = new Random();
		int val = rand.nextInt(2);
		int[] pos = getEmptySquare();
		if (val == 0) {
			return new Square(2, pos);
		}
		return new Square(4, pos);
	}
	
	private int[] getEmptySquare() {
		List<Integer[]> emptySquares = new ArrayList<Integer[]>();
		Random rand = new Random();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == null) {
					emptySquares.add(new Integer[] {i, j});
				}
			}
		}
	
		Integer[] square = emptySquares.get(rand.nextInt(emptySquares.size()));
		return new int[] {square[0], square[1]};
	}
	
	private void updateGrid() {
		clearGrid();
		for (Square s : this.squares) {
			this.grid[s.pos[0]][s.pos[1]] = s;
		}
	}
	
	private void clearGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				this.grid[i][j] = null;
			}
		}
	}
}
