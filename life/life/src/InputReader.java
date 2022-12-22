import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputReader {
    private Scanner s;
    private int boardSize;
    public int steps;
    private void ReadSetup() {
        this.s = new Scanner(System.in);
        String[] firstLine = s.nextLine().split(" ");
        boardSize = Integer.parseInt(firstLine[0]);
        steps = Integer.parseInt(firstLine[1]);
    }

    private void ReadBoard(Board b) {
        for (int row = 0; row < boardSize; row++) {
            char[] chars = s.nextLine().toCharArray();
            List<Cell> curRow = new ArrayList<>();
            for (int col = 0; col < boardSize; col++) {
                Cell c = new Cell(chars[col], row, col, b);
                curRow.add(c);
            }
            b.cells.add(curRow);
        }
    }

    public Board ReadInput() {
        ReadSetup();
        Board b = new Board(boardSize);
        ReadBoard(b);
        return b;
    }
}
