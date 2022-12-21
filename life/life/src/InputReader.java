import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputReader {
    private Scanner s;
    private int boardSize;
    private int steps;
    private void ReadSetup() {
        this.s = new Scanner(System.in);
        String[] firstLine = s.nextLine().split(" ");
        boardSize = Integer.parseInt(firstLine[0]);
        steps = Integer.parseInt(firstLine[1]);
    }

    private void ReadBoard(Board b) {
        for (int i = 0; i < boardSize; i++) {
            b.cells.add(s.nextLine().chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        }
    }

    public Board ReadInput() {
        ReadSetup();
        Board b = new Board(boardSize);
        ReadBoard(b);
        return b;
    }
}
