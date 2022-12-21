import java.util.ArrayList;
import java.util.List;

public class Board {
    public int size;
    public List<List<Character>> cells;
    public Board(int size) {
        this.size = size; this.cells = new ArrayList<>();
    }

    public void WriteBoard() {
        cells.forEach((l) -> l.forEach((c) -> System.out.println(c)));
    }

}
