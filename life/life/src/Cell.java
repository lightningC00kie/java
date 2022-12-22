import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cell implements Runnable {
    char val;
    int row;
    int col;
    Board b;
    public Cell(char val, int row, int col, Board b) {
        this.val = val; this.row = row; this.col = col; this.b = b;
    }

    public List<Character> getNeighbors() {
        int[][] dirs = {{1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}};
        List<Character> neighbors = new ArrayList<>();
        for (int[] d: dirs) {
            int newRow = d[0] + this.row;
            int newCol = d[1] + this.col;

            if (newRow < 0){
                newRow = b.size - 1;
            }
            if (newCol < 0) {
                newCol = b.size - 1;
            }
//            System.out.println("row: " + newRow + ", col: " + newCol);
            neighbors.add(b.cells.get((newRow) % b.size).get((newCol) % b.size).val);
        }
        return neighbors;
    }

    public char computeVal() {
        List<Character> neighbors = this.getNeighbors();
        int liveNeighbors = Collections.frequency(neighbors, 'X');
        if (this.val == 'X') {
            if (liveNeighbors < 2) {
                return '_';
            }
            else if (liveNeighbors == 2 || liveNeighbors == 3) {
                return 'X';
            }
            else {
                return '_';
            }
        }
        else if(this.val == '_' && liveNeighbors == 3) {
            return 'X';
        }
        else {
            return '_';
        }
    }

    @Override
    public String toString() {
        return Character.toString(this.val);
    }

    @Override
    public void run() {
        this.getNeighbors();
        this.val = this.computeVal();
    }
}
