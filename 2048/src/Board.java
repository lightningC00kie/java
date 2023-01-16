import java.util.ArrayList;
import java.util.List;

public class Board {
	
	public List<Square> squares = new ArrayList<Square>();
	
	public boolean gameOver() {
		if (squares.size() == 16) {
			return true;
		}
		return false;
	}
	
	public void addSquare(Square s) {
		squares.add(s);
	}
	
	public void moveSquares (Direction d) {
		for (Square s : squares) {
			s.move(d);
		}
	}
}
