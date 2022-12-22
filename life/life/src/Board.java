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
}
