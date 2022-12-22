import java.util.List;

public class Main {
    public static void main(String[] args) {
        InputReader reader = new InputReader();
        Board b = reader.ReadInput();

        for (int i = 0; i < reader.steps; i++) {
            for (List<Cell> l: b.cells) {
                for (Cell c: l) {
                    b.getLiveNeighbors(c);
                }
            }
            b.updateVals();
        }
        b.WriteBoard();
    }
}