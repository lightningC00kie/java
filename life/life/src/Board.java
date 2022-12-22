import java.util.ArrayList;
import java.util.List;

public class Board {
    public int size;
    public List<List<Cell>> cells;
    public Board(int size) {
        this.size = size; this.cells = new ArrayList<>();
    }

    public void WriteBoard() {
        cells.forEach(this::WriteList);
    }

    private <T> void WriteList(List<T> l) {
        l.forEach(System.out::print);
        System.out.println();
    }

    public void updateVals() {
        for (List<Cell> l: this.cells) {
            for (Cell c: l) {
//                System.out.println(c.neighbors.size());
//                if (c.neighbors != null) {
                    c.val = c.computeVal();
//                }
            }
        }
    }

    public void getLiveNeighbors(Cell c) {
        int[][] dirs = {{1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}};
        int liveNeighbors = 0;
        for (int[] d: dirs) {
            int newRow = d[0] + c.row;
            int newCol = d[1] + c.col;

            if (newRow < 0){
                newRow = this.size - 1;
            }
            if (newCol < 0) {
                newCol = this.size - 1;
            }
            if(this.cells.get((newRow) % this.size).get((newCol) % this.size).val == 'X') {
                liveNeighbors++;
            }
        }
        c.liveNeighbors = liveNeighbors;
    }
}
