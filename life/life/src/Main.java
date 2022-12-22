import java.util.List;

public class Main {
    public static void main(String[] args) {
        InputReader reader = new InputReader();
        Board b = reader.ReadInput();
//        var neighbors = b.cells.get(2).get(b.size-1).getNeighbors(b);

//        for (Character c:
//             neighbors) {
//            System.out.println(c);
//        }
//


        for (int i = 0; i < reader.steps; i++) {
            for (List<Cell> l: b.cells) {
                for (Cell c: l) {
                    Thread t = new Thread(c);
                    t.start();
                }
            }
        }
        b.WriteBoard();
    }
}